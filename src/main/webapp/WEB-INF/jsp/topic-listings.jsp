<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

		<ul class="breadcrumb">
			<li><a href="<c:url value="/"/>">Forums</a> <span
				class="divider">/</span></li>
			<li class="active">${subSection.name}</li>
		</ul>

		<t:pagination>
			<jsp:attribute name="nextPage">${nextPage}</jsp:attribute>
			<jsp:attribute name="previousPage">${previousPage}</jsp:attribute>
			<jsp:attribute name="pageCount">${pageCount}</jsp:attribute>
			<jsp:attribute name="currentPage">${page}</jsp:attribute>
			<jsp:attribute name="baseUrl">/sub/${subSection.id}</jsp:attribute>
		</t:pagination>

		<a href="<c:url value="/sub/${subSection.id}/topic/new"/>"
			style="float: right;" class="btn btn-success"><i
			class="icon-white icon-flag"></i> New Topic</a>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="border-bottom: 0px;">
				<h2>${subSection.name}</h2>
			</div>

			<!-- Table -->
			<table class="table section">
				<c:forEach items="${topics}" var="topic">
					<tr>
						<td class="sub_section_image"><img
							src="<c:url value="/resources/img/bucket.png"/>" /></td>
						<td class="sub_section_title">
							<h4>
								<a
									href="<c:url value="/sub/${subSection.id}/topic/${topic.id}"/>">${topic.name}</a>
							</h4>
							<p>Started by ${topic.poster.name}, <fmt:formatDate
											type="both" dateStyle="short" timeStyle="short"
											value="${topic.mostRecentPost.posted}" /></p>
						</td>
						<td class="sub_section_info">
							<ul>
								<li>${topic.postCount} replies</li>
								<li>${topic.viewCount} views</li>
							</ul>
						</td>
						<td class="recent_poster_avatar"><img
							src="<c:url value="/resources/img/avatar.png"/>" /></td>
						<td class="sub_section_recent_poster_info">
							<ul>
								<li>${topic.mostRecentPost.poster.name}</li>
								<li><a class="small" href=""><fmt:formatDate
											type="both" dateStyle="short" timeStyle="short"
											value="${topic.mostRecentPost.posted}" /></a></li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<a href="<c:url value="/sub/${subSection.id}/topic/new"/>"
			style="float: right;" class="btn btn-success"><i
			class="icon-white icon-flag"></i> New Topic</a>
		<t:pagination>
			<jsp:attribute name="nextPage">${nextPage}</jsp:attribute>
			<jsp:attribute name="previousPage">${previousPage}</jsp:attribute>
			<jsp:attribute name="pageCount">${pageCount}</jsp:attribute>
			<jsp:attribute name="currentPage">${page}</jsp:attribute>
			<jsp:attribute name="baseUrl">/sub/${subSection.id}</jsp:attribute>
		</t:pagination>
	</div>
</body>
</html>