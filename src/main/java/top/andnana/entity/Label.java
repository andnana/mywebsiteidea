package top.andnana.entity;

import javax.validation.constraints.Pattern;

public class Label {
    private Integer id;
    @Pattern(regexp = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)", message = "标签名称在2-50位中英文字符（a-zA-Z0-9_-)")
    private String labelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }
}