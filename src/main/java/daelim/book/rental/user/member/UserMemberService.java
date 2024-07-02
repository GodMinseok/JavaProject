package daelim.book.rental.user.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
public class UserMemberService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private UserMemberDao userMemberDao;

    final static public int USER_ACCOUNT_CREATE_SUCCESS = 1;

    final static public int USER_ACCOUNT_CREATE_EXISTS = 0;

    final static public int USER_ACCOUNT_CREATE_FAIL = -1;

    public int createAccount(UserMemberVo userMemberVo) {
        System.out.println("[UserMemverService] createAccount()");

        boolean isMember = userMemberDao.existUserAccount(userMemberVo.getId());
        if (!isMember) {
            //신규 가입
            int result = userMemberDao.insertUserAccount(userMemberVo);
            if (result > 0) {
                System.out.println("[UserMemberService] createAccount SUCCESS!!");
                return USER_ACCOUNT_CREATE_SUCCESS;
            } else {
                System.out.println("[UserMemberService] createAccount FAIL!!");
                return USER_ACCOUNT_CREATE_FAIL;
            }
        } else {
            System.out.println("[UserMemberService] createAccount already exists!!");
            return USER_ACCOUNT_CREATE_EXISTS;
        }
    }

    public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberService] loginConfirm");
        UserMemberVo loginedUserMemberVo = userMemberDao.selectUser(userMemberVo);

        if (loginedUserMemberVo != null) {
            System.out.println("[UserMemberService] loginConfirm success");
        } else {
            System.out.println("[UserMemberService] loginConfirm fail");
        }
        return loginedUserMemberVo;
    }

    public List<UserMemberVo> selectAllUser() {
        System.out.println("[UserMemberService] getAllUserMembers");
        return userMemberDao.selectAllUser();
    }


    public int modifyAccountConfirm(UserMemberVo userMemberVo) {
        // 로그인된 사용자의 정보 가져오기
        UserMemberVo loginedUser = getLoginedUserMemberVo(userMemberVo.getNo());
        if (loginedUser == null) {
            System.out.println("User not found with no: " + userMemberVo.getNo());
            return 0;
        }

        // 업데이트할 정보 설정
        loginedUser.setName(userMemberVo.getName());
        loginedUser.setGender(userMemberVo.getGender());
        loginedUser.setEmail(userMemberVo.getEmail());
        loginedUser.setPhone(userMemberVo.getPhone());

        // 업데이트 실행
        return userMemberDao.updateUserAccount(loginedUser);
    }


    public UserMemberVo getLoginedUserMemberVo(int no) {
        System.out.println("[UserMemberService] getLoginedUserMemberVo()");
        return userMemberDao.selectUser(no);
    }

    public int findPasswordConfirm(UserMemberVo userMemberVo) {
        System.out.println("[UserMemberService] findPasswordConfirm()");
        int result = 0;
        //1. id, name, email 일치하는 사용자 select
        UserMemberVo selectedUserMemberVo = userMemberDao.selectUser(userMemberVo.getId(),
                userMemberVo.getName(), userMemberVo.getEmail());
        if (selectedUserMemberVo != null) {
            //2. 일치하는 사용자가 있으면 신규 비밀번호 생성 후 업데이트 비밀번호
            String newPassword = createNewPassword();
            result = userMemberDao.updatePassword(userMemberVo.getId(), newPassword);
            //3. 신규 비밀번호를 포함한 이메일 발송
            if (result > 0) {
                sendNewPasswordByMail(userMemberVo.getEmail(), newPassword);
            }
        }
        return result;
    }

    private String createNewPassword() {
        System.out.println("[UserMemberService] createNewPassword()");

        char[] chars = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };

        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(new Date().getTime());

        int index = 0;
        int length = chars.length;

        for (int i = 0; i < 8; i++) {
            index = secureRandom.nextInt(length);

            if (index % 2 == 0)
                stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
            else
                stringBuffer.append(String.valueOf(chars[index]).toLowerCase());

        }

        System.out.println("[UserMemberService] NEW PASSWORD: " + stringBuffer.toString());

        return stringBuffer.toString();

    }

    private void sendNewPasswordByMail(String toMail, String newPassword) {
        System.out.println("[UserMemberService] sendNewPasswordByMail()");
        System.out.println("toMail : " + toMail);
        System.out.println("newPassword : " + newPassword);

        final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(toMail);
                mimeMessageHelper.setFrom("seaminseag@gmail.com");
                mimeMessageHelper.setSubject("[한국 도서관] 새 비밀번호 안내");
                mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
            }
        };
        javaMailSender.send(mimeMessagePreparator);

    }
}