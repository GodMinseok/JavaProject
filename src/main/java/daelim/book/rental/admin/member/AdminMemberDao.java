package daelim.book.rental.admin.member;


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
public class AdminMemberDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminMemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertAdminAccount(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberDao.insertAdminAccount]");
        List<String> args = new ArrayList<>();
        String sql = "INSERT INTO TB_ADMIN_ACCOUNT (";
        if(adminMemberVo.getId().equals("system")) {
            sql += "approval, ";
            args.add("1");
        }

        sql += "id, ";
        args.add(adminMemberVo.getId());

        sql += "password, ";
//        args.add(adminMemberVo.getPassword());
        args.add(bCryptPasswordEncoder.encode(adminMemberVo.getPassword()));


        sql += "name, ";
        args.add(adminMemberVo.getName());

        sql += "gender, ";
        args.add(adminMemberVo.getGender());

        sql += "part, ";
        args.add(adminMemberVo.getPart());

        sql += "position, ";
        args.add(adminMemberVo.getPosition());

        sql += "email, ";
        args.add(adminMemberVo.getEmail());

        sql += "phone, ";
        args.add(adminMemberVo.getPhone());

        sql += "regDate, modDate) ";

        if(adminMemberVo.getId().equals("system")) {
            sql += "VALUES (?,?,?,?,?,?,?,?,?,NOW(), NOW())";
        } else {
            sql += "VALUES (?,?,?,?,?,?,?,?,NOW(), NOW())";
        }

        int result = -1;
        try {
            result = jdbcTemplate.update(sql, args.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public boolean existAdminAccount(String id) {
        System.out.println("[AdminMemberDao.existAdminAccount]");
        String sql = "SELECT COUNT(*) FROM TB_ADMIN_ACCOUNT WHERE id = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if(result > 0) return true;
        else return false;
    }

    public AdminMemberVo selectAdmin(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberDao.selectAdmin]");
        String sql = "SELECT * FROM TB_ADMIN_ACCOUNT WHERE id = ? AND approval > 0";
        List<AdminMemberVo> adminMemberVoList = new ArrayList<>();
        try {
            adminMemberVoList = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {
                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminMemberVo adminMemberVo = new AdminMemberVo();
                    adminMemberVo.setNo(rs.getInt("no"));
                    adminMemberVo.setId(rs.getString("id"));
                    adminMemberVo.setName(rs.getString("name"));
                    adminMemberVo.setPassword(rs.getString("password"));
                    adminMemberVo.setGender(rs.getString("gender"));
                    adminMemberVo.setPart(rs.getString("part"));
                    adminMemberVo.setPosition(rs.getString("position"));
                    adminMemberVo.setEmail(rs.getString("email"));
                    adminMemberVo.setPhone(rs.getString("phone"));
                    adminMemberVo.setRegDate(rs.getString("regDate"));
                    adminMemberVo.setModDate(rs.getString("modDate"));
                    return adminMemberVo;
                }
            }, adminMemberVo.getId());

            // password 확인
            if(!bCryptPasswordEncoder.matches(adminMemberVo.getPassword(), adminMemberVoList.get(0).getPassword())) {
                adminMemberVoList.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminMemberVoList.size() > 0 ? adminMemberVoList.get(0) : null;
    }

    public List<AdminMemberVo> selectAllAdmin() {
        System.out.println("[AdminMemberDao.selectAllAdmin]");
        String sql = "SELECT * FROM TB_ADMIN_ACCOUNT ";

        List<AdminMemberVo> adminMemberVoList = new ArrayList<>();
        try {
            adminMemberVoList = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {

                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminMemberVo adminMemberVo = new AdminMemberVo();
                    adminMemberVo.setNo(rs.getInt("no"));
                    adminMemberVo.setId(rs.getString("id"));
                    adminMemberVo.setName(rs.getString("name"));
                    adminMemberVo.setPassword(rs.getString("password"));
                    adminMemberVo.setGender(rs.getString("gender"));
                    adminMemberVo.setPart(rs.getString("part"));
                    adminMemberVo.setPosition(rs.getString("position"));
                    adminMemberVo.setEmail(rs.getString("email"));
                    adminMemberVo.setPhone(rs.getString("phone"));
                    adminMemberVo.setRegDate(rs.getString("regDate"));
                    adminMemberVo.setModDate(rs.getString("modDate"));
                    adminMemberVo.setApproval(rs.getInt("approval"));
                    return adminMemberVo;
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        return adminMemberVoList;
    }

    public int updateAdminAccount(int no) {
        System.out.println("[AdminMemberDao.updateAdminAccount]");
        String sql = "UPDATE TB_ADMIN_ACCOUNT SET approval = 1 WHERE no = ?";
        int result = -1;
        try {

            result = jdbcTemplate.update(sql, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateAdminAccount(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberDao] updateAdminAccount()");

        String sql = "UPDATE TB_ADMIN_ACCOUNT SET "
                + "name = ?, "
                + "gender = ?, "
                + "part = ?, "
                + "position = ?, "
                + "email = ?, "
                + "phone = ?, "
                + "modDate = NOW() "
                + "WHERE no = ?";

        int result = -1;

        try {

            result = jdbcTemplate.update(sql,
                    adminMemberVo.getName(),
                    adminMemberVo.getGender(),
                    adminMemberVo.getPart(),
                    adminMemberVo.getPosition(),
                    adminMemberVo.getEmail(),
                    adminMemberVo.getPosition(),
                    adminMemberVo.getNo());

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }

    public AdminMemberVo selectAdmin(int no) {
        System.out.println("[AdminMemberDao] selectAdmin()");

        String sql =  "SELECT * FROM TB_ADMIN_ACCOUNT WHERE no = ? ";

        List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();

        try {

            adminMemberVos = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {

                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AdminMemberVo adminMemberVo = new AdminMemberVo();
                    adminMemberVo.setNo(rs.getInt("no"));
                    adminMemberVo.setId(rs.getString("id"));
                    adminMemberVo.setName(rs.getString("name"));
                    adminMemberVo.setPassword(rs.getString("password"));
                    adminMemberVo.setGender(rs.getString("gender"));
                    adminMemberVo.setPart(rs.getString("part"));
                    adminMemberVo.setPosition(rs.getString("position"));
                    adminMemberVo.setEmail(rs.getString("email"));
                    adminMemberVo.setPhone(rs.getString("phone"));
                    adminMemberVo.setRegDate(rs.getString("regDate"));
                    adminMemberVo.setModDate(rs.getString("modDate"));
                    adminMemberVo.setApproval(rs.getInt("approval"));

                    return adminMemberVo;
                }

            }, no);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;

    }

    public AdminMemberVo selectAdmin(String id, String name, String email) {
        System.out.println("[AdminMemberDao] selectAdmin()");

        String sql =  "SELECT * FROM TB_ADMIN_ACCOUNT "
                + "WHERE id = ? AND name = ? AND email = ?";

        List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();

        try {

            adminMemberVos = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {

                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AdminMemberVo adminMemberVo = new AdminMemberVo();
                    adminMemberVo.setNo(rs.getInt("no"));
                    adminMemberVo.setId(rs.getString("id"));
                    adminMemberVo.setName(rs.getString("name"));
                    adminMemberVo.setPassword(rs.getString("password"));
                    adminMemberVo.setGender(rs.getString("gender"));
                    adminMemberVo.setPart(rs.getString("part"));
                    adminMemberVo.setPosition(rs.getString("position"));
                    adminMemberVo.setEmail(rs.getString("email"));
                    adminMemberVo.setPhone(rs.getString("phone"));
                    adminMemberVo.setRegDate(rs.getString("regDate"));
                    adminMemberVo.setModDate(rs.getString("modDate"));
                    adminMemberVo.setApproval(rs.getInt("approval"));

                    return adminMemberVo;

                }

            }, id, name, email);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;

    }

    public int updatePassword(String a_m_id, String newPassword) {
        System.out.println("[AdminMemberDao] updatePassword()");

        String sql =  "UPDATE TB_ADMIN_ACCOUNT SET "
                + "password = ?,  modDate = NOW() "
                + "WHERE id = ?";
        int result = -1;
        try {
            result = jdbcTemplate.update(sql, bCryptPasswordEncoder.encode(newPassword), a_m_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
