<%-- 
    Document   : createAccount
    Created on : Sep 21, 2020, 3:22:23 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create T-FaceBook Account</title>
    </head>
    <body>
    <center>
        <h1>Create a new T-Facebook Account</h1>
        <font color="red"><h3>${requestScope.DUPLICATE}</h3></font>
        <form action="MainController" method="POST">
            Email : <input type="text" name="txtAccID" value="${param.txtAccID}"/><br/>
                    <font color="red">${requestScope.INVALID.accIDError}</font><br/>
            Name :  <input type="text" name="txtName" value="${param.txtName}"/><br/>
                    <font color="red">${requestScope.INVALID.nameError}</font><br/><br/>
            Password :  <input type="password" name="txtPassword"/><br/>
                        <font color="red">${requestScope.INVALID.passwordError}</font><br/><br/>
            Confirm Password :  <input type="password" name="txtPasswordCon"/><br/>
                                <font color="red">${requestScope.INVALID.passwordConError}</font><br/><br/>
            <input type="hidden" name="txtRole" value="member"/>
            <input type="hidden" name="txtStatus" value="New"/>
            <input type="submit" value="Create New Account" name="action"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" value="Reset" name="reset"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="Cancel" name="action"/>
        </form>
    </center>
    </body>
</html>
