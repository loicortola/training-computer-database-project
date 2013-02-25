<%@ page import="java.util.*" %>
<%@ page import="com.formation.project.computerDatabase.base.*" %>
<%
	ArrayList<Company> companies = (ArrayList<Company>) request.getAttribute("companies");
%>

<h1>Add a computer</h1>

<form action="" method="POST" >
<fieldset>
<div class="clearfix ">
    <label for="name">Computer name</label>
    <div class="input">
        
    <input type="text" id="name" name="name" value="" >

        <span class="help-inline">Required</span> 
    </div>
</div>

<div class="clearfix ">
    <label for="introduced">Introduced date</label>
    <div class="input">
        
    <input type="text" id="introduced" name="introduced" value="" >

        <span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span> 
    </div>
</div>
<div class="clearfix ">
    <label for="discontinued">Discontinued date</label>
    <div class="input">
        
    <input type="text" id="discontinued" name="discontinued" value="" >

        <span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span> 
    </div>
</div>
<div class="clearfix ">
    <label for="company">Company</label>
    <div class="input">
        
    <select id="company" name="company" >
        <% 
			for(Company company : companies) {
		%>
	    <option value="<%= company.getId() %>"> <%= company.getName() %></option>
		<%
			}
		%>
    </select>
        <span class="help-inline"></span> 
    </div>
</div>

</fieldset>
        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or 
            <a href="/CoreServlet" class="btn">Cancel</a> 
        </div>
</form>
