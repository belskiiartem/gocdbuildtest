<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
	 <h2>Hello! <c:out value="${firstName}"/> <c:out value="${lastName}"/></h2>
	 You current advertisement:
	<table border='1'>
	<tr>
		<td>Id</td><td>Title</td><td>Message</td><td>Category</td><td>Publication date</td>
	</tr>
	<c:forEach var="advertisement" items="${myAdvertisement}" varStatus="loop">
	<tr>
		<td><c:out value="${advertisement.id}"/></td><td><c:out value="${advertisement.advertTitle}"/></td><td><c:out value="${advertisement.advertText}"/></td>
		<td><c:out value="${advertisement.categoryId}"/></td><td><c:out value="${advertisement.publishDate}"/></td>
	</tr>
    
	</c:forEach>
	
	</table>
<form action="./createNew" method="GET">
	 	 <p><h2>You can add new advertisement</h2></p>
	 	<input type="submit" value="submit">
</form>

<form action="./delete" method="POST">
	 	 <p><h2>or delete exist. (you should put id)</h2></p>
	 	 <input pype="text" name="deleteId" max="16">
	 	<input type="submit" value="submit">
</form>

</body>
</html>
