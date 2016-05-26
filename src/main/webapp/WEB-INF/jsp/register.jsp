<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
	<%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
	<%@ include file="common/header.jsp" %>
</div>


<div id="content">

	<div id="forwarder">
		<div class="forwarder-fixed">
			<div class="forwarder-s-1">Registration</div>
			<div class="forwarder-title">
				Please, fill in all fields within this form to get your new account.
			</div>

            <div class="error-message">${errorMessage}</div>

			<form method="post" action="/user/register">
				<div>Username (email):</div>
				<input type="text" name="email" class="forwarder-in" />

				<div>Password:</div>
				<input type="password" name="password" class="forwarder-in" />

				<div>Retype password:</div>
				<input type="password" name="password_clone" class="forwarder-in" />

				<div>First name:</div>
				<input type="text" name="first_name" class="forwarder-in" />

				<div>Last Name:</div>
				<input type="text" name="last_name" class="forwarder-in" />

				<input type="submit" size="40" class="forwarder-btn btn btn-success" value="Register"/>
			</form>
		</div>
	</div>

	<%@ include file="home/banners.jsp" %>

</div>

<%@ include file="common/footer.jsp" %>

</body>

<html>