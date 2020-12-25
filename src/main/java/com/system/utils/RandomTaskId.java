package com.system.utils;

import com.system.domain.TaskList;
import com.system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
@Component
public class RandomTaskId {
    @Autowired
    private TaskService taskService;
    private Integer task_id;
    public Integer returnTaskId(){
        Random random = new Random();
        for (;;)
        {
            task_id = random.nextInt(900000)+100000;
            TaskList list = taskService.findTaskByTaskId(task_id);
            if (list == null){
                break;
            }
        }
        return task_id;
    }

}
