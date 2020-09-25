package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.service.ex.*;

public interface IUserService {
    /**
     * 用户注册
     * @param user
     * @return
     * @throws UserNotFoundException
     */
    User reg(User user) throws UserNameConflictException,InsertDataException;

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    User Login(String name,String password) throws UserNotFoundException, PasswordNotMatchException;

    /**
     * 通过验证原密码来修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateDataException
     */
    void changePasswordByOldPassword(Integer id,String oldPassword,String newPassword) throws UserNotFoundException,PasswordNotMatchException, UpdateDataException;

    /**
     * 插入用户数据
     * @param user
     * @throws InsertDataException
     */
    void insert(User user) throws InsertDataException;

    /**
     * 根据用户名查询用户数据
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User SelectUserById(Integer id);

    /**
     * 修改密码
     * @param id
     * @param password
     * @throws UpdateDataException
     */
    void changePassword(Integer id,String password) throws UpdateDataException;

    /**
     * 修改个人信息
     * @param user
     */
    void changeInfo(User user);

    /**
     * 修改用户信息根据用户id
     * @param user
     * @return
     */
    Integer updataUserInfoById (User user);







}
