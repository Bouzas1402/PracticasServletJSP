<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="es.carlos.Datos" %>
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

<form action="Paso3_datosBancarios">
<fieldset>
	<legend>Datos profesionales</legend>
  <label for="departamento">Departamento</label>
  <select name="departamento" id="departamento">
    <c:forEach items="${Datos.mapDepartamentos}" var="departamento">
    <option value="${departamento.key}" ${requestScope.departamento == departamento.key ? 'selected':''} >${departamento.value}</option>
    </c:forEach>
  </select>
  <label for="salario">Salario</label> <input name="salario" id="salario" value="${requestScope.salario}" />
  <br />
	<label for="comentarios">Comentarios</label>
	<textarea name="comentarios" id="comentarios" cols="40" rows="5">${requestScope.comentarios}</textarea>
  <br />
	<input type="submit" value="Grabar información e ir al paso 3 - Datos bancarios" />
	</fieldset>
</form>

</body>
</html>
