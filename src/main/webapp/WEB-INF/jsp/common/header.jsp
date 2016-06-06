<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header-title">
    <span>iWish</span>
</div>
<c:if test="${not empty sessionScope.profile}">
    <a href="<c:url value="/catalog/view-category/${sessionScope.profile.defaultCategory}" />">
</c:if>
        <div id="header-subtitle">Driven by the wishflow &nbsp; :::::::::::: &nbsp; Powered by the dreams</div>
<c:if test="${not empty sessionScope.profile}">
    </a>
</c:if>

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

            <c:if test="${headerEdit ne null}">
                <a href="${headerEdit}">
                    <button class="btn btn-warning">Edit</button>
                </a>
            </c:if>


            <c:if test="${headerRM ne null}">
                <a href="${headerRM}">
                    <button class="btn btn-danger">RM</button>
                </a>
            </c:if>

            <a href="<c:url value="/user/friends"/>">
                <button class="btn btn-info m-left-10">Friends</button>
            </a>

            <a href="<c:url value="/workshop/add-template"/>">
                <button class="btn btn-info m-left-10">Workshop</button>
            </a>
        </div>

    </div>

</c:if>