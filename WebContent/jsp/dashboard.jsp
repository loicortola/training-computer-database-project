<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>
<%
	ArrayList<Computer> computers 	= (ArrayList<Computer>) request.getAttribute("computers");
	String searchName				= (String)	request.getParameter("searchName");
	Integer computerCount 			= (Integer) request.getAttribute("computerCount");
	Integer pageCount 				= (Integer) request.getAttribute("pageCount");
	Integer currentPage				= (Integer) request.getAttribute("currentPage");
	Integer resultsPerPage			= (Integer) request.getAttribute("resultsPerPage");
%>
<h1 id="homeTitle">Hey Baby! Wanna get a look at those computers? We have ${computerCount}</h1>

    <div id="actions">
        <form action="" method="GET">
            <input type="search" id="searchbox" name="searchName" value="<% if(searchName != null && !searchName.equals("")) out.print(searchName); %>" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        </form>
        <a class="btn success" id="add" href="?action=addComputer">Add a new computer</a>
    </div>
    
    <% if(computers.size() == 0) { %>
        
    <div class="well">
        <em>Nothing to display</em>
    </div>
        
    <% } else { %>
        
        <table class="computers zebra-striped">
            <thead>
            	<tr>
                	<th>Computer name</th>
					<th>Introduced</th>
					<th>Discontinued</th>
					<th>Company</th>
				</tr>
            </thead>
            <tbody>

            <% 
				for(Computer computer : computers) {
			%>
		    <tr>
		        <td><a href="?action=editComputer&id=<%= computer.getId() %>"><%= computer.getName() %></a></td>
		        <td><%= computer.getFormatedIntroduced() %></td>
		        <td><%= computer.getFormatedDiscontinued() %></td>
		        <td><%= computer.getCompany().getName() %></td>
		    </tr>
			<%
				}
			%>

            </tbody>
        </table>
	<% } %>
	<div id="pagination" class="pagination">
            <ul>
                
	        <% if(currentPage == 1) { %>
	            <li class="prev disabled">
	            	<a>&larr; Previous</a>
	            </li>
	        <% } else { %>
	            <li class="prev ">
	               	<a href="?page=<%= currentPage-1 %><% if(searchName != null && !searchName.equals("")) out.print("&searchName="+searchName); %>">&larr; Previous</a>
	            </li>
	        <% } %>
	             
                <li class="current">
                    <a>Displaying <%= (currentPage-1)*resultsPerPage+1 %> to <%= (currentPage)*resultsPerPage %> of <%= computerCount %></a>
                </li>
                
            <% if(currentPage == pageCount) { %>
	            <li class="next disabled">
	            	<a>Next &rarr;</a>
	            </li>
	        <% } else { %>
	            <li class="next ">
	               	<a href="?page=<%= currentPage+1 %><% if(searchName != null && !searchName.equals("")) out.print("&searchName="+searchName); %>">Next &rarr;</a>
	            </li>
	        <% } %> 
                
            </ul>
        </div>