<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>

</head>
<body>

    <div class="mainContainer">
        <header class="p-3 text-white" style="background-color: #000;">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">


                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/index.do" class="nav-link px-2 text-secondary">Home</a></li>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user and sessionScope.user.userAuth eq 'ROLE_ADMIN'}">
                                <li><a href="/admin/product.do" class="nav-link px-2 text-warning">상품관리</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/mypage.do" class="nav-link px-2 text-white">마이페이지</a></li>
                                <li><a href="/mypage/cart.do" class="nav-link px-2 text-white">장바구니</a></li>

                            </c:otherwise>
                        </c:choose>
                    </ul>
                    <!-- 1) 카테고리 토글 버튼 -->
                    <button class="btn btn-outline-light me-3" type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#categoryCollapse"
                            aria-expanded="false"
                            aria-controls="categoryCollapse">
                        카테고리
                    </button>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
                    </form>

                    <div class="text-end">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <span class="me-3">안녕하세요, <strong>${sessionScope.user.userId}</strong>집사님</span>
                                <form method="GET" action="/logout.do" style="display:inline;">
                                    <button type="submit" class="btn btn-outline-light btn-sm">로그아웃</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-outline-light me-2" href="/login.do">로그인</a>
                                <a class="btn btn-warning" href="/signup.do">회원가입</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="collapse border-top" id="categoryCollapse" style="background-color: #000;">
                <div class="container py-2">
                    <div class="d-flex flex-wrap">
                        <c:forEach var="cat" items="${requestScope.categoryList}">
                            <a href="index.do?category_id=${cat.categoryId}"
                               class="me-3 mb-2 text-decoration-none
               ${cat.categoryId == selectedCategoryId ? 'fw-bold text-primary' : 'text-white'}">
                                <strong>${cat.categoryName}</strong>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </header>

        <main>
            <div class="album py-5 bg-light">
                <div class="container">
                    <jsp:include page="${layout_content_holder}" />
                </div>
            </div>

        </main>

        <footer class="text-muted py-5">
            <div class="container">
                <p class="float-end mb-1">
                    <a href="#">Back to top</a>
                </p>
                <p class="mb-1">shoppingmall example is © nhnacademy.com</p>
            </div>
        </footer>

    </div>


</body>
</html>