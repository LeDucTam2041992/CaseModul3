<%--
  Created by IntelliJ IDEA.
  User: TamLeDuc
  Date: 9/3/2020
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        *{
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/products">Tech Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link " href="/products?special=smartphone">Smartphone</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products?special=laptop">Laptop</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products?special=tablet">Tablet</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products?special=headphone">Headphone</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Orther
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<header class="bg-light" style="height: 100px"></header>
<ul class="nav nav-pills nav-justified m-auto" style="width:  85%">
    <c:forEach items="${requestScope['producers']}" var="producer">
        <li class="nav-item border">
            <a class="nav-link" href="/products?action=sort&nameSort=${producer}&spec=${requestScope['spec']}">${producer}</a>
        </li>
    </c:forEach>
</ul>
<section>
    <div class="row m-auto" style="width: 85%">
        <c:forEach items="${requestScope['listProduct']}" var="product">
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xl-2 border">
                <a href="/products?action=show&id=${product.getId()}">
                    <img class="img-fluid py-3" src="${product.getImgUrl()}" alt="No image">
                    <p class="card-title text-dark">${product.getName()}</p>
                    <p class="card-text text-danger mb-3">Price : ${product.getPrice()} Vnd</p>
                </a>
            </div>
        </c:forEach>
    </div>
</section>
<footer class="footer bg-light" style="height: 100px"></footer>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>