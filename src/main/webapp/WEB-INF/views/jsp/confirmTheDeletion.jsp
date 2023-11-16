<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
Are you sure want delete this advertisement?

<form action="./confirm" method="POST">
	<p>Id: <input type="text" name="deleteId" value=<c:out value="${deleteId}"/> readonly>
	<p>Category: <input type="text" name="category" value=<c:out value="${category}"/> readonly>
	<p>Title: <input type="text" name="title" value=<c:out value="${title}"/> readonly>
	<p>Text: <input type="text" name="text" value=<c:out value="${body}"/> readonly>
	<p>Save advertisement, and return to main page: <input type="radio" name="confirm" value="save" checked>
	<p>Delete advertisement, and return to main page: <input type="radio" name="confirm" value="delete">
	<p><input type="submit" value="Confirm">
</form> 
	

</body>
</html>
