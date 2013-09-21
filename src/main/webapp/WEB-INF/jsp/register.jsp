<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jbulletin.css"/>"/>

</head>
<body>
	<div class="container">
		<t:navbar register="active"/>
		<form:form commandName="registerForm" action="${postUrl}"
			method="post">
			<p>
				<form:label path="userName">User Name</form:label>
				<form:input path="userName" />
				<form:errors path="userName" />
			</p>
			<p>
				<form:label path="password">Password</form:label>
				<form:password path="password" />
				<form:errors path="password" />
			</p>
			<p>
				<form:label path="rePassword">Re Password:</form:label>
				<form:password path="rePassword" />
				<form:errors path="rePassword" />
			</p>
			<p>
				<input type="submit" value="Submit" />
			</p>
		</form:form>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>