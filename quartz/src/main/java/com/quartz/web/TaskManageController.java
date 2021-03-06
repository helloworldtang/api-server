package com.quartz.web;

import com.alibaba.fastjson.JSON;
import com.quartz.common.exception.ServiceException;
import com.quartz.common.json.ResultInfo;
import com.quartz.domain.TaskInfo;
import com.quartz.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务管理
 *
 * @author lance
 */
@Controller
public class TaskManageController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    /**
     * Index.jsp
     * 2016年10月8日下午6:39:15
     */
    @RequestMapping(value = {"", "/", "index"})
    public String info() {
        return "index";
    }

    /**
     * 任务列表
     *
     * @return 2016年10月9日上午11:36:03
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list() {
        Map<String, Object> map = new HashMap<>();
        List<TaskInfo> infos = taskServiceImpl.list();
        map.put("rows", infos);
        map.put("total", infos.size());
        return JSON.toJSONString(map);
    }

    /**
     * 保存定时任务
     *
     * @param info 2016年10月9日下午1:36:59
     */
    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String save(TaskInfo info) {
        try {
            if (info.getId() == 0) {
                taskServiceImpl.addJob(info);
            } else {
                taskServiceImpl.edit(info);
            }
        } catch (ServiceException e) {
            return ResultInfo.error(-1, e.getMessage());
        }
        return ResultInfo.success();
    }

    /**
     * 删除定时任务
     *
     * @param jobName
     * @param jobGroup 2016年10月9日下午1:52:20
     */
    @ResponseBody
    @RequestMapping(value = "delete/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String delete(@PathVariable String jobName, @PathVariable String jobGroup) {
        try {
            taskServiceImpl.delete(jobName, jobGroup);
        } catch (ServiceException e) {
            return ResultInfo.error(-1, e.getMessage());
        }
        return ResultInfo.success();
    }

    /**
     * 暂停定时任务.暂停后再重新启动。暂停期间的执行次数，都会再补上的
     * 2017-04-06 10:12:14.021  INFO 7660 --- [eduler_Worker-2] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:16.058  INFO 7660 --- [eduler_Worker-3] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:18.015  INFO 7660 --- [eduler_Worker-4] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:19.744  INFO 7660 --- [p-nio-80-exec-4] com.quartz.service.TaskServiceImpl       : ===> Pause success, triggerKey:Job_group.com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:37.853  INFO 7660 --- [p-nio-80-exec-6] com.quartz.service.TaskServiceImpl       : ===> Resume success, triggerKey:Job_group.com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:37.920  INFO 7660 --- [eduler_Worker-5] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.032  INFO 7660 --- [eduler_Worker-1] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.078  INFO 7660 --- [eduler_Worker-2] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.118  INFO 7660 --- [eduler_Worker-3] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.156  INFO 7660 --- [eduler_Worker-4] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.288  INFO 7660 --- [eduler_Worker-5] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.526  INFO 7660 --- [eduler_Worker-1] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.664  INFO 7660 --- [eduler_Worker-2] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:38.810  INFO 7660 --- [eduler_Worker-3] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:39.010  INFO 7660 --- [eduler_Worker-4] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:40.058  INFO 7660 --- [eduler_Worker-5] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:42.115  INFO 7660 --- [eduler_Worker-1] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:44.032  INFO 7660 --- [eduler_Worker-2] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     * 2017-04-06 10:12:46.052  INFO 7660 --- [eduler_Worker-3] com.quartz.common.job.Minute3Job         : JobName3: com.quartz.common.job.Minute3Job
     *
     * @param jobName
     * @param jobGroup 2016年10月10日上午9:41:25
     */
    @ResponseBody
    @RequestMapping(value = "pause/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String pause(@PathVariable String jobName, @PathVariable String jobGroup) {
        try {
            taskServiceImpl.pause(jobName, jobGroup);
        } catch (ServiceException e) {
            return ResultInfo.error(-1, e.getMessage());
        }
        return ResultInfo.success();
    }

    /**
     * 重新开始定时任务
     *
     * @param jobName
     * @param jobGroup 2016年10月10日上午9:41:40
     */
    @ResponseBody
    @RequestMapping(value = "resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String resume(@PathVariable String jobName, @PathVariable String jobGroup) {
        try {
            taskServiceImpl.resume(jobName, jobGroup);
        } catch (ServiceException e) {
            return ResultInfo.error(-1, e.getMessage());
        }
        return ResultInfo.success();
    }
}
