<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <div class="nav-title">Your Templates:</div>
            <button class="btn btn-success nav-btn">Add Template</button>
            <div class="nav-empty-elm">Template 1</div>
            <div class="nav-empty-elm">Template 2</div>
        </div>
    </div>

    <div style="margin-left: 240px; min-width: 1000px;">

        <div id="stage">
            <button id="add-wish-btn" class="btn btn-success" style="margin: 10px 0;">Save</button>

            <div style="width: 45%; min-width: 450px; float: left; clear: both;">

                <div style="float: left; width: 100%; height: 30pt;">
                    <div style="float: left; font-size: 20pt; margin-top: 10px; ">Title</div>
                    <input type="text" style="float: left; height: 22pt; width: 370px; margin-left: 15px; margin-top: 5px;"/>
                </div>
                <div style="float: left; width: 100%; margin-top: 15px;">
                    <div style="float: left; margin-top: 5px;">Main color</div>
                    <button class="btn btn-success"
                            style="float: left; font-weight: bold; margin-left: 42px; width: 100pt;">Select
                    </button>
                </div>
                <div style="float: left; width: 100%; margin-top: 15px;">
                    <div style="float: left; margin-top: 5px;">Title color</div>
                    <button class="btn btn-success"
                            style="float: left; font-weight: bold; margin-left: 45px; width: 100pt;">Select
                    </button>
                </div>
                <div style="float: left; width: 100%; margin-top: 15px;">
                    <div style="float: left; margin-top: 5px;">Text color</div>
                    <button id="text-color-btn"
                            class="btn btn-success jscolor {valueElement:'chosen-color-3', onFineChange:'setTextColorBtn(this)'}"
                            style="float: left; font-weight: bold; margin-left: 45px; width: 100pt;">Select
                    </button>
                    <input type="hidden" id="chosen-color-3" value="000000"/>
                </div>
                <div style="float: left; width: 100%; margin-top: 15px;">
                    <div style="float: left; margin-top: 5px;">Border color</div>
                    <button class="btn btn-success"
                            style="float: left; font-weight: bold; margin-left: 21px; width: 100pt;">Select
                    </button>
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Border type</div>
                    <select style="float: left; margin-left: 26px; width: 224pt; height: 18pt;"></select>
                </div>

                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Border width</div>
                    <select style="float: left; margin-left: 15px; width: 224pt; height: 18pt;"></select>
                </div>



            </div>

            <div style="float: left; width: 50%;">
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Title Style</div>
                    <select style="float: left; margin-left: 15px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Title Font</div>
                    <select style="float: left; margin-left: 20px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Text Style</div>
                    <select style="float: left; margin-left: 15px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Text Font</div>
                    <select style="float: left; margin-left: 20px; width: 224pt; height: 18pt;"></select>
                </div>
                <div style="float: left; margin-top: 10px;">
                    <div style="float: left; margin-top: 2px;">Image Position</div>
                    <select style="float: left; margin-left: 20px; width: 185pt; height: 18pt;"></select>
                </div>

            </div>
        </div>

    </div>

</div>

<%@ include file="common/footer.jsp" %>



<script src="<c:url value="/js/jscolor.min.js"/>"></script>

<script>
    function setTextColorBtn(picker) {
        document.getElementsByTagName('text-color-btn')[0].style.color = '#' + picker.toString()
    }
</script>

</body>

</html>