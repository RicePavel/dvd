<%-- 
    Document   : registration
    Created on : 15.07.2015, 21:38:25
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tags.jsp" %>

<%@include file="/WEB-INF/views/head.jsp" %>

<c:if test="${success}" >
  Вы успешно зерегистрировались! Теперь вы можете <a href="<c:url value="/login.jsp" />">войти в систему.</a>
</c:if>

<c:if test="${! success}">
  
 <%@include file="/WEB-INF/views/errors.jsp" %>

  
  <form action="<c:url value="/User/registration" />"> 
    Логин: <input type="text" name="login" /> <br/>
    пароль: <input type="password" name="password" /> <br/>
    Повторите пароль: <input type="password" name="password2" /> <br/>
    <input type="submit" name="submit" value="Регистрироваться" />
  </form>
</c:if>

<%@include file="/WEB-INF/views/bottom.jsp" %>
