<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <div class="row">
        <div class="col-md-6 text-center">
            <img src="${pageContext.request.contextPath}${product.productImage}"
                 alt="${product.productName}"
                 class="img-fluid"
                 style="max-height: 500px; object-fit: contain;">
        </div>

        <div class="col-md-6">
            <div class="border rounded p-4 shadow-sm">
                <h2 class="mb-3">${product.productName}</h2>
                <h4 class="text-danger mb-3">${product.price}원</h4>
                <p class="mb-4">${product.productDescription}</p>

                <div class="mb-4">
                    <strong>카테고리:</strong>
                    <c:forEach var="cat" items="${product.categories}">
                        <span class="badge bg-secondary me-1">${cat.categoryName}</span>
                    </c:forEach>
                </div>

                <form id="addCartForm" method="post" action="${pageContext.request.contextPath}/mypage/cart.do">
                    <input type="hidden" name="product_id" value="${product.productId}" />
                    <div class="mb-3 w-50">
                        <label for="quantity" class="form-label">수량</label>
                        <c:choose>
                            <c:when test="${product.productQuantity > 0}">
                                <input type="number" id="quantity" name="product_count"
                                       class="form-control" value="1" min="1"
                                       max="${product.productQuantity}" required>
                            </c:when>

                            <c:otherwise>
                                <div class="alert alert-warning">품절</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-outline-secondary">
                            장바구니에 담기
                        </button>
                        <a href="${pageContext.request.contextPath}/order/create.do?product_id=${product.productId}&quantity=${quantity}"
                           class="btn btn-primary">
                            바로 구매
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>