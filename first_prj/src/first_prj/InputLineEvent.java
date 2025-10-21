package first_prj;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;


public class InputLineEvent extends WindowAdapter implements ActionListener {

	InputLine il;
	File fileName;
	
	FileLoad fileLoad;
	
	public InputLineEvent(InputLine il) {
		this.il=il;
	}//InputLineEvent
	
	@Override
	public void windowClosing(WindowEvent e) {
		il.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
	      
	      if(e.getSource()==il.getJbtnFileInput()) {//파일 선택 버튼
	         
	    	  chooseFile();
	         
	      }//end if
	      
	      if(e.getSource()==il.getJbtnShow()) {//출력 버튼
	    	  System.out.println( il.getJtfStartLine().getText());
	    	  System.out.println( il.getJtfEndLine().getText());
	    	  if(il.getJtfStartLine().getText().equals("") || 
	    			  il.getJtfEndLine().getText().equals("")
	    			  ||!il.getJtfStartLine().getText().matches("^[0-9]*$")
	    			  ||!il.getJtfEndLine().getText().matches("^[0-9]*$")) {
	    		 JOptionPane.showMessageDialog(il, "줄을 입력해주세요");
	         }else {
	        	 printLineInfo();
	         }
	      }//end if
	      
	      if(e.getSource()==il.getJbtnNext()) {//다음 버튼
	    	  if(  il.getJlblFileName().getText().equals("")) {
	    		  JOptionPane.showMessageDialog(il, "파일을 선택해주세요");
	    	  }else {
	    		  new ChooseJDialog();//번호 선택하는 창 열림
	    		  il.dispose();//라인 입력하는 창 꺼짐(?)
	    	  }
	      }//end if

	   }//actionPerformed
	
	public void chooseFile() {
		
		FileDialog fdOpen=new FileDialog(il,"파일 선택",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String dir=fdOpen.getDirectory();
		String file=fdOpen.getFile();
		if(file == null) return;
		fileName=new File(dir+file);
		if(!(file.substring(file.lastIndexOf("."), file.length()).equals(".log"))) {
	         JOptionPane.showMessageDialog(null,"log 형식의 파일을 선택해주세요");
	         return;
	    }
		
	    	fileLoad = new FileLoad(fileName);
	    	il.getJlblFileName().setText(file);//파일 이름 라벨에 표시
	    
			
	}//chooseFile
	
	public void printLineInfo() {
		
		
		if(fileName.isDirectory() || !fileName.exists() || !fileName.isFile()) {
			//폴더거나 파일이 존재하지 않거나 파일이 아니면
			JOptionPane.showMessageDialog(il, "파일을 다시 확인해주세요");
			return;
		}//end if
		
		String start=il.getJtfStartLine().getText().trim();//입력된 시작 줄
		String end=il.getJtfEndLine().getText().trim();//입력된 끝 줄
		fileLoad.setStartLine(Integer.parseInt(start));
		fileLoad.setEndLine(Integer.parseInt(end));
		
		if(start.isEmpty() || end.isEmpty()) {//시작 줄이나 끝 줄이 공백이라면
			JOptionPane.showMessageDialog(il, "값을 입력해주세요");
			return;
		}//end if
		
		if( !start.matches("^[0-9]*$") || !end.matches("^[0-9]*$") ) {//시작 줄 수나 끝 줄 수가 숫자가 아니라면
			JOptionPane.showMessageDialog(il, "줄 수는 숫자만 입력할 수 있습니다");
			return;
		}//end if
		
		try(BufferedReader br=new BufferedReader(
		 new InputStreamReader(new FileInputStream(fileName)))){
			
			String line="";
			int lineCount=0;
			while(   (line=br.readLine())  != null) {
				lineCount++;
				
				
			}//end while
			
			if(Integer.parseInt(start)<1 || Integer.parseInt(start)>lineCount 
					|| Integer.parseInt(end)<1 || Integer.parseInt(end)>lineCount 
					|| Integer.parseInt(start)>Integer.parseInt(end)) {
				//시작줄이 1보다 작거나 라인 수보다 크거나, 끝줄이 1보다 작거나 라인 수보다 크거나
				//시작줄이 끝줄 수보다 크면 안됨
						JOptionPane.showMessageDialog(il, "줄 수를 다시 입력해주세요");
						return;
						}//end if
			
			il.getJlblLineResult().setText(fileLoad.number1());
			
		}catch(IOException ie){
			ie.printStackTrace();
		}//end catch
		
	}//printLineInfo
	
}//class

