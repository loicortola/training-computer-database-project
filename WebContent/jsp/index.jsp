<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <link href="css/main.css" rel="stylesheet" media="screen">
  </head>
  <body>
  	<section id="main">
		<c:if test="${!empty routerBean.getUrl()}">
			<jsp:include page="${routerBean.getUrl()}" />
		</c:if>
  	</section>
  </body>
</html>