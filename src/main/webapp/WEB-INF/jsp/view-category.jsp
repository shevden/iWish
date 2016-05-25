<html>

<head>
 	<title>iWish Homepage</title>
 	<link rel="stylesheet" type="text/css" href="../css/main-unique.css">
	<link rel="stylesheet" type="text/css" href="../css/main-common.css">
	<link rel="stylesheet" type="text/css" href="../css/common-elements.css">
 	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
	<div id="header-title">iWish</div>
	<div id="header-subtitle">Driven by the wishflow &nbsp; :::::::::::: &nbsp; Powered by the dreams</div>

	<div style="float: left; margin-left: 15pt; margin-top: -15pt;">
		<div style="float: left; margin-top: 3pt;">Hello, Denys</div>
		<div style="float: left; margin-left: 5pt; margin-top: 3pt;"><a>(not you?)</a></div>
		<button class="btn btn-info" style="margin-left: 15pt;">Logout</button>
	</div>

	<div style="float: right; margin-right: 15pt; margin-top: -15pt;">
		<button class="btn btn-warning">Edit</button>
		<button class="btn btn-info" style="margin-left: 10pt;">Friends</button>
		<button class="btn btn-info" style="margin-left: 10pt;">Workshop</button>
	</div>
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

		<div id="demo-wish">
			<img src="../image/arrow-up.jpg" id="demo-wish-img" />
			<div id="demo-wish-title">Add new wish</div>
			<div id="demo-wish-text">... and few ones more</div>
		</div>
	</div>

</div>


<!-- Footer: legal info. -->
<div id="footer">Copyright 2016 iWish Ukraine. All rights reserved.</div>

</body>

<html>