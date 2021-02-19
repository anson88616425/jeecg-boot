package org.jeecg.modules.crawler.pipeline;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.jeecg.modules.crawler.model.*;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Print page model in console.<br>
 * Usually used in test.<br>
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class StudentInfoPipeline implements PageModelPipeline<StudentInfoModel> {
    @Override
    public void process(StudentInfoModel o, Task task) {
        String accountId =o.getAccountId();
        String studentId=o.getUserId();
        List<String> roleIdList=o.getRoleIdList();
        List<String> courseIdList=o.getCourseIdList();
        List<String> learnTypeList=o.getLearnType();
        List<String> courseVersionList=o.getCourseVersion();
        for(int i=0;i<courseIdList.size();i++) {
            if(!learnTypeList.get(i).equals("1")&&!courseVersionList.get(i).equals("10")) {
                Request request = new Request("https://teacher.classba.cn/api/report/studentReportList");
                request.setMethod(HttpConstant.Method.POST);
                Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                        .addHeader("authority","teacher.classba.cn")
                        .addHeader("Cookie", StudentModel.Cookie);
                Map<String,Object> map=new HashMap<>();
                map.put("subject_id",2);
                map.put("course_id",courseIdList.get(i));
                map.put("lesson_id",0);
                map.put("page",1);
                map.put("pagesize",100);
                map.put("student_id",studentId);
                map.put("role_id",roleIdList.get(i));
                request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
                OOSpider.create(site
                        , new EffectiveLearningReportUrlPipeline(), EffectiveLearningReportUrlModel.class)
                        .addRequest(request).thread(1).run();
            }
            else {
                Request request = new Request("https://teacher.classba.cn/api/report/studentChapterReportList");
                request.setMethod(HttpConstant.Method.POST);
                Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                        .addHeader("authority","teacher.classba.cn")
                        .addHeader("Cookie", StudentModel.Cookie);
                Map<String,Object> map=new HashMap<>();
                map.put("subject_id",2);
                map.put("course_id",courseIdList.get(i));
                map.put("start_dt",DateUtil.format(DateUtil.offsetMonth(DateUtil.date(),-3),DatePattern.NORM_DATE_PATTERN));
                map.put("end_dt",DateUtil.today());
                map.put("page",1);
                map.put("pagesize",100);
                map.put("account_id",accountId);
                map.put("role_id",roleIdList.get(i));
                request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
                OOSpider.create(site
                        , new OfficialReportUrlPipeline(), OfficialReportUrlModel.class)
                        .addRequest(request).thread(1).run();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.today());
        System.out.println(DateUtil.format(DateUtil.offsetMonth(DateUtil.date(),-1),DatePattern.NORM_DATE_PATTERN));
    }
}
