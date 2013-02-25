<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>
<%
	ArrayList<Computer> computers = (ArrayList<Computer>) request.getAttribute("computers");
%>
<h1 id="homeTitle">Hey Baby! Wanna get a look at those computers?</h1>

    <div class="alert-message warning">
    	<strong>Done!</strong>
    </div>
    <div id="actions">
        <form action="" method="GET">
            <input type="search" id="searchbox" name="searchName" value="" placeholder="Filter by computer name...">
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
		        <td><%= computer.getName() %></td>
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