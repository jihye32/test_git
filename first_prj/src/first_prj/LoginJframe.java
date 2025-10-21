package first_prj;

import javax.swing.*;
import java.awt.*;

public class LoginJframe extends JFrame {
    private JTextField idField;
    private JPasswordField pwField;
    private JButton loginBtn;

    public LoginJframe() {
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // UI 컴포넌트
        idField = new JTextField(12);
        pwField = new JPasswordField(12);
        loginBtn = new JButton("Login");

        // 패널 구성
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("PW:"));
        panel.add(pwField);
        panel.add(new JLabel(""));
        panel.add(loginBtn);

        add(panel);

        // 이벤트 객체 생성 후 버튼과 필드에 등록
        LoginEvent event = new LoginEvent(this);
        loginBtn.addActionListener(event);
        pwField.addActionListener(event);

        setVisible(true);
    }

    // getter 메서드 → 이벤트 클래스에서 접근 가능
    public JTextField getIdField() {
        return idField;
    }

    public JPasswordField getPwField() {
        return pwField;
    }
    
    public static void main(String[] args) {
		new LoginJframe();
	}
}

