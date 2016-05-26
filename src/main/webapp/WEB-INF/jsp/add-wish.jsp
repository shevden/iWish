<html>

<head>
    <%@ include file="common/head.jsp" %>
</head>

<body>

<!-- Header: display of metainfo and logo. -->
<div id="header">
    <c:set var="showHeaderContents" value="true"/>
    <c:set var="showHeaderRMandCancel" value="true"/>
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
            <button id="add-wish-btn" class="btn btn-success">Save</button>
            <input type="text" id="search-input"/>
            <button id="search-btn" class="btn btn-success">Search</button>
            <div id="search-radio-wrp">
                <input type="radio" name="group1" value=""> By Wish
                <br/>
                <input type="radio" name="group1" value="" checked> By Category
            </div>
        </div>

        <div id="stage">
            <div style="width: 45%; min-width: 450px; float: left; clear: both;">
                <div style="float: left; width: 100%; height: 30pt;">
                    <div style="float: left; font-size: 20pt; margin-top: 10px; ">Title</div>
                    <input type="text" style="float: left; height: 22pt; width: 370px; margin-left: 15px; margin-top: 5px;"/>
                </div>
                <div style="float: left; width: 100%; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Priority</div>
                    <input type="text" style="float: left; margin-left: 15px; width: 100pt; height: 18pt;"/>
                </div>
                <div style="float: left; width: 100%; margin-top: 15px;">
                    <div style="float: left; margin-top: 5px;">Image</div>
                    <button class="btn btn-success"
                            style="float: left; font-weight: bold; margin-left: 25px; width: 100pt;">Select
                    </button>
                </div>
                <div style="float: left; clear:both; font-size: 0.8em;">Here the name of image will be placed.</div>
                <div style="float: left; width: 100%; margin-top: 20px;">
                    <div>Description</div>
                    <textarea name="description" style="width: 330pt; height: 100pt;"></textarea>
                </div>
                <div style="float: left; width: 100%; margin-top: 20px; margin-bottom: 15px;">
                    <div style="float: left; margin-top: 5px;">Links</div>
                    <input type="text" style="float: left; margin-left: 10px; margin-top: 3px; width: 210pt; height: 18pt;"/>
                    <button class="btn btn-success" style="float: left; font-weight: bold; margin-left: 10px; width: 70pt;">Add link</button>
                    <div style="clear: both; font-size: 0.8em; padding-top: 10px;">
                        <div style="clear: both;">
                            <button class="btn btn-warning"
                                    style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                                Remove
                            </button>
                            <div style="float: left; margin-left: 15px;">Link 1</div>
                        </div>
                        <div style="clear: both;">
                            <button class="btn btn-warning"
                                    style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                                Remove
                            </button>
                            <div style="float: left; margin-left: 15px;">Link 2</div>
                        </div>
                        <div style="clear: both;">
                            <button class="btn btn-warning"
                                    style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                                Remove
                            </button>
                            <div style="float: left; margin-left: 15px;">Link 3</div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="float: left; width: 50%;">
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Template</div>
                    <select style="float: left; margin-left: 15px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Wishlist</div>
                    <select style="float: left; margin-left: 24px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Gistlist</div>
                    <select style="float: left; margin-left: 32px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Category</div>
                    <select style="float: left; margin-left: 15px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="clear: both; font-size: 0.8em; padding-top: 10px;">
                    <div style="clear: both;">
                        <button class="btn btn-warning"
                                style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                            Remove
                        </button>
                        <div style="float: left; margin-left: 15px;">Category 1</div>
                    </div>
                    <div style="clear: both;">
                        <button class="btn btn-warning"
                                style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                            Remove
                        </button>
                        <div style="float: left; margin-left: 15px;">Category 2</div>
                    </div>
                    <div style="clear: both;">
                        <button class="btn btn-warning"
                                style="float: left; font-weight: bold; width: 60pt; font-size: 0.8em; height: 20pt;">
                            Remove
                        </button>
                        <div style="float: left; margin-left: 15px;">Category 3</div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>