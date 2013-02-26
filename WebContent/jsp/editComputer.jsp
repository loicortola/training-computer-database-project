<%@ page import="java.util.*"%>
<%@ page import="com.formation.project.computerDatabase.base.*"%>
<%
	ArrayList<Company> companies 	= (ArrayList<Company>) request.getAttribute("companies");
	Computer computer				= (Computer) request.getAttribute("computer");
	
	String nameError				= (String) request.getAttribute("nameError");
	String introducedError			= (String) request.getAttribute("introducedError");
	String discontinuedError		= (String) request.getAttribute("discontinuedError");
	String companyError				= (String) request.getAttribute("companyError");
%>

<h1>Edit a computer</h1>

<form action="?action=submitEditComputer&id=<%= computer.getId() %>" method="POST">
	<fieldset>
		<div class="clearfix <% if(nameError != null) out.println("error"); %>">
			<label for="name">Computer name</label>
			<div class="input">

				<input type="text" id="name" name="name"
					value="<% if(computer.getName() != null) out.print(computer.getName()); %>">

				<span class="help-inline">Required</span>
			</div>
		</div>

		<div class="clearfix <% if(introducedError != null) out.println("error"); %>">
			<label for="introduced">Introduced date</label>
			<div class="input">

				<input type="text" id="introduced" name="introduced"
					value="<% if(computer.getIntroduced() != null) out.print(computer.getFormatedIntroduced()); %>">

				<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
			</div>
		</div>
		<div class="clearfix <% if(discontinuedError != null) out.println("error"); %>">
			<label for="discontinued">Discontinued date</label>
			<div class="input">

				<input type="text" id="discontinued" name="discontinued"
					value="<% if(computer.getDiscontinued() != null) out.print(computer.getFormatedDiscontinued()); %>">

				<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
			</div>
		</div>
		<div class="clearfix <% if(companyError != null) out.println("error"); %>">
			<label for="company">Company</label>
			<div class="input">

				<% if(companies == null) { %>
					<select id="company" name="company" disabled="true"> </select>				
				<% } else { %>
					<select id="company" name="company">
					<% 
						for(Company company : companies) {
					%>
						<option value="<%= company.getId() %>" <% if(computer.getCompany().getId() != null && company.getId().toString().equals(computer.getCompany().getId())) out.print("selected"); %>>
							<%= company.getName() %>
						</option>
					<%
						}
					%>
					</select><span class="help-inline"></span>
				<%
					}
				%>
			</div>
		</div>

	</fieldset>
	<div class="actions">
		<input type="submit" value="Save modifications" class="btn primary">
		or <a href="CoreServlet" class="btn">Cancel</a>
	</div>
</form>

<form action="?action=submitEditComputer" method="POST" class="topRight">

	<input type="button" value="Delete this computer" class="btn danger" onclick="if(confirm('Are you sure you want to delete that computer?')){this.form.submit();}">

</form>