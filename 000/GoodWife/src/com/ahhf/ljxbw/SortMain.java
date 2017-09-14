package com.ahhf.ljxbw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SortMain {

	@Test
	public void test01() {
		User u1 = new User();
		u1.setAge(10);
		u1.setMark(100);
		u1.setUserName("u1");
		User u2 = new User();
		u2.setAge(11);
		u2.setMark(100);
		u2.setUserName("u2");
		User u3 = new User();
		u3.setAge(10);
		u3.setMark(98);
		u3.setUserName("u3");

		List<User> ulist = new ArrayList<User>();
		ulist.add(u1);
		ulist.add(u2);
		ulist.add(u3);
		Collections.sort(ulist);
		for (User user : ulist) {
			System.err.println(user.toString());
		}

	}

	

}
