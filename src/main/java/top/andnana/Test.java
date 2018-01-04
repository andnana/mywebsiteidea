package top.andnana;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import top.andnana.dao.UserDao;
import top.andnana.entity.User;

public class Test {
public static void main(String[] args) {
	try {
		SqlSession sqlSession = top.andnana.util.Util.openSession();
//		User user = sqlSession.selectOne("top.andnana.User.selectUser", "renjie");
//		System.out.println("user" + user);
//		sqlSession.close();
//		UserDao userDao = sqlSession.getMapper(UserDao.class);
//		User user2 = userDao.selectUser("renjie");
//		System.out.println("user2" + user2);
//		User user3 = new User();
//		user3.setPassword("123");
//		user3.setUsername("username123");
//		userDao.insertUser(user3);
//		user2.setUsername("username456");
//		
//		userDao.updateUser(user2);
//		userDao.deleteUser(3);
//		User user4 = userDao.selectUserByUsername("renjie");
//		System.out.println(user4);
		sqlSession.close();
	} catch (IOException e) {
		
	
		e.printStackTrace();
	}
	
}
}
