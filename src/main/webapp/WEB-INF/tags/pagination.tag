<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="previousPage" required="true"%>
<%@ attribute name="nextPage" required="true"%>
<%@ attribute name="pageCount" required="true"%>
<%@ attribute name="currentPage" required="true"%>
<%@ attribute name="baseUrl" required="true"%>

<div class="pagination">
	<ul>
		<li><a href="<c:url value="${baseUrl}?page=${previousPage}"/>">Previous</a></li>
		<c:forEach begin="0" end="${pageCount - 1}" var="val">
			<c:choose>
				<c:when test="${val==currentPage}">
					<li class="active">
				</c:when>
				<c:otherwise>
					<li>
				</c:otherwise>
			</c:choose>
			<a href="<c:url value="${baseUrl}?page=${val}"/>"> <c:out
					value="${val + 1}" />
			</a>
			</li>
		</c:forEach>
		<li><a href="<c:url value="${baseUrl}?page=${nextPage}"/>">Next</a>
		</li>
	</ul>
</div>