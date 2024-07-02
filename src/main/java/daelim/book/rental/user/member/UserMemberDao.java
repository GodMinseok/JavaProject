package daelim.book.rental.user.member;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMemberDao {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserMemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertUserAccount(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberDao.insertUserAccount]");
        List<String> args = new ArrayList<>();
        String sql = "INSERT INTO TB_USER_MEMBER (";

        sql += "id, ";
        args.add(userMemberVo.getId());

        sql += "pw, ";
//        args.add(userMemberVo.getPw()());
        args.add(bCryptPasswordEncoder.encode(userMemberVo.getPw()));

        sql += "name, ";
        args.add(userMemberVo.getName());

        sql += "gender, ";
        args.add(userMemberVo.getGender());

        sql += "email, ";
        args.add(userMemberVo.getEmail());

        sql += "phone, ";
        args.add(userMemberVo.getPhone());

        sql += "regDate, modDate) ";

        if(userMemberVo.getId().equals("system")) {
            sql += "VALUES (?,?,?,?,?,?,?,NOW(), NOW())";
        } else {
            sql += "VALUES (?,?,?,?,?,?,NOW(), NOW())";
        }

        int result = -1;
        try {
            result = jdbcTemplate.update(sql, args.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public boolean existUserAccount(String id) {
        System.out.println("[UserMemberDao.existUserAccount]");
        String sql = "SELECT COUNT(*) FROM TB_USER_MEMBER WHERE id = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if(result > 0) return true;
        else return false;
    }

    public UserMemberVo selectUser(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberDao] selectUser()");
        String sql = "SELECT * FROM TB_USER_MEMBER WHERE id = ? ";
        List<UserMemberVo> userMemberVoList = new ArrayList<>();
        try {
            userMemberVoList = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {
                @Override
                public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserMemberVo userMemberVo1 = new UserMemberVo();
                    userMemberVo1.setNo(rs.getInt("no"));
                    userMemberVo1.setId(rs.getString("id"));
                    userMemberVo1.setName(rs.getString("name"));
                    userMemberVo1.setPw(rs.getString("pw"));
                    userMemberVo1.setGender(rs.getString("gender"));
                    userMemberVo1.setEmail(rs.getString("email"));
                    userMemberVo1.setPhone(rs.getString("phone"));
                    userMemberVo1.setRegDate(rs.getString("regDate"));
                    userMemberVo1.setModDate(rs.getString("modDate"));

                    return userMemberVo;
                }
            }, userMemberVo.getId());

            System.out.println(userMemberVoList.size());
            System.out.println(userMemberVo.getPw()+"/"+bCryptPasswordEncoder.encode(userMemberVo.getPw()));
            System.out.println(userMemberVoList.get(0).getPw());

            if(!userMemberVo.getPw().equals(userMemberVoList.get(0).getPw())) {
                userMemberVoList.clear();
            }
//            if (!bCryptPasswordEncoder.matches(userMemberVo.getPw()(), userMemberVoList.get(0).getPw()())) {
//                userMemberVoList.clear();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userMemberVoList.size() > 0 ? userMemberVoList.get(0) : null;
    }

    public UserMemberVo selectUser(int no) {
        System.out.println("[UserMemberDao] selectUser()");
        String sql = "SELECT * FROM TB_USER_MEMBER WHERE id = ? ";
        List<UserMemberVo> userMemberVoList = new ArrayList<>();
        try {
            userMemberVoList = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {
                @Override
                public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserMemberVo userMemberVo1 = new UserMemberVo();
                    userMemberVo1.setNo(rs.getInt("no"));
                    userMemberVo1.setId(rs.getString("id"));
                    userMemberVo1.setName(rs.getString("name"));
                    userMemberVo1.setPw(rs.getString("pw"));
                    userMemberVo1.setGender(rs.getString("gender"));
                    userMemberVo1.setEmail(rs.getString("email"));
                    userMemberVo1.setPhone(rs.getString("phone"));
                    userMemberVo1.setRegDate(rs.getString("regDate"));
                    userMemberVo1.setModDate(rs.getString("modDate"));

                    return userMemberVo1;
                }
            }, no);
            System.out.println(userMemberVoList.get(0).getPw());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMemberVoList.size() > 0 ? userMemberVoList.get(0) : null;
    }

    public UserMemberVo selectUser(String id, String name, String email) {
        System.out.println("[UserMemberDao] selectUser()");

        String sql =  "SELECT * FROM TB_USER_MEMBER "
                + "WHERE id = ? AND name = ? AND email = ?";

        List<UserMemberVo> userMemberVos = new ArrayList<UserMemberVo>();

        try {

            userMemberVos = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

                @Override
                public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                    UserMemberVo userMemberVo = new UserMemberVo();
                    userMemberVo.setNo(rs.getInt("no"));
                    userMemberVo.setId(rs.getString("id"));
                    userMemberVo.setName(rs.getString("name"));
                    userMemberVo.setPw(rs.getString("pw"));
                    userMemberVo.setGender(rs.getString("gender"));
                    userMemberVo.setEmail(rs.getString("email"));
                    userMemberVo.setPhone(rs.getString("phone"));
                    userMemberVo.setRegDate(rs.getString("regDate"));
                    userMemberVo.setModDate(rs.getString("modDate"));

                    return userMemberVo;
                }
            }, id, name, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMemberVos.size() > 0 ? userMemberVos.get(0) : null;
    }

    public int updatePassword(String id, String newPassword) {
        System.out.println("[UserMemberDao] updatePassword()");

        String sql =  "UPDATE TB_USER_MEMBER SET "
                + "pw = ?,  modDate = NOW() "
                + "WHERE id = ?";
        int result = -1;
        try {
            result = jdbcTemplate.update(sql, bCryptPasswordEncoder.encode(newPassword), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<UserMemberVo> selectAllUser() {
        System.out.println("[UserMemberDao.selectAllUser]");
        String sql = "SELECT * FROM TB_USER_MEMBER ";

        List<UserMemberVo> userMemberVoList = new ArrayList<>();
        try {
            userMemberVoList = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

                @Override
                public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserMemberVo userMemberVo = new UserMemberVo();
                    userMemberVo.setNo(rs.getInt("no"));
                    userMemberVo.setId(rs.getString("id"));
                    userMemberVo.setName(rs.getString("name"));
                    userMemberVo.setPw(rs.getString("pw"));
                    userMemberVo.setGender(rs.getString("gender"));
                    userMemberVo.setEmail(rs.getString("email"));
                    userMemberVo.setPhone(rs.getString("phone"));
                    userMemberVo.setRegDate(rs.getString("regDate"));
                    userMemberVo.setModDate(rs.getString("modDate"));
                    return userMemberVo;
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        return userMemberVoList;
    }

    public int updateUserAccount(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberDao] updateUserAccount");
        String sql = "UPDATE TB_USER_MEMBER SET "
                + "name = ? ,"
                + "gender = ? ,"
                + "email = ? ,"
                + "phone = ? ,"
                + "modDate = NOW() "
                + "WHERE no = ? ";
        System.out.println("UserMemberVo: " + userMemberVo);
        System.out.println("Executing SQL: " + sql);
        System.out.println("Parameters: ");
        System.out.println("name: " + userMemberVo.getId());
        System.out.println("name: " + userMemberVo.getName());
        System.out.println("gender: " + userMemberVo.getGender());
        System.out.println("email: " + userMemberVo.getEmail());
        System.out.println("phone: " + userMemberVo.getPhone());
        System.out.println("no: " + userMemberVo.getNo());

        int result = -1;
        try {
            result = jdbcTemplate.update(sql,
                    userMemberVo.getName(),
                    userMemberVo.getGender(),
                    userMemberVo.getEmail(),
                    userMemberVo.getPhone(),
                    userMemberVo.getNo());
            System.out.println("SQL Update Result: " + result); // 결과 확인
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage()); // 예외 메시지 출력
        }
        return result;
    }


}
