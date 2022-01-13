package com.example.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepListVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long departmentId;
	
	private String departmentName; 
	
	private Long managerId;
	
	private Long locationId;
	
}
