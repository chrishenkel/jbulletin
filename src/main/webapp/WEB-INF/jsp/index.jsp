<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	test
		<t:navbar />
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<c:forEach items="${sections}" var="section">
				<div class="panel-heading" style="border-bottom: 0px;">
					<h2>
						<c:out value="${section.name}" />
					</h2>
				</div>

				<!-- Table -->
				<table class="table section">
					<c:forEach items="${section.subSections}" var="subSection">
						<tr>

							<td class="sub_section_image"><img
								src="<c:url value="/resources/img/netherchest.png"/>" /></td>
							<td class="sub_section_title">
								<h4>
									<a href="<c:url value="/sub/${subSection.id}"/>"> <c:out
											value="${subSection.name}" /></a>
								</h4>
								<p>
									<c:out value="${subSection.description}" />
								</p>
							</td>
							<td class="sub_section_info">
								<ul>
									<li><strong>${subSection.topicCount}</strong> topics</li>
									<li><strong>${subSection.replyCount}</strong> replies</li>
								</ul>
							</td>
							<td class="recent_poster_avatar"><img
								src="<c:url value="/profile/${subSection.mostRecentPost.poster.id}/avatar"/>" /></td>
							<td class="sub_section_recent_poster_info">
								<ul>
									<li><a class="small" href="">${subSection.mostRecentPost.poster.name}</a></li>
									<li>${subSection.mostRecentPost.topic.name}</li>
									<li></li>
								</ul>
							</td>
						</tr>

					</c:forEach>
				</table>
			</c:forEach>
		</div>

	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>