package top.andnana.entity;

import javax.validation.constraints.Pattern;

public class User {
    private Integer id;
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{3,16})|(^[\\u2E80-\\u9FFF]{2,5}$)", message = "用户名在3-16位英文字符，或2-5位中文。（a-zA-Z0-9_-)")
    private String username;
    @Pattern(regexp = "^[a-z0-9_-]{6,18}$", message = "密码格式为(a-z0-9_-)长度为6-18位")
    private String password;
    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "请输入合法的电子邮箱")
    private String email;

    private String gender;

    private Integer deptId;

    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}