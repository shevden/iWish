<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/catalog/view-wku/${requestScope.wku.wkuId}"/> ">

        <%-- TODO: enable default image when uncom. --%>
        <%--<c:when test="${empty wku.smallImageUrl}">--%>
            <%--<img src="<c:url value="/repository/images/default-s.bmp" />" class="content-img"/>--%>
        <%--</c:when>--%>

        <c:if test="${not empty wku.smallImageUrl}">
            <img src="<c:url value="/repository/images/${wku.smallImageUrl}"/>" class="content-img"/>
        </c:if>

</a>