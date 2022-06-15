package com.example.blob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blob.repositories.UserRepo;

@SpringBootTest
class BlobAppApiApplicationTests {

	@Autowired
	private UserRepo user;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest()
	{
		String name = this.user.getClass().getName();
		Package package1 = this.user.getClass().getPackage();
		System.out.println(name + "--------------");
		System.out.println(package1 +"+++++++++++++");
	}

}
