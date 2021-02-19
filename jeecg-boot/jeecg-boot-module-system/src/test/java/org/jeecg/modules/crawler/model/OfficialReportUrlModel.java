package org.jeecg.modules.crawler.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * @Description: TODO
 * @author: Anson
 * @date: 2021年02月12日 10:51
 */
public class OfficialReportUrlModel {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..report_url", source = ExtractBy.Source.RawText)
    private List<String> reportUrlList;

    public List<String> getReportUrlList() {
        return reportUrlList;
    }

    public void setReportUrlList(List<String> reportUrlList) {
        this.reportUrlList = reportUrlList;
    }

    @Override
    public String toString() {
        return "EffectiveLearningReportUrlModel{" +
                "reportUrlList=" + reportUrlList +
                '}';
    }
}
