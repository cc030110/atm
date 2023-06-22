package atm;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> list;

	// Design Pattern 설계 패턴(gof) 중
	// 싱글 인스턴스를 만드는 
	// Singletom Pattern 사용
	
	// 기본생성자의 접근제어자를 private로 바꿈
	private UserManager() {
		list = new ArrayList<User>();
	}
	// 정적 메모리 공간에 올려 사용 가능하게 만들기
	private static UserManager instance = new UserManager(); // 외부에서 변경이 불가능해야 하므로 private
	public static UserManager getInstance() { // getter로만 접근 가능
		return instance;
	}
	
	// 가입
	public void addUser(String name, String id, String pw) {
		int index=-1;
		for(int i=0;i<list.size();i++) {
			if(instance.list.get(i).getId().equals(id))
				index=i;
		}
		
		
		instance.list.add(new User(name,id,pw));
		
	}
	
	// 탈퇴
	public void delUser(int index) {
		instance.list.remove(index);
	}
	
	// 로그인
	public int login(String id,String pw) {
		return -1;
	}
	
	// 로그아웃
	
	
}
