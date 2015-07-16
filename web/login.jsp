<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" type="text/css" href=<c:url value='/css/login.css'/> >
    <title>59 ВУЗ</title>
  </head>
  <body>


    <c:if test="${not empty param.error}">
      <font color="red"> Ошибка:
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
      </c:if>

    
    
    <div class="divline">
      <form method="POST" action="<c:url value="/j_spring_security_check" />">
   
        <input class="namein" tabindex="1" placeholder="USERNAME OR EMAIL *" required="required" name="j_username" id="UserLogin_username" type="text">
    
        <input class="passin" tabindex="2" placeholder="PASSWORD *" required="required" name="j_password" id="UserLogin_password" type="password">
        
    
          <%--
          <tr>
                  <td align="right"><spring:message code="label.remember" /></td>
                  <td><input type="checkbox" name="_spring_security_remember_me" /></td>
          </tr>
          --%>
          
          <input class="subin" type="submit" tabindex="4" name="oversub" value="LOGIN">
          
      </form>
    </div>
          
          <a href="<c:url value="/User/registration" />">Регистрация</a>

  </body>
</html>