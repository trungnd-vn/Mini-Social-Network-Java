<%-- 
    Document   : loginPage
    Created on : Sep 15, 2020, 8:54:11 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login T-Facebook</title>
        <link rel="stylesheet" type="text/css" href="css/style_login.css"/>
        
        
    </head>
    <body>
<!--        <center>
            <h1>LOGIN</h1>
            <form action="MainController" method="POST">
                Email : <input type="text" name="txtEmail" value="${param.txtEmail}"/><br/><br/>
                <font color="red">${requestScope.INVALID.emailError}</font><br/>
                Password : <input type="password" name="txtPassword"/><br/><br/>
                <font color="red">${requestScope.INVALID.passwordError}</font><br/>
                <font color="red">${requestScope.ERROR}</font><br/>
                <input type="submit" name="action" value="Sign In"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" name="action" value="Sign Up"/>
            </form>
        </center>-->
        
        <div class="cont">
            <div class="form sign-in">
                <form action="MainController" method="POST">              
                    <h2>Welcome back,</h2>
                    <label>
                        <span>Email</span>
                        <input type="text" name="txtEmail" value="${param.txtEmail}"/>
                        <font color="red">${requestScope.INVALID.emailError}</font>
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="txtPassword"/>
                        <font color="red">${requestScope.INVALID.passwordError}</font>
                    </label>
                    <p><font color="red">${requestScope.ERROR}</font></p>
                    <p class="forgot-pass">Forgot password?</p>
                    <input type="hidden" name="txtSearch" value="">
                    <input type="hidden" name="PAGEID" value="1">
                    <input type="submit" class="submit" name="action" value="Sign In"/>               
                </form>
            </div>
            <div class="sub-cont">
                <font color="red"><h3>${requestScope.DUPLICATE}</h3></font>
                <form action="MainController" method="POST">
                <div class="img">
                    <div class="img__text m--up">
                        <h2>New here?</h2>
                        <p>Sign up and discover great amount of new opportunities!</p>
                    </div>
                    <div class="img__text m--in">
                        <h2>One of us?</h2>
                        <p>If you already has an account, just sign in. We've missed you!</p>
                    </div>
                    <div class="img__btn">
                        <span class="m--up">Sign Up</span>
                        <span class="m--in">Sign In</span>
                    </div>
                </div>
                <div class="form sign-up">
                    <h2>Time to feel like home,</h2>
                    <label>
                        <span>Email</span>
                        <input type="text" name="txtAccID" value="${param.txtAccID}"/>
                        <font color="red">${requestScope.INVALID.accIDError}</font>
                    </label>
                    <label>
                        <span>Name</span>
                        <input type="text" name="txtName" value="${param.txtName}"/>
                        <font color="red">${requestScope.INVALID.nameError}</font>
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="txtPassword"/>
                        <font color="red">${requestScope.INVALID.passwordError}</font>
                    </label>
                    <label>
                        <span>Confirm</span>
                        <input type="password" name="txtPasswordCon"/>
                        <font color="red">${requestScope.INVALID.passwordConError}</font>
                    </label>
                    <input type="hidden" name="txtRole" value="member"/>
                    <input type="hidden" name="txtStatus" value="New"/>                 
                    <input type="submit" class="submit" value="Create New Account" name="action"/>
<!--                    <button type="button" class="fb-btn">Join with <span>facebook</span></button>-->
                </div>
                </form>
            </div>
        </div>
                
        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
