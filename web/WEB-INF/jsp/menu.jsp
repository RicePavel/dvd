<%-- 
    Document   : menu
    Created on : 14.07.2015, 16:59:24
    Author     : Новый профиль
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="margin: 50px;">
  <a class="btn btn-default" role="button" href="<c:url value="/Disk/allMyDiskList" />" >Все мои диски</a>
  <a class="btn btn-default" role="button" href="<c:url value="/Disk/add" />">Добавить новый диск</a>
  <a class="btn btn-default" role="button" href="<c:url value="/Disk/free" />">Свободные диски</a>
  <a class="btn btn-default" role="button" href="<c:url value="/Disk/takeList" />">Диски, взятые мной</a>
  <a class="btn btn-default" role="button" href="<c:url value="/Disk/takeOthers" />">Диски, отданные другим</a>
  <a class="btn btn-default" role="button" href="<c:url value="/logout" />" >Выйти из системы</a>
</div>
