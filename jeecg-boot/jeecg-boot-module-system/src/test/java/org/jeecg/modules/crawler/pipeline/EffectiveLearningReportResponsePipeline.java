package org.jeecg.modules.crawler.pipeline;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.crawler.model.EffectiveLearningReportResponseModel;
import org.jeecg.modules.squirrel.entity.SquirrelStudentReport;
import org.jeecg.modules.squirrel.service.ISquirrelStudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Print page model in console.<br>
 * Usually used in test.<br>
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */

public class EffectiveLearningReportResponsePipeline implements PageModelPipeline<EffectiveLearningReportResponseModel> {



    @Override
    public void process(EffectiveLearningReportResponseModel o, Task task) {
        ISquirrelStudentReportService squirrelStudentReportService=(ISquirrelStudentReportService)SpringContextUtils.getBean(ISquirrelStudentReportService.class);
        SquirrelStudentReport squirrelStudentReport=new SquirrelStudentReport();
        BeanUtil.copyProperties(o,squirrelStudentReport);
                if(squirrelStudentReport.getStudyAfter().equals("[]")) {
                    squirrelStudentReport.setStudyAfter(o.getLearnMasterRate());
                }
        System.out.println(squirrelStudentReport);
                Map map=new HashMap();
                map.put("course_name",o.getCourseName());
                map.put("topic_name",o.getTopicName());
                map.put("student_name",o.getStudentName());
                map.put("finish_day",o.getFinishDay());
        List<SquirrelStudentReport> list=squirrelStudentReportService.listByMap(map);
        if(ObjectUtil.isNotEmpty(list)) {
            SquirrelStudentReport sr = list.get(0);
            if("先行测试".equals(sr.getReportType())) {
                sr.setStudyAfter(o.getStudyAfter());
                sr.setRightRate(o.getRightRate());
            }
            else {
                sr.setStudeyBeforeMaster(o.getStudeyBeforeMaster());
                sr.setStudyBeforeRight(o.getStudyBeforeRight());
            }
            squirrelStudentReportService.updateById(sr);
        }
        else {
            if("先行测试".equals(squirrelStudentReport.getReportType())) {
                squirrelStudentReport.setStudyAfter("[]");
                squirrelStudentReport.setRightRate("[]");
            }
            else {
                squirrelStudentReport.setStudeyBeforeMaster("[]");
                squirrelStudentReport.setStudyBeforeRight("[]");
            }
            squirrelStudentReportService.save(squirrelStudentReport);
        }
    }

}
