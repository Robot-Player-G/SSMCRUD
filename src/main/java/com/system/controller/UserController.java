package com.system.controller;

import com.system.domain.*;
import com.system.service.AdminService;
import com.system.service.UserService;
import com.system.utils.CodeCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CodeCheck codeCheck;
    //这是一个跳转到userCenter时传值的关键一步
    private String userFlag = null;

    //管理员业务层实例
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test")
    @ResponseBody
    public String testInterface(){
        return "success111";
    }
    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/userRegister")
    public String userRegister(String username, String password, HttpServletRequest request, HttpSession session,HttpServletResponse response) throws Exception {
        String userCode = request.getParameter("userCode");
        String randomCode = (String)session.getAttribute("randomCode");
        User user1 = userService.findOneByName(username);
        String codeCheckResult = codeCheck.codeCheck(randomCode,userCode);
        if(user1!=null||username==null){
            return "error";
        }
        else if(codeCheckResult.equals("success")&&
                password.equals(request.getParameter("repassword"))){
            userService.saveUser(username,password);
            userService.saveUserInfo(username,"nickname","男",new Date());
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
        User userInfo = userService.findOneByName(user.getUsername());
        User user1 = userService.findUserByName(user.getUsername());
        String userCode = request.getParameter("userCode");
        String randomCode = (String)session.getAttribute("randomCode");
        System.out.println(user);
        if (user.getUsername()==""||user.getPassword()==""){
            mv.addObject("user","0");
            mv.setViewName("error");
            return mv;
        }else if (userCode==""){
            mv.addObject("code","0");
            mv.setViewName("error");
            return mv;
        }else if (user1==null){
            mv.addObject("user","1");
            mv.setViewName("error");
            return mv;
        }else {
            String codeCheckResult = codeCheck.codeCheck(randomCode,userCode);
            if(user1.getUsername().equals(user.getUsername())&&
                    user1.getPassword().equals(user.getPassword())&&
                    codeCheckResult.equals("success")&&user1!=null) {
                userInfo.setPassword(null);
                mv.addObject("user",userInfo);
                mv.setViewName("success");
                return mv;
            }else{
                mv.addObject("check","0");
                mv.setViewName("error");
                return mv;
            }
        }
    }

    /**
     * 管理员登录
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.PUT)
    public String adminLogin(User user,HttpServletRequest request,HttpSession session){
        Admin admin = new Admin();
        admin.setAdmin(user.getUsername());
        admin.setAdminpswd(user.getPassword());
        String userCode = request.getParameter("userCode");
        String randomCode = (String)session.getAttribute("randomCode");
        String codeCheckResult = codeCheck.codeCheck(randomCode,userCode);
        if (adminService.checkAdmin(admin)&&codeCheckResult.equals("success")){
            return "redirect:/jump/adminHomePage";
        }else {
            return "redirect:/jump/adminError";
        }
    }

    /**
     * 用户中心
     */
    @RequestMapping(value = "/userCenter")
    public ModelAndView userCenter(String username){
        ModelAndView mv = new ModelAndView();
//        if (username==null){
        mv.addObject("username",username);
        mv.addObject("focus_flag","userInfo");
        mv.setViewName("userCenter");
//            userFlag = null;
        return mv;
//        }else {
//            userFlag = username;
//            return mv;
//        }
    }

    /**
     * 通过username获取用户信息
     * @param username
     * @return
     */
    @RequestMapping("/getUserInfoByUsername")
    @ResponseBody
    public User getUserInfoByUsername(String username){
        User user = userService.findOneByName(username);
        return user;
    }

    /**
     * 修改个人信息
     * @param user
     */
    @RequestMapping(value = "/modifyUserInfo")
    public ModelAndView modifyUserInfo(User user){
        ModelAndView mv = new ModelAndView();
        userService.modifyUserInfo(user);
        mv.addObject("username",user.getUsername());
        mv.addObject("focus_flag","userInfo");
        mv.setViewName("userCenter");
        return mv;
    }

    /**
     * 校验用户是否已存在
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(String username){

        if(userService.isExist(username)){
            return "1";
        }
        else {
            return "0";
        }
    }

    /**
     * 历史交易记录
     * @return
     */
    @RequestMapping("/historyDeal")
    public ModelAndView historyDeal(String username){
        ModelAndView mv = new ModelAndView();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        if (username==null){
        mv.addObject("username",username);
        mv.addObject("focus_flag","userDeal");
        mv.setViewName("userCenter");
//            userFlag = null;
            return mv;
//        }else {
//            userFlag = username;
//            Date date = new Date();
//            date = userFlag.getBirthday();
//            String timeFormat = sdf.format(date);
//            userFlag.setBirthday(date);
//            return mv;
//        }
    }

    /**
     * 通过username获取管理员留言
     * @param username
     * @return
     */
    @RequestMapping("/getAMessageByUsername")
    @ResponseBody
    public List<Message> getAMessageByUsername(String username){
        List<Message> adminMessage = userService.getAMessageByUsername(username);
        return adminMessage;
    }

    /**
     * 用户给管理员留言
     * @return
     */
    @RequestMapping("/sendMessageToAdmin")
    @ResponseBody
    public String sendMessageToAdmin(String username,String content){
        Message userMessage = new Message();
        userMessage.setFrom(username);
        userMessage.setTo("admin");
        userMessage.setContent(content);
        userMessage.setTime(new Date());
        userMessage.setType(0);
        System.out.println(userMessage);
        userService.sendMessageToAdmin(userMessage);
        return "success";
    }

    /**
     * 用户获取留言
     * @param userA
     * @param userB
     * @return
     */
    @RequestMapping("/getMessage")
    @ResponseBody
    public List<Message> getMessage(String userA,String userB){
        List<Message> messages = userService.getMessage(userA,userB);
        return messages;
    }

    /**
     * 用户给用户发送留言
     * @param userA
     * @param userB
     * @param content
     * @return
     */
    @RequestMapping("/leaveMessage")
    @ResponseBody
    public String leaveMessage(String userA,String userB,String content){
        userService.leaveMessage(userA,userB,content);
        return "success";
    }

    /**
     * 管理员获取所有用户信息
     * @return
     */
    @RequestMapping("/adminGetAllUser")
    @ResponseBody
    public Map adminGetAllUser(){
        Map userMap = new HashMap();
        Integer count = adminService.getAllUserCount();
        List<User> list = adminService.getAllUser();
        userMap.put("count",count);
        userMap.put("List",list);
        return userMap;
    }

    /**
     * 管理员获取充值记录
     * @return
     */
    @RequestMapping("/adminGetRechargeRecord")
    @ResponseBody
    public Map adminGetRechargeRecord(){
        Map rechargeMap = new HashMap();
        Integer count = adminService.getRechargeRecordCount();
        List<RechargeRecord> list = adminService.getRechargeRecord();
        rechargeMap.put("count",count);
        rechargeMap.put("List",list);
        return rechargeMap;
    }

    /**
     * 管理员删除用户
     * @param username
     * @return
     */
    @RequestMapping("/adminDeleteUser")
    @ResponseBody
    public String adminDeleteUser(String username){
        if (adminService.deleteUserByName(username))
        {
            return "success";
        }
        return "failed";
    }

    /**
     * 管理员删除用户充值记录
     * @param recharge_id
     * @return
     */
    @RequestMapping("/adminDeleteRechargeRecord")
    @ResponseBody
    public String adminDeleteRechargeRecord(String recharge_id){
        if (adminService.deleteRechargeById(recharge_id))
        {
            return "success";
        }
        return "failed";
    }

    /**
     * 管理员获取所有用户的留言
     * @return
     */
    @RequestMapping("/getAdminMessage")
    @ResponseBody
    public List<Message> getAdminMessage(){
        List<Message> userMessage = adminService.getAdminMessage();
        return userMessage;
    }

    /**
     * 管理员发送留言
     * @param to
     * @param content
     * @return
     */
    @RequestMapping("/adminSendMessage")
    @ResponseBody
    public String adminSendMessage(String to,String content){
        adminService.adminSendMessage(to,content);
        return "success";
    }

    /**
     * 用户退出
     */
    @RequestMapping("/userSignOut")
    public String userSignOut(){
        return "redirect:/";
    }
}
