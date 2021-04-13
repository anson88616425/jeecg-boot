package org.jeecg.modules.squirrel.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: squirrel_student_report
 * @Author: jeecg-boot
 * @Date:   2021-02-12
 * @Version: V1.0
 */
@Data
@TableName("squirrel_student_report")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="squirrel_student_report对象", description="squirrel_student_report")
public class SquirrelStudentReport implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
    private String id;
	/**课程名称*/
	@Excel(name = "课程名称", width = 15)
    @ApiModelProperty(value = "课程名称")
    private String courseName;
	/**学习点*/
	@Excel(name = "学习点", width = 15)
    @ApiModelProperty(value = "学习点")
    private String topicName;
	/**报告类型*/
	@Excel(name = "报告类型", width = 15)
    @ApiModelProperty(value = "报告类型")
    private String reportType;
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
	/**完成日期*/
	@Excel(name = "完成日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成日期")
    private Date finishDay;
    @Excel(name = "学前掌握程度", width = 15)
    @ApiModelProperty(value = "学前掌握程度")
    private String studeyBeforeMaster;
	/**学习前*/
	@Excel(name = "学习前掌握率", width = 15)
    @ApiModelProperty(value = "学习前掌握率")
    private String studyBeforeRight;
	/**学习后*/
	@Excel(name = "学习后", width = 15)
    @ApiModelProperty(value = "学习后")
    private String studyAfter;
	/**答题正确率*/
	@Excel(name = "答题正确率", width = 15)
    @ApiModelProperty(value = "答题正确率")
    private String rightRate;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
