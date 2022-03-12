<%-- 
    Document   : bookSummary
    Created on : Mar 12, 2020
    Author     : Brian Aylward

    The view for the process book
    Support
    -Create
    -Saving changes
    -Delete
--%>


<%@page import="edu.nbcc.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/jspf/header.jspf"%>
<title>Book</title>
</head>
<body class="p-4">
	<%@include file="WEB-INF/jspf/navigation.jspf"%>
	<h1>Book Summary</h1>
	
	<%
	
	Book deletedBook = null;
	Book book = null;
    		
	if(request.getAttribute("deletedBook") != null) {
		deletedBook = (Book)request.getAttribute("deletedBook");
	}
	if(request.getAttribute("savedBook") != null){
		book = (Book)request.getAttribute("savedBook");
	}
	if(request.getAttribute("createdBook") != null) {
		book = (Book)request.getAttribute("createdBook");
	}
	
	if(deletedBook != null) {
	%>

	<h2><%=deletedBook.getName() %> Id: <%=deletedBook.getId() %> has been deleted.</h2>
	
	<%} else if (book != null){ %>

	<table class="table table-striped">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Term</th>
		</tr>
		<tr>
			<td><%=book.getId() %></td>
			<td><%=book.getName() %></td>
			<td><%=book.getPrice() %></td>
			<td><%=book.getTerm() %></td>
		</tr>
	</table>


	<a href="${pageContext.request.contextPath}/books"> books</a>
<% } %>

</body>
</html>
