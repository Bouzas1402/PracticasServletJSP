<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 07/11/2021
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sesiones</title>
</head>
<body>
<form action="index.jsp" method="post">
    <fieldset>
        <legend>Datos personales</legend>
         <br/>
        <label>
            Nombre    <input type="text" name="nombre" value="" />
        </label>
        <label>
            Apellidos   <input type="text" name="apellidos" value="" />
        </label>
         <br/>
        <label>
            Fecha  <input type="date" name="dni" value="" size="10" maxlength="9" />
        </label>
        <br/>
        <label>Genero
        <input type="radio" name="hombre" value="hombre" />
        <input type="radio" name="hombre" value="mujer" />
        <input type="radio" name="otros" value="otros" />
        </label>
        <br/>
        Casado pareja de hecho <br/>
        <input type="radio" name="hombre" value="hombre" />
        <input type="radio" name="hombre" value="mujer" />
        <input type="radio" name="otros" value="otros" />
        <br/>
        <label>Nacionalidad:
            <select>
                <option value="española" selected="selected">Español</option>
                <option value="portuguesa">Portugues</option>
                <option value="francesa">Frances</option>
                <option value="italiana">Italiano</option>
                <option value="alemana">Aleman</option>
            </select>
        </label>
    </fieldset>
</form>
</body>
</html>
