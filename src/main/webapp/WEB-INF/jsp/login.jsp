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
				<h2>Login</h2>

				<form class="form-horizontal" action="<c:url value="/j_spring_security_check"/>" method="post">
					<div class="control-group">
						<label class="control-label" for="inputEmail">Email</label>
						<div class="controls">
							<input type="text" name="j_username" id="inputEmail" placeholder="Email">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">Password</label>
						<div class="controls">
							<input type="password" name="j_password" id="inputPassword" placeholder="Password">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <input type="checkbox">
								Remember me
							</label>
							<button type="submit" class="btn btn-primary">Sign in</button>
						</div>
					</div>
				</form>

			</div>
		</div>

		<jsp:include page="common/footer.jsp" />

	</div>
	<!-- /container -->


</body>
</html>
