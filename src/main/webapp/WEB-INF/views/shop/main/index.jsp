<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${productPage.content}">
        <div class="col">
            <div class="card shadow-sm">
                <img src="${product.productImage}" class="card-img-top" style="width:100%; height:225px; object-fit:cover;" alt="상품 이미지">

                <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">
                            ${product.productDescription}
                    </p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <a href="/product/view.do?product_id=${product.productId}" class="btn btn-sm btn-outline-secondary">View</a>
                            <a href="/cart/add.do?product_id=${product.productId}" class="btn btn-sm btn-outline-secondary">Add to Cart</a>
                        </div>
                        <small class="text-muted">${product.price}원</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<nav class="pagination fixed-bottom justify-content-center py-3">
    <c:set var="total" value="${productPage.totalCount}" />
    <c:set var="pages" value="${(total + size - 1) / size}" />
    <ul class="pagination justify-content-center mb-0">
        <c:forEach begin="1" end="${pages}" var="i">
            <li class="page-item ${i == page ? 'active' : ''}">
                <a class="page-link" href="?page=${i}&size=${size}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>


<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: nhn--%>
<%--  Date: 2023/11/08--%>
<%--  Time: 10:20 AM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>


<%--<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
