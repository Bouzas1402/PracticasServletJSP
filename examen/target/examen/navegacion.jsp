<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="es.carlos.Datos" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="navegacion">
  <c:forEach var="paso" items="${Datos.mapNavegacion}">
    <a href="${paso.value[0]}">
      <img src="${pageContext.request.contextPath}/img/${paso.value[1]}" title="${paso.value[2]}"
         style="width:40px; height:40px;<c:if test = "${!paso.key.equals(param.pasoActivo)}">opacity:0.4;</c:if>"/></a>
  </c:forEach>
</div>
