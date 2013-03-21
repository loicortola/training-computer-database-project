<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="lbl"%>
<%@ page import="java.util.*"%>

<jsp:include page="include/header.jsp" />
	<section id="main">
	<h1><lbl:message code="page.editComputer.title"/></h1>
	
	<form:form method="POST" commandName="computerForm">
		<form:hidden path="id" />
		<fieldset>
			<c:choose>
				<c:when test="${empty result.getFieldError('name')}">
					<div class="clearfix">
				</c:when>
				<c:otherwise>
					<div class="clearfix error">
				</c:otherwise>
			</c:choose>
				<label for="name"><lbl:message code="form.computer.name"/></label>
				<div class="input">
	
					<form:input path="name" />
	
					<span class="help-inline"><lbl:message code="form.help.required"/></span>
				</div>
			</div>
	
			<c:choose>
				<c:when test="${empty result.getFieldError('introduced')}">
					<div class="clearfix">
				</c:when>
				<c:otherwise>
					<div class="clearfix error">
				</c:otherwise>
			</c:choose>
				<label for="introduced"><lbl:message code="form.computer.introduced"/></label>
				<div class="input">
					<form:input path="introduced" />
					<span class="help-inline"><lbl:message code="form.help.dateFormat"/></span>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty result.getFieldError('discontinued')}">
					<div class="clearfix">
				</c:when>
				<c:otherwise>
					<div class="clearfix error">
				</c:otherwise>
			</c:choose>
				<label for="discontinued"><lbl:message code="form.computer.discontinued"/></label>
				<div class="input">
					<form:input path="discontinued" />
					<span class="help-inline"><lbl:message code="form.help.dateFormat"/></span>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty result.getFieldError('company')}">
					<div class="clearfix">
				</c:when>
				<c:otherwise>
					<div class="clearfix error">
				</c:otherwise>
			</c:choose>
				<label for="company"><lbl:message code="form.company.name"/></label>
				<div class="input">
					<form:select path="company">
						<form:option value="" label="--" />			
						<form:options items="${companies}" itemValue="id" itemLabel="name" />						
					</form:select>
				</div>
			</div>
	
		</fieldset>
		<div class="actions">
			<input type="submit" value="<lbl:message code="form.submit.save"/>" class="btn primary">
			or <a href="dashboard.html" class="btn"><lbl:message code="form.submit.cancel"/></a>
		</div>
	</form:form>
	
	<form action="deleteComputer.html?id=${computerForm.id}" method="POST" class="topRight">
	
		<input type="button" value="<lbl:message code="form.submit.deleteComputer"/>" class="btn danger" onclick="if(confirm('<lbl:message code="form.help.submitDelete"/>')){this.form.submit();}">
	
	</form>
<jsp:include page="include/footer.jsp" />