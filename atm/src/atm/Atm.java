package atm;
import java.util.Scanner;

/* # Atm
 * ㄴ 회원관리 (가입/탈퇴/로그인/로그아웃)
 * ㄴ 계좌관리 (계약/철회/조회)
 * ㄴ 뱅킹서비스 (입금/인출/이체)
 * ㄴ 파일처리 (저장/로드)
 * */

public class Atm {
	private final int JOIN = 1;				// 회원 - 가입
	private final int LEAVE = 2;			// 회원 - 탈퇴
	private final int LOGIN = 3;			// 회원 - 로그인
	private final int LOGOUT = 4;			// 회원 - 로그아웃
	private final int CREATE_ACC = 5;		// 계좌 - 계약(개설)
	private final int DELETE_ACC =  6;		// 계좌 - 철회
	private final int VIEW_BALANCE = 7;		// 계좌 - 조회
	private final int INPUT_MONEY = 8;		// 뱅킹 - 입금
	private final int OUT_MONEY = 9;		// 뱅킹 - 인출
	private final int MOVE_MONEY = 10;		// 뱅킹 - 이체
	private final int SAVE_FILE = 11;		// 파일 - 저장
	private final int LOAD_FILE = 12;		// 파일 - 로드
	private final int QUIT = 13;			// 종료
	
	public static final Scanner scanner = new Scanner(System.in);
	
	private String brandName;
	private int log; // 로그인 중인 userCode(로그아웃 시 기본 0)
	
	private UserManager userManager;
	private AccountManager accManager;
	private FileManager fileManager;
	
	public Atm(String brandName) {
		this.brandName = brandName;
		this.userManager = UserManager.getInstance();
		this.accManager = AccountManager.getInstance();
		this.fileManager = FileManager.getInstance();
		this.log=0;
	}
	
	private void printMenu() {
		System.out.printf("== %s BANK ==\n", this.brandName);
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 계좌개설");
		System.out.println("6. 계좌철회");
		System.out.println("7. 계좌조회");
		System.out.println("8. 입금");
		System.out.println("9. 출금");
		System.out.println("10. 이체");
		System.out.println("11. 저장");
		System.out.println("12. 로드");
		System.out.println("13. 종료");
		
		if(loginCheck()) {
			System.out.println(getLoginUser().getName()+"님 로그인 중");
		}
	}

	public static int inputNumber(String msg) {
		System.out.print(msg + " : ");
		String input = Atm.scanner.next();

		int number = -1;
		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자 입력만 가능합니다");
		}
		return number;
	}

	private void printAlldata() {
		// 동일한 주소 참조값을 주지 않도록 해야함
		System.out.println("[User List]");
		if(!userManager.getList().isEmpty()) {
			for (User user : userManager.getList()) // getList() <- 클론 생성하여 보여줌
				System.out.println(user); // 각 user는 User 클래스의 오버라이드 된 toString()으로 출력
		}else {
			System.out.println("(유저정보 없음)");
		}
		System.out.println("[Account List]");
		if(!accManager.getList().isEmpty()) {
			for (Account acc : accManager.getList())
				System.out.println(acc);
		}else {
			System.out.println("(계좌정보 없음)");
		}
		System.out.println();

	}
	
	private boolean loginCheck() {
		if(userManager.isLogin(this.log))
			return true;
		return false;
	}
	
	private User getLoginUser() {
		if(loginCheck()) {
			return userManager.getUserByUserCode(this.log);
		}
		return null;
	}
	
	public void run() {
		while(true) {
			printAlldata();	// 검토용
			printMenu();
			int select = inputNumber("메뉴");
			if (select == JOIN) {
				userManager.joinUser();
			} else if (select == LEAVE) {
				this.log = userManager.leaveUser(this.log);
			} else if (select == LOGIN) {
				this.log = userManager.loginUser(this.log);
			} else if (select == LOGOUT) {
				this.log = userManager.logoutUser();
			} else if (select == CREATE_ACC) {
				if (loginCheck()) {
					accManager.createAccount(getLoginUser());
				}else {
					System.out.println("로그인 후 이용 가능한 서비스입니다.");
				}
			} else if (select == DELETE_ACC) {
				if (loginCheck()) {
					accManager.deleteAcc(this.log);
				}else {
					System.out.println("로그인 후 이용 가능한 서비스입니다.");
				}
			}else if(select == VIEW_BALANCE) {
				if(loginCheck()) {
					System.out.println("accManager : "+accManager);
					accManager.viewBalance(this.log);
				}else {
					System.out.println("로그인 후 이용 가능한 시스템입니다.");
				}
			}
//			else if(select == INPUT_MONEY)
//				inputMoney();
//			else if(select == OUT_MONEY)
//				outputMoney();
//			else if(select == MOVE_MONEY)
//				moveMoney();
//			else if(select == SAVE_FILE)
//				saveFile();
//			else if(select == LOAD_FILE)
//				loadFile();
			else if(select == QUIT)
				break;
			System.out.println();
		}
		scanner.close();
	} // run()
	
	
}