<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<body>
	 <h2>In this form you can create new advertisement:</h2>
<form action="" method="POST">

    <c:if test="${fn:length(categoryList) ne 0}">
    Choice category:
		<select name="categoryId">
			<c:forEach var="category" items="${categoryList}" varStatus="loop">
		  		<option value="${category.id}"><c:out value="${category.categoryTitle}"/></option>
			</c:forEach>
		</select>
	</c:if>
    <c:if test="${fn:length(categoryList) eq 0}">
	<p>Put category title
	<p><input type="text" max="50"  name="categoryTitle" size="50px">
	</c:if>	
<p>Put advertisement title
<p><input type="text" max="16"  name="title" size="50px">
<p>Put advertisement body:
<p><textarea name="body" ROWS="5" cols="50"></textarea>
 <p><input type="submit" value="Create!"></p>
 </form>
</body>
</html>
