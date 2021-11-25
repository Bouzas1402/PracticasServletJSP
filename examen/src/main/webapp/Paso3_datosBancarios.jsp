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

<form action="Resumen">
<fieldset>
	<legend>Datos bancarios</legend>
  <label for="cuentaCorriente">Cuenta corriente</label>
  <input name="cuentaCorriente" id="cuentaCorriente" value="${requestScope.cuentaCorriente}" />
  <br />
	<input type="submit" value="Grabar información e ir al resumen final" />
</fieldset>
</form>

</body>
</html>
