package first_prj;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LoginEvent implements ActionListener {
	private LoginInfo loginInfo;
    private LoginJframe loginFrame;  // UI 참조

    public LoginEvent(LoginJframe loginFrame) {
        this.loginFrame = loginFrame;
        loginInfo = new LoginInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginCheck();
    }

    private void loginCheck() {
        String id = loginFrame.getIdField().getText().trim();
        String pw = new String(loginFrame.getPwField().getPassword());
        
//        System.out.println(id +" / "+pw);
//        System.out.println(LoginInfo.getUsers().containsKey(id) +" / "+LoginInfo.getUsers().get(id));

        if (LoginInfo.getUsers().containsKey(id) && LoginInfo.getUsers().get(id).equals(pw)) {
        	//로그인한 정보를 저장해줌 
        	LoginInfo.setCurrentUser(id);
            JOptionPane.showMessageDialog(loginFrame, "로그인 성공: " + id);

            if ("root".equals(id)) {
                JOptionPane.showMessageDialog(loginFrame, "root 계정으로 로그인 되었습니다.");
            }

            loginFrame.dispose(); // 로그인 창 닫기
            new InputLine();
        } else {
            JOptionPane.showMessageDialog(loginFrame, "로그인 실패: ID 또는 PW가 올바르지 않습니다.");
        }
    }
}