<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag import="org.jbulletin.beans.session.UserSession" %>
<%@ attribute name="userSession" type="org.jbulletin.beans.session.UserSession"%>
<%@ attribute name="login"%>
<%@ attribute name="register"%>
<%@ attribute name="contact"%>
<%@ attribute name="about"%>

<div class="navbar">
	<div class="navbar-inner">
		<a class="brand" href="<c:url value="/"/>">JBulletin Forum</a>
		<ul class="nav">
			<c:choose>
				<c:when test="${userSession.loggedIn}">
				<li><a href="<c:url value="/account/logout"/>">Logout</a></li>
				</c:when>
				<c:otherwise>
					<li class="${login}"><a href="<c:url value="/account/login"/>">Login</a></li>				
					<li class="${register}"><a href="<c:url value="/account/new"/>">Register</a></li>
				</c:otherwise>
			</c:choose>
			<li class="${contact}}/>"><a href="#">Contact</a></li>
			<li class="${about}}/>"><a href="#">About</a></li>
		</ul>
	</div>
</div>