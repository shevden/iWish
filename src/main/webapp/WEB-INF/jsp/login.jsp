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
            <div class="forwarder-q-1">Already have an account?</div>
            <div class="forwarder-s-1">Sign In</div>

            <div class="error-message">${errorMessage}</div>

            <form method="post" action="<c:url value="/user/login"/>" >
                <div>Username (email):</div>
                <input type="text" name="email" class="forwarder-in"/>

                <div>Password:</div>
                <input type="password" name="password" class="forwarder-in"/>

                <div id="forwarder-remember">
                    <input type="checkbox" name="remember" value="true" class="f-l"/>
                    <span>Remember me</span>
                </div>

                <input type="submit" size="40" class="forwarder-btn btn btn-success" value="Start it"/>
            </form>

            <div class="forwarder-q-2">Haven't got an account, yet?</div>
            <div class="forwarder-s-2">Register</div>
            <div class="forwarder-s-2">for free</div>

            <a href="<c:url value="/user/register"/>" >
                <button class="btn btn-success forwarder-btn">Register</button>
            </a>
        </div>
    </div>

    <%@ include file="home/banners.jsp" %>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>