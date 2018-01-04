package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.CategoryMapper;
import top.andnana.dao.JobMapper;
import top.andnana.entity.Category;
import top.andnana.entity.CategoryExample;
import top.andnana.entity.Job;
import top.andnana.entity.JobExample;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getCategory() {
        List<Category> categoryList = categoryMapper.selectAll();
        return categoryList;
    }

    public Category getCategoryByName(String categoryName) {
        Category category = categoryMapper.selectByCategoryName(categoryName);
        return category;
    }

    public void addCategory(Category category) {

        categoryMapper.insertCategory(category);
    }

    public void deleteByPrimaryKey(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(List<Integer> ids) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andIdIn(ids);
        categoryMapper.deleteByExample(categoryExample);
    }
    public Category selectByPrimaryKey(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }
}
