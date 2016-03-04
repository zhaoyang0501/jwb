<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.category.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${tip}" != null && "${tip}" != ""){
			noty({"text":"${tip}","layout":"top","type":"success","timeout":"2000"});
		}
		$(".date").datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
	        format:'yyyy-mm-dd',
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
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
								<h3>个人中心</h3>
							</div>
							
							<div class="widget-container">
							
							<div class="row-fluid">
								<div class="tab-widget">
									<ul class="nav nav-tabs" id="myTab2">
										<li class="active"><a href="#toapprove"><i class="icon-edit"></i> 个人中心</a></li>
										<li class=""><a href="#approved"><i class="icon-check"></i>密码修改</a></li>
									</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="toapprove">
										<form action="${pageContext.request.contextPath}/admin/center/docenter" method="post">
							<input type="hidden" name="id" value="${sessionScope.adminuser.id}">
							<div class="control-group">
								<label for="name" class="control-label">用户名：</label>
								<div class="controls">
									<input type="text" name="username" value='${sessionScope.adminuser.username}' placeholder="" readonly="readonly">
								</div>
							</div>
							
							<div class="control-group" id='control_project'>
								<label for="num" class="control-label">姓名：</label>
								<div class="controls">
									<input type="text" name="name" id="name" placeholder="" value='${sessionScope.adminuser.name}'>
								</div>
							</div>
							<div class="control-group" id='control_project'>
								<label for="tel" class="control-label">电话：</label>
								<div class="controls">
									<input type="text" name="tel" id="tel" placeholder="" value='${sessionScope.adminuser.tel}'>
								</div>
							</div>
							<div class="modal-footer center" id="div_footer">
									<button type="submit" class='btn btn-primary'>修改</button>
								</div>
						</form>
									</div>
									<div class="tab-pane" id="approved">
									
									
									<form action="${pageContext.request.contextPath}/admin/center/docenterpassword" method="post">
							<input type="hidden" name="id" value="${sessionScope.adminuser.id}">
							<div class="control-group">
								<label for="name" class="control-label">旧密码：</label>
								<div class="controls">
									<input type="password" name="oldpassword" id="password" placeholder="" value=''>
								</div>
							</div>
							
							
							<div class="control-group">
								<label for="name" class="control-label">新密码：</label>
								<div class="controls">
									<input type="password" name="newpassword" id="password" placeholder="" value=''>
								</div>
							</div>
							<div class="control-group">
								<label for="name" class="control-label">新密码确认：</label>
								<div class="controls">
									<input type="password" name="newpasswordtwo" id="password" placeholder="" value=''>
								</div>
							</div>
							<div class="modal-footer center" id="div_footer">
									<button type="submit" class='btn btn-primary'>修改</button>
								</div>
						</form>
									</div>
								</div>
							</div>
						</div>
				
				
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>
</body>
</html>