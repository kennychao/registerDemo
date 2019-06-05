<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style_component.css"></link>
<link rel="stylesheet" href="css/style_component2.css"></link>
<link rel="stylesheet" href="css/style_utilities.css"></link>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/index.js?v=100"></script>
<title>TestDemo</title>
</head>
<body>
	<header>
        <div class="c-logo">
            <a href="index.jsp"><img src="image/tomcat.png"></a>
        </div>
        <nav>
            <ul>
                <li class="c-nav-li"><a href="index.jsp">| 首頁</a></li>
                <li class="c-nav-li">| 關於我們</li>
                <li class="c-nav-li">| 活動消息</li>
                <li class="c-nav-li">| Q & A</li>
                <li class="c-nav-li">| 登入 |</li>
            </ul>
        </nav>
    </header>
    
    <section>
        <div class="c-page-content">
            <div class="u-t-align--center u-margin--vertical-1em">使用者註冊</div>
            <div class="c-form">
                <div class="c-form__tr">
                    <div class="c-form__th">使用者名稱：</div>
                    <div class="c-form__td">
                        <input class="u-width--70" type="text" name="userName" id="userName">
                    </div>
                </div>
                <div class="c-form__tr">
                    <div class="c-form__th">使用者密碼：</div>
                    <div class="c-form__td">
                        <input class="u-width--70" type="password" name="userPwd" id="userPwd">
                    </div>
                </div>
                <div class="c-form__tr u-t-align--center u-margin--vertical-1em">
                    <button class="c-button" onclick="signUp()">註冊</button>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="c-footer-left">
            <ul>
                <li>聯絡我們</li>
                <li>週一至週五</li>
                <li>聯絡電話：0912345678</li>
            </ul>
        </div>
        <div class="c-footer-right">
            <img src="image/tomcat.png" width="100">
        </div>
    </footer>
	
</body>
</html>