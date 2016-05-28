<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="headerRM" value="/workshop/rm-template/${requestScope.template.templateId}"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content" class="workshop">

    <c:set var="isTemplate" value="true"/>
    <%@ include file="navigation/workshop-navigation.jsp" %>

    <div id="content-workshop">

        <form id="stage" action="<c:url value="/workshop/edit-template" />" method="post">

            <div class="error-message">${requestScope.errorMessage}</div>

            <button id="main-workshop-btn" class="btn btn-success" onclick="postEditForm();">Save</button>

            <div id="main-edit-block">

                <input type="hidden" name="templateId" value="${requestScope.template.templateId}"/>

                <div id="edit-title-wrapper">
                    <div id="edit-title-label">Title</div>
                    <input type="text" name="title" id="edit-title-in" value="${requestScope.template.title}"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Main color</div>
                    <button id="main-color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-1', onFineChange:'setMainColorBtn(this)'}"
                            style="margin-left: 42px;">Select</button>
                    <input type="hidden" name="mainColor" id="chosen-color-1" value="${requestScope.template.mainColor}"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Title color</div>
                    <button id="title-color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-2', onFineChange:'setTitleColorBtn(this)'}"
                            style="margin-left: 45px;">Select</button>
                    <input type="hidden" name="titleColor" id="chosen-color-2" value="${requestScope.template.titleColor}"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Text color</div>
                    <button id="text-color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-3', onFineChange:'setTextColorBtn(this)'}"
                            style="margin-left: 45px;">Select</button>
                    <input type="hidden" name="textColor" id="chosen-color-3" value="${requestScope.template.textColor}"/>
                </div>

                <div class="edit-color-btn-wrapper">
                    <div class="edit-generic-wrapper-lt5">Border color</div>
                    <button id="border-color-btn"
                            class="btn btn-success pick-color-btn-generic jscolor {valueElement:'chosen-color-4', onFineChange:'setBorderColorBtn(this)'}"
                            style="margin-left: 21px;">Select</button>
                    <input type="hidden" name="borderColor" id="chosen-color-4" value="${requestScope.template.borderColor}"/>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Border type</div>
                    <select name="borderType" class="select-generic" style="margin-left: 26px;">
                        <c:forEach var="borderType" items="${requestScope.borderTypes}">
                            <option
                            <c:if test="${borderType eq requestScope.template.borderType}">
                                selected="selected"
                            </c:if>
                            >${borderType}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Border width</div>
                    <select name="borderWidth" class="select-generic" style="margin-left: 15px;">
                        <c:forEach var="borderWidthType" items="${requestScope.borderWidthTypes}">
                            <option
                                <c:if test="${borderWidthType eq requestScope.template.borderWidth}">
                                    selected="selected"
                                </c:if>
                            >${borderWidthType}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>

            <div class="half-to-left">

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Title Style</div>
                    <select name="titleStyle" class="select-generic" style="margin-left: 15px;">
                        <c:forEach var="textStyle" items="${requestScope.textStyles}">
                            <option
                                <c:if test="${textStyle eq requestScope.template.titleStyle}">
                                    selected="selected"
                                </c:if>
                            >${textStyle}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Title Font</div>
                    <select name="titleFont" class="select-generic" style="margin-left: 20px;">
                        <c:forEach var="textFont" items="${requestScope.textFonts}">
                            <option
                                <c:if test="${textFont eq requestScope.template.titleFont}">
                                    selected="selected"
                                </c:if>
                            >${textFont}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Text Style</div>
                    <select name="textStyle" class="select-generic" style="margin-left: 15px;">
                        <c:forEach var="textStyle" items="${requestScope.textStyles}">
                            <option
                                <c:if test="${textStyle eq requestScope.template.textStyle}">
                                    selected="selected"
                                </c:if>
                            >${textStyle}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Text Font</div>
                    <select name="textFont" class="select-generic" style="margin-left: 20px;">
                        <c:forEach var="textFont" items="${requestScope.textFonts}">
                            <option
                                <c:if test="${textFont eq requestScope.template.textFont}">
                                    selected="selected"
                                </c:if>
                            >${textFont}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select-generic-wrapper">
                    <div class="edit-generic-label">Image Position</div>
                    <select name="imagePosition" id="select-image-position">
                        <c:forEach var="imagePosition" items="${requestScope.imagePositions}">
                            <option
                                <c:if test="${imagePosition eq requestScope.template.imagePosition}">
                                    selected="selected"
                                </c:if>
                            >${imagePosition}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
        </form>

    </div>

</div>

<%@ include file="common/footer.jsp" %>



<script src="<c:url value="/js/jscolor.min.js"/>"></script>

<script>
    function setMainColorBtn(picker) {
        document.getElementsByTagName('main-color-btn')[0].style.color = '#' + picker.toString()
    }

    function setTitleColorBtn(picker) {
        document.getElementsByTagName('title-color-btn')[0].style.color = '#' + picker.toString()
    }

    function setTextColorBtn(picker) {
        document.getElementsByTagName('text-color-btn')[0].style.color = '#' + picker.toString()
    }

    function setBorderColorBtn(picker) {
        document.getElementsByTagName('border-color-btn')[0].style.color = '#' + picker.toString()
    }

    function postEditForm() {
        document.getElementById("stage").submit();
    }

</script>

</body>

</html>