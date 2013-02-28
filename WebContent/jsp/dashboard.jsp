<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>


<c:set var="actionPrefix" value="?&sortBy=${sortBy}&searchName=${searchName}" scope="page" />
<c:set var="sortActionPrefix" value="?&searchName=${searchName}" scope="page" />

<h1 id="homeTitle">Hey Baby! Wanna get a look at those computers? We have ${computerCount}</h1>

    <div id="actions">
        <form action="" method="GET">
            <input type="search" id="searchbox" name="searchName" value="${param.searchName}" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        </form>
        <a class="btn success" id="add" href="?action=addComputer">Add a new computer</a>
    </div>
    
    <c:if test="${ computers.size() == 0 }">    
    	<div class="well">
        	<em>Nothing to display</em>
    	</div>
    </c:if>
    <c:if test="${ computers.size() > 0 }">
        <table class="computers zebra-striped">
            <thead>
            	<tr>
            		<!-- Table header for Computer Name -->             	
                	<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='2' name='colId'/>
						<jsp:param value='${sortBy.contains("name")}' name='selected'/>
						<jsp:param value='${sortBy.contains("0")}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='name' name="thField"/>
						<jsp:param value='Computer name' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Introduced Date -->
                	<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='3' name='colId'/>
						<jsp:param value='${sortBy.contains("introduced")}' name='selected'/>
						<jsp:param value='${sortBy.contains("0")}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='introduced' name="thField"/>
						<jsp:param value='Introduction date' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Discontinued Date -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='4' name='colId'/>
						<jsp:param value='${sortBy.contains("discontinued")}' name='selected'/>
						<jsp:param value='${sortBy.contains("0")}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='discontinued' name="thField"/>
						<jsp:param value='Discontinued date' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Company -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='5' name='colId'/>
						<jsp:param value='${sortBy.contains("company")}' name='selected'/>
						<jsp:param value='${sortBy.contains("0")}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='company' name="thField"/>
						<jsp:param value='Company' name="thLabel"/>
					</jsp:include>	
					
				</tr>
            </thead>
            <tbody>

			<c:forEach var="computer" items="${computers}">
			    <tr>
			        <td><a href="?action=editComputer&id=${computer.id}">${computer.name}</a></td>
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
	            	<a>&larr; Previous</a>
	            </li>
	        </c:if>
	        <c:if test="${ currentPage > 1 }">
	            <li class="prev ">
	               	<a href="${actionPrefix}&page=${currentPage-1}">&larr; Previous</a>
	            </li>
	        </c:if>
	             
                <li class="current">
                    <a>Displaying ${(currentPage-1)*resultsPerPage+1} to ${(currentPage)*resultsPerPage} of ${computerCount}</a>
                </li>
                
            <c:if test="${ currentPage == pageCount }">
	            <li class="next disabled">
	            	<a>Next &rarr;</a>
	            </li>
	        </c:if>
	        <c:if test="${ currentPage != pageCount }">
	            <li class="next ">
	               	<a href="${actionPrefix}&page=${currentPage+1}">Next &rarr;</a>
	            </li>
	        </c:if>
                
            </ul>
        </div>