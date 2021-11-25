
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="es.carlos.Datos" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset='utf-8' />
<title>Registro por pasos - Datos personales</title>
</head>
<body>

<c:import url="http://localhost:8080/examen/navegacion.jsp">
	  <c:param name="pasoActivo" value="${requestScope.pasoActivo}"/>
</c:import>

<form action="Paso2_datosProfesionales">
<fieldset>
	<legend>Datos personales</legend>
	<label>Nombre</label> <input name="nombre" id="nombre" <c:out value=" ${requestScope.nombre}"/> />
	<label>Apellidos</label> <input name="apellidos" id="apellidos" <c:out  value="${requestScope.apellidos}" /> /> <br />
  <label>Fecha de nacimiento</label> <input type="date" name="fechaNacimiento" <c:out  value="${requestScope.fechaNacimiento}" /> /> <br />
  <fieldset>
    <legend>Género</legend>
    <c:forEach items="${Datos.mapGeneros}" var="genero">
      <label>${genero.value}</label>
      <input type="radio" name="genero" value="${genero.key}" ${requestScope.genero == genero.key ? 'checked':''} >
    </c:forEach>
  </fieldset>
	<br />
  <label for="casado">Casado o Pareja de hecho</label>
	<input type="checkbox" name="casado" id="casado" value="CPH" />
	<label for="hijos">Hijos</label>
	<input type="checkbox" name="hijos" id="hijos" value="HJS" />
	<br />
	<label for="nacionalidad">Nacionalidades</label>
  <select name="nacionalidad" id="nacionalidad" multiple="multiple" size="4">
  <c:forEach items="${Datos.mapPaises}" var="pais">
    <option value="${pais.key}" ${requestScope.nacionalidades[0] == pais.key ? 'selected':''} >${pais.value}</option>
  </c:forEach>
  </select>
	<br />
	<input type="submit" value="Grabar información e ir al paso 2 - Datos profesionales" />
	</fieldset>
</form>

</body>
</html>


