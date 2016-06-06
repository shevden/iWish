<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="<c:url value="/catalog/view-search" />" method="get" id="searchbox">
    <input type="text" name="keyword" id="search-input"/>
    <button id="search-btn" class="btn btn-success">Search</button>
</form>
