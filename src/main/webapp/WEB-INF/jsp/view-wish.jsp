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

        <button id="near-search-btn" class="btn btn-success">Edit</button>

        <%@ include file="search/search-wish.jsp" %>

        <div id="stage">

            <div id="wish-title">Here goes the title</div>

            <div id="wish-primary-wrapper">
                <img src="no-src" id="wish-hero-img" />

                <div id="wish-priority-label">Priority: 0-100</div>

                <div>
                    <%-- Description here --%>
                </div>
            </div>

            <div id="wish-categories-wrapper">

                <div class="edit-generic-wrapper-lt5">Categories</div>

                <div class="btns-list">
                    <div class="wish-category-tag-wrapper">
                        <div class="nav-empty-elm wish-category-tag">Category 1</div>
                    </div>
                    <div class="wish-category-tag-wrapper">
                        <div class="nav-empty-elm wish-category-tag">Category 2</div>
                    </div>
                    <div class="wish-category-tag-wrapper">
                        <div class="nav-empty-elm wish-category-tag">Category 3</div>
                    </div>
                </div>

            </div>

            <div id="wish-links-wrapper">

                <div class="edit-generic-wrapper-lt5">Links</div>

                <div class="btns-list">
                    <div class="wish-remote-link">Link 1</div>
                    <div class="wish-remote-link">Link 2</div>
                    <div class="wish-remote-link">Link 3</div>
                </div>
            </div>

        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>