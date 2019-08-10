package com.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.common.util.MessageProp;
import com.common.util.Status;
import com.example.mapper.SampleMapper;
import com.example.service.SampleService;
import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.EmpSaveVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

@Service
@MapperScan("com.example.mapper")
public class SampleServiceImpl implements SampleService {

	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	private SampleMapper sampleMapper;

	/**
	 * Cache Refresh 
	 * 매일 새벽 3시에 실행 
	 */
	@Override
	@CacheEvict(value = { "DepList", "JobList" })
	@Scheduled(cron = "0 0 3 * * ?")
	public void scheRefreshCache() {
		logger.debug("Execute scheRefreshCache");
	}
	
	@Override
	public List<EmpListVo> getEmpList(Long departmentId) {
		return sampleMapper.getEmpList(departmentId);
	}

	@Override
	public String setEmp(List<EmpSaveVo> vos) throws Exception {

		try {

			for (EmpSaveVo vo : vos) {

				if (vo.get_status() == Status.Delete.getStatus()) {

					int delCnt = sampleMapper.delEmp(vo.getEmployeeId());

					if (delCnt < 1) {
						throw new Exception("Error: sampleMapper.delEmp");
					}

				} else if (vo.get_status() == Status.New.getStatus()) {
					int insCnt = sampleMapper.insEmp(vo);
					
					if (insCnt < 1) {
						throw new Exception("Error: sampleMapper.insEmp");
					}

				} else if (vo.get_status() == Status.Modified.getStatus()) {
					int setCnt = sampleMapper.setEmp(vo);
					
					if (setCnt < 1) {
						throw new Exception("Error: sampleMapper.setEmp");
					}
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(MessageProp.ERR_SAVE.getMsg());
		}

		return MessageProp.INFO_SAVE.getMsg();
	}

	@Override
	@Cacheable(value="DepList")
	public List<DepListVo> getDepList() {
		return sampleMapper.getDepList();
	}

	@Override
	@Cacheable(value="JobList")
	public List<JobListVo> getJobList() {
		return sampleMapper.getJobList();
	}

	@Override
	public List<DepChartVo> getDepChart() {
		return sampleMapper.getDepChart();
	}

	@Override
	public List<JobChartVo> getJobChart() {
		return sampleMapper.getJobChart();
	}

}
