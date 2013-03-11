<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="lbl"%>
<%@ page import="java.util.*" %>

<jsp:include page="include/header.jsp" />
<section id="main">
  	
<c:set var="actionPrefix" value="?&sortBy=${computers.tableSort.ordinal()}&searchName=${searchName}" scope="page" />
<c:set var="sortActionPrefix" value="?&searchName=${searchName}" scope="page" />

<a href="#" onclick='window.location.href="?ln=fr_FR"'>fr</a>
<a href="#" onclick='window.location.href="?ln=en_EN"'>en</a>
<div style="position:absolute;top:0px;right:20px;"> Ln: <a href="?ln=fr_FR">FR</a> / <a href="?ln=en_EN">EN</a></div>

<h1 id="homeTitle">
	<lbl:message code="page.dashboard.title" arguments="${computers.computerCount}" />
</h1>

    <div id="actions">
        <form action="" method="GET">
            <input type="search" id="searchbox" name="searchName" value="${param.searchName}" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="<lbl:message code="form.submit.filterByName"/>" class="btn primary">
        </form>
        <a class="btn success" id="add" href="addComputer.html"><lbl:message code="page.dashboard.addNewComputer"/></a>
    </div>
    	
    <c:if test="${ computers.size() == 0 }">    
    	<div class="well">
        	<em><lbl:message code="page.dashboard.noRecord"/></em>
    	</div>
    </c:if>
    <c:if test="${ computers.size() > 0 }">
        <table class="computers zebra-striped">
            <thead>
            	<tr>
            		<!-- Variable declarations for passing labels as parameters -->
					<lbl:message code="form.computer.name" var="lblComputerName"/>
					<lbl:message code="form.computer.introduced" var="lblComputerIntroduced"/>
					<lbl:message code="form.computer.discontinued" var="lblComputerDiscontinued"/>
					<lbl:message code="form.company.name" var="lblCompanyName"/>
            		<!-- Table header for Computer Name -->             	
                	<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='2' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==0 || computers.tableSort.ordinal()==1}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==0}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='0' name="thFieldA"/>
						<jsp:param value='1' name="thFieldD"/>
						<jsp:param value='${lblComputerName}' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Introduced Date -->
                	<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='3' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==2 || computers.tableSort.ordinal()==3}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==2}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='2' name="thFieldA"/>
						<jsp:param value='3' name="thFieldD"/>
						<jsp:param value='${lblComputerIntroduced}' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Discontinued Date -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='4' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==4 || computers.tableSort.ordinal()==5}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==4}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='4' name="thFieldA"/>
						<jsp:param value='5' name="thFieldD"/>
						<jsp:param value='${lblComputerDiscontinued}' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Company -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='5' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==6 || computers.tableSort.ordinal()==7}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==6}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='6' name="thFieldA"/>
						<jsp:param value='7' name="thFieldD"/>
						<jsp:param value='${lblCompanyName}' name="thLabel"/>
					</jsp:include>	
					
				</tr>
            </thead>
            <tbody>

			<c:forEach var="computer" items="${computers.list()}">
			    <tr>
			        <td><a href="editComputer.html?editId=${computer.id}">${computer.name}</a></td>
			        <td>${computer.getFormatedIntroduced()}</td>
			        <td>${computer.getFormatedDiscontinued()}</td>
			        <td>${computer.company.name}</td>
			    </tr>
			</c:forEach>
            </tbody>
        </table>
	</c:if>
	<div id="pagination" class="pagination">
            <ul>
                
	        <c:if test="${ currentPage == 1 }">
	            <li class="prev disabled">
	            	<a>&larr; <lbl:message code="form.pagination.previous" /></a>
	            </li>
	        </c:if>
	        <c:if test="${ currentPage > 1 }">
	            <li class="prev ">
	               	<a href="${actionPrefix}&page=${currentPage-1}">&larr; <lbl:message code="form.pagination.previous" /></a>
	            </li>
	        </c:if>
	             
                <li class="current">
                    <a><lbl:message code="form.pagination.display" arguments="${(currentPage-1)*resultsPerPage+1},${(currentPage)*resultsPerPage},${computers.computerCount}"/></a>
                </li>
                
            <c:if test="${ currentPage == pageCount }">
	            <li class="next disabled">
	            	<a><lbl:message code="form.pagination.next" /> &rarr;</a>
	            </li>
	        </c:if>
	        <c:if test="${ currentPage != pageCount }">
	            <li class="next ">
	               	<a href="${actionPrefix}&page=${currentPage+1}"><lbl:message code="form.pagination.next" /> &rarr;</a>
	            </li>
	        </c:if>
                
            </ul>
        </div>
</section>

<jsp:include page="include/footer.jsp" />
        