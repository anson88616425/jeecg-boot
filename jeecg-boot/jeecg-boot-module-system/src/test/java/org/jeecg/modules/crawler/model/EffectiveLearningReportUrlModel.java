package org.jeecg.modules.crawler.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * @Description: TODO
 * @author: Anson
 * @date: 2021年02月12日 10:51
 */
public class EffectiveLearningReportUrlModel {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.data.list..report_list[?(@.report_name == '先行测试报告')].report_url", source = ExtractBy.Source.RawText)
    private List<String> reportUrlList0;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.data.list..report_list[?(@.report_name == '高效学习报告')].report_url", source = ExtractBy.Source.RawText)
    private List<String> reportUrlList;


    public List<String> getReportUrlList() {
        return reportUrlList;
    }



    public void setReportUrlList(List<String> reportUrlList) {
        this.reportUrlList = reportUrlList;
    }

    public List<String> getReportUrlList0() {
        return reportUrlList0;
    }

    public void setReportUrlList0(List<String> reportUrlList0) {
        this.reportUrlList0 = reportUrlList0;
    }

    @Override
    public String toString() {
        return "EffectiveLearningReportUrlModel{" +
                "reportUrlList=" + reportUrlList +
                '}';
    }
}
