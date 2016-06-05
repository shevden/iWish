<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/catalog/view-wku/${wku.wkuId}"/> ">
    <span style="
            color: ${wku.template.textColorDP};
            font-weight: ${wku.template.textBoldDP};
            font-style: ${wku.template.textItalicDP};
            font-family: ${wku.template.textFont};
            font-size: 0.8em;
            ">
        ${wku.description}
        <c:if test="${empty wku.description}">
            No description defined.
        </c:if>
    </span>
</a>