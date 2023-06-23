package atm;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<User> test = new ArrayList<>();
		
		test.add(new User(123,"이름1","아이디1","비밀번호"));
		test.add(new User(124,"이름2","아이디2","비밀번호"));
		test.add(new User(125,"이름3","아이디","비밀번호"));
		
		test.remove(test.get(0));
		
		System.out.println(test);
	}

}
