<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>


<c:set var="actionPrefix" value="?&sortBy=${computers.tableSort.ordinal()}&searchName=${searchName}" scope="page" />
<c:set var="sortActionPrefix" value="?&searchName=${searchName}" scope="page" />

<h1 id="homeTitle">Hey Baby! Wanna get a look at those computers? We have ${computers.computerCount}</h1>

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
						<jsp:param value='${computers.tableSort.ordinal()==0 || computers.tableSort.ordinal()==1}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==0}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='0' name="thFieldA"/>
						<jsp:param value='1' name="thFieldD"/>
						<jsp:param value='Computer name' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Introduced Date -->
                	<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='3' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==2 || computers.tableSort.ordinal()==3}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==2}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='2' name="thFieldA"/>
						<jsp:param value='3' name="thFieldD"/>
						<jsp:param value='Introduction date' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Discontinued Date -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='4' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==4 || computers.tableSort.ordinal()==5}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==4}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='4' name="thFieldA"/>
						<jsp:param value='5' name="thFieldD"/>
						<jsp:param value='Discontinued date' name="thLabel"/>
					</jsp:include>
					<!-- Table header for Company -->
					<jsp:include page="include/dashboard_th.jsp">
						<jsp:param value='5' name='colId'/>
						<jsp:param value='${computers.tableSort.ordinal()==6 || computers.tableSort.ordinal()==7}' name='selected'/>
						<jsp:param value='${computers.tableSort.ordinal()==6}' name='orderAsc'/>
						<jsp:param value='${sortActionPrefix}' name='sortActionPrefix'/>
						<jsp:param value='6' name="thFieldA"/>
						<jsp:param value='7' name="thFieldD"/>
						<jsp:param value='Company' name="thLabel"/>
					</jsp:include>	
					
				</tr>
            </thead>
            <tbody>

			<c:forEach var="computer" items="${computers.list()}">
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
                    <a>Displaying ${(currentPage-1)*resultsPerPage+1} to ${(currentPage)*resultsPerPage} of ${computers.computerCount}</a>
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