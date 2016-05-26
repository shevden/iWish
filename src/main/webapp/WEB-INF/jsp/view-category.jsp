<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<div id="flex-content">

	<!-- Forwarder: navigate user to login/register/start. -->
	<div id="navigation">
		<div class="white-dashed-bottom nav-block">
			<div class="nav-title">Your Categories:</div>
			<div class="nav-empty-elm">Default CAT</div>
			<button class="btn btn-success nav-btn">Add Category</button>
		</div>
		<div class="white-dashed-bottom nav-block">
			<div class="nav-title">Your Wishlists:</div>
			<div class="nav-empty-elm">No wishlists, yet</div>
			<button class="btn btn-success nav-btn">Add Wishlist</button>
		</div>
		<div class="nav-block">
			<div class="nav-title">Your Giftlists:</div>
			<div class="nav-empty-elm">No giftlists, yet</div>
			<button class="btn btn-success nav-btn">Add Giftlist</button>
		</div>
	</div>


	<div id="searchbox">
		<button id="add-wish-btn" class="btn btn-success">Add Wish</button>
		<input type="text" id="search-input" />
		<button id="search-btn" class="btn btn-success">Search</button>
		<div id="search-radio-wrp">
			<input type="radio" name="group1" value=""> By Wish 
			<br />
			<input type="radio" name="group1" value="" checked> By Category
		</div>
	</div>


	<div id="stage">
		<div style="float: left; min-width: 800px; max-width: 1100px; min-height: 30pt; font-size: 20pt; margin-left: 25px; margin-top: 10px; text-align: center;">
			<span style="color: grey">Category: </span>Here goes the title
		</div>

        <%@ include file="home/demo-wish.jsp" %>
	</div>

</div>

<%@ include file="common/footer.jsp" %>

</body>

<html>