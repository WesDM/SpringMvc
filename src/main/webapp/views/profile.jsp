<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Spittr</title>
</head>
<body>
	<p>Hello <security:authentication property="principal.username" />!</p>
	<h1>Your Profile</h1>
	<c:out value="${spitter.username}" />
	<br />
	<c:out value="${spitter.firstName}" />
	<c:out value="${spitter.lastName}" />
	<sec:authorize access="hasRole('ROLE_SPITTER')"></sec:authorize> <!-- Conditionally displays content -->
</body>
</html>