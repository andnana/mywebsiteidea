package top.andnana.entity;

import javax.validation.constraints.Pattern;

public class Category {
    private Integer id;
    @Pattern(regexp = "(^[\\u2E80-\\u9FFFa-zA-Z0-9_-]{2,50}$)", message = "分类名称在2-50位中英文字符（a-zA-Z0-9_-)")
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}