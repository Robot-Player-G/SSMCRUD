package com.system.controller;

import com.system.domain.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/userRegister")
    public void userRegister(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean b = userService.checkByUserName(user.getUsername());
        if(b==true){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            userService.saveUser(user);
            response.sendRedirect(request.getContextPath()+"/WEB-INF/pages/success.jsp");
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin")
    public String userLogin(User user){
        User user1 = userService.findOneByName(user.getUsername());
        if(user!=null){
            if(user1!=null&&user1.getUsername().equals(user.getUsername())&&user1.getPassword().equals(user.getPassword())){
                return "success";
            }else{
                return "error";
            }
        }
        return "error";
    }
}
