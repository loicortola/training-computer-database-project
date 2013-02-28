<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.formation.project.computerDatabase.base.*"%>

<h1>Edit a computer</h1>

<form action="?action=submitEditComputer&id=${computer.id}" method="POST">
	<fieldset>
		<c:choose>
			<c:when test="${empty nameError}">
				<div class="clearfix">
			</c:when>
			<c:otherwise>
				<div class="clearfix error">
			</c:otherwise>
		</c:choose>
			<label for="name">Computer name</label>
			<div class="input">

				<input type="text" id="name" name="name"
					value="${computer.name}">

				<span class="help-inline">Required</span>
			</div>
		</div>

		<c:choose>
			<c:when test="${empty introducedError}">
				<div class="clearfix">
			</c:when>
			<c:otherwise>
				<div class="clearfix error">
			</c:otherwise>
		</c:choose>
			<label for="introduced">Introduced date</label>
			<div class="input">

				<c:choose>
					<c:when test="${!empty computer.introduced }">
						<input type="text" id="introduced" name="introduced" value="${computer.getFormatedIntroduced()}">
					</c:when>
					<c:otherwise>
						<input type="text" id="introduced" name="introduced">
					</c:otherwise>
				</c:choose>
				
				<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
			</div>
		</div>
		<c:choose>
			<c:when test="${empty discontinuedError}">
				<div class="clearfix">
			</c:when>
			<c:otherwise>
				<div class="clearfix error">
			</c:otherwise>
		</c:choose>
			<label for="discontinued">Discontinued date</label>
			<div class="input">

				<c:choose>
					<c:when test="${!empty computer.discontinued }">
						<input type="text" id="discontinued" name="discontinued" value="${computer.getFormatedDiscontinued()}">
					</c:when>
					<c:otherwise>
						<input type="text" id="discontinued" name="discontinued">
					</c:otherwise>
				</c:choose>
				
				<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
			</div>
		</div>
		<c:choose>
			<c:when test="${empty companyError}">
				<div class="clearfix">
			</c:when>
			<c:otherwise>
				<div class="clearfix error">
			</c:otherwise>
		</c:choose>
			<label for="company">Company</label>
			<div class="input">

				<c:choose>
					<c:when test="${!empty companies}">
						<select id="company" name="company">
							<c:forEach var="company" items="${companies}">
								<c:choose>
									<c:when test="${computer.company.id == company.id}">
										
												<option value="${company.id}" selected>
													${company.getName()}
												</option>
									</c:when>
									<c:otherwise>
										<option value="${company.id}">
											${company.name}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</c:when>
					<c:otherwise>
						<select id="company" name="company" disabled="true"> </select>
					</c:otherwise>
				</c:choose> 
				
			</div>
		</div>

	</fieldset>
	<div class="actions">
		<input type="submit" value="Save modifications" class="btn primary">
		or <a href="CoreServlet" class="btn">Cancel</a>
	</div>
</form>

<form action="?action=submitDeleteComputer&id=${computer.id}" method="POST" class="topRight">

	<input type="button" value="Delete this computer" class="btn danger" onclick="if(confirm('Are you sure you want to delete that computer?')){this.form.submit();}">

</form>