<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<main class="container py-5">
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <h2 class="mb-4">👤 마이페이지</h2>
            <p><strong>아이디:</strong> ${sessionScope.user.userId}</p>
            <p><strong>이름:</strong> ${sessionScope.user.userName}</p>
            <p><strong>포인트:</strong> ${sessionScope.user.userPoint}</p>
            <hr/>
            <ul>
                <li><a href="/order/history.do">주문 내역</a></li>
                <li><a href="/mypage/cart.do">장바구니</a></li>
                <li><a href="/mypage/edit.do">내 정보 수정</a></li>
            </ul>
            <form action="/mypage/delete.do" method="post" style="display:inline;">
                <input type="hidden" name="user_id" value="${sessionScope.user.userId}">
                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('정말 탈퇴할까요?')">탈퇴</button>
            </form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger">로그인이 필요합니다. <a href="/login.do">로그인하기</a></div>
        </c:otherwise>
    </c:choose>
</main>