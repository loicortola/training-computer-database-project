<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>
<%
	ArrayList<Computer> computers 	= (ArrayList<Computer>) request.getAttribute("computers");
	String searchName				= (String)	request.getParameter("searchName");
	String sortBy					= (String)	request.getAttribute("sortBy");
	Integer computerCount 			= (Integer) request.getAttribute("computerCount");
	Integer pageCount 				= (Integer) request.getAttribute("pageCount");
	Integer currentPage				= (Integer) request.getAttribute("currentPage");
	Integer resultsPerPage			= (Integer) request.getAttribute("resultsPerPage");
	
	String actionPrefix				= "?";
	String newSortActionPrefix		= "?";
	if(sortBy != null)
		actionPrefix += "&sortBy=" + sortBy;
	if(searchName != null && !searchName.equals("")) {
		actionPrefix += "&searchName=" + searchName;
		newSortActionPrefix += "&searchName=" + searchName;
	}
	
	String newSortByName 			= "1";
	String newSortByIntroduced 		= "1";
	String newSortByDiscontinued 	= "1";
	String newSortByCompany 		= "1";
	
	if(sortBy.equals("name1"))
		newSortByName = "0";
	if(sortBy.equals("introduced1"))
		newSortByIntroduced = "0";
	if(sortBy.equals("discontinued1"))
		newSortByDiscontinued = "0";
	if(sortBy.equals("company1"))
		newSortByCompany = "0";
	
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
                	<th class="col2 header <% if(sortBy.contains("name")) { if(newSortByName.equals("1")) out.print("headerSortDown"); else out.print("headerSortUp");} %>"><a href="<%= newSortActionPrefix %>&sortBy=name<%= newSortByName %>">Computer name</a></th>
					<th class="col3 header <% if(sortBy.contains("introduced")) { if(newSortByIntroduced.equals("1")) out.print("headerSortDown"); else out.print("headerSortUp");} %>"><a href="<%= newSortActionPrefix %>&sortBy=introduced<%= newSortByIntroduced %>">Introduced</a></th>
					<th class="col4 header <% if(sortBy.contains("discontinued")) { if(newSortByDiscontinued.equals("1")) out.print("headerSortDown"); else out.print("headerSortUp");} %>"><a href="<%= newSortActionPrefix %>&sortBy=discontinued<%= newSortByDiscontinued %>">Discontinued</a></th>
					<th class="col5 header <% if(sortBy.contains("company")) { if(newSortByCompany.equals("1")) out.print("headerSortDown"); else out.print("headerSortUp");} %>"><a href="<%= newSortActionPrefix %>&sortBy=company<%= newSortByCompany %>">Company</a></th>
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
	               	<a href="<%= actionPrefix %>&page=<%= currentPage-1 %>">&larr; Previous</a>
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
	               	<a href="<%= actionPrefix %>&page=<%= currentPage+1 %>">Next &rarr;</a>
	            </li>
	        <% } %> 
                
            </ul>
        </div>