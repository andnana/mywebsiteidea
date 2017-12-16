


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.andnana.dao.DepartmentMapper;
import top.andnana.dao.UserMapper;
import top.andnana.entity.Department;

/**
 * Created by Administrator on 2017/12/16 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper = null;
    @Autowired
    UserMapper userMapper = null;
    @Test
    public void Test01(){
       /* ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        System.out.println("abc");
        System.out.println(departmentMapper);
        System.out.println("def");*/

        System.out.println("---------");
        Department department = new Department();
        department.setDeptName("财务部");
        departmentMapper.insertSelective(department);

        System.out.println("---------");
    }
}
