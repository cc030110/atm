package atm;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> list;
	private int log; // 로그인 관리(인덱스 번호)

	// Design Pattern 설계 패턴(gof) 중
	// 싱글 인스턴스를 만드는
	// Singletom Pattern 사용

	// 기본생성자의 접근제어자를 private로 바꿈
	private UserManager() {
		list = new ArrayList<>();
		log=-1;
	}
	// 정적 메모리 공간에 올려 사용 가능하게 만들기
	private static UserManager instance = new UserManager(); // 외부에서 변경이 불가능해야 하므로 private
	public static UserManager getInstance() { // getter로만 접근 가능
		return instance;
	}

	// 회원 - 가입
	public void joinUser() {
		int userCode = generateRandomCode();
		System.out.print("name: ");
		String name = Atm.scanner.next();
		System.out.print("id: ");
		String id = Atm.scanner.next();
		System.out.print("password: ");
		String password = Atm.scanner.next();
		
		if (!duplId(id)) {
			User user = new User(userCode, name, id, password);
			this.list.add(user);
			System.err.println("회원가입 완료.");
		} else {
			System.err.println("중복되는 아이디입니다.");
		}
	}
	
	// 회원 - 탈퇴
	public void leaveUser() {
		if(isLogin()) {
			System.out.print("password: ");
			String password = Atm.scanner.next();
			if(this.list.get(log).getPassword().equals(password)) {
				this.list.remove(log);
				logoutUser();
				System.out.println("회원탈퇴 완료.");
			}else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}else {
			System.out.println("로그인 후 이용 가능한 서비스입니다.");
		}
	}
	
	// 회원 - 로그인
	public void loginUser() {
		if(!isLogin()) {
			System.out.print("id: ");
			String id = Atm.scanner.next();
			System.out.print("password: ");
			String password = Atm.scanner.next();
			for (User user : this.list) {
				if (user.getId().equals(id) && user.getPassword().equals(password))
					log = list.indexOf(user);
			}
			if(this.log!=-1) {
				System.out.println("로그인되었습니다.");
			}else {
				System.out.println("아이디/비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("이미 로그인 중입니다.");
		}
	}
	
	// 회원 - 로그아웃
	public void logoutUser() {
		if(isLogin())
			this.log=-1;
	}
	
	// 로그인 여부 확인
	public boolean isLogin() {
		if(this.log!=-1)
			return true;
		return false;
	}

	// 유저 리스트 조회
	public ArrayList<User> getList() {
		// 클론(clone)으로 제공
		return (ArrayList<User>) this.list.clone();
	}
	
	// id 중복검사
	private boolean duplId(String id) {
		boolean dupl = false;
		for (User user : this.list) {
			if (user.getId().equals(id))
				dupl = true;
		}
		return dupl;
	}

	// 유저 코드 랜덤 생성(중복 X)
	private int generateRandomCode() {
		int code = 0;

		while (true) {
			code = (int) (Math.random() * 9000) + 1000;
			// 중복검사
			boolean dupl = false;
			for (User user : this.list) {
				if (user.getUserCode() == code)
					dupl = true;
			}
			if (!dupl)
				break;
		}
		return code;
	}

}
