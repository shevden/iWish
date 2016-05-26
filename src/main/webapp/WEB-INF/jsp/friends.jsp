<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="showHeaderRM" value="true"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%-- Special navigation for friends management --%>
    <div id="navigation">
        <div class="white-dashed-bottom nav-block">
            <div class="nav-title">Pending Approve:</div>
            <div class="nav-empty-elm">Some friend</div>
            <button class="btn btn-success half-to-left">Approve</button>
            <button class="btn btn-danger half-to-left">Reject</button>
        </div>
        <div class="nav-block" style="margin-top: 20px;">
            <div class="nav-title">Friends:</div>
            <div class="nav-empty-elm">First Last</div>
            <button class="btn btn-success nav-btn">Giftlist 1</button>
            <button class="btn btn-success nav-btn">Giftlist 2</button>
        </div>
    </div>

    <%-- Special search for another Profiles --%>
    <div id="searchbox">
        <input type="text" id="search-input"/>
        <button id="search-btn" class="btn btn-success">Search</button>
    </div>

    <div id="stage">
        <div class="stage-title">
            <span class="grey">Friend: </span>
            First Last
            <span class="grey">Giftlist: </span>
            Giftlist 1
        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>