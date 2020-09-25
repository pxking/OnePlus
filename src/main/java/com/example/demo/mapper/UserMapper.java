package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Mapper
public interface UserMapper {
    /**
     * 根据用户主键id删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户数据
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 插入不为空的用户数据
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据用户主键id查询用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据用户主键id修改用户信息中不为空的数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据用户主键id修改用户信息全部修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询是否存在用户
     * @param username
     * @return
     */
    User selectByName(@Param("username")String username);

    /**
     * 根据用户主键id修改密码
     * @param id
     * @param password
     * @return
     */
    Integer changePassword(@Param("id") Integer id,@Param("passowrd") String password);
}