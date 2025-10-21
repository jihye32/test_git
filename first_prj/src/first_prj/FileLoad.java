package first_prj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class FileLoad {
    private static List<String> liResult;
    private static List<String> liUrl;
    private static List<String> liBrowser;
    private static List<String> liDateTime;
    
    private static File file;
    private static int startLine;
    private static int endLine;
    
    public FileLoad() {
    	if(file == null) {
    		System.err.println("파일 없음");
    		return;
    	}
    	new FileLoad(file);
    }

    public FileLoad(File loadFile) {
    	this.file = loadFile;
    	
    	liResult = new ArrayList<String>();
        liUrl = new ArrayList<String>();
        liBrowser = new ArrayList<String>();
        liDateTime = new ArrayList<String>();

        File file = this.file;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            String token = "[]";
            StringTokenizer st = null;

            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, token);

                liResult.add(st.nextToken());   // 응답 결과
                liUrl.add(st.nextToken());      // URL
                liBrowser.add(st.nextToken());  // 브라우저
                liDateTime.add(st.nextToken()); // 시간대
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
        	ie.printStackTrace();
        }
    }

    // 최다 사용 키워드 (key= 기준)!!!
    public String number1() {
        List<String> keywords = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();

        //최다 사용키 (처음부터 끝까지)
        for (int i = startLine; i < endLine; i++) {
        	String url = liUrl.get(i);
            int keyStart = url.indexOf("key=");
            if (keyStart != -1) {
                String keyword = url.substring(keyStart + 4).trim(); // "key=" 이후 문자열

                int paramStart = keyword.indexOf("&");
                if (paramStart != -1) {
                    keyword = keyword.substring(0, paramStart).trim();
                }

                if (keywords.contains(keyword)) {
                    int index = keywords.indexOf(keyword);
                    counts.set(index, counts.get(index) + 1);
                } else {
                    keywords.add(keyword);
                    counts.add(1);
                }
            }
        }

        // 최다 키워드 찾기
        int maxCount = counts.get(0);
        String topKeyword = keywords.get(0);

        for (int i = 1; i < keywords.size(); i++) {
            if (counts.get(i) > maxCount) {
                maxCount = counts.get(i);
                topKeyword = keywords.get(i); 
            }
        }
        
        
//        System.out.println("===== 최다 사용 키워드 =====");
//        System.out.println(topKeyword + " / " + maxCount + "회");
        return topKeyword + " / " + maxCount + "회\n";
    }//1


	// 브라우저별 접속 통계
    public String number2() {
        List<String> browsers = new ArrayList<String>();
        List<Integer> browserCounts = new ArrayList<Integer>();

        String browser = "";
        for (int i = startLine; i < endLine; i++) {
        	browser = liBrowser.get(i);
            browser = browser.trim();
            if (browser.isEmpty()) continue;

            if (browsers.contains(browser)) {
                int index = browsers.indexOf(browser);
                browserCounts.set(index, browserCounts.get(index) + 1);
            } else {
                browsers.add(browser);//브라우저가 새로 발견되면 추가 하고
                browserCounts.add(1);// 카운트하기.
            }
        }

        int total = endLine - startLine;
        StringBuilder sb = new StringBuilder();
//        System.out.println("===== 브라우저별 접속 통계 =====");
        for (int i = 0; i < browsers.size(); i++) {
            double percent = (browserCounts.get(i) * 100.0) / total;
            
            sb.append(browsers.get(i)).append(" - ").append(browserCounts.get(i)).append("회 / ").append(String.format("%.2f%%", percent)).append("\n");
        }
        
        return sb.toString();
        
        
    }
    
    //3. 서비스를 성공적으로 수행한(200) 횟수,실패(404) 횟수
  	public String number3() {
  		int count200 = 0;
  		StringBuilder sb = new StringBuilder();
  		for (int i = startLine; i < endLine; i++) {
  		    if (liResult.get(i).equals("200")) {//서비스를 성공적으로 수행한 횟수
  		        count200++;
  		    }//end if
  		}//end for
  		sb.append("성공한(200) 횟수: ").append(count200).append("회\n");
  		int count404 = 0;
  		for (int i = startLine; i < endLine; i++) {
  		    if (liResult.get(i).equals("404")) {//서비스에 실패한 횟수
  		        count404++;
  		    }//end if
  		}//end for
  		sb.append("실패한(404) 횟수: ").append(count404).append("회\n");
  		return sb.toString();
  	}
  	
  	//4. 요청이 가장 많은 시간 [10시]
  	public String number4() {
  		int countTime = 1;
  		Map<String, Integer> mTime = new HashMap<>();
  		StringTokenizer st = null;
  		String div = " :";
  		String clock = "";
  		for(int i = startLine; i < endLine; i++) {
  			st = new StringTokenizer(liDateTime.get(i),div);
  			clock = st.nextToken();
  			clock = st.nextToken();
  			if(mTime.containsKey(clock)) {
  				countTime = mTime.get(clock);
  				countTime++;
  			}
  			mTime.put(clock, countTime);
  			countTime = 1;
  		}
  		
  		Set<String> keyTime = mTime.keySet();
  		
  		countTime = 0;
  		int max = 0;
  		String maxTime = "";
  		for(String time : keyTime) {
  			countTime = mTime.get(time);
  			if(max < countTime) {
  				max = countTime;
  				maxTime = time;
  			}
  		}
  		return "요청이 가장 많은 시간 : "+maxTime+"시\n";
  	}
  	
  	//5. 비정상적인 요청(403)이 발생한 횟수,비율구하기
  	public String number5() {
  		int count403 = 0;
  		StringBuilder sb = new StringBuilder();
  		for (int i = startLine; i < endLine; i++) {
  		    if (liResult.get(i).equals("403")) {//비정상적인 요청이 발생한 횟수
  		        count403++;
  		    }//end if
  		}//end for
  		sb.append(count403).append("회 / ");
  		
//  		sb.append("서비스 요청 총 횟수: ").append(endLine - startLine).append("\n");
  		
  		double rate404=((double)count403/(endLine - startLine))*100;//비정상적 요청 발생 비율
  		String roundRate404=String.format("%.2f%%", rate404);//소수 두자리 반올림
//  		System.out.println(roundRate404);
  		sb.append(roundRate404).append("\n");
  		
  		return sb.toString();
  	}
  	
  	//6. books에 대한 요청 URL중 에러(500)가 발생한 횟수, 비율 구하기
  	public String number6() {
  		int count500 = 0;
  		int books = 0;
  		String resultUrl = "";
  		
  		for(int i = startLine; i < endLine; i++) {
  			if(liUrl.get(i).contains("books")) {
  				resultUrl = liResult.get(i);
  				if(resultUrl.equals("500")) {
  					count500++;
  				}
  				books++;
  			}
  		}
  		double rate500 = ((double)count500/books)*100; //발생한 비율
  		StringBuilder sb = new StringBuilder();
  		sb.append(count500).append("회 / ");
  		if(count500 != 0) {
  			sb.append(String.format("%.2f%%", rate500));
  		}else {
  			sb.append(0.0).append("%");
  		}
  		return sb.toString();
  	}

  	public static void setStartLine(int startLine) {
  		FileLoad.startLine = startLine - 1;
  	}
  	
  	public static void setEndLine(int endLine) {
  		FileLoad.endLine = endLine;
  	}

	public static final File getFile() {
		return file;
	}

}
