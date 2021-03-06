<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content" class="workshop">


    <c:set var="isLayout" value="true"/>
    <%@ include file="navigation/workshop-navigation.jsp" %>

    <div id="content-workshop">

        <form id="stage" action="<c:url value="/workshop/add-layout" />" method="post">

            <div class="error-message">${requestScope.errorMessage}</div>

            <button id="main-workshop-btn" class="btn btn-success" onclick="postEditForm();">Save</button>

            <div id="main-edit-block">

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" id="edit-title-in"/>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Model type</div>
                    <select name="model" class="select-generic" style="margin-left: 26px;">
                        <c:forEach var="model" items="${requestScope.models}">
                            <option>${model}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Block width</div>
                    <input type="text" name="width" class="select-generic" style="margin-left: 18px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Block height</div>
                    <input type="text" name="height" class="select-generic" style="margin-left: 11px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Padding</div>
                    <input type="text" name="padding" class="select-generic" style="margin-left: 49px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Margin</div>
                    <input type="text" name="margin" class="select-generic" style="margin-left: 61px;" />
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
</script>

</body>

</html>