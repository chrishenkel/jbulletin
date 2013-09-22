<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="resources/css/bootstrap.css"/>"
	rel="stylesheet" media="screen">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->

<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/jbulletin.css"/>"/>

</head>
<body>
	<div class="container">
		<t:navbar/>
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<ul class="breadcrumb">
							<li><a href="<c:url value=""/>">Forums</a> <span
								class="divider">/</span></li>
							<li><a href="<c:url value="sub/${subSection.id}"/>">${subSection.name}</a>
								<span class="divider">/</span></li>
							<li class="active">New Topic</li>
						</ul>
						<div class="page-header">
							<h1>${subSection.name}</h1>
						</div>
					</div>
				</div>
			</div>
		</div>
		<form:form commandName="topicForm" action="${postUrl}" method="post">
			<p>
				<form:label path="topicName">Topic Name</form:label>
				<form:input path="topicName" />
				<form:errors path="topicName" />
			</p>
			<p>
				<form:textarea id="editor1" path="topicContent" cols="80"
					name="forum-editor" rows="10"></form:textarea>
				<form:errors path="topicContent" />
			</p>
			<p>
				<input type="submit" value="Submit" />
			</p>
		</form:form>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>
	<ckeditor:replace replace="editor1" basePath="${ckeditorRoot}" />
</body>
</html>