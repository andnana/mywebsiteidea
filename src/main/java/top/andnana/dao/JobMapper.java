package top.andnana.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.andnana.entity.Job;
import top.andnana.entity.JobExample;

public interface JobMapper {
    long countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    public List<Job> selectAll();

    public Job selectByJobname(String jobname);

    public void insertJob(Job job);
}