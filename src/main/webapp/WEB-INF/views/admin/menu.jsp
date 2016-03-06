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
					<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-pencil"></i>产品管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-pencil"></i>保洁产品分类</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/create"><i class="icon-pencil"></i>保洁产品发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-pencil"></i>保洁产品管理</a></li>
					
					
					
				<!-- 
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-pencil"></i>科研项目管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/create"><i class="icon-pencil"></i>科研项目发布</a></li>
					
					<li><a href="${pageContext.request.contextPath}/admin/expert/index"><i class="icon-pencil"></i>专家学者管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/expert/create"><i class="icon-pencil"></i>专家学者发布</a></li>
					
					<li><a href="${pageContext.request.contextPath}/admin/paper/index"><i class="icon-pencil"></i>科研论文管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/paper/create"><i class="icon-pencil"></i>科研论文发布发布</a></li>
					
					
					<li><a href="${pageContext.request.contextPath}/admin/patent/index"><i class="icon-pencil"></i>专利文献管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/patent/create"><i class="icon-pencil"></i>专利文献发布</a></li>
					
					<li><a href="${pageContext.request.contextPath}/admin/lab/index"><i class="icon-pencil"></i>实验仪器管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/lab/create"><i class="icon-pencil"></i>实验仪器发布</a></li>
					 -->
					
					<!-- 
					<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-pencil"></i>学生管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/teacher/index"><i class="icon-pencil"></i>教师管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/news/create"><i class="icon-pencil"></i>公告发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/work/index"><i class="icon-pencil"></i>就业管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/score/index"><i class="icon-pencil"></i>成绩管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-pencil"></i>课程管理</a></li>	
					<li><a href="${pageContext.request.contextPath}/admin/grade/index"><i class="icon-pencil"></i>培训计划管理</a></li>	
					<li><a href="${pageContext.request.contextPath}/admin/timetable/index"><i class="icon-pencil"></i>课表管理</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/attence/index"><i class="icon-pencil"></i>考勤管理</a></li>
					 -->
				</c:if>
				<c:if test="${sessionScope.adminuser.username!='admin'}">
					<li><a href="${pageContext.request.contextPath}/admin/work/index"><i class="icon-pencil"></i>就业管理</a></li>
				
				</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>