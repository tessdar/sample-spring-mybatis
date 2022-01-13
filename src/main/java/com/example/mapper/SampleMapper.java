package com.example.mapper;

import java.util.List;

import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

public interface SampleMapper {

	public List<EmpListVo> getEmpList(Long departmentId);
	
	public int setEmp(EmpSaveVo empSaveVo);
	
	public int delEmp(Long employeeId);
	
	public int insEmp(EmpSaveVo empSaveVo);
	
	public List<DepListVo> getDepList();
	
	public List<JobListVo> getJobList();
	
	public List<DepChartVo> getDepChart();
	
	public List<JobChartVo> getJobChart();
	
}
