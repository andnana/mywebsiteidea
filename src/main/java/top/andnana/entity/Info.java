package top.andnana.entity;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class Info {
    private Integer id;
    @Pattern(regexp =  "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)", message = "标题在2-50位中英文字符（a-zA-Z0-9_-)")
    private String title;

    private String picName;

    private Date infoDate;

    private String content;

    private Category category;

    private List<Label> labelList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picName='" + picName + '\'' +
                ", infoDate=" + infoDate +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", labelList=" + labelList +
                '}';
    }
}