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


<div id="flex-content" style="background: #a0d8f1;">

    <!-- Forwarder: navigate user to login/register/start. -->
    <div id="navigation" style="background: #a0d8f1; color: #000000; border: none; margin-left: 15px;">
        <div class="nav-block">
            <div class="nav-title">Your Layouts:</div>
            <button class="btn btn-success nav-btn">Add Layout</button>
            <div class="nav-empty-elm">Layout 1</div>
            <div class="nav-empty-elm">Layout 2</div>
        </div>
    </div>

    <div style="margin-left: 240px; min-width: 1000px;">

        <div id="stage">

            <div style="width: 45%; min-width: 450px; float: left; clear: both;">

                <button id="add-wish-btn" class="btn btn-success" style="margin: 10px 0;">Save</button>

                <div style="float: left; width: 100%; height: 30pt;">
                    <div style="float: left; font-size: 20pt; margin-top: 10px; ">Title</div>
                    <input type="text" style="float: left; height: 22pt; width: 370px; margin-left: 15px; margin-top: 5px;"/>
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Model type</div>
                    <select style="float: left; margin-left: 26px; width: 224pt; height: 18pt;"></select>
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Block width</div>
                    <input type="text" style="float: left; margin-left: 18px; width: 224pt; height: 18pt;" />
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Block height</div>
                    <input type="text" style="float: left; margin-left: 11px; width: 224pt; height: 18pt;" />
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Block width</div>
                    <input type="text" style="float: left; margin-left: 18px; width: 224pt; height: 18pt;" />
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Padding</div>
                    <input type="text" style="float: left; margin-left: 49px; width: 224pt; height: 18pt;" />
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Margin</div>
                    <input type="text" style="float: left; margin-left: 61px; width: 224pt; height: 18pt;" />
                </div>
            </div>

        </div>

    </div>

</div>


<%@ include file="common/footer.jsp" %>

</body>

</html>