<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="WEB-INF/images/favicon.png" type="image/png">
  <title>Find your couse!</title>
  <style>
    <%@include file='/WEB-INF/css/bootstrap.min.css' %>
    <%@include file='/WEB-INF/css/my-style.css' %>
  </style>


  <script src="/../js/bootstrap.min.js"></script>
  <script src="/../js/jquery-3.3.1.min.js"></script>
  <script src="/../js/popper.min.js"></script>
</head>

<body>c
  <div class="container-fluid">

    <div class="row my-header bg-light align-items-center py-2">
      <div class="col-md-9 col-12">
        <h1 class="display-4 text-uppercase text-center">Find your course</h1>
      </div>
      <div class="col-md-2 col-6">
        <div class="dropdown">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Language
          </button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Русский</a>
            <a class="dropdown-item" href="#">English</a>
          </div>
        </div>
      </div>
      <div class="col-md-1 col-6">
        <a href="#">
          <img class="mylogo img-fluid img-profile rounded-circle mx-auto mb-2" src="../../images/profile-picture.png">
        </a>
      </div>
    </div>

    <div class="row py-5 align-items-center">
      <div class="col-md-12">

        <p class="display-3 text-center"> Welcome to our site, </p>
        <p class="display-4 text-center"> we hope it can help you! </p>
        <p class="text-center">
          <button type="button" class="btn btn-primary btn-lg">Sign in</button>
          <button type="button" class="btn btn-primary btn-lg">Register</button>
        </p>
      </div>
    </div>
  </div>

  <div class="row bg-dark align-items-center my-footer fixed-bottom">
    <div class="col-md-12 col-12 text-center py-3">
      <h6 class="text-white ">Makhabbat Kuzhaniyazova</h6>
      <a href="mailto:makonya2302@gmail.com" class="btn btn-primary">Contact me</a>
    </div>
  </div>
</body>

</html>