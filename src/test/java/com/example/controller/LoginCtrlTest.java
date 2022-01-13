package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.common.security.JwtManager;
import com.common.util.MessageReturn;
import com.example.vo.LoginVo;
import com.google.gson.Gson;

public class LoginCtrlTest {

	private MockMvc mockMvc;

	@Mock
	private JwtManager jwtManager;

	@Mock
	private MessageReturn messageReturn;

	@InjectMocks
	private LoginCtrl loginCtrl;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(loginCtrl).build();
	}

	@Test
	public void givenIdPasswordwhenGetToken() throws Exception {
		LoginVo login = new LoginVo();
		login.setUserId("admin");
		login.setPassword("1234");

		Gson gson = new Gson();
		String element = gson.toJson(login);

		Map<String, Object> result = new HashMap<>();
		result.put("authToken", "Bearer 12345");
		result.put("isFalse", false);

		when(messageReturn.getRestResp(any(), anyString()))
				.thenReturn(new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK));

		mockMvc.perform(post("/api/auth/login")
				.content(element)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}
