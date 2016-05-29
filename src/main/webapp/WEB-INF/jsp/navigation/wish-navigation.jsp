<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="navigation">

    <div class="white-dashed-bottom nav-block">
        <div class="nav-title">Your Categories:</div>
        <c:forEach var="category" items="${categories}">
            <a href="<c:url value="/catalog/view-category/${category.categoryId}" />">
                <div class="nav-empty-elm"
                     style="background: #${category.background}; color: #${category.color}">
                     ${category.title}
                </div>
            </a>
        </c:forEach>
        <a href="<c:url value="/catalog/add-category" />">
            <button class="btn btn-success nav-btn">Add Category</button>
        </a>
    </div>

    <div class="white-dashed-bottom nav-block">
        <div class="nav-title">Your Wishlists:</div>
        <c:if test="${empty wishlists}" >
            <div class="nav-empty-elm">No wishlists, yet</div>
        </c:if>
        <c:forEach var="wishlist" items="${wishlists}">
            <a href="<c:url value="/catalog/view-wishlist/${wishlist.wishlistId}" />">
                <div class="nav-empty-elm"
                     style="background: #${wishlist.background}; color: #${wishlist.color}">
                        ${wishlist.title}
                </div>
            </a>
        </c:forEach>
        <a href="<c:url value="/catalog/add-wishlist" />">
            <button class="btn btn-success nav-btn">Add Wishlist</button>
        </a>
    </div>

    <div class="nav-block">
        <div class="nav-title">Your Giftlists:</div>
        <div class="nav-empty-elm">No giftlists, yet</div>
        <a href="<c:url value="/catalog/add-giftlist" />">
            <button class="btn btn-success nav-btn">Add Giftlist</button>
        </a>
    </div>
</div>