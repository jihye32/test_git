package first_prj;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class InputLine extends JFrame{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	
	private JButton jbtnFileInput;
	private JButton jbtnShow;
	private JButton jbtnNext;
	private JLabel jlblFileName;
	private JTextField jtfStartLine;
	private JTextField jtfEndLine;
	private JLabel jlblRange;
	private JLabel jlblLineResult;
	
	public InputLine() {
		super("줄 입력");

		jbtnFileInput=new JButton("파일 선택");
		jbtnShow=new JButton("출력");
		jbtnNext=new JButton("다음");
		
	    jlblFileName=new JLabel();//파일 이름 표시 
		
		jtfStartLine=new JTextField(10);
	    jtfEndLine=new JTextField(10);
		jlblRange=new JLabel("~");
		JLabel jlblStart=new JLabel("시작 줄");
		JLabel jlblEnd=new JLabel("끝 줄");
		jlblLineResult=new JLabel();//7번 결과 표시
		
		setLayout(null);
		
		jlblLineResult.setBorder(new TitledBorder("최다 사용키 / 최다 사용키의 사용 횟수"));
		jbtnFileInput.setBounds(30,30,90,30);
		add(jbtnFileInput);
		jlblFileName.setBounds(150,30,400,30);
		add(jlblFileName);
		
		jlblStart.setBounds(30,75,70,30);
		jtfStartLine.setBounds(30,100,90,30);
		jlblRange.setBounds(135,100,30,30);
		jlblEnd.setBounds(160,75,70,30);
		jtfEndLine.setBounds(160,100,90,30);
		
		jbtnShow.setBounds(350,100,90,30);
		jlblLineResult.setBounds(100,150,350,250);
		jbtnNext.setBounds(300,420,90,30);
		
		add(jlblStart);
		add(jlblEnd);
		add(jtfStartLine);
		add(jlblRange);
		add(jtfEndLine);
		add(jbtnShow);
		add(jlblLineResult);
		add(jbtnNext);
		
		InputLineEvent ile=new InputLineEvent(this);
		jbtnFileInput.addActionListener(ile);
		jbtnShow.addActionListener(ile);
		jbtnNext.addActionListener(ile);
		
		
		setSize(600,500);
		int x = screenSize.width/2 - this.getWidth()/2;
		int y = screenSize.height/2 - this.getHeight()/2;
		setLocation(x,y);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}//InputLine

	public JButton getJbtnFileInput() {
		return jbtnFileInput;
	}//JLabel jlblLineResult



	public JButton getJbtnShow() {
		return jbtnShow;
	}//getJbtnShow



	public JButton getJbtnNext() {
		return jbtnNext;
	}//getJbtnNext



	public JLabel getJlblFileName() {
		return jlblFileName;
	}//getJlblFileName



	public JTextField getJtfStartLine() {
		return jtfStartLine;
	}//getJtfStartLine



	public JTextField getJtfEndLine() {
		return jtfEndLine;
	}//getJtfEndLine



	public JLabel getJlblRange() {
		return jlblRange;
	}//getJlblRange



	public JLabel getJlblLineResult() {
		return jlblLineResult;
	}//getJlblLineResult
	
	public static void main(String[] args) {
		
	}

}//class

