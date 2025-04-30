<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form id="mypageForm" method="post" action="/mypage/edit.do">

            <h1 class="h3 mb-3 fw-normal">내 정보 수정</h1>

            <div class="form-floating mb-2">
                <input type="text" class="form-control" id="user_id"
                       name="user_id" value="${sessionScope.user.userId}" readonly>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" class="form-control" id="user_name"
                       name="user_name" value="${sessionScope.user.userName}" required>
                <label for="user_name">이름</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" class="form-control" id="user_password"
                       name="user_password" placeholder="새 비밀번호">
                <label for="user_password">새 비밀번호</label>
            </div>
            <div class="form-floating mb-2">
                <input type="password" class="form-control" id="user_password_confirm"
                       name="user_password_confirm" placeholder="새 비밀번호 확인">
                <label for="user_password_confirm">새 비밀번호 확인</label>
            </div>

            <!-- 생년월일 -->
            <div class="form-floating mb-2">
                <input type="text" class="form-control" id="user_birth"
                       name="user_birth" value="${sessionScope.user.userBirth}" required>
                <label for="user_birth">생년월일 (예: 19900101)</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">정보 수정</button>
            <a href="/mypage.do" class="w-100 btn btn-lg btn-secondary mt-2">취소</a>
        </form>
    </div>
</div>

<script>
    document.getElementById('mypageForm').addEventListener('submit', function(e) {
        const pw = document.getElementById('user_password').value;
        const pwConfirm = document.getElementById('user_password_confirm').value;
        // 비밀번호 변경 입력이 있을 때만 확인
        if (pw || pwConfirm) {
            if (pw !== pwConfirm) {
                e.preventDefault();
                alert('새 비밀번호와 확인이 일치하지 않습니다.');
                document.getElementById('user_password').focus();
            }
        }
    });
</script>