package top.andnana.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.andnana.entity.Category;
import top.andnana.entity.CategoryExample;
import top.andnana.entity.Job;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    public List<Category> selectAll();

    public Category selectByCategoryName(String categoryName);

    public void insertCategory(Category category);
}