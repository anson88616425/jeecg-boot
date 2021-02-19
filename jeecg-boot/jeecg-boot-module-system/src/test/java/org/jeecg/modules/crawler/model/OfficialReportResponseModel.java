package org.jeecg.modules.crawler.model;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * @Description: TODO
 * @author: Anson
 * @date: 2021年02月12日 10:51
 */
@Data
public class OfficialReportResponseModel {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..msg", source = ExtractBy.Source.RawText)
    private String msg;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..courseName", source = ExtractBy.Source.RawText)
    private String courseName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..reportChapterName", source = ExtractBy.Source.RawText)
    private String topicName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..userName", source = ExtractBy.Source.RawText)
    private String studentName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..finishTime", source = ExtractBy.Source.RawText)
    private Long finishTime;

    /**
     * 学习前
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..beforeStudyTagCodeMasterRate", source = ExtractBy.Source.RawText)
    private String studyBefore;

    /**
     * 学习后
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..afterStudyModuleTagCodeMasterRate", source = ExtractBy.Source.RawText)
    private String studyAfter;

    /**
     * 作答正确率
     */
    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..correctAnswerRate", source = ExtractBy.Source.RawText)
    private String rightRate;
}
