<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

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
<%--    total product count--%>
    <h3>total product : ${productPage.totalCount}</h3>
    <div class="d-flex justify-content-end mb-3">
        <a href="/admin/product/register.do" class="btn btn-success">+ 새 상품 등록</a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>재고</th>
            <th>카테고리</th>
            <th>관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productPage.content}">
            <tr>
                <td>${product.productName}</td>
                <td><fmt:formatNumber value="${product.price}" type="number"/>원</td>
                <td>${product.productQuantity}</td>
                <td>
                <c:forEach var="category" items="${product.categories}">
                    ${category.categoryName}
                </c:forEach>
                </td>
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
    <c:set var="total" value="${productPage.totalCount}" />
    <c:set var="pages" value="${(total + size - 1) / size}" />
    <nav class="pagination justify-content-center">
        <ul class="pagination mb-0">
            <c:forEach begin="1" end="${pages}" var="i">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}&size=${size}">${i}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>

</div>
</body>
</html>