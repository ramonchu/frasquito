<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<form:form modelAttribute="persona" action="addPersona">
		<form:hidden path="id" />
		<label>Name <form:input path="name" />
		</label>
		<label>Surname <form:input path="surname" />
		</label>
		<button type="submit">enviar</button>
	</form:form>
	
	<ul>
	<c:forEach items="${personas}" var="persona">
		<li>${persona.id} - ${persona.name} ${persona.surname}</li>
	</c:forEach>
	</ul>
</body>
</html>
