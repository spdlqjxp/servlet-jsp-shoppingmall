<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form id="signupForm" method="post" action="/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">Sign Up</h1>

            <div class="form-floating mb-2">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="이름" required>
                <label for="user_name">이름</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" name="user_password_confirm" class="form-control" id="user_password_confirm" placeholder="비밀번호 확인" required>
                <label for="user_password_confirm">비밀번호 확인</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생년월일 (YYYYMMDD)" required>
                <label for="user_birth">생년월일 (예: 19900101)</label>
            </div>

            <button class="w-100 btn btn-lg btn-success mt-3" type="submit">Sign Up</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>

<script>
    document.getElementById('signupForm').addEventListener('submit', function(e) {
        const pw = document.getElementById('user_password').value;
        const pwConfirm = document.getElementById('user_password_confirm').value;
        if (pw !== pwConfirm) {
            e.preventDefault();
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 입력해주세요.');
            document.getElementById('user_password').focus();
        }
    });
</script>