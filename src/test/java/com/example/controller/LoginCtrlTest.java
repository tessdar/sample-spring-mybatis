package com.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.common.security.JwtManager;
import com.common.util.MessageReturn;
import com.example.vo.LoginVo;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.class)
public class LoginCtrlTest {

	private MockMvc mockMvc;

	@Mock
	private JwtManager jwtManager;

	@Mock
	private MessageReturn messageReturn;

	@InjectMocks
	private LoginCtrl loginCtrl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
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
