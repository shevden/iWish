<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="showHeaderRMandCancel" value="true"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%@ include file="navigation/wish-navigation.jsp" %>

    <div id="content-main">

        <button id="near-search-btn" class="btn btn-success">Save</button>

        <%@ include file="search/search-wish.jsp" %>

        <form id="stage" enctype="multipart/form-data">

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
                    <div class="edit-generic-wrapper-lt5">Big image</div>
                    <button class="btn btn-success select-img-btn" style="margin-left: 45px;">Select</button>
                </div>

                <div class="loaded-image-name">Here the name of image will be placed.</div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Small image</div>
                    <button class="btn btn-success select-img-btn">Select</button>
                </div>

                <div class="loaded-image-name">Here the name of image will be placed.</div>

                <div id="add-wish-description-wrapper">
                    <div>Description</div>
                    <textarea name="description" id="add-wish-description"></textarea>
                </div>

                <div id="add-wish-link-wrapper">

                    <div class="edit-generic-wrapper-lt5">Links</div>
                    <input type="text" id="add-wish-link-in"/>
                    <button id="add-wish-link-btn" class="btn btn-success">Add link</button>

                    <div class="btns-list">
                        <div class="c-b">
                            <button class="btn btn-warning btns-remove">Remove</button>
                            <div class="btns-remove-label">Link 1</div>
                        </div>
                        <div class="c-b">
                            <button class="btn btn-warning btns-remove">Remove</button>
                            <div class="btns-remove-label">Link 2</div>
                        </div>
                        <div class="c-b">
                            <button class="btn btn-warning btns-remove">Remove</button>
                            <div class="btns-remove-label">Link 3</div>
                        </div>
                    </div>

                </div>

            </div>

            <div class="half-to-left">

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Template</div>
                    <select class="select-generic" style="margin-left: 15px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Wishlist</div>
                    <select class="select-generic" style="margin-left: 24px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Giftlist</div>
                    <select class="select-generic" style="margin-left: 32px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Category</div>
                    <select class="select-generic" style="margin-left: 15px;"></select>
                </div>

                <div class="btns-list">
                    <div class="c-b">
                        <button class="btn btn-warning btns-remove">Remove</button>
                        <div class="btns-remove-label">Category 1</div>
                    </div>
                    <div class="c-b">
                        <button class="btn btn-warning btns-remove">Remove</button>
                        <div class="btns-remove-label">Category 2</div>
                    </div>
                    <div class="c-b">
                        <button class="btn btn-warning btns-remove">Remove</button>
                        <div class="btns-remove-label">Category 3</div>
                    </div>
                </div>

            </div>

        </form>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>