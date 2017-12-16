

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import top.andnana.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16 0016.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-mvc-servlet.xml"})
public class MVCTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc = null;
    @Before
    public void initMockMVC(){
        System.out.println("1234");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/index").param("pageNumber", "2")).andReturn();
        MockHttpServletRequest request = mvcResult.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码：");
        int[] pages = pageInfo.getNavigatepageNums();
        for (int i = 0; i < pages.length; i++) {
            System.out.println(" " + pages[i]);
        }
        System.out.println("员工数据：");
        List<User> userList = pageInfo.getList();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUsername());
        }
    }
}
