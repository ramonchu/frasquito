<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/tags" prefix="spring"%><!DOCTYPE HTML>
<html>
<head>

<jsp:include page="common/htmlhead.jsp" />

<title>My title</title>
<meta name="description" content="">

</head>
<body>

	<jsp:include page="common/navbar.jsp" />

	<div class="container">

		<!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit">
			<h3>
				<spring:message code="error" />
			</h3>
			<p>
				<spring:message code="${msgCode}" />
			</p>
			<p>
				<a class="btn btn-primary btn-large" href="<c:url value="/"/>"><spring:message
						code="continue" /></a>
			</p>
		</div>

		<jsp:include page="common/footer.jsp" />

	</div>
	<!-- /container -->


</body>
</html>
