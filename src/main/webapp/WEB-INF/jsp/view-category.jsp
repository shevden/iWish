<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="headerEdit" value="/catalog/edit-category/${currentCategory.categoryId}"/>
    <%@ include file="common/header.jsp" %>
</div>


<div id="content">

	<%@ include file="navigation/wish-navigation.jsp" %>

	<a href="<c:url value="/catalog/add-wku"/>" >
		<button id="near-search-btn" class="btn btn-success">Add Wish</button>
	</a>

	<%@ include file="search/search-wish.jsp" %>

	<div id="stage">
		<div class="stage-title">
			<span class="grey">Category: </span>
			<span>${currentCategory.title}</span>
		</div>

        <%@ include file="home/demo-wish.jsp" %>
	</div>

</div>

<%@ include file="common/footer.jsp" %>

</body>

</html>