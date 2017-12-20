package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.DepartmentMapper;
import top.andnana.entity.Department;

import java.util.List;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    public List<Department> getDepts() {
        List<Department> deptsList = departmentMapper.selectAll();

        return deptsList;
    }

}
