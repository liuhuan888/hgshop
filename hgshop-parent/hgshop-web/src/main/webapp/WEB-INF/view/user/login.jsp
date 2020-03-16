<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN" class="translated-ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>用户登录</title>

    <!-- Bootstrap core CSS -->
<link href="/resource/bootstrap4/css/bootstrap.min.css" rel="stylesheet" >

    <!-- Favicons -->
<link rel="apple-touch-icon" href="https://v4.bootcss.com/docs/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="https://v4.bootcss.com/docs/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="https://v4.bootcss.com/docs/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="/resource/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
    <form class="form-signin" action="/user/login" method="post">
	  <img class="mb-4" src="login_files/bootstrap-solid.svg" alt="" width="72" height="72">
	  <h1 class="h3 mb-3 font-weight-normal">请输入用户名和密码</h1>
	  <label for="inputEmail" class="sr-only">登录账号</label>
	  <input type="text"  name="userName" value="${userName}" id="inputEmail" class="form-control" placeholder="用户账号" required="" autofocus="">
	  <label for="inputPassword" class="sr-only">登录密码</label>
	  <input type="password" id="inputPassword" name="password" value="${password}" class="form-control" placeholder="用户密码" required="">
	  <div class="checkbox mb-3">
	    <label>
	      <input type="checkbox" value="remember-me"> 记住登录
	    </label>
	  </div>
	  <font color="red">${error}</font> 
	  <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
	  <button class="btn btn-lg btn-primary btn-block" type="button" onclick="toRegister()">注册</button>
	  <p class="mt-5 mb-3 text-muted">© 2017-2020</p>
	</form>

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function toRegister() {
		location="/user/toregister";
	}
</script>
</body>
</html>