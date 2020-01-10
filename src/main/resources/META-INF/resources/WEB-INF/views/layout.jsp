<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
    <title>example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <script src="/webjars/jquery/3.4.1/dist/jquery.min.js"></script>
    <script src="/webjars/jquery-validation/1.19.0/dist/jquery.validate.min.js"></script>
    <script src="/webjars/jquery-serialize-object/2.1.0/jquery.serialize-object.min.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/index">메인</a></li>
                <li><a href="/freeboards/list">자유 게시판</a></li>
                <li><a href="#">회원 게시판</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="/join"><span class="glyphicon glyphicon-log-in"></span> 회원가입</a></li>
                </sec:authorize>
                <li><a href="/leave"><span class="glyphicon glyphicon-log-in"></span> 회원탈퇴</a></li>
                <li><a href="/update"><span class="glyphicon glyphicon-log-in"></span> 회원수정</a></li>
                <sec:authorize access="isAnonymous()">
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> 로그아웃</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
            <p><a href="#">Link</a></p>
        </div>

        <jsp:include page="${empty body ? 'main/index.jsp' : body}"/>

        <div class="col-sm-2 sidenav">
            <div class="well"><p>ADS</p></div>
            <div class="well"><p>ADS</p></div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>