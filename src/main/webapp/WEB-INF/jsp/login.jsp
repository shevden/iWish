<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>iWish Homepage</title>
    <link rel="stylesheet" type="text/css" href="../css/main-unique.css">
    <link rel="stylesheet" type="text/css" href="../css/main-common.css">
    <link rel="stylesheet" type="text/css" href="../css/common-elements.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
    <div id="header-title">iWish</div>
    <div id="header-subtitle">Driven by the wishflow &nbsp; :::::::::::: &nbsp; Powered by the dreams</div>
</div>


<div id="content">

    <!-- Forwarder: navigate user to login/register/start. -->
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
                    <input type="checkbox" name="remember" class="f-l"/>
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

    <div id="banners">
        <div class="main-banner">
            <br/>
            <h1>Discover the world of dreams visualization</h1>
            <div class="banner-img-wrapper">
                <img src="../image/banner-1.png" class="main-banner-img"/>
                <div class="banner-label banner-label-nw">Write down Your wishes simply and compact</div>
                <div class="banner-label banner-label-ne">You've got solid storage for it here</div>
                <div class="banner-label banner-label-se">View them with desktop and mobile devices</div>
                <div class="banner-label banner-label-sw">Search your wishes in a blink of an eye</div>
            </div>
        </div>

        <div class="banner dashed-top">
            <div class="banner-img-wrapper">
                <img src="../image/banner-2.png" class="banner-img"/>
                <div class="banner-label banner-label-nw">Customize every single wish as You want</div>
                <div class="banner-label banner-label-ne">Categorize wishes with multiple tags</div>
                <div class="banner-label banner-label-se">Link wishes to the real world objects</div>
                <div class="banner-label banner-label-sw">Define Your personal layout per each group</div>
            </div>
        </div>

        <div class="banner dashed-top">
            <div class="banner-img-wrapper">
                <img src="../image/banner-3.png" class="banner-img"/>
                <div class="banner-label banner-label-nw">Find your friends and their wishes</div>
                <div class="banner-label banner-label-ne">Populate your community with new people</div>
                <div class="banner-label banner-label-se">Share your wishes with the people you know</div>
                <div class="banner-label banner-label-sw">Manage personal wishlists and public giftlists</div>
            </div>
        </div>
    </div>

</div>


<!-- Footer: legal info. -->
<div id="footer">Copyright 2016 iWish Ukraine. All rights reserved.</div>

</body>

<html>