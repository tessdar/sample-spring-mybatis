package com.example.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.common.util.MessageProp;
import com.common.util.Status;
import com.example.mapper.SampleMapper;
import com.example.service.impl.SampleServiceImpl;

import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

@ExtendWith(SpringExtension.class)
public class SampleServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SampleServiceTest.class);

	@Mock
	private SampleMapper sampleMapper;

	@InjectMocks
	private SampleService sampleService = new SampleServiceImpl();

	@Test
	public void shouldReturnEmpList() {
		List<EmpListVo> empLists = new ArrayList<>();
		EmpListVo empList = new EmpListVo();
		empLists.add(empList);

		given(sampleMapper.getEmpList(anyLong())).willReturn(empLists);
		assertThat(sampleService.getEmpList(anyLong()), hasSize(1));
	}

	@Test
	public void shouldReturnDelEmpInfoSave() {
		List<EmpSaveVo> empSaves = new ArrayList<>();
		EmpSaveVo empSave = new EmpSaveVo();
		empSave.set_status((long) Status.Delete.getStatus());
		empSave.setEmployeeId(anyLong());
		empSaves.add(empSave);

		given(sampleMapper.delEmp(empSave.getEmployeeId())).willReturn(1);
		try {
			assertThat(sampleService.setEmp(empSaves), equalTo(MessageProp.INFO_SAVE.getMsg()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void shouldReturnNewEmpInfoSave() {
		List<EmpSaveVo> empSaves = new ArrayList<>();
		EmpSaveVo empSave = new EmpSaveVo();
		empSave.set_status((long) Status.New.getStatus());
		empSaves.add(empSave);

		given(sampleMapper.insEmp(empSave)).willReturn(1);
		try {
			assertThat(sampleService.setEmp(empSaves), equalTo(MessageProp.INFO_SAVE.getMsg()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void shouldReturnModifyEmpInfoSave() {
		List<EmpSaveVo> empSaves = new ArrayList<>();
		EmpSaveVo empSave = new EmpSaveVo();
		empSave.set_status((long) Status.Modified.getStatus());
		empSaves.add(empSave);

		given(sampleMapper.setEmp(empSave)).willReturn(1);

		try {
			assertThat(sampleService.setEmp(empSaves), equalTo(MessageProp.INFO_SAVE.getMsg()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void shouldExceptionModifyEmpErrSave() {
		List<EmpSaveVo> empSaves = new ArrayList<>();
		EmpSaveVo empSave = new EmpSaveVo();
		empSave.set_status((long) Status.Modified.getStatus());
		empSaves.add(empSave);

		given(sampleMapper.setEmp(empSave)).willReturn(0);
		assertThrows(Exception.class, () -> { sampleService.setEmp(empSaves); });
	}

	@Test
	public void shouldReturnDepList() {
		List<DepListVo> depLists = new ArrayList<>();
		DepListVo depList = new DepListVo();
		depLists.add(depList);

		given(sampleMapper.getDepList()).willReturn(depLists);
		assertThat(sampleService.getDepList(), hasSize(1));
	}

	@Test
	public void shouldReturnJobList() {
		List<JobListVo> jobLists = new ArrayList<>();
		JobListVo jobList = new JobListVo();
		jobLists.add(jobList);

		given(sampleMapper.getJobList()).willReturn(jobLists);
		assertThat(sampleService.getJobList(), hasSize(1));
	}

	@Test
	public void shouldReturnDepChart() {
		List<DepChartVo> depCharts = new ArrayList<>();
		DepChartVo depChart = new DepChartVo();
		depCharts.add(depChart);

		given(sampleMapper.getDepChart()).willReturn(depCharts);
		assertThat(sampleService.getDepChart(), hasSize(1));
	}

	@Test
	public void shouldReturnJobChart() {
		List<JobChartVo> jobCharts = new ArrayList<>();
		JobChartVo jobChart = new JobChartVo();
		jobCharts.add(jobChart);

		given(sampleMapper.getJobChart()).willReturn(jobCharts);
		assertThat(sampleService.getJobChart(), hasSize(1));
	}

}
