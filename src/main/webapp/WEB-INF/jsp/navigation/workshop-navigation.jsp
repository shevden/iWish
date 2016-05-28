<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="workshop-navigation">

    <div class="nav-block">

        <c:if test="${isTemplate}">
            <a href="<c:url value="/workshop/add-layout" />" >
                <button id="switch-workshop-btn" class="btn btn-info">Layout Mode</button>
            </a>

            <div class="nav-title">Your Templates:</div>

            <a href="<c:url value="/workshop/add-template"/>">
                <button class="btn btn-success nav-btn">Add Template</button>
            </a>

            <c:forEach var="template" items="${requestScope.templates}">
                <a href="<c:url value="/workshop/edit-template/${template.templateId}"/>">
                    <div class="nav-empty-elm">${template.title}</div>
                </a>
            </c:forEach>
        </c:if>

        <c:if test="${isLayout}">
            <a href="<c:url value="/workshop/add-template" />" >
                <button id="switch-workshop-btn" class="btn btn-info">Template Mode</button>
            </a>

            <div class="nav-title">Your Layouts:</div>

            <a href="<c:url value="/workshop/add-layout" />" >
                <button class="btn btn-success nav-btn">Add Layout</button>
            </a>

            <c:forEach var="layout" items="${requestScope.layouts}">
                <a href="<c:url value="/workshop/edit-layout/${layout.layoutId}"/>">
                    <div class="nav-empty-elm">${layout.title}</div>
                </a>
            </c:forEach>
        </c:if>

    </div>

</div>