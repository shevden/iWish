<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/catalog/view-wku/${wku.wkuId}"/> ">

        <c:if test="${not empty wku.smallImageUrl}">
            <img src="<c:url value="/repository/images/${wku.smallImageUrl}"/>" class="content-img"/>
        </c:if>

</a>