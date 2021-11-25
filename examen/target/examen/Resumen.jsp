<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
<meta charset='utf-8' />
<title>Registro por pasos - Datos profesionales</title>
</head>
<body>

<jsp:include page="navegacion.jsp">
  <jsp:param name="pasoActivo" value="${requestScope.pasoActivo}"/>
</jsp:include>

<form action="Resumen">
<fieldset>
	<legend>Datos personales</legend>
	Nombre: <b> text="${requestScope.nombre}" </b>
  <br />
	Apellidos: <b>${requestScope.apellidos}</b>
  <br />
	Fecha de nacimiento: <b>${requestScope.fechaNacimiento}</b>
  <br />
	Género: <b>${requestScope.genero.equals("") ? "" : Datos.mapGeneros.get(requestScope.genero)}</b>
  <br />
	Nacionalidades:
  <b><c:forEach items="${requestScope.nacionalidad}" var="pais">${Datos.mapPaises.get(pais)}> &nbsp;</c:forEach></b>
</fieldset>
<fieldset>
	<legend>Datos profesionales</legend>
	Departamento:
  <b>${requestScope.departamento.equals("") ? "" : Datos.mapDepartamentos.get(requestScope.departamento)}</b>
  <br />
	Salario: <b>${requestScope.salario}</b> <br />
	Comentarios: <b>${requestScope.comentarios}</b> <br />
</fieldset>
<fieldset>
	<legend>Datos bancarios</legend>
	Cuenta corriente: <b>${requestScope.cuentaCorriente}</b> <br />
</fieldset>
<a href="${pageContext.request.contextPath}/Paso1_datosPersonales?empezar">Volver al principio</a>
</form>

</body>
</html>
