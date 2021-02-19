package org.jeecg.modules.crawler.model;

import lombok.Data;
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
@Data
public class StudentInfoModel {



    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..account_id", source = ExtractBy.Source.RawText)
    private String accountId;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..user_id", source = ExtractBy.Source.RawText)
    private String userId;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..course_id", source = ExtractBy.Source.RawText)
    private List<String> courseIdList;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..role_id", source = ExtractBy.Source.RawText)
    private List<String> roleIdList;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..learn_type", source = ExtractBy.Source.RawText)
    private List<String> learnType;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..course_version", source = ExtractBy.Source.RawText)
    private List<String> courseVersion;


}
