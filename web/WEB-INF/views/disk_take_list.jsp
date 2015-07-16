<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tags.jsp" %>

<%@include file="/WEB-INF/views/head.jsp" %>

<%@include file="/WEB-INF/views/menu.jsp" %>

<h2 class="standart-head" >Диски, которые я взял</h2>

<div class="standart-div">
  <table class="table"> 
    <tr>
      <th>Диск</th>
      <th>владелец</th>
      <th> </th>
    </tr>
    <c:forEach items="${diskList}" var="disk" >
      <tr>
        <td>${disk.name}</td>
        <td>${disk.owner.surname} ${disk.owner.name}</td>
        <td> <a сlass="btn btn-default" role="button" href="<c:url value="/Disk/toOwner?diskId=${disk.diskId}" />">Вернуть обратно владельцу</a> </td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@include file="/WEB-INF/views/bottom.jsp" %>
