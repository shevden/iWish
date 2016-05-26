<html>

<head>
	<%@ include file="common/head.jsp" %>
</head>

<body>


<div id="header">
	<c:set var="showHeaderContents" value="true"/>
	<c:set var="showHeaderEdit" value="true"/>
	<%@ include file="common/header.jsp" %>
</div>


<div id="content">

	<%@ include file="navigation/wish-navigation.jsp" %>

	<button id="near-search-btn" class="btn btn-success">Add Wish</button>

	<%@ include file="search/search-wish.jsp" %>

	<div id="stage">
		<div class="stage-title">
			<span class="grey">Giftlist: </span>
			Here goes the title
		</div>

		<%@ include file="home/demo-wish.jsp" %>
	</div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>