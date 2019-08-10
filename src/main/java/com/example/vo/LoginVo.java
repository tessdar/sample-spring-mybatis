package com.example.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String password;

}
