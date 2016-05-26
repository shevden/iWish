<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="workshop-navigation">

    <div class="nav-block">

        <c:if test="${isTemplate}">
            <button id="switch-workshop-btn" class="btn btn-info">Layout Mode</button>
            <div class="nav-title">Your Templates:</div>
            <button class="btn btn-success nav-btn">Add Template</button>
            <div class="nav-empty-elm">Template 1</div>
            <div class="nav-empty-elm">Template 2</div>
        </c:if>

        <c:if test="${isLayout}">
            <button id="switch-workshop-btn" class="btn btn-info">Template Mode</button>
            <div class="nav-title">Your Layouts:</div>
            <button class="btn btn-success nav-btn">Add Layout</button>
            <div class="nav-empty-elm">Layout 1</div>
            <div class="nav-empty-elm">Layout 2</div>
        </c:if>

    </div>

</div>