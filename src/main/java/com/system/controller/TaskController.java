package com.system.controller;

import com.system.domain.DealRecord;
import com.system.domain.TaskList;
import com.system.service.AdminService;
import com.system.service.TaskService;
import com.system.utils.RandomTaskId;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务信息控制层
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RandomTaskId taskId;
    //task_id传值
    private String gotoTaskInfoFlag = null;
    //username传值
    private String usernameFlag = null;
    /**
     * 查询所有用户不为发布者的任务
     * @return
     */
    @RequestMapping("/findAllTask")
    @ResponseBody
    public List<TaskList> findAllTask(String username){
        List<TaskList> taskList = taskService.findAllTask(username);
       return taskList;
    }

    @RequestMapping(value = "/findAllTask1",method = RequestMethod.POST)
    @ResponseBody
    public Map findAllTaskByPage(String username, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        Integer count = taskService.findAllTaskCount(username);
        List<TaskList> TList = taskService.findAllTask(username);
        map.put("count",count);
        if (count == null){
            map.put("List",null);
            return map;
        }
        map.put("List",TList);
        return map;
    }

     /**
     * 查询愿意分享的任务
     * @param username
     * @return
     */
   @RequestMapping("/findShareTask")
    @ResponseBody
    public List<TaskList> findShareTask(String username){
        List<TaskList> taskList = taskService.findShareTask(username);
        return taskList;
    }

    /**
     *  前往任务详情页
     * @return
     */
    @RequestMapping(value = "/gotoTaskInfo")
    public ModelAndView gotoTaskInfo(String trFlag,String username){
        ModelAndView mv = new ModelAndView();
//        if (trFlag==null){
        mv.addObject("taskId",trFlag);
        mv.addObject("username",username);
        mv.setViewName("taskInfo");
//        gotoTaskInfoFlag = null;
//        usernameFlag = null;
        return mv;
//        }else {
//            gotoTaskInfoFlag = trFlag;
//            usernameFlag = username;
//            return mv;
//        }
    }

    /**
     * 更新receiver
     * @param task_id
     * @param receiver
     * @return
     */
    @RequestMapping(value = "/updateReceiver")
    @ResponseBody
    public boolean updateReceiver(Integer task_id,String receiver){
        taskService.saveReceiver(task_id,receiver);
        return true;
    }

    /**
     * 前往发布任务页面taskPublish.jsp
     */
    @RequestMapping(value = "/gotoPublishTask")
    public ModelAndView gotoPublishTask(String username){
        ModelAndView mv = new ModelAndView();
//        if (username==null){
            mv.addObject("publisher_username",username);
            mv.setViewName("taskPublish");
//            usernameFlag = null;
            return mv;
//        }else {
//            usernameFlag = username;
//            return mv;
//        }
    }

    /**
     * 发布任务，任务发布完成后跳转回success页面，并在success判断是否发布成功
     * @return
     */
    @RequestMapping(value = "/publishTask")
    @ResponseBody
    public String publishTask(TaskList taskList){
        System.out.println(taskList);
        //随机生成task_id,然后与数据库中的task_id对比是否已存在，
        //将已验证数据库不存在的taskId赋给新任务
        Integer task_id = taskId.returnTaskId();
        taskList.setTask_id(task_id);
        //获取当前时间并赋值给publish_time
        Date date = new Date();
        taskList.setPublish_time(date);
        //保存任务进数据库，task_tbl表
        taskService.saveTask(taskList);
        //保存任务信息进taskInfo_tbl表
        taskService.saveTaskInfo(taskList);
        return "success";
    }

    /**
     * 用户撤回任务
     * @param task_id
     * @return
     */
    @RequestMapping("/cancelTask")
    @ResponseBody
    public String cancelTask(String task_id){
        taskService.cancelTask(task_id);
        return "success";
    }


    /**
     * 保存pay_hash
     * @param hash
     * @return
     */
    @RequestMapping("/saveHash")
    public String saveHash(String task_id,String hash){
        System.out.println(task_id+":"+hash);
        taskService.saveHash(task_id,hash);
        return "success";
    }

    /**
     * 获取交易hash
     * @return
     */
    @RequestMapping("/getHashByTaskId")
    @ResponseBody
    public String getHashByTaskId(String username,String task_id){
        String hash =taskService.getHashByTaskId(username,task_id);
        return hash;
    }

    /**
     * 按task_id查询任务信息
     * @param task_id
     * @return
     */
    @RequestMapping("/getTaskInfoById")
    @ResponseBody
    public TaskList getTaskInfoById(Integer task_id){
        TaskList list = taskService.findTaskByTaskId(task_id);
        return list;
    }

    /**
     * 根据接收者查询任务
     * @param receiver
     * @return
     */
    @RequestMapping("/getTaskInfoByReceiver")
    @ResponseBody
    public List<TaskList> getTaskInfoByReceiver(String receiver){
        List<TaskList> list = taskService.findTaskInfoByReceiver(receiver);
        return list;
    }

    /**
     * 根据发布者查询任务
     * @param publisher
     * @return
     */
    @RequestMapping("/getTaskInfoByPublisher")
    @ResponseBody
    public List<TaskList> getTaskInfoByPublisher(String publisher){
        System.out.println(publisher);
        List<TaskList> list = taskService.findTaskInfoByPublisher(publisher);
        return list;
    }

    /**
     * 根据关键字查询任务
     * @param keyword
     * @return
     */
    @RequestMapping("/getTaskInfoByKeyWord")
    @ResponseBody
    public List<TaskList> getTaskInfoByKeyWord(String keyword){
        keyword = "%"+keyword+"%";
        List<TaskList> list = taskService.getTaskInfoByKeyWord(keyword);
        return list;
    }

    /**
     * 完成任务
     * @param task_id
     * @return
     */
    @RequestMapping("/finishTask")
    @ResponseBody
    public String finishTask(String task_id){
        Date date = new Date();
        TaskList list = taskService.getTaskInfoByTaskId(task_id);
        if (list.getFinish_time()!=null){
            return "error";
        }
        taskService.finishTask(task_id,date);
        return "success";
    }

    /**
     * 支付报酬
     * @return
     */
    @RequestMapping("/updatePaymentStatus")
    @ResponseBody
    public String updatePaymentStatus(String task_id){
        taskService.updatePaymentStatus(task_id);
        return "success";
    }

    /**
     * 管理员获取所有任务信息
     * @return
     */
    @RequestMapping("/adminGetAllTask")
    @ResponseBody
    public Map adminGetAllTask(){
        Map map = new HashMap();
        Integer count = adminService.getAllTaskCount();
        List<TaskList> list = adminService.getAllTask();
        map.put("count",count);
        map.put("List",list);
        return map;
    }

    /**
     * 管理员获取交易记录
     * @return
     */
    @RequestMapping("/adminGetDealRecord")
    @ResponseBody
    public Map adminGetDealRecord(){
        Map rechargeMap = new HashMap();
        Integer count = adminService.getDealRecordCount();
        List<DealRecord> list = adminService.getDealRecord();
        rechargeMap.put("count",count);
        rechargeMap.put("List",list);
        return rechargeMap;
    }

    /**
     * 管理员审核任务
     * @param task_id
     * @return
     */
    @RequestMapping("/adminCheckTask")
    @ResponseBody
    public String adminCheckTask(String task_id,String reason){
        adminService.checkTask(task_id,reason);
        return "success";
    }

    /**
     * 管理员删除用户交易记录
     * @param deal_id
     * @return
     */
    @RequestMapping("/adminDeleteDealRecord")
    @ResponseBody
    public String adminDeleteDealRecord(String deal_id){
        if (adminService.deleteDealById(deal_id))
        {
            return "success";
        }
        return "failed";
    }
}
