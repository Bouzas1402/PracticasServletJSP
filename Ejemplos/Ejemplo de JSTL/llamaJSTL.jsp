<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home Page</title>

	<style>
	table,th,td {
		border:1px solid black;
	}
	</style>
</head>
<body>
	<h2>Ejemplo de uso de etiquetas JSTL y EL</h2>
	<%-- uso de c:forEach y c:out para iterar sobre una lista y visualizarla como una tabla HTML --%>
	<table>
	<tbody>
	<tr><th>Empno</th><th>Ename</th><th>Job</th></tr>
	<%-- no es necesario escribir un getAttribute para recuperar el atributo pasado por el servlet --%>
	<c:forEach items="${requestScope.listaEmpleados}" var="emp">
		<tr>
			<td><c:out value="${emp.empno}"></c:out></td>
			<td><c:out value="${emp.ename}"></c:out></td>
			<td>
				<c:set var="oficio" scope="request" value="${emp.job}"/>
				<%-- uso de c:choose + c:when + c:otherwise para realizar un tratamiento condicional --%>
				<c:choose>
    			<c:when test="${oficio eq 'Manager'}">
    				<b><c:out value="${emp.job}"></c:out></b>
    			</c:when>
			    <c:otherwise>
        			<c:out value="${emp.job}"></c:out>
    			</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<br />

	<%-- ejemplo de c:if y c:out con "escapado" de HTML  --%>
	<c:if test="${requestScope.htmlTagData ne null }">
		<c:out value="${requestScope.htmlTagData}" escapeXml="true"></c:out>
	</c:if>
	<br /><br />

	<%-- uso de c:set para establecer el valor de una variable --%>
	<c:set var="id" value="5" scope="request"></c:set>
	<c:out value="${requestScope.id }" ></c:out>
	<br />

	<%-- uso de c:catch para el tratamiento de excepciones --%>
	<c:catch var ="exception">
	   <% int x = 5/0;%>
	</c:catch>

	<c:if test = "${exception ne null}">
	   <p>Excepción producida: ${exception} <br />
	   Mensaje de la excepción: ${exception.message}</p>
	</c:if>

	<%-- uso de c:url para crear enlaces <a> --%>
	<a href="<c:url value="${requestScope.url }"></c:url>">JournalDev</a>
</body>
</html>
