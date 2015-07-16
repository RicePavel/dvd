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
  <p class="bg-success standart-message">
    Диск успешно добавлен!
  <p>
  </c:if>

  <%@include file="/WEB-INF/views/errors.jsp" %>

  <form:form modelAttribute="disk" role="form" class="standart-form" >
  <div class="form-group">
    <label for="nameInput"> Название диска: </label>
    <form:input path="name"  placeholder="Название диска" class="form-control" id="nameInput" />
  </div>
  <input type="submit" name="submit" value="Добавить диск"  class="btn btn-default" />
</form:form>

<%@include file="/WEB-INF/views/bottom.jsp" %>
