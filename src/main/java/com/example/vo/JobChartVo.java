package com.example.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class JobChartVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jobId;
	
	private String jobTitle; 
	
	private Long jobCnt;
	
}
