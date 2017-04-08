package com.rockies.webservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rockies.webservice.model.User;
import com.rockies.webservice.service.IUserService;

@RestController
public class UsersController {

	@Autowired
	private IUserService userService;

	/**
	 * 查询 所有users
	 * 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * 创建新 user
	 * 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody User saveUser(@RequestBody User user) {
		System.out.println(user.getEmail());
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		userService.saveUser(user);
		return user;
	}

	/**
	 * 通过id查询 user
	 * 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/user/{id:\\d+}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		if (id > 0) {
			return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	
	/**
	 * 多条件查询 users
	 * 
	 * @param id
	 * @param userName
	 * @param email
	 * @param mobilePhone
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/searchUsers")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) final Long id,
			@RequestParam(required = false) final String userName, @RequestParam(required = false) final String email,
			@RequestParam(required = false) final String mobilePhone) {

		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setEmail(email);
		user.setMobilePhone(mobilePhone);
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);

	}

	/**
	 * 更改 user
	 * 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody User updateUser(@RequestBody User user) {
		user.setUpdateDate(new Date());
		userService.modifyUser(user);
		return user;
	}

	/**
	 * 删除 user
	 * 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody User deleteUser(@RequestBody User user) {
		userService.deleteByPrimaryKey(user.getId());
		return user;
	}

}
