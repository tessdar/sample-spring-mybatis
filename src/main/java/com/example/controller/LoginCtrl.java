package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.security.JwtManager;
import com.common.util.MessageReturn;
import com.example.vo.LoginVo;

@RestController
@RequestMapping("/api/auth")
public class LoginCtrl {

	private Map<String, Object> result = new HashMap<>();
	private String msg = null;

	@Autowired
	private JwtManager jwtManager;

	@Autowired
	private MessageReturn messageReturn;

	@PostMapping(path = "/login", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginVo vo) {

		if (vo.getUserId().equals("admin") && vo.getPassword().equals("1234")) {
			result.put("authToken", "Bearer " + jwtManager.createToken());
		} else {
			result.put("authToken", null);
		}

		msg = "INFO_OK";

		return messageReturn.getRestResp(result, msg);
	}

}
