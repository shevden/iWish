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


<div id="content" class="workshop">

    <c:set var="isTemplate" value="true"/>
    <%@ include file="navigation/workshop-navigation.jsp" %>

    <div id="content-workshop">

        <div id="stage">

            <button id="main-workshop-btn" class="btn btn-success">Save</button>

            <div id="main-edit-block">

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" id="edit-title-in"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Main color</div>
                    <button class="btn btn-success pick-color-btn-generic" style="margin-left: 42px;">Select</button>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Title color</div>
                    <button class="btn btn-success pick-color-btn-generic" style="margin-left: 45px;">Select</button>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Text color</div>
                    <button id="text-color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-3', onFineChange:'setTextColorBtn(this)'}"
                            style="margin-left: 45px;">Select</button>
                    <input type="hidden" id="chosen-color-3" value="000000"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Border color</div>
                    <button class="btn btn-success pick-color-btn-generic" style="margin-left: 21px;">Select</button>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Border type</div>
                    <select class="select-generic" style="margin-left: 26px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Border width</div>
                    <select class="select-generic" style="margin-left: 15px;;"></select>
                </div>

            </div>

            <div class="half-to-left">

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Title Style</div>
                    <select class="select-generic" style="margin-left: 15px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Title Font</div>
                    <select class="select-generic" style="margin-left: 20px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Text Style</div>
                    <select class="select-generic" style="margin-left: 15px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Text Font</div>
                    <select class="select-generic" style="margin-left: 20px;"></select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Image Position</div>
                    <select id="select-image-position"></select>
                </div>

            </div>
        </div>

    </div>

</div>

<%@ include file="common/footer.jsp" %>



<script src="<c:url value="/js/jscolor.min.js"/>"></script>

<script>
    function setTextColorBtn(picker) {
        document.getElementsByTagName('text-color-btn')[0].style.color = '#' + picker.toString()
    }
</script>

</body>

</html>