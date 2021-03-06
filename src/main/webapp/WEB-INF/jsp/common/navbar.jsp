<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#">Project name</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li class="nav-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>
				</ul>
				<% if(request.getUserPrincipal()==null){ %>
				<form class="navbar-form pull-right"
					action="<c:url value="/j_spring_security_check"/>" method="post">
					<input class="span2" type="text" name="j_username"
						placeholder="Email"> <input class="span2" type="password"
						name="j_password" placeholder="Password">
					<button type="submit" class="btn">Sign in</button>
					<a href="<c:url value="/signup"/>" class="btn btn-success">Register</a>
				</form>
				<% }else{ %>
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><%= request.getUserPrincipal().getName() %> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/logout"/>"><spring:message code="logout"/></a></li>
						</ul>
					</li>
 					
 				</ul>
 				<% } %>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>

<div style="margin-top: 30px;">
	&nbsp;<!-- empty div for push down body content -->
</div>