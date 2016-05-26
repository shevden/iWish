<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="showHeaderCancel" value="true"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%@ include file="navigation/wish-navigation.jsp" %>

    <div id="content-main">

        <button id="near-search-btn" class="btn btn-success">Save</button>

        <%@ include file="search/search-wish.jsp" %>

        <div id="stage">

            <div id="main-edit-block">

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" id="edit-title-in"/>
                </div>

                <div id="edit-priority-wrapper">
                    <div class="edit-generic-label">Priority</div>
                    <input type="text" class="edit-priority-in"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button class="btn btn-success edit-bg-color-btn">Select background</button>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button class="btn btn-success edit-fg-color-btn">Select foreground</button>
                </div>

            </div>

        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>