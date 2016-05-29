<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:if test="${sessionScope.profile.defaultLayout ne requestScope.layout.layoutId}">
        <c:set var="headerRM" value="/workshop/rm-layout/${requestScope.layout.layoutId}"/>
    </c:if>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content" class="workshop">


    <c:set var="isLayout" value="true"/>
    <%@ include file="navigation/workshop-navigation.jsp" %>

    <div id="content-workshop">

        <form id="stage" action="<c:url value="/workshop/edit-layout" />" method="post">

            <div class="error-message">${requestScope.errorMessage}</div>

            <button id="main-workshop-btn" class="btn btn-success" onclick="postEditForm();">Save</button>

            <div id="main-edit-block">

                <input type="hidden" name="layoutId" value="${requestScope.layout.layoutId}"/>

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" id="edit-title-in" value="${requestScope.layout.title}"/>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Model type</div>
                    <select name="model" class="select-generic" style="margin-left: 26px;">
                        <c:forEach var="model" items="${requestScope.models}">
                            <option
                                <c:if test="${model eq requestScope.layout.model}">
                                    selected="selected"
                                </c:if>
                            >${model}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Block width</div>
                    <input type="text" name="width" value="${requestScope.layout.width}" class="select-generic" style="margin-left: 18px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Block height</div>
                    <input type="text" name="height" value="${requestScope.layout.height}" class="select-generic" style="margin-left: 11px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Padding</div>
                    <input type="text" name="padding" value="${requestScope.layout.padding}" class="select-generic" style="margin-left: 49px;" />
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Margin</div>
                    <input type="text" name="margin" value="${requestScope.layout.margin}" class="select-generic" style="margin-left: 61px;" />
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