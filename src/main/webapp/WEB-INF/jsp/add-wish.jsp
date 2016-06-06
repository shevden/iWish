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

        <button id="near-search-btn" onclick="postEditForm();" class="btn btn-success">Save</button>

        <%@ include file="search/search-wish.jsp" %>

        <form id="stage" action="<c:url value="/catalog/add-wku" />" method="post" enctype="multipart/form-data">

            <div class="error-message">${requestScope.errorMessage}</div>

            <div id="main-edit-block">

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" id="edit-title-in"/>
                </div>

                <div id="edit-priority-wrapper">
                    <div class="edit-generic-label">Priority</div>
                    <input type="text" name="priority" class="edit-priority-in"/>
                </div>


                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Big image</div>
                    <div class="btn-browse btn-file btn btn-success" style="margin-left: 45px;">
                        <span>Browse</span>
                        <input type="file" name="largeImage" id="large-image" onChange="changeLargeImageLabel();">
                    </div>
                </div>

                <div id="large-image-label" class="loaded-image-name"></div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Small image</div>
                    <div class="btn-browse btn-file btn btn-success" style="margin-left: 25px;">
                        <span>Browse</span>
                        <input type="file" name="smallImage" id="small-image" onChange="changeSmallImageLabel();">
                    </div>
                </div>

                <div id="small-image-label" class="loaded-image-name"></div>

                <div id="add-wish-description-wrapper">
                    <div>Description</div>
                    <textarea name="description" id="add-wish-description"></textarea>
                </div>

                <div id="add-wish-link-wrapper">

                    <div class="edit-generic-wrapper-lt5">Links</div>
                    <input type="text" id="add-wish-link-in"/>
                    <button id="add-wish-link-btn" type="button" onclick="addLink();"
                            class="btn btn-success">Add link</button>

                    <div id="link-listing" class="btns-list">

                    </div>

                </div>

            </div>

            <div class="half-to-left">

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Template</div>
                    <select name="templateId" class="select-generic" style="margin-left: 15px;">
                        <option disabled="disabled" selected="selected"></option>
                        <c:forEach var="template" items="${templates}">
                            <option value="${template.templateId}">${template.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Wishlist</div>
                    <select name="wishlistId" class="select-generic" style="margin-left: 24px;">
                        <option disabled="disabled" selected="selected"></option>
                        <c:forEach var="wishlist" items="${wishlists}">
                            <option value="${wishlist.wishlistId}">${wishlist.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Giftlist</div>
                    <select name="giftlistId" class="select-generic" style="margin-left: 36px;">
                        <option disabled="disabled" selected="selected"></option>
                        <c:forEach var="giftlist" items="${giftlists}">
                            <option value="${giftlist.wishlistId}">${giftlist.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Category</div>
                    <select onchange="addCategory(this.options[this.selectedIndex]);" id="wku-category-select"
                            class="select-generic" style="margin-left: 15px;">
                        <option id="disabled-option-cat" disabled="disabled" selected="selected"></option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}">
                                    ${category.title}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div id="category-listing" class="btns-list">

                </div>

            </div>

        </form>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

<script>
    function postEditForm() {
        document.getElementById("stage").submit();
    }

    function addLink() {
        var val = document.getElementById("add-wish-link-in").value;
        var parentGuest = document.getElementById("link-listing");
        var wrapperDiv = document.createElement('div');
        wrapperDiv.className = "c-b";
        wrapperDiv.id = "d-" + val;
        var rmButton = document.createElement('button');
        rmButton.className = "btn btn-warning btns-remove";
        rmButton.innerHTML = "Remove";
        rmButton.type = "button";
        rmButton.onclick = function() {rmLink(val)};
        var textDiv = document.createElement('div');
        textDiv.className = "btns-remove-label";
        textDiv.id = "l-" + val;
        textDiv.innerHTML = val;
        var hiddenIn = document.createElement('input');
        hiddenIn.type = "hidden";
        hiddenIn.name = "assignedLinks[]";
        hiddenIn.value = val;

        wrapperDiv.appendChild(rmButton);
        wrapperDiv.appendChild(textDiv);
        wrapperDiv.appendChild(hiddenIn);

        parentGuest.appendChild(wrapperDiv);
    }

    function rmLink(val) {
        var wrapperDiv = document.getElementById("d-" + val);
        wrapperDiv.parentNode.removeChild(wrapperDiv);
    }


    function addCategory(option) {
        if(option.id == "disabled-option-cat") {
            return;
        }
        var parentGuest = document.getElementById("category-listing");
        var wrapperDiv = document.createElement('div');
        wrapperDiv.className = "c-b";
        wrapperDiv.id = "d-" + option.value;
        var rmButton = document.createElement('button');
        rmButton.className = "btn btn-warning btns-remove";
        rmButton.innerHTML = "Remove";
        rmButton.type = "button";
        rmButton.onclick = function() {rmDiv(option.value)};
        var textDiv = document.createElement('div');
        textDiv.className = "btns-remove-label";
        textDiv.id = "l-" + option.value;
        textDiv.innerHTML = option.text;
        var hiddenIn = document.createElement('input');
        hiddenIn.type = "hidden";
        hiddenIn.name = "assignedCategories[]";
        hiddenIn.value = option.value;

        wrapperDiv.appendChild(rmButton);
        wrapperDiv.appendChild(textDiv);
        wrapperDiv.appendChild(hiddenIn);

        parentGuest.appendChild(wrapperDiv);

        option.parentNode.removeChild(option);

        document.getElementById("disabled-option-cat").selected = "selected";
    }

    function rmDiv(categoryId) {
        var wrapperDiv = document.getElementById("d-" + categoryId);

        var optionFriend = document.createElement('option');
        optionFriend.value = categoryId;
        optionFriend.innerHTML = document.getElementById("l-" + categoryId).innerHTML;
        document.getElementById("wku-category-select").appendChild(optionFriend);

        wrapperDiv.parentNode.removeChild(wrapperDiv);
    }

    function changeLargeImageLabel(){
        var label = document.getElementById("large-image-label");
        var input = document.getElementById("large-image");
        label.innerHTML = input.value;
    }

    function changeSmallImageLabel(){
        var label = document.getElementById("small-image-label");
        var input = document.getElementById("small-image");
        label.innerHTML = input.value;
    }
</script>

</body>

</html>