<%@ page import="java.util.*"%>
<%@ page import="com.formation.project.computerDatabase.base.*"%>
<%
	ArrayList<Company> companies 	= (ArrayList<Company>) request.getAttribute("companies");
	String reqParamName 			= request.getParameter("name");
	String reqParamIntroduced 		= request.getParameter("introduced");
	String reqParamDiscontinued 	= request.getParameter("discontinued");
	String reqParamCompany 			= request.getParameter("company");
	
	String nameError				= (String) request.getAttribute("nameError");
	String introducedError			= (String) request.getAttribute("introducedError");
	String discontinuedError		= (String) request.getAttribute("discontinuedError");
	String companyError				= (String) request.getAttribute("companyError");
%>

<h1>Add a computer</h1>

<form action="?action=submitAddComputer" method="POST">
	<fieldset>
		<div class="clearfix <% if(nameError != null) out.println("error"); %>">
			<label for="name">Computer name</label>
			<div class="input">

				<input type="text" id="name" name="name"
					value="<% if(reqParamName != null) out.print(reqParamName); %>">

				<span class="help-inline">Required</span>
			</div>
		</div>

		<div class="clearfix <% if(introducedError != null) out.println("error"); %>">
			<label for="introduced">Introduced date</label>
			<div class="input">

				<input type="text" id="introduced" name="introduced"
					value="<% if(reqParamIntroduced != null) out.print(reqParamIntroduced); %>">

				<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
			</div>
		</div>
		<div class="clearfix <% if(discontinuedError != null) out.println("error"); %>">
			<label for="discontinued">Discontinued date</label>
			<div class="input">

				<input type="text" id="discontinued" name="discontinued"
					value="<% if(reqParamDiscontinued != null) out.print(reqParamDiscontinued); %>">

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
						<option value="<%= company.getId() %>" <% if(reqParamCompany != null && company.getId().toString().equals(reqParamCompany)) out.print("selected"); %>>
							<%= company.getName() %>
						</option>
					<%
						}
					%>
					</select> <span class="help-inline"></span>
				<%
					}
				%>
			</div>
		</div>

	</fieldset>
	<div class="actions">
		<input type="submit" value="Create this computer" class="btn primary">
		or <a href="CoreServlet" class="btn">Cancel</a>
	</div>
</form>
