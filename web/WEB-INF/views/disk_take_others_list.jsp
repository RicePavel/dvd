<%-- 
    Document   : disk_give_list
    Created on : 14.07.2015, 17:49:09
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tags.jsp" %>

<%@include file="/WEB-INF/views/head.jsp" %>

<%@include file="/WEB-INF/views/menu.jsp" %>

<h2>Диски, которые у меня взяли</h2>

<table> 
  <tr>
    <th>Диск</th>
    <th>кто взял</th>
    <th> </th>
  </tr>
  <c:forEach items="${takenItemList}" var="item" >
    <tr>
    <td>${item.disk.name}</td>
    <td>${item.user.surname} ${item.user.name}</td>
    </tr>
  </c:forEach>
</table>

<%@include file="/WEB-INF/views/bottom.jsp" %>