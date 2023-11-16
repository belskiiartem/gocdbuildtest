<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<form action="./userHome" method="POST">
		<h2>Hello on bulletin board!</h2>
		<p>
		You can use your email address as login, for enter to private zone:	<input type="text" max="16" name="email" size="20px"> <input type="submit" value="login">
	</form>
<form action="./filter" method="POST">
	<p>or you can create filter for all advertisements:
	<p> By category: 
	<select name="categoryId">
	<c:forEach var="category" items="${categoryList}" varStatus="loop">
  		<option value="${category.id}"><c:out value="${category.categoryTitle}"/></option>
	</c:forEach>
</select>
By user email: 
<input type="text" max="16" name="email" size="20px"> <input type="submit" value="Get filtered!"></p>
</form>
<form action="./" method="POST">
<input type="text" max="16" name="email" size="20px"> <input type="submit" value="remove filter!"></p>
</form>
	
	<table border='1'>
		<tr>
			<td>Id</td>
			<td>Title</td>
			<td>Message</td>
			<td>Category</td>
			<td>Publication date</td>
		</tr>
		<c:forEach var="advertisement" items="${advertisements}"
			varStatus="loop">
			<tr>
				<td><c:out value="${advertisement.id}" /></td>
				<td><c:out value="${advertisement.advertTitle}" /></td>
				<td><c:out value="${advertisement.advertText}" /></td>
				<td><c:out value="${advertisement.categoryId}" /></td>
				<td><c:out value="${advertisement.publishDate}" /></td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>
