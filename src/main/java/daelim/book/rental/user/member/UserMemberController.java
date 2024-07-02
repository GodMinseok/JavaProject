package daelim.book.rental.user.member;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    private UserMemberService userMemberService;

    @RequestMapping("/createAccountForm")
    public String createAccountForm() {
        System.out.println("[UserHomeController] createAccountForm()");
        return "/user/member/create_account_form";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
        System.out.println("[UserHomeController] loginForm()");
        return "/user/member/login_form";
    }

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(UserMemberVo userMemberVo) {

        System.out.println("[UserMemberController] createAccountConfirm()");
        String nextPage = "/user/member/create_account_ok";
        int result = userMemberService.createAccount(userMemberVo);
        if (result <= 0) {
            nextPage = "/user/member/create_account_ng";
        }
        return nextPage;
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UserMemberVo userMemberVo, HttpSession session) {
        System.out.println("[UserMemberController] loginConfirm()");
        String nextPage = "/user/member/login_ok";
        UserMemberVo loginedUserMemberVo = userMemberService.loginConfirm(userMemberVo);
        if (loginedUserMemberVo == null){
            nextPage = "/user/member/login_ng";
        } else {
            // cookie 설정
//            Cookie cookie = new Cookie("loginMember", loginedUserMemberVo.getId());
//            cookie.setMaxAge(60 * 30);
//            response.addCookie(cookie);
            //session 설정
            session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
            session.setMaxInactiveInterval(60 * 30);
        }
        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutForm( HttpSession session) {
        System.out.println("[UserMemberController.logoutForm]");
        String nextPage = "redirect:/user/";
        session.invalidate();

        return nextPage;
    }

    @GetMapping("/listupUser")
    public String listupUser(Model model) {
        System.out.println("[UserMemberController] listupUser()");
        String nextPage = "/user/member/listup_users";
        List<UserMemberVo> userMemberVos = userMemberService.selectAllUser();
        model.addAttribute("userMemberVos", userMemberVos);
        return nextPage;
    }


    @GetMapping("/modifyAccountForm")
    public String modifyAccountForm(HttpSession session) {
        System.out.println("[UserMemberController] modifyAccountForm()");
        String nextPage = "/user/member/modify_account_form";
        UserMemberVo userMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
        System.out.println("[UserMemberController] == ()"+ userMemberVo.getId());
        session.setAttribute("loginedUserMemberVo", userMemberVo);
        session.setMaxInactiveInterval(60 * 30);

        if (userMemberVo == null) {
            nextPage = "redirect:/user/member/loginForm";
        }
        return nextPage;
    }

    @PostMapping("/modifyAccountConfirm")
    public String modifyAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
        System.out.println("[UserMemberController] modifyAccountConfirm()");

        String nextPage = "user/member/modify_account_ok";

        int result = userMemberService.modifyAccountConfirm(userMemberVo);
        System.out.println("[UserMemberController] modifyAccountConfirm() result: " + result);

        if (result > 0) {
            UserMemberVo loginedUserMemberVo = userMemberService.getLoginedUserMemberVo(userMemberVo.getNo());
            session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            nextPage = "user/member/modify_account_ng";
        }

        return nextPage;
    }



    @GetMapping("/findPasswordForm")
    public String findPasswordForm() {
        System.out.println("[UserMemberController] findPasswordForm()");
        String nextPage = "user/member/find_password_form";
        return nextPage;
    }

    @PostMapping("/findPasswordConfirm")
    public String findPasswordConfirm(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberController] findPasswordConfirm()");
        String nextPage = "user/member/find_password_ok";

        int result = userMemberService.findPasswordConfirm(userMemberVo);

        if (result <= 0) {
            nextPage = "user/member/find_password_ng";
        }
        return nextPage;
    }
}
