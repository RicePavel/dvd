<%-- 
    Document   : disk_add
    Created on : 14.07.2015, 17:17:21
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tags.jsp" %>

<%@include file="/WEB-INF/views/head.jsp" %>

<%@include file="/WEB-INF/views/menu.jsp" %>

<c:if test="${success}" > 
  Диск успешно добавлен!
</c:if>

<%@include file="/WEB-INF/views/errors.jsp" %>

<form:form modelAttribute="disk" >
  Название диска: <br/>
  <form:input path="name" />
  <input type="submit" name="submit" value="Добавить диск" />
</form:form>

  <%@include file="/WEB-INF/views/bottom.jsp" %>
