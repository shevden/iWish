<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%@ include file="navigation/wish-navigation.jsp" %>

    <div id="content-main">

        <a href="<c:url value="/catalog/edit-wku/${currentWku.wkuId}"/>" >
            <button id="near-search-btn" class="btn btn-success">Edit</button>
        </a>

        <%@ include file="search/search-wish.jsp" %>

        <div id="stage">

            <div id="wish-title">${currentWku.title}</div>

            <div id="wish-primary-wrapper">
                <c:choose>
                    <c:when test="${not empty currentWku.largeImageUrl}">
                        <img src="<c:url value="/repository/images/${currentWku.largeImageUrl}" />" id="wish-hero-img" />
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value="/repository/images/default-l.png" />" id="wish-hero-img" />
                    </c:otherwise>
                </c:choose>


                <div id="wish-priority-label">Priority: ${currentWku.priority}</div>

                <div>
                    ${currentWku.description}
                </div>
            </div>

            <div id="wish-categories-wrapper">

                <div class="edit-generic-wrapper-lt5">Categories</div>

                <div class="btns-list">
                    <c:forEach var="category" items="${currentWku.categories}">
                        <div class="wish-category-tag-wrapper">
                            <a href="<c:url value="/catalog/view-category/${category.categoryId}" />">
                                <div class="nav-empty-elm wish-category-tag"
                                     style="color: #${category.color}; background: #${category.background}">
                                     ${category.title}
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

            </div>

            <div id="wish-links-wrapper">

                <div class="edit-generic-wrapper-lt5">Links</div>

                <div class="btns-list">
                    <c:forEach var="remote" items="${currentWku.remotes}">
                        <a href="${remote.remoteUrl}">
                            <div class="wish-remote-link">${remote.remoteUrl}</div>
                        </a>
                    </c:forEach>
                </div>
            </div>

        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>