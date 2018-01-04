package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.DepartmentMapper;
import top.andnana.dao.JobMapper;
import top.andnana.entity.Department;
import top.andnana.entity.Job;
import top.andnana.entity.JobExample;
import top.andnana.entity.UserExample;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Service
public class JobService {
    @Autowired
    private JobMapper jobMapper;

    public List<Job> getJobs() {
        List<Job> jobList = jobMapper.selectAll();
        return jobList;
    }

    public Job getJobByName(String jobname) {
        Job job = jobMapper.selectByJobname(jobname);
        return job;
    }

    public void addJob(Job job) {

        jobMapper.insertJob(job);
    }

    public void deleteByPrimaryKey(Integer id) {
        jobMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(List<Integer> ids) {
        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria = jobExample.createCriteria();
        criteria.andIdIn(ids);
        jobMapper.deleteByExample(jobExample);
    }
}
