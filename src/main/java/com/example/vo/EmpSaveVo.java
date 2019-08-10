package com.example.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmpSaveVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long employeeId;

	private String firstName;

	private String lastName;

	private String phoneNumber;
	
	private String jobId;
	
	private Long departmentId;
	
	private Long managerId;
	
	private String email;
	
	private Long _status;
	
}
