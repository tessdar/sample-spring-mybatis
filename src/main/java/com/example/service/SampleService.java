package com.example.service;

import java.util.List;

import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

public interface SampleService {
	
	public void scheRefreshCache();

	public List<EmpListVo> getEmpList(Long departmentId);
	
	public String setEmp(List<EmpSaveVo> vos) throws Exception;
	
	public List<DepListVo> getDepList();
	
	public List<JobListVo> getJobList();
	
	public List<DepChartVo> getDepChart();
	
	public List<JobChartVo> getJobChart();	

}
