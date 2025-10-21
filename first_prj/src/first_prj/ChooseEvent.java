package first_prj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChooseEvent extends WindowAdapter implements ActionListener {

	private ChooseJDialog cd;
	private FileLoad loadFile;
	

	public ChooseEvent(ChooseJDialog cd) {
		this.cd = cd;
		loadFile = new FileLoad();
	}//ChooseEvent

	@Override
	public void windowClosing(WindowEvent e) {
		cd.dispose();
	}
	
	//1번 버튼을 누를 경우 message dialog를 불러오는 함수 실행
	//2번 버튼을 누를 경우 file을 생성하는 함수 실행
	//버튼을 누르면 해당 창은 사라짐
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == cd.getMessageBtn()) {
			logMessage();
			return;
		}//if return;
		if(ae.getSource() == cd.getFileBtn()) {
			if("root".equals(LoginInfo.getCurrentUser())) {
				JOptionPane.showMessageDialog(null, "문서를 생성할 수 있는 권한이 없음");
				return;
			}
			try {
				outputFile();
			} catch (IOException e) {
				e.printStackTrace();
			}//try catch
			return;
		}//if return;
		if(ae.getSource() == cd.getPreBtn()) {
			preJFrame();
			return;
		}//if return;
		if(ae.getSource() == cd.getCloseBtn()) {
			closeDialog();
			return;
		}//if return;
	}//actionPerformed
	
	//1번 버튼을 누를 경우 실행되는 함수
	//JTextArea에는 scroll이 없으므로 JScrollPane에 추가해줌
	//message dialog에 JScrollPane을 넣어 스크롤 있게 원하는 내용을 볼 수 있게 함.
	//보여줄 내용은 추후 추가될 예정
	private void logMessage() {
		JTextArea jtaLog = new JTextArea();
		JScrollPane jspLog = new JScrollPane(jtaLog);
		jtaLog.append(stringResult());
		
		JOptionPane.showMessageDialog(cd, jspLog, "TITLE", JOptionPane.PLAIN_MESSAGE);
	}
	
	//2번 버튼을 누를 경우 실행되는 함수
	//파일을 만들어서 지정 폴더에 저장
	//지정 폴더가 존재하지 않을 경우 만들고 파일을 생성
	//파일 생성에 성공하면 알려줄 message dialog 생성
	private void outputFile() throws IOException {
		
		String dirStr = "c:/dev/report";
		File dir = new File(dirStr);
		if(!dir.exists()) {
			dir.mkdirs();
		}//if dir
		
		Calendar milli = Calendar.getInstance();
		long dateNum = milli.getTimeInMillis();
		
		String fileStr = "report_" + dateNum + ".bat";
		File file = new File(dirStr+File.separator+fileStr);

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			
			osw.write(stringResult());
			osw.flush();
			JOptionPane.showMessageDialog(cd, "파일이 정상적으로 만들어짐");
		} catch (FileNotFoundException e) {
			System.err.println("file이 없음 어떤 file인지 확인할 것");
			e.printStackTrace();
		} finally {
			if(fos != null) {
				fos.close();
			}//if null
			if(osw != null) {
				osw.close();
			}//if null
		}//try catch
	}//outputFile
	
	public void preJFrame() {
		new InputLine();
		closeDialog();
	}
	
	public String stringResult() {
		StringBuilder sb = new StringBuilder();
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sb.append("---------------------------------------------------------------------------\n");
		sb.append(loadFile.getFile().getName());
		sb.append(" (생성된 날짜 ").append(sdf.format(date)).append(")\n");
		sb.append("---------------------------------------------------------------------------\n");
		sb.append("1. 최다 사용키 : ").append(loadFile.number1()).append("\n");
		sb.append("2. 브라우저 별 접속 횟수, 비율\n").append(loadFile.number2()).append("\n");
		sb.append("3. 서비스를 수행한 횟수\n").append(loadFile.number3()).append("\n");
		sb.append("4. 요청이 가장많은 시간 : ").append(loadFile.number4()).append("\n");
		sb.append("5. 비정상적인 요청(403)이 발생한 횟수, 비율 : ").append(loadFile.number5()).append("\n");
		sb.append("6. books에 대한 요청 URL 중에러(500)가 발생한 횟수, 비율 : ").append(loadFile.number6());
		
		
		return sb.toString();
	}
	
	
	private void closeDialog() {
		cd.dispose();
	}
}
