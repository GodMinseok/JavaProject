package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    final static public int BOOK_ISBN_ALREADY_EXISTS = 0;   // 이미 등록된 도서
    final static public int BOOK_REGISTER_SUCCESS = 1;      // 신규 도서 등록 성공
    final static public int BOOK_REGISTER_FAIL = -1;        // 신규 도서 등록 실패


    public int registerBook(BookVo bookVo) {
        System.out.println("[BookService] registerBookConfirm()");
        boolean isISBN = bookDao.isISBN(bookVo.getIsbn());

        if(!isISBN) {
            int result = bookDao.insertBook(bookVo);
            if(result > 0 ) return BOOK_REGISTER_SUCCESS;
            else return BOOK_REGISTER_FAIL;
        } else {
            return BOOK_ISBN_ALREADY_EXISTS;
        }

    }


}
