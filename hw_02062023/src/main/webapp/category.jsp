<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${categoryName}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand">${categoryName}</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="eshop?command=get-homePage">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Профиль</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="eshop?command=redirect-to-shopping-cart">Корзина</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="row">
    <c:forEach items="${categoryProducts}" var="product">
        <div class="col d-flex justify-content-center">
            <a href="eshop?command=product-redirect&id=${product.getId()}" style="text-decoration:none;color:inherit">
                <div class="card" style="width: 19rem; margin: 20px">
                    <img class="card-img-top"
                         src="${contextPath}/images/${product.getImageName()}" alt="Card image">
                    <div class="card-body" style="text-align: center">
                        <h5 class="card-title"> ${product.getName()}</h5>
                        <p class="card-text">${product.getDescription()}</p>
                        <p class="card-text">Цена: <fmt:formatNumber value="${product.getPrice()}"
                                                                     type="currency"/><br>
                    </div>
                </div>
            </a>
        </div>
    </c:forEach>
</div>

</body>
</html>
