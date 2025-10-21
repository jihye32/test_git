package first_prj;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChooseJDialog extends JFrame {//diagram? frame?
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();

	private JButton messageBtn;
	private JButton fileBtn;
	
	private JButton preBtn;
	private JButton closeBtn;

	public ChooseJDialog() {

		messageBtn = new JButton("1");
		fileBtn = new JButton("2");
		preBtn = new JButton("이전");
		closeBtn = new JButton("완료");
		
		Font btnFont = new Font(messageBtn.getFont().getName(), Font.PLAIN, 30);
		messageBtn.setFont(btnFont);
		fileBtn.setFont(btnFont);
		messageBtn.setSize(100, 80);
		fileBtn.setSize(100, 80);
		
		
		JPanel jp = new JPanel();
		jp.add(messageBtn);
		jp.add(fileBtn);
		
		JPanel southJp = new JPanel();
		southJp.add(preBtn);
		southJp.add(closeBtn);
		
		//event
		ChooseEvent ce = new ChooseEvent(this);
		messageBtn.addActionListener(ce);
		fileBtn.addActionListener(ce);
		preBtn.addActionListener(ce);
		closeBtn.addActionListener(ce);
		
		add("Center", jp);
		add("South", southJp);
		
		setResizable(false);
		
		//크기랑 위치는 추후 수정
		setSize(300,200);
		
		int x = screenSize.width/2 - this.getWidth()/2;
		int y = screenSize.height/2 - this.getHeight()/2;
		setLocation(x,y);
		setVisible(true);
		addWindowListener(ce);
	}
	
	public final JButton getPreBtn() {
		return preBtn;
	}

	public final JButton getCloseBtn() {
		return closeBtn;
	}

	public JButton getMessageBtn() {
		return messageBtn;
	}
	public JButton getFileBtn() {
		return fileBtn;
	}
}
