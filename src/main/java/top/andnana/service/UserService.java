package top.andnana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import top.andnana.dao.UserMapper;
import top.andnana.entity.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public List<User> selectAll(){
		return userMapper.selectAll();
	}
	public User findUserByName(String username){
		System.out.println("12341234" + username);
		 User user = null;
		try {
			user = userMapper.selectUserByUsername(username);
			System.out.println("7890" + user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
