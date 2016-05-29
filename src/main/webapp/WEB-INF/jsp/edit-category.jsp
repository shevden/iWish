<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:if test="${sessionScope.profile.defaultCategory ne currentCategory.categoryId}">
        <c:set var="headerRM" value="/catalog/rm-category/${currentCategory.categoryId}" />
    </c:if>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

    <%@ include file="navigation/wish-navigation.jsp" %>

    <div id="content-main">

        <button id="near-search-btn" onclick="postEditForm();" class="btn btn-success">Save</button>

        <%@ include file="search/search-wish.jsp" %>

        <form id="stage" action="<c:url value="/catalog/edit-category" />" method="post">

            <div class="error-message">${requestScope.errorMessage}</div>

            <div id="main-edit-block">

                <input type="hidden" name="categoryId" value="${currentCategory.categoryId}"/>

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" value="${currentCategory.title}" id="edit-title-in"/>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Layout</div>
                    <select name="layoutId" class="select-generic" style="margin-left: 26px;">
                        <c:forEach var="layout" items="${requestScope.layouts}">
                            <option
                                <c:if test="${layout.layoutId eq currentCategory.layoutId}">
                                    selected="selected"
                                </c:if>
                                value="${layout.layoutId}">${layout.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div id="edit-priority-wrapper">
                    <div class="edit-generic-label">Priority</div>
                    <input type="text" name="priority" value="${currentCategory.priority}" class="edit-priority-in"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button id="background-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-1', onFineChange:'setBackgroundBtn(this)'}"
                            style="width: 214px;">Select background</button>
                    <input type="hidden" name="background" id="chosen-color-1" value="${currentCategory.background}"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <button id="color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-2', onFineChange:'setColorBtn(this)'}"
                            style="width: 214px;">Select color</button>
                    <input type="hidden" name="color" id="chosen-color-2" value="${currentCategory.color}"/>
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
</script>

</body>

</html>