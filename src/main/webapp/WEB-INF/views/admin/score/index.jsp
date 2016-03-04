<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.score.js"></script>
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
								<h3>成绩</h3>
							</div>
							<div class="box well form-inline">
								
								<input type="text" id="_name" placeholder="学生姓名">
								<a onclick="$.score.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="row-fluid ">
								
									<a class="btn btn-success" style="float: right; margin: 5px;" onclick="$.score.showUserAddModal()"><i class="icon-plus"></i>成绩录入</a>
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >id</th>
											<th >学生姓名</th>
											<th >课程名称</th>
											<th >总分</th>
											<th >得分</th>
											<th >等级</th>
											<th >录入时间</th>
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
								<label for="title" class="control-label">学生：</label>
								<div class="controls">
									<select name='user.id'>
										<c:forEach items="${users }" var="bean">
											<option value="${bean.id }">${bean.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">课程：</label>
								<div class="controls">
								<select name='category.id' >
										<c:forEach items="${categorys }" var="bean">
											<option value="${bean.id }">${bean.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">总分：</label>
								<div class="controls">
									<input type="text" name="maxscore">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">成绩：</label>
								<div class="controls">
									<input type="text" name="score">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">等级：</label>
								<div class="controls">
									<select name='degree'>
										<option value="优秀">优秀</option>
											<option value="良好">良好</option>
											<option value="及格">及格</option>
											<option value="不及格">不及格</option>
									</select>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal-footer center" id="div_footer">
			<a class="btn btn-primary" onclick="$.score.saveUser()">保存</a>
			<a href="#" class="btn" data-dismiss="modal" id="closeViewModal">关闭</a>
		</div>
	</div>
</body>
</html>