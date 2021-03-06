package top.andnana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import top.andnana.dao.UserMapper;
import top.andnana.entity.User;
import top.andnana.entity.UserExample;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User findUserByName(String username) {
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

    public boolean checkUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUser(Integer id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);

    }

    public void deleteByPrimaryKey(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(List<Integer> ids) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdIn(ids);
        userMapper.deleteByExample(userExample);
    }
}
