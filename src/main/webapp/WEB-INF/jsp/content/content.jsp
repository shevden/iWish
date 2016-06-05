<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="clear: both;">
<c:forEach var="wku" items="${requestScope.content}">

    <div style="
            width:    ${requestScope.layout.width};
            height:   ${requestScope.layout.height};
            margin:   ${requestScope.layout.margin};
            padding:  ${requestScope.layout.padding};

            background:   ${wku.template.mainColorDP};
            border-color: ${wku.template.borderColorDP};
            border-style: ${wku.template.borderType};
            border-width: ${wku.template.borderWidth};
            overflow: hidden;

            <c:if test="${requestScope.layout.model eq 'Grid'}">
                float: left;
            </c:if>
            ">

        <c:choose>
            <c:when test="${wku.template.imagePosition eq 'Top-Left'}">
                <div style="float: left;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-title.jsp" %>
                <br/>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Top-Center'}">
                <div style="margin-left: ${layout.width/2 - 28};">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-title.jsp" %>
                <br/>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Top-Right'}">
                <div style="float: right;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-title.jsp" %>
                <br/>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Center-Right'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div style="clear: both; float: right;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Bottom-Right'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div>
                    <%@ include file="../content/content-desc.jsp" %>
                </div>
                <div style="clear: both; float: right;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Bottom-Center'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div>
                    <%@ include file="../content/content-desc.jsp" %>
                </div>
                <div style="margin-left: ${layout.width/2 - 28};">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Bottom-Left'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div>
                    <%@ include file="../content/content-desc.jsp" %>
                </div>
                <div style="clear: both; float: left;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Center-Left'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div style="clear: both; float: left;">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>

            <c:when test="${wku.template.imagePosition eq 'Center'}">
                <div>
                    <%@ include file="../content/content-title.jsp" %>
                </div>
                <div style="clear: both; margin-left: ${layout.width/2 - 28};">
                    <%@ include file="../content/content-image.jsp" %>
                </div>
                <%@ include file="../content/content-desc.jsp" %>
            </c:when>
        </c:choose>


        <br/>



    </div>


</c:forEach>
</div>
