package daelim.book.rental.user.member;

public class UserMemberVo {

    int no;              // 관리자 번호
    String id;           // 관리자 아이디
    String name;         // 관리자 이름
    String pw;           // 관리자 비밀번호
    String gender;       // 관리자 성별 구분
    String email;        // 관리자 이메일
    String phone;        // 관리자 전화번호
    String regDate;      // 관리자 등록일
    String modDate;      // 관리자 수정일

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
