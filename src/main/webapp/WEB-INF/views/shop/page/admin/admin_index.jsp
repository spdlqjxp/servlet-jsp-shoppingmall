<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 관리</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2>📦 상품 관리</h2>

    <div class="d-flex justify-content-end mb-3">
        <a href="/admin/product/register.do" class="btn btn-success">+ 새 상품 등록</a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>상품 ID</th>
            <th>상품명</th>
            <th>가격</th>
            <th>재고</th>
            <th>카테고리</th>
            <th>관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td>${product.productQuantity}</td>
                <td>${product.categoryName}</td> <!-- 상품+카테고리 조인 조회 추천 -->
                <td>
                    <a href="/admin/product/edit.do?product_id=${product.productId}" class="btn btn-primary btn-sm">수정</a>
                    <form action="/admin/product/delete.do" method="post" style="display:inline;">
                        <input type="hidden" name="product_id" value="${product.productId}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('정말 삭제할까요?')">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>