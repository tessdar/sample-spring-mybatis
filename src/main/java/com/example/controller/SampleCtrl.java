package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.common.util.MessageReturn;
import com.common.util.MessageTrans;
import com.example.service.SampleService;
import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

@Tag(name = "Employee", description = "Employee")
@RestController
@RequestMapping({"/api/emp"})
public class SampleCtrl {

	private Map<String, Object> result = new HashMap<>();
	private String msg = null;
	
	@Autowired
	private SampleService service;

	@Autowired
	private MessageTrans messageTrans;

	@Autowired
	private MessageReturn messageReturn;

	@Operation(summary = "Retrieve employee list", description = "Retrieve employee list")
	@GetMapping(path = {"/list"})
	@ResponseBody
	public ResponseEntity<List<EmpListVo>> getEmpList(@RequestParam("departmentId") Long departmentId) {
		
		List<EmpListVo> empLists = service.getEmpList(departmentId);

		return messageReturn.getRestRespList(empLists);
	}

	@Operation(summary = "Save employee info", description = "Save employee info")
	@PostMapping(path = "/save", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> setEmp(@RequestBody List<EmpSaveVo> vos) {
		
		result.clear();
		try {
			msg = service.setEmp(vos);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result = messageTrans.getMapLang(msg);

		return messageReturn.getRestResp(result, msg);
	}

	@Operation(summary = "Retrieve the department list", description = "Retrieve the department list")
	@GetMapping(path = "/dep")
	@ResponseBody
	public ResponseEntity<List<DepListVo>> getDepList() {

		List<DepListVo> depLists = service.getDepList();

		return messageReturn.getRestRespList(depLists);
	}

	@Operation(summary = "Retrieve the job list", description = "Retrieve the job list")
	@GetMapping(path = "/job")
	@ResponseBody
	public ResponseEntity<List<JobListVo>> getJobList() {

		List<JobListVo> jobLists = service.getJobList();

		return messageReturn.getRestRespList(jobLists);
	}

	@Operation(summary = "Retrieve the department chart", description = "Retrieve the department chart")
	@GetMapping(path = "/depChart")
	@ResponseBody
	public ResponseEntity<List<DepChartVo>> getDepChart() {

		List<DepChartVo> depCharts = service.getDepChart();

		return messageReturn.getRestRespList(depCharts);
	}

	@Operation(summary = "Retrieve the job chart", description = "Retrieve the job chart")
	@GetMapping(path = "/jobChart")
	@ResponseBody
	public ResponseEntity<List<JobChartVo>> getJobChart() {

		List<JobChartVo> jobCharts = service.getJobChart();

		return messageReturn.getRestRespList(jobCharts);
	}
	
}
