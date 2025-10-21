package first_prj;

import java.util.HashMap;
import java.util.Map;

public class LoginInfo {
	
    private static Map<String, String> users = new HashMap<>();
    private static String currentUser;

	public LoginInfo() {
        // 계정 정보
        users.put("admin", "1234");
        users.put("root", "1111");
        users.put("administrator", "12345");
	}
	
	public LoginInfo(String id, String pw) {
		users.put(id, pw);
	}
	
	public static Map<String, String> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, String> users) {
		LoginInfo.users = users;
	}

	//로그인한 사용자의 아이디를 저장해 줌
	public static void setCurrentUser(String user) {
		currentUser = user;
	}
	//사용자의 아이디를 식별하기 위한 getter메서드
    public static String getCurrentUser() {
        return currentUser; 
    }
}
