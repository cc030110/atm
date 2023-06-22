package atm;

import java.util.ArrayList;

public class AccountManager {
	private ArrayList<Account> list;
	
	// 1. 생성자 숨기기
	private AccountManager() {}
	// 2. 클래스 내부에서 단일 인스턴스를 생성(외부에서 접근 X)
	private static AccountManager instance = new AccountManager();
	// 3. 외부에서 단일 인스턴스를 참조할 수 있도록 getter 제공
	public static AccountManager getInstance() {
		return instance;
	}
	
}
