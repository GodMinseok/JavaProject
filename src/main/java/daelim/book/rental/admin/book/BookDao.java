package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {


    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isISBN(String isbn) {
        System.out.println("[BookDao] isbn: " + isbn);
        String sql = "SELECT COUNT(*) FROM TB_BOOK WHERE ISBN = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);

        return result > 0 ? true : false;
    }

    public int insertBook(BookVo bookVo) {
        System.out.println("[BookDao] insertBook: " + bookVo);
        String sql = "INSERT INTO TB_BOOK (thumbnail, name, author, publisher, publishYear, isbn, callNumber, rentalAble, regDate, modDate) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        int result = -1;

        try {
            result = jdbcTemplate.update(sql,
                    bookVo.getThumbnail(), bookVo.getName(), bookVo.getAuthor(), bookVo.getPublisher(), bookVo.getPublishYear(),
                    bookVo.getIsbn(), bookVo.getCallNumber(), bookVo.getRentalAble());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<BookVo> selectAllBooks() {
        System.out.println("[AdminMemberDao.selectAllAdmin]");
        String sql = "SELECT * FROM TB_BOOK ";

        List<BookVo> bookVoList = new ArrayList<>();
        try {
            bookVoList = jdbcTemplate.query(sql, new RowMapper<BookVo>() {

                @Override
                public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVo bookVo = new BookVo();
                    bookVo.setNo(rs.getInt("no"));
                    bookVo.setThumbnail(rs.getString("thumbnail"));
                    bookVo.setName(rs.getString("name"));
                    bookVo.setAuthor(rs.getString("author"));
                    bookVo.setPublisher(rs.getString("publisher"));
                    bookVo.setPublishYear(rs.getString("publishYear"));
                    bookVo.setIsbn(rs.getString("isbn"));
                    bookVo.setCallNumber(rs.getString("callNumber"));
                    bookVo.setRentalAble(rs.getInt("rentalAble"));
                    bookVo.setRegDate(rs.getString("regDate"));
                    bookVo.setModDate(rs.getString("modDate"));

                    return bookVo;
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        return bookVoList;
    }



}
