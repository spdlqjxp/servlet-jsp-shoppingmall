<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2>🛠️ 상품 수정</h2>

    <form action="/admin/product/edit.do" method="post">
        <input type="hidden" name="product_id" value="${product.productId}">

        <div class="mb-3">
            <label for="product_name" class="form-label">상품명</label>
            <input type="text" class="form-control" id="product_name" name="product_name" value="${product.productName}" required>
        </div>

        <div class="mb-3">
            <label for="product_price" class="form-label">가격</label>
            <input type="number" class="form-control" id="product_price" name="product_price" value="${product.price}" required>
        </div>

        <div class="mb-3">
            <label for="product_quantity" class="form-label">재고 수량</label>
            <input type="number" class="form-control" id="product_quantity" name="product_quantity" value="${product.productQuantity}" required>
        </div>

        <div class="mb-3">
            <label for="product_description" class="form-label">상품 설명</label>
            <textarea class="form-control" id="product_description" name="product_description" rows="5" required>${product.productDescription}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">카테고리 선택 (최소 1개 이상 선택)</label>

            <select class="form-select mb-2" id="category_id_1" name="category_id" required>
                <option value="">카테고리 선택</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}"
                            <c:if test="${category.categoryId eq category_id_1}">selected</c:if>
                    >
                            ${category.categoryName}
                    </option>
                </c:forEach>
            </select>

            <select class="form-select mb-2" id="category_id_2" name="category_id">
                <option value="">-- 선택 안함 --</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}"
                            <c:if test="${category.categoryId eq category_id_2}">selected</c:if>
                    >
                            ${category.categoryName}
                    </option>
                </c:forEach>
            </select>

            <select class="form-select" id="category_id_3" name="category_id">
                <option value="">-- 선택 안함 --</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}"
                            <c:if test="${category.categoryId eq category_id_3}">selected</c:if>
                    >
                            ${category.categoryName}
                    </option>
                </c:forEach>
            </select>

        </div>
        <button type="submit" class="btn btn-primary">수정 완료</button>
        <a href="/admin/product.do" class="btn btn-secondary">취소</a>
    </form>
    <form action="/admin/product/image-upload.do" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="product_id" value="${product.productId}"/>
        <div class="mb-3">
            <label class="form-label">현재 이미지</label><br>
            <img src="${pageContext.request.contextPath}${product.productImage}"
                 alt="${product.productName}"
                 style="max-width:200px; height:auto;" alt="현재 이미지">
        </div>
        <div class="mb-3">
            <label class="form-label" for="product_image">새 이미지 업로드</label>
            <input type="file" id="product_image" name="product_image"
                   class="form-control" accept="image/*">
        </div>
        <button type="submit" class="btn btn-secondary">이미지 저장</button>
    </form>
</div>
</body>
</html>