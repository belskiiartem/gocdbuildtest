<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<body>
 	<table border='1'>
		<tr>
			<td>Id</td>
			<td>Title</td>
		</tr>
		<c:forEach var="category" items="${categoryList}"
			varStatus="loop">
			<tr>
				<td><c:out value="${category.id}" /></td>
				<td><c:out value="${category.categoryTitle}" /></td>
			</tr>
		</c:forEach>
	</table>
	Create new category with title:
	<form action="./createCategory" method="POST">
		<p><input type="text" max="50"  name="categoryTitle" size="50px">
		<p><input type="submit" value="Create!"></p>
	</form>
	
		delete category with ID:
	<form action="./deleteCategory" method="POST">
		<p><input type="text" max="50"  name="categoryId" size="50px">
		<p><input type="submit" value="Remove!"></p>
	</form>
 
 
</body>
</html>
