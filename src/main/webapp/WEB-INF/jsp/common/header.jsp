<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header-title">iWish</div>
<div id="header-subtitle">Driven by the wishflow &nbsp; :::::::::::: &nbsp; Powered by the dreams</div>

<c:if test="${showHeaderContents}">

    <div id="recognized">
        <div id="recognized-label">Hello, ${sessionScope.profile.firstName}</div>
        <div id="recognized-not-me">
            <a href="<c:url value="/user/logout" />">(not you?)</a>
        </div>
        <a href="<c:url value="/user/logout" />">
            <button id="logout-btn" class="btn btn-info">Logout</button>
        </a>
    </div>

    <div id="right-top-menu">
        <div class="f-r">

            <c:if test="${showHeaderEdit}">
                <a href="<c:url value=""/>">
                    <button class="btn btn-warning">Edit</button>
                </a>
            </c:if>

            <c:if test="${showHeaderCancel}">
                <button class="btn btn-warning">Cancel</button>
            </c:if>

            <c:if test="${showHeaderRM}">
                <button class="btn btn-danger">RM</button>
            </c:if>

            <a href="<c:url value="/user/friends"/>">
                <button class="btn btn-info m-left-10">Friends</button>
            </a>

            <a href="<c:url value="/workshop/add-template"/>">
                <button class="btn btn-info m-left-10">Workshop</button>
            </a>
        </div>

        <c:if test="${showHeaderRMandCancel}">
            <div id="rtm-rm-and-cancel">
                <a href="<c:url value=""/>">
                    <button id="rtm-rm-grouped" class="btn btn-danger">RM</button>
                </a>

                <a href="<c:url value=""/>">
                    <button class="btn btn-warning">Cancel</button>
                </a>
            </div>
        </c:if>
    </div>

</c:if>