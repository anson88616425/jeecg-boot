package org.jeecg.modules.crawler;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.JeecgSystemApplication;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.crawler.model.StudentModel;
import org.jeecg.modules.crawler.pipeline.StudentPipeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
@SuppressWarnings({"FieldCanBeLocal", "SpringJavaAutowiredMembersInspection"})
public class CrawlerTest {

    @Test
    public void test() {
        Request request = new Request("https://teacher.classba.cn/api/student/formalStudentList?subject_id=2&class_id=0&course_id=0&student_id=0&sort_by='ACTIVE_TIME'&sort_type='DESC'&page=1");
        request.setMethod(HttpConstant.Method.POST);
        Map<String,Object> map=new HashMap<>();
        map.put("pagesize",100);
        map.put("subject_id",2);
        map.put("class_id",0);
        map.put("course_id",0);
        map.put("student_id",0);
        map.put("sort_by","ACTIVE_TIME");
        map.put("sort_type","DESC");
        map.put("page",1);
        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
        Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                .addHeader("authority","teacher.classba.cn")
                .addHeader("Cookie", StudentModel.Cookie);
        OOSpider.create(site
                , new StudentPipeline(), StudentModel.class)
                .addRequest(request).thread(1).run();
    }

    public static void main(String[] args) {
        Request request = new Request("https://teacher.classba.cn/api/student/formalStudentList?subject_id=2&class_id=0&course_id=0&student_id=0&sort_by='ACTIVE_TIME'&sort_type='DESC'&page=1");
        request.setMethod(HttpConstant.Method.POST);
        Map<String,Object> map=new HashMap<>();
        map.put("pagesize",100);
        map.put("subject_id",2);
        map.put("class_id",0);
        map.put("course_id",0);
        map.put("student_id",0);
        map.put("sort_by","ACTIVE_TIME");
        map.put("sort_type","DESC");
        map.put("page",1);
        request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
        Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                .addHeader("authority","teacher.classba.cn")
                .addHeader("Cookie", StudentModel.Cookie);
        OOSpider.create(site
                , new StudentPipeline(), StudentModel.class)
                .addRequest(request).thread(1).run();
    }

}
