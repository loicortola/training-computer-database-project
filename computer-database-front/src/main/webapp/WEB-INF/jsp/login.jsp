<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<spring:message code="login.page.title" />
	</h1>
	<div style="position: absolute; top: 70px; right: 20px; z-index:99;">
		Ln: <a href="?ln=fr_FR">FR</a> / <a href="?ln=en_EN">EN</a>
		<br/><a href="logout"><lbl:message code="form.logout"/></a>
	</div>
	<div style="display:block;text-align:center;">
		<form name='f' style="width:30%;" action='j_spring_security_check' method='POST'>
			<table>
				<tr>
					<td><spring:message code="form.user" />:</td>
					<td><input type='text' name='j_username' value=''></td>
				</tr>
				<tr>
					<td><spring:message code="form.password" />:</td>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="<spring:message code="form.login"/>" /></td>
				</tr>
			</table>
		</form>
	</div>
</section>
<jsp:include page="include/footer.jsp" />