package com.example.demo.controller;

import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.UploadAvatarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 登录请求方法，
     * @param name 用户名
     * @param password 密码
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> Login(@RequestParam("name") String name, @RequestParam("password") String password,
                                      HttpSession session) {
        User user = userService.Login(name,password);
        session.setAttribute("uid",user.getId());
        session.setAttribute("name",name);
        return new ResponseResult<Void>();
    }

    /**
     * 注册方法
     * @param name 用户名
     * @param password 密码
     * @param email 邮箱
     * @param phone 电话
     * @param gender 性别默认男0
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/reg.do",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> register(@RequestParam("name") String name,@RequestParam("password")String password,
                                         String email,String phone,
                                         @RequestParam(value="gender" ,required=false,defaultValue="1") Integer gender,HttpSession session) throws Exception {
        User user = new User(name,password,email,phone,gender);
        userService.reg(user);
        //session.setAttribute("name",name);
        return new ResponseResult<Void>();
    }

    @RequestMapping(value = "/chengePassword.do",method=RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Void> changePassword(@RequestParam("oldPassword") String oldPassword,
                                               @RequestParam("newPassword") String newPassword,HttpSession session){
        // 从session中获取id
        Integer uid = getIdBySession(session);
        userService.changePasswordByOldPassword(uid,oldPassword,newPassword);
        return new ResponseResult<Void>();
    }

    @RequestMapping(value = "/viewUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<User> changeUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer uId = getIdBySession(session);
            ResponseResult<User> rr = new ResponseResult<User>();
            rr.setData(userService.SelectUserById((Integer) uId));
            return rr ;
    }


    public String uploadAvatar(HttpServletRequest request, CommonsMultipartFile avatar) throws UploadAvatarException {
        String uploadDirPath = request.getServletContext().getRealPath("upload");
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        int beginIndex = avatar.getOriginalFilename().lastIndexOf(".");
        String suffix = avatar.getOriginalFilename().substring(beginIndex);
        String fileName = UUID.randomUUID().toString() + suffix;

        File dest = new File(uploadDir,fileName);

        try{
            avatar.transferTo(dest);
            return "upload/" + fileName;
        }catch (IllegalStateException e){
            throw new UploadAvatarException("非法状态");
        }
        catch (IOException e){
            throw new UploadAvatarException("读写异常");
        }

    }



}
