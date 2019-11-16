package com.supplierconsumer.controller;

import com.supplierconsumer.model.UsersModel;
import com.supplierconsumer.mongoobjects.Users;
import com.supplierconsumer.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

	private UserRepository userRepository;

	public UsersResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/all")
	public List<Users> getAll() {
		return userRepository.findAll();
	}

	@PostMapping("/all")
	public Users saveRequest(@RequestBody UsersModel userModel) {

		List<Users> userList = userRepository.findAll();

		int count = 0;

		if (userList != null && !userList.isEmpty()) {
			count = userList.size();
		}

		Users user1 = new Users(count + 1, userModel.getName(), userModel.getTeamName(), userModel.getSalary());

		return userRepository.save(user1);
	}
}
