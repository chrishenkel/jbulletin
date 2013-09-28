<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet" media="screen">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/jbulletin.css"/>" />

</head>
<body>
	<div class="container">
		<t:navbar />
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<ul class="breadcrumb">
							<li><a href="<c:url value="/"/>">Forums</a> <span
								class="divider">/</span></li>
							<li><a href="<c:url value="/sub/${topic.subSection.id}"/>">${topic.subSection.name}</a>
								<span class="divider">/</span></li>
							<li class="active">${topic.name}</li>
						</ul>

						<t:pagination>
							<jsp:attribute name="nextPage">${nextPage}</jsp:attribute>
							<jsp:attribute name="previousPage">${previousPage}</jsp:attribute>
							<jsp:attribute name="pageCount">${pageCount}</jsp:attribute>
							<jsp:attribute name="currentPage">${page}</jsp:attribute>
							<jsp:attribute name="baseUrl">/sub/${topic.subSection.id}/topic/${topic.id}</jsp:attribute>
						</t:pagination>

						<a
							href="<c:url value="/sub/${topic.subSection.id}/topic/${topic.id}/post/new"/>"
							style="float: right;" class="btn btn-success"><i
							class="icon-white icon-flag"></i> New Post</a>

						<div class="page-header">
							<h1>${topic.name}</h1>
						</div>
						<c:set var="count" value="0" scope="page" />
						<c:forEach items="${posts}" var="post">
							<br>
							<c:set var="count" value="${post.selector + 1}" />
							<a id="entry"></a>
							<div class="row-fluid post_wrap post_top">
								<div class="span12">
									<h3 class="top_grad">
										<div align="right">#${count}</div>
									</h3>
								</div>
							</div>
							<div class="row-fluid post_wrap">
								<div class="span2">
									<ul class="basic_info">
										<p class="member_title">${post.poster.name}</p>
										<li><img
											src="<c:url value="/profile/${post.poster.id}/avatar"/>" width="140" height="140" />
										</li>
										<li>${post.poster.postCount} posts</li>
									</ul>
								</div>
								<div class="span10 post_content">
									<p>posted on ${post.posted}</p>
									<div>${post.content}</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div>
			<a
				href="<c:url value="/sub/${topic.subSection.id}/topic/${topic.id}/post/new"/>"
				style="float: right;" class="btn btn-success"><i
				class="icon-white icon-flag"></i> New Post</a>
			<t:pagination>
				<jsp:attribute name="nextPage">${nextPage}</jsp:attribute>
				<jsp:attribute name="previousPage">${previousPage}</jsp:attribute>
				<jsp:attribute name="pageCount">${pageCount}</jsp:attribute>
				<jsp:attribute name="currentPage">${page}</jsp:attribute>
				<jsp:attribute name="baseUrl">/sub/${topic.subSection.id}/topic/${topic.id}</jsp:attribute>
			</t:pagination>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

	<ckeditor:replace replace="editor1" basePath="${ckeditorRoot}" />
</body>
</html>