<html>

<head>
	<%@ include file="common/head.jsp" %>
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
	<c:set var="showHeaderContents" value="true"/>
	<c:set var="showHeaderRM" value="true"/>
	<%@ include file="common/header.jsp" %>
</div>


<div id="flex-content">

	<!-- Forwarder: navigate user to login/register/start. -->
	<div id="navigation">
		<div class="white-dashed-bottom nav-block">
			<div class="nav-title">Pending Approve:</div>
			<div class="nav-empty-elm">Some friend</div>
			<button class="btn btn-success" style="float: left; width: 50%;">Approve</button>
			<button class="btn btn-danger" style="float: left; width: 50%;">Reject</button>
		</div>
		<div class="nav-block" style="margin-top: 20px;">
			<div class="nav-title">Friends:</div>
			<div class="nav-empty-elm">First Last</div>
			<button class="btn btn-success nav-btn">Giftlist 1</button>
			<button class="btn btn-success nav-btn">Giftlist 2</button>
		</div>
	</div>

	<div id="searchbox">
		<input type="text" id="search-input" />
		<button id="search-btn" class="btn btn-success">Search</button>
	</div>

	<div id="stage">
		<div style="float: left; min-width: 800px; max-width: 1100px; min-height: 30pt; font-size: 20pt; margin-left: 25px; margin-top: 10px; text-align: center;">
			<span style="color: grey">Friend: </span>First Last
			<span style="color: grey">Giftlist: </span>Giftlist 1
		</div>

	</div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

<html>