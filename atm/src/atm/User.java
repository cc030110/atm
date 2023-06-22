package atm;

import java.util.ArrayList;

//R - 필수 / U - 수정가능

public class User {
	private int userCode;			 // R
	private String name;			 // R
	private String id; 				 // R
	private String password;		 // R U
	private int age; 				 // U
	private ArrayList<Account> accs; // U

	public User(int userCode, String name, String id, String password) {
		this.accs = new ArrayList<Account>();

		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
	}

	public User(int userCode, String name, String id, String password, int age) {
		this.accs = new ArrayList<Account>();

		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
	}

	public User(int userCode, String name, String id, String password, int age, ArrayList<Account> accs) {
		this.accs = new ArrayList<Account>();

		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
		this.accs = accs;
	}

	public int getUserCode() {
		return this.userCode;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// 조회만가능한 용도
	public ArrayList<Account> getAccs() {
		// clone() : 복제본 제공
		return (ArrayList<Account>) this.accs.clone();
	}

	public void setAccs(ArrayList<Account> accs) {
		this.accs = accs;
	}

	@Override
	public String toString() {
		// 회원 정보 출력
		// 이름(코드) : 아이디/비밀번호
		String str = String.format("%s(%d) : %s/%s", this.name, this.userCode, this.id, this.password);
		// 계좌 상황
		for (int i = 0; i < this.accs.size(); i++) {
			str += "\n" + this.accs.get(i);
		}
		return str;
	}

	/* toString()
	 * name(userCode) : id/password
	 * ㄴ accNumber1(password) : balance1
	 * ㄴ accNumber2(password) : balance2
	 * ㄴ accNumber3(password) : balance3
	 * */

}