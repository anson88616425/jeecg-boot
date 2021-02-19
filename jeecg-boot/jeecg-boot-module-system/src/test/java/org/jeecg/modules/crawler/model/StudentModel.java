package org.jeecg.modules.crawler.model;

import org.jeecg.modules.crawler.pipeline.StudentPipeline;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
public class StudentModel {

    public static String Cookie="gr_user_id=cc14dfb9-7c09-4cdb-b547-555d5db950d6; grwng_uid=91229acb-e943-4eab-a98c-0d780e6a3bdc; 94bcc04a1f422cef_gr_last_sent_cs1=3140101052054467; 94bcc04a1f422cef_gr_session_id=692ddf79-485b-4a8e-bd7e-173ad5935bb8; 94bcc04a1f422cef_gr_last_sent_sid_with_cs1=692ddf79-485b-4a8e-bd7e-173ad5935bb8; PHPSESSID=7q3ot85i9jc7kcjfm02i4v35f6; 94bcc04a1f422cef_gr_session_id_692ddf79-485b-4a8e-bd7e-173ad5935bb8=true; 94bcc04a1f422cef_gr_cs1=3140101052054467";


    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..user_id", source = ExtractBy.Source.RawText)
    private List<String>  userIdList;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..course_id", source = ExtractBy.Source.RawText)
    private List<String> courseIdList;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..role_id", source = ExtractBy.Source.RawText)
    private List<String> roleIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public List<String> getCourseIdList() {
        return courseIdList;
    }

    public void setCourseIdList(List<String> courseIdList) {
        this.courseIdList = courseIdList;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "TestRepo{" +
                "userIdList=" + userIdList +
                ", courseIdList=" + courseIdList +
                ", roleIdList=" + roleIdList +
                '}';
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
                .addHeader("Cookie",StudentModel.Cookie);
        OOSpider.create(site
                , new StudentPipeline(), StudentModel.class)
                .addRequest(request).thread(1).run();
    }


}
