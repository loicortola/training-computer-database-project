<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="routerBean" class="com.formation.project.computerDatabase.base.Router" scope="session" />    
<html>
  <head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  </head>
  <body>
    <% 
		if( routerBean.getUrl() != "")  
		{
	%>  
		<jsp:include page="<%= routerBean.getUrl() %>" />
	<% 
		}
	%>
  </body>
</html>