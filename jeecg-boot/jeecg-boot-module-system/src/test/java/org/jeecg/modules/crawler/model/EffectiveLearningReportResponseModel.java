package org.jeecg.modules.crawler.model;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * @Description: TODO
 * @author: Anson
 * @date: 2021年02月12日 10:51
 */
@Data
public class EffectiveLearningReportResponseModel {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..msg", source = ExtractBy.Source.RawText)
    private String msg;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..courseName", source = ExtractBy.Source.RawText)
    private String courseName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..topicName", source = ExtractBy.Source.RawText)
    private String topicName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..sectionName", source = ExtractBy.Source.RawText)
    private String reportType;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..userName", source = ExtractBy.Source.RawText)
    private String studentName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..finishDay", source = ExtractBy.Source.RawText)
    private String finishDay;

    /**
     * 学习前
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..testMasterRate", source = ExtractBy.Source.RawText)
    private String studyBefore;

    /**
     * 学习后
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..studyMasterRate", source = ExtractBy.Source.RawText)
    private String studyAfter;
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..learnMasterRate", source = ExtractBy.Source.RawText)
    private String learnMasterRate;
    /**
     * 作答正确率
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..answerRate.rightRate", source = ExtractBy.Source.RawText)
    private String rightRate;
}
