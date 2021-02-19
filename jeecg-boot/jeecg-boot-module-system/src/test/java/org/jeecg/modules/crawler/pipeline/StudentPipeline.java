package org.jeecg.modules.crawler.pipeline;

import org.jeecg.modules.crawler.model.EffectiveLearningReportUrlModel;
import org.jeecg.modules.crawler.model.StudentInfoModel;
import org.jeecg.modules.crawler.model.StudentModel;
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
public class StudentPipeline implements PageModelPipeline<StudentModel> {
    @Override
    public void process(StudentModel o, Task task) {
        List<String> userIdList=o.getUserIdList();
        List<String> roleIdList=o.getRoleIdList();
        List<String> courseIdList=o.getCourseIdList();
        System.out.println(o.getCourseIdList().size());
        System.out.println("userIdList"+o.getUserIdList());
        System.out.println("courseIdList"+o.getCourseIdList());
        System.out.println("roleIdList"+o.getRoleIdList());
        for(int i=0;i<userIdList.size();i++) {
            Request request = new Request("https://teacher.classba.cn/api/student/studentInfo");
            request.setMethod(HttpConstant.Method.POST);
            Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                    .addHeader("authority","teacher.classba.cn")
                    .addHeader("Cookie", StudentModel.Cookie);
            Map<String,Object> map=new HashMap<>();
            map.put("subject_id",2);
            map.put("student_id",userIdList.get(i));
            request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
            OOSpider.create(site
                    , new StudentInfoPipeline(), StudentInfoModel.class)
                    .addRequest(request).thread(1).run();
        }
    }
}
