package atm;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> list;

	// Design Pattern 설계 패턴(gof) 중
	// 싱글 인스턴스를 만드는
	// Singletom Pattern 사용

	// 기본생성자의 접근제어자를 private로 바꿈
	private UserManager() {
		list = new ArrayList<>();
	}

	// 정적 메모리 공간에 올려 사용 가능하게 만들기
	private static UserManager instance = new UserManager(); // 외부에서 변경이 불가능해야 하므로 private

	public static UserManager getInstance() { // getter로만 접근 가능
		return instance;
	}

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

	public ArrayList<User> getList() {
		return (ArrayList<User>) this.list.clone();
	}

	private boolean duplId(String id) {
		boolean dupl = false;
		for (User user : this.list) {
			if (user.getId().equals(id))
				dupl = true;
		}
		return dupl;
	}

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
