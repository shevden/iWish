<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
    <c:set var="showHeaderContents" value="true"/>
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

    <div style="margin-left: 210px; min-width: 1000px;">
        <div id="searchbox">
            <button id="add-wish-btn" class="btn btn-success">Edit</button>
            <input type="text" id="search-input"/>
            <button id="search-btn" class="btn btn-success">Search</button>
            <div id="search-radio-wrp">
                <input type="radio" name="group1" value=""> By Wish
                <br/>
                <input type="radio" name="group1" value="" checked> By Category
            </div>
        </div>

        <div id="stage">
            <div style="float: left; clear:right; min-width: 800px; max-width: 1100px; min-height: 30pt; font-size: 20pt; margin-left: 25px; margin-top: 10px; text-align: center;">Here goes the title</div>
            <div style="float: left; width: 100%;">
                <img src="no-src" style="float: left; min-width: 300px; max-width: 40%; min-height: 200px; border: solid 2px; margin-right: 15px;" />

                <div style="margin-bottom: 15px;">Priority: 0-100</div>

                <div>
                    Here goes some long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    long long long long long long long long long long
                    description
                </div>
            </div>

            <div style="float: left; min-width: 300px; margin-top: 20px; margin-right: 20px; margin-bottom: 15px;">
                <div style="float: left; margin-top: 5px;">Categories</div>
                <div style="clear: both; font-size: 0.8em; padding-top: 10px;">
                    <div style="float: left; clear: both; margin-left: 15px;">
                        <div class="nav-empty-elm" style="padding: 5px 10px; text-align: center;">Category 1</div>
                    </div>
                    <div style="float: left; clear: both; margin-left: 15px;">
                        <div class="nav-empty-elm" style="padding: 5px 10px; text-align: center;">Category 2</div>
                    </div>
                    <div style="float: left; clear: both; margin-left: 15px;">
                        <div class="nav-empty-elm" style="padding: 5px 10px; text-align: center;">Category 3</div>
                    </div>
                </div>
            </div>

            <div style="float: left; margin-top: 20px; margin-bottom: 15px;">
                <div style="float: left; margin-top: 5px;">Links</div>
                <div style="clear: both; font-size: 0.8em; padding-top: 10px;">
                    <div style="float: left; clear: both; margin-left: 15px;">Link 1</div>
                    <div style="float: left; clear: both; margin-left: 15px;">Link 2</div>
                    <div style="float: left; clear: both; margin-left: 15px;">Link 3</div>
                </div>
            </div>

        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

<html>