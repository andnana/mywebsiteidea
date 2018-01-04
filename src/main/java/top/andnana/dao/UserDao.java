package top.andnana.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.andnana.entity.User;


public interface UserDao {
    public User selectUser(String username);

    public List<User> selectAll();

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(Integer id);

    public User selectUserByUsername(@Param("username") String username);

}
