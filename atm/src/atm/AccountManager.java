package atm;

import java.util.ArrayList;

public class AccountManager {
	private ArrayList<Account> list;
	private UserManager userManager = UserManager.getInstance();

	// 1. 생성자 숨기기
	private AccountManager() {
		list = new ArrayList<>();
	}

	// 2. 클래스 내부에서 단일 인스턴스를 생성(외부에서 접근 X)
	private static AccountManager instance = new AccountManager();

	// 3. 외부에서 단일 인스턴스를 참조할 수 있도록 getter 제공
	public static AccountManager getInstance() {
		return instance;
	}

	// 계좌 생성
	public void createAccount(User user) {
		Account acc = null;

		int accNumber = generateRandomCode();
		int accPassword = Atm.inputNumber("계좌 비밀번호");

		// account 객체 생성
		acc = new Account(user.getUserCode(), accNumber, accPassword);
		// AccountManager의 list에 추가
		this.list.add(acc);

		// User 객체가 가진 account 목록에도 추가
		ArrayList<Account> accs = user.getAccs();
		accs.add(acc);
		user.setAccs(accs);
	}

	// 계좌 철회(삭제)
	public void deleteAcc(int log) {
		User user = userManager.getUserByUserCode(log);
		if (user.getAccs() != null) {
			// 계좌목록 보여주기
			userManager.getUserAccList(user);
			int selAccNum = Atm.inputNumber("철회할 계좌 번호");
			Account delAcc = null;
			for (Account acc : this.list) {
				if (acc.getAccNumber() == selAccNum)
					delAcc = acc;
			}
			if (delAcc != null) {
				int accPassword=Atm.inputNumber("계좌 비밀번호");
				if(delAcc.getAccPassword()==accPassword) {
					this.list.remove(delAcc);
					System.out.println("계좌 철회 완료");
				}else {
					System.out.println("계좌 비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("올바르지 않는 계좌 번호입니다.");
			}
		}else {
			System.out.println("계좌가 존재하지 않습니다.");
		}
	}

	// 모든 계좌 삭제
	public void deleteAllAcc(User user) {
		int userCode = user.getUserCode();
		for (Account acc : this.list) {
			if (acc.getUserCode() == userCode)
				this.list.remove(acc);
		}
	}

	// 계좌번호 랜덤 생성(중복 X)
	private int generateRandomCode() {
		int code = 0;

		while (true) {
			// 100 ~ 999
			code = (int) (Math.random() * 900) + 100;
			// 중복검사
			boolean dupl = false;
			for (Account acc : this.list) {
				if (acc.getUserCode() == code)
					dupl = true;
			}
			if (!dupl)
				break;
		}
		return code;
	}

	// 모든 계좌 리스트 조회
	public ArrayList<Account> getList() {
		return (ArrayList<Account>) this.list.clone();
	}

}
