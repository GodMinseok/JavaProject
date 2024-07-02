package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import daelim.book.rental.admin.util.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/book/admin")
public class BookController {


    @Autowired
    private BookService bookService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/registerBookForm")
    public String registerBookForm() {
        System.out.println("[BookController] registerBookForm()");
        String nextPage = "admin/book/register_book_form";
        return nextPage;
    }

    @PostMapping("/registerBookConfirm")
    public String registerBookConfirm(BookVo bookVo, MultipartFile file) {
        System.out.println("[BookController] registerBookConfirm()");
        String nextPage = "admin/book/register_book_ok";

        String savedFileName = uploadFileService.upload(file);
        if (savedFileName != null) {
            bookVo.setThumbnail(savedFileName);
            int result = bookService.registerBook(bookVo);
            if (result <= 0) {
                nextPage = "admin/book/register_book_ng";
            }
        } else {
            nextPage = "admin/book/register_book_ng";
        }

        return nextPage;
    }

//    ---------------------

    @GetMapping("/searchBookForm")
    public String searchBookForm() {
        System.out.println("[BookController] searchBookForm()");
        String nextPage = "admin/book/search_book";
        return nextPage;
    }


    @GetMapping("/getAllBooks")
    public String getAllBooks() {
        System.out.println(("[BookController] getAllBooks()"));
        String nextPage = "admin/book/full_list_of_books";
        return nextPage;
    }


    @GetMapping("/bookDetail")
    public String bookDetail(BookVo bookvo,  @RequestParam("no") int no) {
        System.out.println("[BookController] bookDetail()");

        String nextPage = "admin/book/book_detail";
        return nextPage;
    }

    @PostMapping("/modifyBookConfirm")
    public String modifyBookConfirm(BookVo bookVo){
        System.out.println("[BookController] modifyBookConfirm()");
        String nextPage = "admin/book/full_list_of_books";

        return nextPage;
    }

    @PostMapping("/modifyBookForm")
    public String modifyBookForm(BookVo bookVo){
        System.out.println("[BookController] modifyBookForm()");
        String nextPage = "admin/book/book_detail";

        return nextPage;
    }

    @PostMapping("/deleteBookForm")
    public String deleteBookForm(BookVo bookVo){
        System.out.println("[BookController] deleteBookForm()");
        String nextPage = "admin/book/book_detail";

        return nextPage;
    }




}
