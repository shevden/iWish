<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/catalog/view-wku/${wku.wkuId}"/> ">
        <span style="
                color: ${wku.template.titleColorDP};
                font-weight: ${wku.template.titleBoldDP};
                font-style: ${wku.template.titleItalicDP};
                font-family: ${wku.template.titleFont};
                font-size: 1.1em;
                ">
            ${wku.title}
        </span>
</a>
