<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE HTML>
<html>
<head>

<jsp:include page="common/htmlhead.jsp" />

<title>My title</title>
<meta name="description" content="">

</head>
<body>

	<jsp:include page="common/navbar.jsp" />

	<div class="container">

		<div class="row">
			<div class="span5">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn btn-success" href="#">View details &raquo;</a>
				</p>
			</div>
			<div class="span7">
				<h2>Register</h2>

				<form:form modelAttribute="signupForm" cssClass="form-horizontal" method="post">
								
					<div class="control-group">
						<label class="control-label" for="firstName">first name</label>
						<div class="controls">
							<form:input path="firstName" placeholder="John"/>
							<form:errors path="firstName" cssClass="red" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="lastName">last name</label>
						<div class="controls">
							<form:input path="lastName" placeholder="Smith"/>
							<form:errors path="lastName" cssClass="red" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">username</label>
						<div class="controls">
							<form:input path="username" placeholder="jsmith"/>
							<form:errors path="username" cssClass="red" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">email</label>
						<div class="controls">
							<form:input path="email" placeholder="jsmith@mail.com"/>
							<form:errors path="email" cssClass="red" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">email 2</label>
						<div class="controls">
							<form:input path="email2" placeholder="jsmith@mail.com"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">password</label>
						<div class="controls">
							<form:password path="password" placeholder="*****"/>
							<form:errors path="password" cssClass="red" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">password 2</label>
						<div class="controls">
							<form:password path="password2" placeholder="*****"/>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<%--
							<label class="checkbox"> <input type="checkbox">
								Remember me
							</label>
							 --%>
							<button type="submit" class="btn btn-primary">Register</button>
						</div>
					</div>
				</form:form>

			</div>
		</div>

		<jsp:include page="common/footer.jsp" />

	</div>
	<!-- /container -->


</body>
</html>
