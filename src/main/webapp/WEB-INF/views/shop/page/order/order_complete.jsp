<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container py-5 text-center">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <div class="card border-0 shadow-sm mb-4">
                <div class="card-body">
                    <h2 class="card-title mb-3">🎉 주문이 완료되었습니다!</h2>
                    <p class="card-text mb-4">
                        주문해 주셔서 감사합니다.<br/>
                    </p>

                    <div class="btn-group" role="group" aria-label="Order complete actions">
                        <!-- 메인페이지로 돌아가기 -->
                        <a href="${pageContext.request.contextPath}/index.do"
                           class="btn btn-primary">
                            메인으로
                        </a>

                        <!-- 결제 내역(주문 내역) 조회 -->
                        <a href="${pageContext.request.contextPath}/mypage/order/history.do"
                           class="btn btn-outline-secondary">
                            결제 내역 보기
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>