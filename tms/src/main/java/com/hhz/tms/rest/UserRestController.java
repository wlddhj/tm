/**
 * 
 */
package com.hhz.tms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhz.tms.admin.service.sys.UserService;
import com.hhz.tms.entity.sys.User;

/**
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserRestController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "login")
	@ResponseBody
	public Boolean login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
		boolean flag = false;
		flag = userService.validate(loginName, password);
		return flag;
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		User user = userService.getEntity(id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

}
