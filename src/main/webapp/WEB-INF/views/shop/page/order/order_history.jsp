<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주문 내역</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2 class="mb-4">🧾 주문 내역</h2>

    <c:choose>
        <c:when test="${empty orderProductList}">
            <div class="alert alert-info">주문 내역이 없습니다.</div>
            <a href="${pageContext.request.contextPath}/index.do" class="btn btn-primary">메인으로 돌아가기</a>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered align-middle">
                <thead class="table-light">
                <tr>
                    <th>주문번호</th>
                    <th>주문일시</th>
                    <th>상품명</th>
                    <th>수량</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orderHistory}">
                    <tr>
                        <td>${o.orderId}</td>
                        <td>
                            <fmt:formatDate value="${o.orderDate}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>${o.productName}</td>
                        <td>${o.quantity}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/index.do" class="btn btn-secondary me-2">
                    메인으로
                </a>
                <a href="${pageContext.request.contextPath}/product/search.do" class="btn btn-primary">
                    상품 찾기
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>