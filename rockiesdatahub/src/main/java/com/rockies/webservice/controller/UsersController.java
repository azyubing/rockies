package com.rockies.webservice.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rockies.webservice.model.User;
import com.rockies.webservice.service.IUserService;

@RestController
public class UsersController {
	
	@Autowired
	private IUserService userService;
	
    private static final String SUCCESS = "success";
    
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST,consumes = "application/json")
    public @ResponseBody User saveUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        userService.saveUser(user);
        return user;
    }
	
	
    @RequestMapping(value = "/restPost", method = RequestMethod.POST)
    public String restPost(@RequestParam(value = "id") Integer id) {
        System.out.println("POST ID:" + id);
        return SUCCESS;
    }
	
}
