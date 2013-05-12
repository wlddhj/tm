package com.hhz.tms.data;

import org.springside.modules.test.data.RandomData;

import com.hhz.tms.entity.sys.User;

public class UserData {

	public static User randomNewUser() {
		User user = new User();
		user.setLoginName(RandomData.randomName("user"));
		user.setName(RandomData.randomName("User"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}
}
