<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2>🛒 내 장바구니</h2>

    <c:choose>
        <c:when test="${empty sessionScope.cartList}">
            <p>장바구니에 담긴 상품이 없습니다.</p>
            <a href="${pageContext.request.contextPath}/index.do" class="btn btn-primary">
                쇼핑 계속하기
            </a>
        </c:when>
        <c:otherwise>
            <div>
                <table class="table table-bordered">
                    <thead class="table-light">
                    <tr>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>합계</th>
                        <th>삭제</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="totalPrice" value="${0}" />
                    <c:forEach var="cart" items="${sessionScope.cartList}" varStatus="st">
                        <c:set var="prod" value="${productList[st.index]}" />
                        <c:set var="lineTotal" value="${prod.price * cart.quantity}" />
                        <tr>
                            <td>${prod.productName}</td>
                            <td>${cart.quantity}</td>
                            <td><fmt:formatNumber value="${prod.price}" type="number"/>원</td>
                            <td><fmt:formatNumber value="${lineTotal}" type="number"/>원</td>
                            <td>
                                <form method="post"
                                      action="${pageContext.request.contextPath}/mypage/cart/delete.do"
                                      style="display:inline;"
                                      onsubmit="return confirm('정말 삭제하시겠습니까?');">
                                    <input type="hidden" name="index" value="${st.index}" />
                                    <button type="submit" class="btn btn-link p-0 m-0 align-baseline">
                                        삭제
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <c:set var="totalPrice" value="${totalPrice + lineTotal}" />
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="3" class="text-end">총 합계</th>
                        <th colspan="2"><fmt:formatNumber value="${totalPrice}" type="number"/>원</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/mypage/order.do">
                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/index.do" class="btn btn-secondary">
                        쇼핑 계속하기
                    </a>
                    <button type="submit" class="btn btn-primary">
                        상품 구매
                    </button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>