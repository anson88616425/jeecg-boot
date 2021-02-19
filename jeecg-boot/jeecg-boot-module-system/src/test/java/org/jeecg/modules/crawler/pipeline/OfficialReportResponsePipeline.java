package org.jeecg.modules.crawler.pipeline;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.crawler.model.EffectiveLearningReportResponseModel;
import org.jeecg.modules.crawler.model.OfficialReportResponseModel;
import org.jeecg.modules.squirrel.entity.SquirrelStudentReport;
import org.jeecg.modules.squirrel.service.ISquirrelStudentReportService;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.text.DateFormat;

/**
 * Print page model in console.<br>
 * Usually used in test.<br>
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */

public class OfficialReportResponsePipeline implements PageModelPipeline<OfficialReportResponseModel> {



    @Override
    public void process(OfficialReportResponseModel o, Task task) {
        ISquirrelStudentReportService squirrelStudentReportService=(ISquirrelStudentReportService)SpringContextUtils.getBean(ISquirrelStudentReportService.class);
        SquirrelStudentReport squirrelStudentReport=new SquirrelStudentReport();
        BeanUtil.copyProperties(o,squirrelStudentReport);
        squirrelStudentReport.setFinishDay(DateUtil.date(o.getFinishTime()*1000l));
        squirrelStudentReport.setReportType("正式课");
        System.out.println(squirrelStudentReport);
        squirrelStudentReportService.save(squirrelStudentReport);
    }
}
