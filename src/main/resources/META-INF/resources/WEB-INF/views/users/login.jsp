<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="col-sm-8 text-left">
    <h1>로그인</h1>
    <form action="/login" method="post">
        이메일: <input type="text" placeholder="이메일" name="username"/><br>
        비밀번호: <input type="password" placeholder="비밀번호" name="password"/><br>
        <input type="submit" value="로그인"/>
        <input type="reset" value="초기화"/>
    </form><br>
</div>