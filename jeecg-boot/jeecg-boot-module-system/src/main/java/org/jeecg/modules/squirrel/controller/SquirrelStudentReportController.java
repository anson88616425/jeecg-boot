package org.jeecg.modules.squirrel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.squirrel.entity.SquirrelStudentReport;
import org.jeecg.modules.squirrel.service.ISquirrelStudentReportService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: squirrel_student_report
 * @Author: jeecg-boot
 * @Date:   2021-02-12
 * @Version: V1.0
 */
@Api(tags="squirrel_student_report")
@RestController
@RequestMapping("/squirrel/squirrelStudentReport")
@Slf4j
public class SquirrelStudentReportController extends JeecgController<SquirrelStudentReport, ISquirrelStudentReportService> {
	@Autowired
	private ISquirrelStudentReportService squirrelStudentReportService;
	
	/**
	 * 分页列表查询
	 *
	 * @param squirrelStudentReport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-分页列表查询")
	@ApiOperation(value="squirrel_student_report-分页列表查询", notes="squirrel_student_report-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SquirrelStudentReport squirrelStudentReport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SquirrelStudentReport> queryWrapper = QueryGenerator.initQueryWrapper(squirrelStudentReport, req.getParameterMap());
		Page<SquirrelStudentReport> page = new Page<SquirrelStudentReport>(pageNo, pageSize);
		IPage<SquirrelStudentReport> pageList = squirrelStudentReportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param squirrelStudentReport
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-添加")
	@ApiOperation(value="squirrel_student_report-添加", notes="squirrel_student_report-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SquirrelStudentReport squirrelStudentReport) {
		squirrelStudentReportService.save(squirrelStudentReport);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param squirrelStudentReport
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-编辑")
	@ApiOperation(value="squirrel_student_report-编辑", notes="squirrel_student_report-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SquirrelStudentReport squirrelStudentReport) {
		squirrelStudentReportService.updateById(squirrelStudentReport);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-通过id删除")
	@ApiOperation(value="squirrel_student_report-通过id删除", notes="squirrel_student_report-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		squirrelStudentReportService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-批量删除")
	@ApiOperation(value="squirrel_student_report-批量删除", notes="squirrel_student_report-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.squirrelStudentReportService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "squirrel_student_report-通过id查询")
	@ApiOperation(value="squirrel_student_report-通过id查询", notes="squirrel_student_report-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SquirrelStudentReport squirrelStudentReport = squirrelStudentReportService.getById(id);
		if(squirrelStudentReport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(squirrelStudentReport);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param squirrelStudentReport
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SquirrelStudentReport squirrelStudentReport) {
        return super.exportXls(request, squirrelStudentReport, SquirrelStudentReport.class, "squirrel_student_report");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SquirrelStudentReport.class);
    }

}
