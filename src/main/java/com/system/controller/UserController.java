package com.system.controller;

import com.system.domain.User;
import com.system.service.UserService;
import com.system.utils.CodeCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            mv.addObject("focus_flag","userInfo");
            mv.setViewName("userCenter");
            return mv;
        }else {
            userFlag = userService.findOneByName(username);
            Date date = new Date();
            date = userFlag.getBirthday();
            String timeFormat = sdf.format(date);
            userFlag.setBirthday(date);
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
     * 历史交易记录
     * @return
     */
    @RequestMapping("/historyDeal")
    public ModelAndView historyDeal(String username){
        ModelAndView mv = new ModelAndView();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (username==null){
            mv.addObject("user",userFlag);
            mv.addObject("focus_flag","userDeal");
            mv.setViewName("userCenter");
            return mv;
        }else {
            userFlag = userService.findOneByName(username);
            Date date = new Date();
            date = userFlag.getBirthday();
            String timeFormat = sdf.format(date);
            userFlag.setBirthday(date);
            return mv;
        }
    }

    /**
     * 用户退出
     */
    @RequestMapping("/userSignOut")
    public String userSignOut(){
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/modifyUserImage", method = RequestMethod.POST)
    @ResponseBody
    // 这里的MultipartFile对象变量名跟表单中的file类型的input标签的name相同，所以框架会自动用MultipartFile对象来接收上传过来的文件，当然也可以使用@RequestParam("img")指定其对应的参数名称
    public String modifyUserImage(MultipartFile userImage, HttpServletRequest request)
            throws Exception {
        System.out.println(userImage.getOriginalFilename());
        // 如果没有文件上传，MultipartFile也不会为null，可以通过调用getSize()方法获取文件的大小来判断是否有上传文件
        if (userImage.getSize() > 0) {
            System.out.println("1");
            // 得到项目在服务器的真实根路径，如：/home/tomcat/webapp/项目名/images
//       String path = session.getServletContext().getRealPath("/");
            String path=request.getSession().getServletContext().getRealPath("/static/images/user");
            String path1 = "D:\\ProjectWorkspace\\MavenProjSpace\\ssm_information_management_system\\src\\main\\webapp\\static\\images\\user";
            // 得到文件的原始名称，如：美女.png
            String fileName = "123.jpg";
            // 通过文件的原始名称，可以对上传文件类型做限制，如：只能上传jpg和png的图片文件
            if (fileName.endsWith("jpg") || fileName.endsWith("png") || fileName.endsWith("txt")) {
                System.out.println("2");
                System.out.println(path+fileName);
                File file = new File(path1, fileName);
                System.out.println(file);
                userImage.transferTo(file);
                System.out.println("2.2");
                return "success";
            }
            System.out.println("2.3");
        }
        System.out.println("3");
        return "error";
    }
}
