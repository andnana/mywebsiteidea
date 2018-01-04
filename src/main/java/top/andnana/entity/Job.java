package top.andnana.entity;

import javax.validation.constraints.Pattern;

public class Job {
    private Integer id;
    @Pattern(regexp = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)", message = "工作名称在2-50位中英文字符（a-zA-Z0-9_-)")
    private String jobName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }
}