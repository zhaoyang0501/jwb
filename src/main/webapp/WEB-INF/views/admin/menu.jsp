<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.adminuser.name}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="订单"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
				<c:if test="${sessionScope.adminuser.username=='admin'}">
					<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-pencil"></i>注册用户管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/order/index"><i class="icon-pencil"></i>订单管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-pencil"></i>保洁产品分类</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/create"><i class="icon-pencil"></i>保洁产品发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-pencil"></i>保洁产品管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/worker/index"><i class="icon-pencil"></i>员工管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/order/report"><i class="icon-pencil"></i>订单汇总</a></li>
					
					
				</c:if>
				<c:if test="${sessionScope.adminuser.username!='admin'}">
				<li><a href="${pageContext.request.contextPath}/admin/center/index"><i class="icon-pencil"></i>个人中心</a></li>
				
					<li><a href="${pageContext.request.contextPath}/admin/project/create"><i class="icon-pencil"></i>保洁产品发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-pencil"></i>保洁产品管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/order/index"><i class="icon-pencil"></i>订单管理</a></li>
				</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>