<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.teacher.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>
</head>
<body>
	<div class="layout">
		<!-- top -->
		<%@ include file="../top.jsp"%>
		<!-- 导航 -->
		<%@ include file="../menu.jsp"%>
		
		<input type="hidden" id="hf_id" />

		<div class="main-wrapper">
			<div class="container-fluid">
				<div class="row-fluid ">
					<div class="span12">
						<div class="content-widgets ">
							<div class="widget-head  bondi-blue" >
								<h3>教师管理管理</h3>
							</div>
							<div class="box well form-inline">
								<span>教师名称：</span>
								<input type="text" id="_name" >
								<a onclick="$.teacher.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="row-fluid ">
								
									<a class="btn btn-success" style="float: right; margin: 5px;" onclick="$.teacher.showUserAddModal()"><i class="icon-plus"></i>创建</a>
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >id</th>
											<th >教师名称</th>
											<th >电话</th>
											<th >email</th>
											<th >职称</th>
											<th >工作年限</th>
											<th >操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>

	<!-- 编辑新增弹出框 -->
	<div class="modal hide fade" id="_modal">
		<div class="modal-header blue">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<label id="_modal_header_label"></label>
		</div>
		<div class="modal-body" >
			<div class="row-fluid">
				<div class="span12">
					<div class="form-container grid-form form-background left-align form-horizontal">
						<form action="" method="get" id=''>
							<input type="hidden"  name='id' id="id" value="">
							
							<div class="control-group">
								<label for="title" class="control-label">教师名称：</label>
								<div class="controls">
									<input type="text" name='name' id="name" placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">email：</label>
								<div class="controls">
									<input type="text" name='email' id="email" placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">电话：</label>
								<div class="controls">
									<input type="text" name='tel' id="tel" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">职称：</label>
								<div class="controls">
									<select name="level" id='level'>
										<option value="" ></option>
										<option value="讲师" >讲师</option>
										<option value="中级讲师" >中级讲师</option>
										<option value="高级讲师" >高级讲师</option>
										<option value="特级讲师" >特级讲师</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">工作年限：</label>
								<div class="controls">
									<select name="year" id='year' >
										<option value="" ></option>
										<option value="1到5年" >1到5年</option>
										<option value="5年以上" >5年以上</option>
										<option value="10年以上" >10年以上</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">工作经历：</label>
								<div class="controls">
								<textarea id='work' rows="3" cols="" name='work'></textarea>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">项目经历：</label>
								<div class="controls">
								<textarea id='project' rows="3" cols="" name='project'></textarea>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">个人简介：</label>
								<div class="controls">
								<textarea  id='remark' rows="3" cols="" name='remark'></textarea>
								</div>
							</div>
							
							
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal-footer center" id="div_footer">
			<a class="btn btn-primary" onclick="$.teacher.saveUser()">保存</a>
			<a href="#" class="btn" data-dismiss="modal" id="closeViewModal">关闭</a>
		</div>
	</div>
</body>
</html>