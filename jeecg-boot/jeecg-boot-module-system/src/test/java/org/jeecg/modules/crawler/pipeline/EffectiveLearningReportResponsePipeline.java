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
        squirrelStudentReportService.save(squirrelStudentReport);
    }

}
