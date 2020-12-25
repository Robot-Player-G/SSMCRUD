package com.system.controller;

import com.system.domain.User;
import com.system.service.UserService;
import com.system.utils.CodeCheck;
import com.system.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CodeCheck codeCheck;
    //这是一个跳转到userCenter时传值的关键一步
    private User userFlag = null;

    /**
     * 用户注册跳转页面
     * @return
     */
    @RequestMapping(value = "/toUserRegister")
    public String userRegisterTo(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "register";
    }

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/userRegister")
    public String userRegister(User user, HttpServletRequest request, HttpSession session,HttpServletResponse response) throws Exception {
        String userCode = request.getParameter("userCode");
        String randomCode = (String)session.getAttribute("randomCode");
        User user1 = userService.findOneByName(user.getUsername());
        String codeCheckResult =(String)codeCheck.codeCheck(randomCode,userCode);
        if(user1!=null&&user==null){
            return "error";
        }
        else if(codeCheckResult.equals("success")&&
                user.getPassword().equals(request.getParameter("repassword"))){
            userService.saveUser(user);
            response.getWriter().print("<script>alert('注册成功!');</script>");
            return "redirect:/index.jsp?param=1";
        }else {
            return "error";
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ModelAndView userLogin(User user, HttpServletRequest request, HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user.getUsername());
        String userCode = request.getParameter("userCode");
        String randomCode = (String)session.getAttribute("randomCode");
        User user1 = userService.findOneByName(user.getUsername());
        System.out.println(userCode+" "+randomCode+" "+user.getUsername()+" "+user.getPassword());
        String codeCheckResult =(String)codeCheck.codeCheck(randomCode,userCode);
        if(user!=null){
            if(user1.getUsername().equals(user.getUsername())&&
               user1.getPassword().equals(user.getPassword())&&
               codeCheckResult.equals("success")&&user1!=null) {
                   mv.setViewName("success");
                   return mv;
            }else{
                   mv.setViewName("error");
                   return mv;
            }
        }else {
            mv.setViewName("error");
            return mv;
        }
    }

    /**
     * 用户中心
     */
    @RequestMapping(value = "/userCenter")
    public ModelAndView userCenter(String username){
        ModelAndView mv = new ModelAndView();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (username==null){
            mv.addObject("user",userFlag);
            mv.setViewName("userCenter");
            return mv;
        }else {
            userFlag = userService.findOneByName(username);
            Date date = new Date();
            date = userFlag.getBirthday();
            String timeFormat = sdf.format(date);
            userFlag.setBirthday(date);
            System.out.println(timeFormat);
            return mv;
        }
    }

    /**
     * 修改个人信息
     * @param user
     */
    @RequestMapping(value = "/modifyUserInfo")
    public ModelAndView modifyUserInfo(User user){
        ModelAndView mv = new ModelAndView();
        System.out.println(user);
        mv.addObject("user",user);
        mv.setViewName("userCenter");
        return mv;
    }

    /**
     * 校验用户是否已存在
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(String username){
        User user = userService.findOneByName(username);
        if(user!=null){
            return "1";
        }
        else {
            return "0";
        }
    }

    /**
     * 用户退出
     */
    @RequestMapping("/userSignOut")
    public String userSignOut(){
        return "redirect:/index.jsp";
    }
    /**
     * 测试通道
     */
    @RequestMapping(value = "/Test")
    public ModelAndView Test(){
        ModelAndView mv =new ModelAndView();
        String username = "123";
        mv.addObject("user",username);
        mv.setViewName("success");
        return mv;
    }
}
