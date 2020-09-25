package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     * @throws UserNameConflictException
     * @throws InsertDataException
     */
    @Override
    public User reg(User user) throws UserNameConflictException, InsertDataException {
        // 根据用户名查询用户信息
        User result = getUserByUsername(user.getUsername());
        if (result == null){
            insert(user);
            return result;
        }else {
            throw new UserNameConflictException("该用户名已存在");
        }
    }

    /**
     * 跟据用户名查用户信息
     * @param name
     * @return
     */
    public User getUserByUsername(String name){
        return userMapper.selectByName(name);
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public User getUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 将用户密码加密并存入数据库
     * @param user
     * @throws InsertDataException
     */
    public void insert(User user) throws InsertDataException {
        // 密码加密
        String salt = getRandomSalt();
        String md5Password = getEncrpytedPasswrod(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        // 为用户没有提交的属性设置值
        user.setStatus(1);
        user.setIsdelete(0);
        Date now = new Date();
        user.setCreatedTime(now);
        user.setCreatedUser(user.getUsername());
        user.setModifiedTime(now);
        user.setModifiedUser(user.getUsername());
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertDataException("注册时发生未知错误，请联系系统管理员");
        }

    }

    /**
     * 获取加密后的密码
     * @param password
     * @param salt
     * @return
     */
    private String getEncrpytedPasswrod(String password, String salt) {
        String oldPw = md5(password);
        String md5Salt = md5(salt);
        String newPw = oldPw + md5Salt;
        String result = md5(newPw);
        for (int i = 0;i<5;i++) {
            result = md5(result);
        }
        return result;
    }

    /**
     * 使用MD5算法对数据进行加密
     * @param src
     * @return
     */
    private String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes()).toUpperCase();
    }

    /**
     * 获取UUID作为随机盐值
     * @return
     */
    private String getRandomSalt() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    @Override
    public User Login(String name,String password) throws UserNotFoundException, PasswordNotMatchException {
        User user = getUserByUsername(name);
        if (user != null){
            String salt = user.getSalt();
            String md5Pw = getEncrpytedPasswrod(password,salt);
            if (user.getPassword().equals(md5Pw)){
                //登录成功
                user.setPassword(null);
                user.setSalt(null);
                return user;
            }else {
                throw new PasswordNotMatchException("输入密码有误");
            }
        }else {
            throw new UserNotFoundException("用户名" + name + "不存在");
        }
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateDataException
     */
    @Override
    public void changePasswordByOldPassword(Integer id, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateDataException {
        User user = getUserById(id);
        if (user != null) {
            String salt = user.getSalt();
            String md5OldPw = getEncrpytedPasswrod(oldPassword, salt);
            if (user.getPassword().equals(md5OldPw)) {
                String md5NewPw = getEncrpytedPasswrod(newPassword, salt);
                changePassword(id, newPassword);
            } else {
                throw new PasswordNotMatchException("原密码不正确");
            }
        } else {
            throw new UserNotFoundException("尝试访问用户(id="+id+")数据不存在");
        }
    }

    /**
     * 根据用户id查询用户数据
     * @param id
     * @return
     */
    @Override
    public User SelectUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户主键id修改密码
     * @param id
     * @param password
     * @throws UpdateDataException
     */
    @Override
    public void changePassword(Integer id, String password) throws UpdateDataException {
        Integer rows = userMapper.changePassword(id,password);
        if (rows != 1){
            throw new UpdateDataException("修改密码时出现未知错误，请联系系统管理员");
        }
    }

    /**
     * 修改个人信息
     * @param user
     */
    @Override
    public void changeInfo(User user) {
        User result = getUserById(user.getId());
        if (result != null){
            user.setModifiedUser(user.getUsername() == null?result.getUsername():user.getUsername());
            user.setModifiedTime(new Date());
            Integer rows = userMapper.updateByPrimaryKeySelective(user);
            if (rows != 1){
                throw new UpdateDataException("修改用户数据时出现未知错误，请联系系统管理员");
            }
        } else {
            throw new UserNotFoundException("尝试访问的用户数据(id=" + user.getId() + ")不存在！");

        }
    }

    /**
     * 根据用户主键id修改用户非空数据
     * @param user
     * @return
     */
    @Override
    public Integer updataUserInfoById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
