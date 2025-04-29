<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2>📦 상품 등록</h2>

    <form action="/admin/product/register.do" method="post">
        <div class="mb-3">
            <label for="product_name" class="form-label">상품명</label>
            <input type="text" class="form-control" id="product_name" name="product_name" required>
        </div>

        <div class="mb-3">
            <label for="product_price" class="form-label">가격</label>
            <input type="number" class="form-control" id="product_price" name="product_price" required>
        </div>

        <div class="mb-3">
            <label for="product_quantity" class="form-label">재고 수량</label>
            <input type="number" class="form-control" id="product_quantity" name="product_quantity" required>
        </div>

        <div class="mb-3">
            <label for="product_description" class="form-label">상품 설명</label>
            <textarea class="form-control" id="product_description" name="product_description" rows="5" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">카테고리 선택 (최대 3개)</label>

            <select class="form-select mb-2" id="category_id_1" name="category_id" required>
                <option value="">-- 카테고리 선택 --</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select>

            <select class="form-select mb-2" id="category_id_2" name="category_id">
                <option value="">-- 선택 안함 --</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select>

            <select class="form-select" id="category_id_3" name="category_id">
                <option value="">-- 선택 안함 --</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </select>

        </div>

        <button type="submit" class="btn btn-primary">상품 저장</button>
        <a href="/admin.do" class="btn btn-secondary">취소</a>
    </form>
</div>
</body>
</html>