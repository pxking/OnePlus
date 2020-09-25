package com.example.demo.controller;

import com.example.demo.entity.ResponseResult;
import com.example.demo.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public abstract class BaseController {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Void> HandleException(Exception e) {
        //判断异常类型
        if (e instanceof UserNameConflictException) {
            // 用户名冲突 例如注册时用户名被占用
            return new ResponseResult<Void>(401, e);
        } else if (e instanceof UserNotFoundException) {
            // 尝试访问数据不存在
            return new ResponseResult<Void>(402, e);
        } else if (e instanceof PasswordNotMatchException) {
            // 验证密码时密码不匹配
            return new ResponseResult<Void>(403,e);
        } else if(e instanceof InsertDataException) {
            // 增加数据时异常，原因未知
            return new ResponseResult<Void>(501,e);
        } else if(e instanceof UpdateDataException) {
            // 修改数据时，原因未知
            return new ResponseResult<Void>(502,e);
        }else {
            // 其它可能遗漏没有处理的异常
            return new ResponseResult<Void>(600, e);
        }

    }

    /**
     * 获取当前session中uid数据
     * @param session
     * @return
     */
    public Integer getIdBySession(HttpSession session){
        return Integer.valueOf(
                session.getAttribute("uid").toString()
        );
    }
}
