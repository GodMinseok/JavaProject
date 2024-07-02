package daelim.book.rental.admin.member;


import daelim.book.rental.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    private AdminMemberService adminMemberService;

    @RequestMapping("/createAccountForm")
    public String createAccountForm() {
        System.out.println("[AdminHomeController] createAccountForm()");
        return "/admin/member/create_account_form";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
        System.out.println("[AdminHomeController] loginForm()");
        return "/admin/member/login_form";
    }

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(AdminMemberVo adminMemberVo) {

        System.out.println("[AdminMemberController] createAccountConfirm()");
        String nextPage = "/admin/member/create_account_ok";
        int result = adminMemberService.createAccount(adminMemberVo);
        if (result <= 0) {
            nextPage = "/admin/member/create_account_ng";
        }
        return nextPage;
    }

    // Cookie로 하는 법
//    @PostMapping("/loginConfirm")
//    public String loginConfirm(AdminMemberVo adminMemberVo, @CookieValue(value ="loginMember", required = false) String loginMember, HttpServletResponse response) {
//        System.out.println("[AdminMemberController] loginConfirm()");
//        String nextPage = "/admin/member/login_ok";
//        AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
//        if(loginedAdminMemberVo == null) {
//            nextPage = "/admin/member/login_ng";
//        } else {
//            // cookie 설정
//            Cookie cookie = new Cookie("loginMember", loginedAdminMemberVo.getId());
//            cookie.setMaxAge(60 * 30);
//            response.addCookie(cookie);
//        }
//        return nextPage;
//    }
//
//    @GetMapping("/logoutConfirm")
//    public String logoutForm(@CookieValue(value="loginMember", required=false) String loginMember, HttpServletResponse response) {
//        System.out.println("[AdminMemberController.logoutForm]");
//        String nextPage = "redirect:/admin/";
//        Cookie cookie = new Cookie("loginMember", loginMember);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        return nextPage;
//    }

    // session으로 하는 법
    @PostMapping("/loginConfirm")
    public String loginConfirm(AdminMemberVo adminMemberVo, HttpSession session) {
        System.out.println("[AdminMemberController.loginConfirm]");
        String nextPage = "/admin/member/login_ok";
        AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);

        if(loginedAdminMemberVo == null) {
            nextPage = "/admin/member/login_ng";
        } else {
            // 세션 생성
            session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
            session.setMaxInactiveInterval(60 * 30);

        }
        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutForm(@CookieValue(value="loginMember", required=false) String loginMember, HttpSession session) {
        System.out.println("[AdminMemberController.logoutForm]");
        String nextPage = "redirect:/admin/";
        session.invalidate();

        return nextPage;
    }

    @GetMapping("/listupAdmin")
    public String listupAdmin(Model model) {
        System.out.println("[AdminMemberController.listupAdmin]");
        String nextPage = "/admin/member/listup_admins";
        List<AdminMemberVo> adminMemberVos = adminMemberService.selectAllAdmin();
        model.addAttribute("adminMemberVos", adminMemberVos);
        return nextPage;
    }

    @GetMapping("/setAdminApproval")
    public String seAdminApproval(@RequestParam("no") int no) {
        System.out.println("[AdminMemberController.seAdminApproval]");
        String nextPage = "redirect:/admin/member/listupAdmin";
        adminMemberService.setAdminApproval(no);
        return nextPage;
    }

    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session) {
        System.out.println("[AdminMemberController.modifyAccountForm]");
        String nextPage = "/admin/member/modify_account_form";
        AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
        if(adminMemberVo == null) {
            nextPage = "redirect:/admin/member/loginForm";
        }
        return nextPage;
    }

    @PostMapping("/modifyAccountConfirm")
    public String modifyAccountConfirm(AdminMemberVo adminMemberVo, HttpSession session) {
        System.out.println("[AdminMemberController] modifyAccountConfirm()");

        String nextPage = "admin/member/modify_account_ok";

        int result = adminMemberService.modifyAccountConfirm(adminMemberVo);

        if (result > 0) {
            AdminMemberVo loginedAdminMemberVo = adminMemberService.getLoginedAdminMemberVo(adminMemberVo.getNo());

            session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
            session.setMaxInactiveInterval(60 * 30);

        } else {
            nextPage = "admin/member/modify_account_ng";

        }

        return nextPage;

    }

    @GetMapping("/findPasswordForm")
    public String findPasswordForm() {
        System.out.println("[AdminMemberController] findPasswordForm()");
        String nextPage = "admin/member/find_password_form";
        return nextPage;
    }

    @PostMapping("/findPasswordConfirm")
    public String findPasswordConfirm(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberController] findPasswordConfirm");
        String nextPage = "admin/member/find_password_ok";

        int result = adminMemberService.findPasswordConfirm(adminMemberVo);

        if(result <= 0){
            nextPage = "admin/member/find_password_ng";
        }
        return nextPage;
    }
    @GetMapping("/getAllBooks")
    public String getAllBooks(Model model) {
        System.out.println("[AdminMemberController] getAllBooks()");
        String nextPage = "/admin/book/full_list_of_books";
        List<BookVo> bookVos = adminMemberService.selectAllBooks();
        model.addAttribute("bookVos", bookVos);
        return nextPage;
    }

    @GetMapping("/modifyBookForm")
    public String modifyBookForm(Model model) {
        System.out.println("[AdminMemberController] getAllBooks()");
        String nextPage = "/admin/book/book_detail";
        List<BookVo> bookVos = adminMemberService.selectAllBooks();
        model.addAttribute("bookVos", bookVos);
        return nextPage;
    }

}
