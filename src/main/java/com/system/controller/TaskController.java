package com.system.controller;

import com.system.domain.TaskList;
import com.system.service.TaskService;
import com.system.utils.RandomTaskId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 任务信息控制层
 */
@Controller
@RequestMapping("/task")
public class TaskController {
        @Autowired
        private TaskService taskService;
        @Autowired
        private RandomTaskId taskId;
        //task_id传值
        private String gotoTaskInfoFlag = null;
        //username传值
        private String usernameFlag = null;
        /**
         * 查询所有任务
         * @return
         */
        @RequestMapping("/findAllTask")
        @ResponseBody
        public List<TaskList> findAllTask(){
            List<TaskList> taskList = taskService.findAllTask();
           return taskList;
        }

        /**
         *  前往任务详情页
         * @return
         */
        @RequestMapping(value = "/gotoTaskInfo")
        public ModelAndView gotoTaskInfo(String trFlag,String username){
            ModelAndView mv = new ModelAndView();
            if (trFlag==null){
                mv.addObject("taskId",gotoTaskInfoFlag);
                mv.addObject("username",usernameFlag);
                mv.setViewName("taskInfo");
                return mv;
            }else {
                gotoTaskInfoFlag = trFlag;
                usernameFlag = username;
                return mv;
            }
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
            System.out.println("username:"+username);
            if (username==null){
                mv.addObject("publisher_username",usernameFlag);
                System.out.println(usernameFlag);
                mv.setViewName("taskPublish");
                return mv;
            }else {
                usernameFlag = username;
                return mv;
            }
        }

        /**
         * 发布任务，任务发布完成后跳转回success页面，并在success判断是否发布成功
         * @return
         */
        @RequestMapping(value = "/publishTask")
        public ModelAndView publishTask(TaskList taskList, HttpSession session){
            ModelAndView mv = new ModelAndView();
            session.setAttribute("username",taskList.getPublisher());
            session.setAttribute("publish","success");
            //随机生成task_id,然后与数据库中的task_id对比是否已存在，
            //将已验证数据库不存在的taskId赋给新任务
            taskList.setTask_id(taskId.returnTaskId());
            //获取当前时间并赋值给publish_time
            Date date = new Date();
            taskList.setPublish_time(date);
            System.out.println("----------");
            System.out.println(taskList);
            //保存任务进数据库，task_tbl表
            taskService.saveTask(taskList);
            //保存任务信息进taskInfo_tbl表
            taskService.saveTaskInfo(taskList);
            mv.setViewName("success");
            return mv;
        }

        /**
         * 按task_id查询任务信息
         * @param task_id
         * @return
         */
        @RequestMapping(value = "/getTaskInfoById")
        @ResponseBody
        public TaskList getTaskInfoById(Integer task_id){
            TaskList list = taskService.findTaskByTaskId(task_id);
            System.out.println(list);
            return list;
        }
}
