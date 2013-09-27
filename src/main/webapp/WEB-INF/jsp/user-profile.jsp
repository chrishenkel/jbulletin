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

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/jbulletin.css"/>" />

<script src='http://codeorigin.jquery.com/jquery-2.0.3.min.js'
	type='text/javascript'></script>
<script type="text/javascript">
	//document.write("\<script src='//ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js' type='text/javascript'>\<\/script>");

	function myFunction() {
		alert("Hello World!");
		return true;
	}
	$(document).ready(function() {
		// Handler for .ready() called.
		$('#changeAvatar').click(function() {
			$('#fileInput').click();
		});

		$("#fileInput").change(function() {
			$("#fileForm").submit();
		});
	});
</script>
</head>
<body>
	<div class="container">
		<t:navbar profile="active" />
		<img src="<c:url value="/profile/${userSession.userDetails.id}/avatar"/>"
			width="140" height="140" /> <a id="changeAvatar" name="changeAvatar"
			class="btn btn-success btn-large"><i class="icon-white icon-tag"></i>Change
			Avatar</a>

		<c:url var="avatarEndPoint"
			value="/profile/${userSession.userDetails.id}/avatar" />

		<form:form modelAttribute="uploadForm" id="fileForm" type="hidden"
			action="${avatarEndPoint}" method="post"
			enctype="multipart/form-data">
			<input style="visibility: hidden;" type="file" name="file"
				id="fileInput" />
		</form:form>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>