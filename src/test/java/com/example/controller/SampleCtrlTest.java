package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.common.util.MessageReturn;
import com.common.util.MessageTrans;
import com.common.util.Status;
import com.example.service.SampleService;
import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;
import com.google.gson.Gson;

@RunWith(MockitoJUnitRunner.class)
public class SampleCtrlTest {

	private MockMvc mockMvc;

	@Mock
	private SampleService service;

	@Mock
	private MessageTrans messageTrans;

	@Mock
	private MessageReturn messageReturn;

	@InjectMocks
	private SampleCtrl sampleCtrl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(sampleCtrl).build();
	}

	@Test
	public void givenDepartmentIdWhenGetEmpList() throws Exception {
		List<EmpListVo> empLists = new ArrayList<>();
		EmpListVo empList = new EmpListVo();
		empList.setEmployeeId(50L);
		empLists.add(empList);

		given(service.getEmpList(anyLong())).willReturn(empLists);
		given(messageReturn.getRestRespList(empLists)).willReturn(new ResponseEntity<List<EmpListVo>>(empLists, HttpStatus.OK));

		mockMvc.perform(get("/api/emp/list").param("departmentId", "50").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void givenEmployeeDataWhenPostEmpSave() throws Exception {
		List<EmpSaveVo> empSaveVos = new ArrayList<>();
		EmpSaveVo empSaveVo = new EmpSaveVo();
		empSaveVo.setEmployeeId(100L);
		empSaveVo.set_status((long) Status.Modified.getStatus());
		empSaveVos.add(empSaveVo);

		Gson gson = new Gson();
		String element = gson.toJson(empSaveVos);

		String msg = "Test";
		
		Map<String, Object> result = new HashMap<>();
		result.put("message", msg);	

//		given(service.setEmp(empSaveVos)).willThrow(new Exception());
//		given(messageTrans.getMapLang(msg)).willReturn(result);
//		given(messageReturn.getRestResp(any(), anyString()))
//		.willReturn(new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK));
		
		mockMvc.perform(post("/api/emp/save")
				.content(element)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void givenNoneWhenGetDepList() throws Exception {
		List<DepListVo> depLists = new ArrayList<>();
		DepListVo depList = new DepListVo();
		depList.setDepartmentId(100L);
		depLists.add(depList);
		
		given(service.getDepList()).willReturn(depLists);
		given(messageReturn.getRestRespList(depLists)).willReturn(new ResponseEntity<List<DepListVo>>(depLists, HttpStatus.OK));
		
		mockMvc.perform(get("/api/emp/dep").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void givenNoneWhenGetJobList() throws Exception {
		List<JobListVo> jobLists = new ArrayList<>();
		JobListVo jobList = new JobListVo();
		jobList.setJobId("Test");
		jobLists.add(jobList);
		
		given(service.getJobList()).willReturn(jobLists);
		given(messageReturn.getRestRespList(jobLists)).willReturn(new ResponseEntity<List<JobListVo>>(jobLists, HttpStatus.OK));
		
		mockMvc.perform(get("/api/emp/job").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void givenNoneWhenGetDepChart() throws Exception {
		List<DepChartVo> depCharts = new ArrayList<>();
		DepChartVo depChart = new DepChartVo();
		depChart.setDepartmentId(100L);
		depCharts.add(depChart);
		
		given(service.getDepChart()).willReturn(depCharts);
		given(messageReturn.getRestRespList(depCharts)).willReturn(new ResponseEntity<List<DepChartVo>>(depCharts, HttpStatus.OK));
		
		mockMvc.perform(get("/api/emp/depChart").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void givenNoneWhenGetJoChart() throws Exception {
		List<JobChartVo> jobCharts = new ArrayList<>();
		JobChartVo jobChart = new JobChartVo();
		jobChart.setJobId("Test");
		jobCharts.add(jobChart);
		
		given(service.getJobChart()).willReturn(jobCharts);
		given(messageReturn.getRestRespList(jobCharts)).willReturn(new ResponseEntity<List<JobChartVo>>(jobCharts, HttpStatus.OK));
		
		mockMvc.perform(get("/api/emp/jobChart").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
	}

}
