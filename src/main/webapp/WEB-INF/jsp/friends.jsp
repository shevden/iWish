<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="headerRM" value="/user/friends/rm/${currentFriend.id}"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%-- Special navigation for friends management --%>
        <div id="navigation">
            <c:if test="${pendingAction ne null}">
                <div class="white-dashed-bottom nav-block" style="margin-bottom: 30px;">
                    <div class="nav-title">Pending Approve:</div>
                    <c:forEach var="profile" items="${pendingAction}">
                        <div class="nav-empty-elm">${profile.firstName} ${profile.lastName}</div>
                        <a href="<c:url value="/user/friends/approve/${profile.id}" />" class="clean-link">
                            <button class="btn btn-success half-to-left">Approve</button>
                        </a>
                        <a href="<c:url value="/user/friends/reject/${profile.id}" />" class="clean-link">
                            <button class="btn btn-danger half-to-left">Reject</button>
                        </a>
                    </c:forEach>
                </div>
            </c:if>
        <div class="nav-block">
            <div class="nav-title">Friends:</div>
            <c:if test="${friends eq null}">
                <div class="nav-empty-elm">No friends, yet</div>
            </c:if>
            <c:if test="${friends ne null}">
                <c:forEach var="friend" items="${friends}">
                    <a href="<c:url value="/user/friends/${friend.id}" />">
                        <div class="nav-empty-elm">${friend.firstName} ${friend.lastName}</div>
                    </a>
                </c:forEach>
            </c:if>
            <%--<button class="btn btn-success nav-btn">Giftlist 1</button>--%>
        </div>
    </div>

    <%-- Special search for another Profiles --%>
    <form id="searchbox" action="<c:url value="/user/friends/search" />" method="post">
        <input type="text" name="keyword" id="search-input"/>
        <input type="submit" id="search-btn" class="btn btn-success" value="Search">
    </form>

    <div id="stage">

        <c:if test="${emptyResult}">
            <h3 style="margin-top: -20px;">No matching users found</h3>
        </c:if>

        <div id="friends-search-results"
            <c:if test="${searchResults eq null}">
                hidden="hidden"
            </c:if>
        >
            <h3>Search results:</h3>
            <c:forEach var="user" items="${searchResults}">
                <div>
                    <a href="<c:url value="/user/friends/add/${user.id}" />" class="clean-link">
                        <button class="btn btn-success">Add to Friends</button>
                    </a>
                    <span>${user.firstName} ${user.lastName}</span>
                </div>
            </c:forEach>
        </div>

        <c:if test="${searchResults eq null and emptyResult eq null and currentFriend ne null}">
            <div class="stage-title">
                <span class="grey">Friend: </span>
                ${currentFriend.firstName} ${currentFriend.lastName}

                <span class="grey">Giftlist: </span>
                Giftlist 1
            </div>
        </c:if>
    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>