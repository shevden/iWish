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

        <form id="stage" action="<c:url value="/catalog/add-giftlist" />" method="post">

            <div class="error-message">${requestScope.errorMessage}</div>

            <div id="main-edit-block">

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" id="edit-title-in"/>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Layout</div>
                    <select name="layoutId" class="select-generic" style="margin-left: 26px;">
                        <c:forEach var="layout" items="${requestScope.layouts}">
                            <option value="${layout.layoutId}">${layout.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div id="edit-priority-wrapper">
                    <div class="edit-generic-label">Priority</div>
                    <input type="text" name="priority" class="edit-priority-in"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button id="background-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-1', onFineChange:'setBackgroundBtn(this)'}"
                            style="width: 214px;">Select background</button>
                    <input type="hidden" name="background" id="chosen-color-1" value="FFFFFF"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button id="color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-2', onFineChange:'setColorBtn(this)'}"
                            style="width: 214px;">Select color</button>
                    <input type="hidden" name="color" id="chosen-color-2" value="000000"/>
                </div>

            </div>

            <div class="half-to-left">

                <div id="giftlist-friends-select-wrapper">
                    <div class="edit-generic-label">Friends</div>
                    <select onchange="addFriend(this.options[this.selectedIndex]);" id="giftlist-friends-select">
                        <option id="disabled-option" disabled="disabled" selected="selected"></option>
                        <c:forEach var="friend" items="${friends}">
                            <option value="${friend.id}">
                                ${friend.firstName} ${friend.lastName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div id="friend-listing" class="btns-list">

                </div>

            </div>

        </form>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

<script src="<c:url value="/js/jscolor.min.js"/>"></script>
<script>
    function setBackgroundBtn(picker) {
        document.getElementsByTagName('background-btn')[0].style.color = '#' + picker.toString()
    }

    function setColorBtn(picker) {
        document.getElementsByTagName('color-btn')[0].style.color = '#' + picker.toString()
    }

    function postEditForm() {
        document.getElementById("stage").submit();
    }

    function addFriend(option) {
        if(option.id == "disabled-option") {
            return;
        }
        var parentGuest = document.getElementById("friend-listing");
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
        hiddenIn.name = "assignedFriends[]";
        hiddenIn.value = option.value;

        wrapperDiv.appendChild(rmButton);
        wrapperDiv.appendChild(textDiv);
        wrapperDiv.appendChild(hiddenIn);

        parentGuest.appendChild(wrapperDiv);

        option.parentNode.removeChild(option);

        document.getElementById("disabled-option").selected = "selected";
    }

    function rmDiv(friendId) {
        var wrapperDiv = document.getElementById("d-" + friendId);

        var optionFriend = document.createElement('option');
        optionFriend.value = friendId;
        optionFriend.innerHTML = document.getElementById("l-" + friendId).innerHTML;
        document.getElementById("giftlist-friends-select").appendChild(optionFriend);

        wrapperDiv.parentNode.removeChild(wrapperDiv);
    }
</script>

</body>

</html>