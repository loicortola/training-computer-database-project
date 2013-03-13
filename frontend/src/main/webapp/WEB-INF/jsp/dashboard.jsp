<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="lbl"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pag" %>
<%@ page import="java.util.*"%>

<jsp:include page="include/header.jsp" />

<section id="main">

	<c:set var="actionPrefix"
		value="?&sortBy=${computers.tableSort.ordinal()}&searchName=${param.searchName}"
		scope="page" />
	<c:set var="sortActionPrefix" value="?&searchName=${param.searchName}"
		scope="page" />

	<div style="position: absolute; top: 70px; right: 20px; z-index:99;">
		Ln: <a href="?ln=fr_FR">FR</a> / <a href="?ln=en_EN">EN</a>
	</div>

	<h1 id="homeTitle">
		<lbl:message code="page.dashboard.title"
			arguments="${computers.computerCount}" />
	</h1>
	<c:if test="${!empty submitAction}">
		<div class="alert-message warning">
			<strong>BAM!</strong> 
			<c:choose>
				<c:when test="${submitAction eq 'add'}">
					<lbl:message code="page.dashboard.confirmAdd" arguments="${computerName}" />				
				</c:when>
				<c:when test="${submitAction eq 'update'}">
					<lbl:message code="page.dashboard.confirmUpdate" arguments="${computerName}" />				
				</c:when>
				<c:when test="${submitAction eq 'delete'}">
					<lbl:message code="page.dashboard.confirmDelete" arguments="${computerName}" />				
				</c:when>
			</c:choose>
			 
		</div>
	</c:if>	
	

	<div id="actions">
		<form action="" method="GET">
			<lbl:message code="form.placeHolder.filterByName" var="phSearchName"/>
			<input type="search" id="searchbox" name="searchName"
				value="${param.searchName}" placeholder="${phSearchName}">
			<input type="submit" id="searchsubmit"
				value="<lbl:message code="form.submit.filterByName"/>"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer.html"><lbl:message
				code="page.dashboard.addNewComputer" /></a>
	</div>

	<c:if test="${ computers.size() == 0 }">
		<div class="well">
			<em><lbl:message code="page.dashboard.noRecord" /></em>
		</div>
	</c:if>
	<c:if test="${ computers.size() > 0 }">
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<lbl:message code="form.computer.name" var="lblComputerName" />
					<lbl:message code="form.computer.introduced"
						var="lblComputerIntroduced" />
					<lbl:message code="form.computer.discontinued"
						var="lblComputerDiscontinued" />
					<lbl:message code="form.company.name" var="lblCompanyName" />
					<!-- Table header for Computer Name -->
					<pag:tableSortedHead thFieldA="0" thFieldD="1" thLabel="${lblComputerName}" colId="2" 
										 selected="${computers.tableSort.ordinal()==0 || computers.tableSort.ordinal()==1}" 
										 sortActionPrefix="${sortActionPrefix}" 
										 orderAsc="${computers.tableSort.ordinal()==0}" />
					<!-- Table header for Introduced Date -->
					<pag:tableSortedHead thFieldA="2" thFieldD="3" thLabel="${lblComputerIntroduced}" colId="3" 
										 selected="${computers.tableSort.ordinal()==2 || computers.tableSort.ordinal()==3}" 
										 sortActionPrefix="${sortActionPrefix}" 
										 orderAsc="${computers.tableSort.ordinal()==2}" />
					<!-- Table header for Discontinued Date -->
					<pag:tableSortedHead thFieldA="4" thFieldD="5" thLabel="${lblComputerDiscontinued}" colId="4" 
										 selected="${computers.tableSort.ordinal()==4 || computers.tableSort.ordinal()==5}" 
										 sortActionPrefix="${sortActionPrefix}" 
										 orderAsc="${computers.tableSort.ordinal()==4}" />
					<!-- Table header for Company -->
					<pag:tableSortedHead thFieldA="6" thFieldD="7" thLabel="${lblCompanyName}" colId="5" 
										 selected="${computers.tableSort.ordinal()==6 || computers.tableSort.ordinal()==7}" 
										 sortActionPrefix="${sortActionPrefix}" 
										 orderAsc="${computers.tableSort.ordinal()==6}" />
				</tr>
			</thead>
			<tbody>

				<c:forEach var="computer" items="${computers.list()}">
					<tr>
						<td><a href="editComputer.html?editId=${computer.id}">${computer.name}</a></td>
						<td><joda:format value="${computer.introduced}" pattern="yyyy-MM-dd" /></td>
						<td><joda:format value="${computer.discontinued}" pattern="yyyy-MM-dd" /></td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div id="pagination" class="pagination">
		<pag:pagination actionPrefix="${actionPrefix}" page="${currentPage}" pageCount="${pageCount}" resultsPerPage="${resultsPerPage}"/>
	</div>
</section>

<jsp:include page="include/footer.jsp" />
