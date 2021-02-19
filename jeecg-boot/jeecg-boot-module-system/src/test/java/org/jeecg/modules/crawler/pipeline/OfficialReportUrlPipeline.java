package org.jeecg.modules.crawler.pipeline;

import org.jeecg.modules.crawler.model.*;
import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Print page model in console.<br>
 * Usually used in test.<br>
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */

public class OfficialReportUrlPipeline implements PageModelPipeline<OfficialReportUrlModel> {

    @Override
    public void process(OfficialReportUrlModel o, Task task) {
        List<String> list=o.getReportUrlList().stream().filter(a->!a.equals("")).collect(Collectors.toList());
        for(String l:list) {
            String replaceBefore=l;
            //替换
            if(l.indexOf("https://mmath.classba.cn/report")>=0) {
                l=l.replace("https://mmath.classba.cn/report","https://mmath.classba.cn/api/math/report/getModuleReport");
                //System.out.println(l);
                Request request = new Request(l);
                request.setMethod(HttpConstant.Method.POST);
                Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                        .addHeader("authority","teacher.classba.cn")
                        .addHeader("Cookie", StudentModel.Cookie);
                MultiMap multiMap = new MultiMap();
                UrlEncoded.decodeTo(l.substring(l.indexOf("?") + 1), multiMap, "UTF-8");
                multiMap.put("reportUrl",replaceBefore);
                request.setRequestBody(HttpRequestBody.form(multiMap,"utf-8"));
                OOSpider.create(site
                        , new EffectiveLearningReportResponsePipeline(), EffectiveLearningReportResponseModel.class)
                        .addRequest(request).thread(1).run();
            }
            if(l.indexOf("https://report.classba.cn/study/report/")>=0) {
                String str1=l.replace("https://report.classba.cn/study/report/","");
                String[] str1Arry=str1.split("/");
                System.out.println(Arrays.toString(str1.split("/")));
                String userId=str1Arry[0];
                String courseId=str1Arry[1];
                String date=str1Arry[2];
                String sign=str1Arry[3].substring(0,str1Arry[3].indexOf("?"));
                String chapterId=str1Arry[3].substring(str1Arry[3].indexOf("=")+1);
                l="https://report.classba.cn/api/sci/concluse/index";
                Request request = new Request(l);
                request.setMethod(HttpConstant.Method.POST);
                Site site= Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                        .addHeader("authority","teacher.classba.cn")
                        .addHeader("Cookie", StudentModel.Cookie);
                Map<String, Object> map=new HashMap<>();
                map.put("userId",userId);
                map.put("courseId",courseId);
                map.put("date",date);
                map.put("sign",sign);
                map.put("chapterId",chapterId);
                map.put("reportUrl",replaceBefore);
                request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
                OOSpider.create(site
                        , new OfficialReportResponsePipeline(), OfficialReportResponseModel.class)
                        .addRequest(request).thread(1).run();
            }

        }


    }

    static Map<String, Object> urlSplit(String data){
        StringBuffer strbuf = new StringBuffer();
        StringBuffer strbuf2 = new StringBuffer();
        Map<String ,Object> map = new HashMap<String,Object>();
        for(int i =0;i<data.length();i++){

            if(data.substring(i,i+1).equals("=")){

                for(int n=i+1;n<data.length();n++){
                    if(data.substring(n,n+1).equals("&")|| n ==data.length()-1){
                        map.put(strbuf.toString(), strbuf2);
                        strbuf =new  StringBuffer("");
                        strbuf2 =new  StringBuffer("");
                        i=n;
                        break;
                    }
                    strbuf2.append(data.substring(n,n+1));
                }
                continue;
            }
            strbuf.append(data.substring(i,i+1));
        }

        return map;
    }

    public static void main(String[] args) {
        String url = "https://mmath.classba.cn/report?userId=6860100947954238&topicId=1125199012547778&courseId=1025199012534930&subjectId=2&sModule=990001&sectionId=1125199012547780&batchNum=1";
        MultiMap multiMap = new MultiMap();
        UrlEncoded.decodeTo(url.substring(url.indexOf("?") + 1), multiMap, "UTF-8");
        System.out.println(multiMap.toString());

    }
}
