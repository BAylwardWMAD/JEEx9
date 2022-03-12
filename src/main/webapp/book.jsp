<%-- 
    Document   : book
    Created on : Mar 12, 2020
    Author     : Brian Aylward


    This view supports a retrieved book or creation of a new book

--%>


<%@page import="edu.nbcc.model.Book"%>
<%@page import="edu.nbcc.model.ErrorModel"%>
<%@page import="edu.nbcc.model.BookModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/jspf/header.jspf"%>
<title>Book</title>
</head>
<body class="p-4">
	<%@include file="WEB-INF/jspf/navigation.jspf"%>
	<h2>Book</h2>

	<%
	if (request.getAttribute("vm") != null) {
		BookModel vm = (BookModel) request.getAttribute("vm");
		String name = vm.getBook().getName() != null ? vm.getBook().getName() : "";
		int id = vm.getBook().getId() != 0 ? vm.getBook().getId() : 0;
		double price = vm.getBook().getPrice() != 0 ? vm.getBook().getPrice() : 0;
		int term = vm.getBook().getTerm() != 0 ? vm.getBook().getTerm() : 0;
	%>
	<form method="POST" action="save">
		<table class="table">
			<!-- Display details in view mode -->

			<tr>
				<td><label>Book Id:</label></td>
				<td><input type="hidden" value='<%=id%>' name="hdnBookId" /><%=id%></td>
			</tr>


			<tr>
				<td>Book Name:</td>
				<td><input type="text" name="bookName" value='<%=name%>' /></td>
			</tr>
			<tr>
				<td>Book Price:</td>
				<td><input type="text" name="bookPrice" value='<%=price%>' /></td>
			</tr>
			<tr>
				<td>Term:</td>
				<td>
					<%
					for (int i : vm.getTerms()) {
						if (vm.getBook() != null && vm.getBook().getTerm() == i) {
					%> <!-- Updating a book or creating a book must have the supported list of terms -->
					<input type="radio" name="bookTerm" value="<%=i%>" checked /> Term<%=i%> 
						<%
						 } else {
						 %> 
						 <input type="radio" name="bookTerm" value="<%=i%>" /> Term <%=i%>
						<%
						}
					}
						%>
				</td>
			</tr>
		</table>

		<%
		if (vm.getBook() != null && vm.getBook().getId() > 0) {
		%>

		<!-- Decide on what buttons to render. When updating, show Save and Delete, create show Create -->

		<input class="btn btn-primary" type="submit" value="Delete"
			name="action" /> <input class="btn btn-primary" type="submit"
			value="Save" name="action" />
		<%
		} else {
		%>
		<input class="btn btn-primary" type="submit" value="Create"
			name="action" />
		<%
		}
		%>
	</form>
	<%
	}
	%>
	<!--Set up errors here -->

	<%
	if (request.getAttribute("error") != null) {
		ErrorModel em = (ErrorModel) request.getAttribute("error");
		if (em.getErrors() != null && em.getErrors().size() > 0) {
	%>

	<ul class="alert alert-danger">

		<%
		for (String e : em.getErrors()) {
		%>

		<li><%=e%></li>
		<%
		}
		%>

	</ul>

	<%
	}
	}
	%>
</body>
</html>
