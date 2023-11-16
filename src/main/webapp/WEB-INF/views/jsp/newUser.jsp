<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
	 <h2>Hello! New User pleas put info in form:</h2>
<form action="./newUser" method="POST">
	<p>First name: <input type="text" max="16"  name="firstName" size="50px">
	<p>Last name: <input type="text" max="16"  name="lastName" size="50px">
	<p>Email: <input type="text" max="16"  name="email" value=<c:out value="${email}"/> size="50px">
	<p><input type="submit" value="Create!">
</form> 
</body>
</html>
