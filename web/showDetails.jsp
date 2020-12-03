<%-- 
    Document   : ShowDetails
    Created on : Sep 25, 2020, 9:27:09 PM
    Author     : HOME
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="" />
        <meta name="keywords" content="" /> 
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <title>${requestScope.txtAccName}</title>
        <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16"> 

        <link rel="stylesheet" href="css/main.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">

    </head>
    <body><center>
        <a href="SearchArticleController?pageIDPaging=1&txtSearch=">Back to Index</a>

        <br/>
        <br/>

    </center>
    <div class="theme-layout">
        <div class="responsive-header">
            <div class="mh-head first Sticky">
                <span class="mh-btns-left">
                    <a class="" href="#menu"><i class="fa fa-align-justify"></i>                                        
                    </a>
                </span>
                <span class="mh-text">
                    <a href="SearchArticleController?pageIDPaging=1&txtSearch=" title=""><img src="images/logo2.png" alt=""></a>
                        ${sessionScope.EMAILID}
                </span>
            </div>
        </div><!-- responsive header -->
        <div class="topbar stick">
            <div class="logo">
                <a title="" href="SearchArticleController?pageIDPaging=1&txtSearch="><img src="images/logo.png" alt=""></a>
                    ${sessionScope.EMAILID}
            </div>
        </div><!-- topbar -->
        <section>

            <div class="gap gray-bg">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row" id="page-contents">
                                <div class="col-lg-3">
                                    <aside class="sidebar static">
                                    </aside>
                                </div><!-- sidebar -->
                                <div class="col-lg-6">
                                    <div class="loadMore">
                                        <div class="central-meta item">
                                            <div class="user-post">
                                                <h3>${requestScope.txtAccName}</h3>
                                                ${requestScope.txtArcDate}<br/>
                                                ${requestScope.txtArcTitle}<br/>
                                                ${requestScope.txtArcDes}<br/>
                                                <c:if test="${requestScope.txtArcImage != null}">
                                                    <img src="images/${requestScope.txtArcImage}" style="width: 500px; height: 500px; border-radius: 15px;"/>                     
                                                </c:if>
                                                <form action="MainController" method="POST">
                                                    <c:if test="${sessionScope.ROLE != 'admin'}">
                                                        <input type="hidden" name="txtArcImage" value="${requestScope.txtArcImage}"/>
                                                        <input type="hidden" name="txtAcc" value="${requestScope.txtAcc}"/>
                                                        <input type="hidden" name="txtAccName" value="${requestScope.txtAccName}"/>
                                                        <input type="hidden" name="txtArcDate" value="${requestScope.txtArcDate}"/>
                                                        <input type="hidden" name="txtArcTitle" value="${requestScope.txtArcTitle}"/>
                                                        <input type="hidden" name="txtArcDes" value="${requestScope.txtArcDes}"/>
                                                        <input type="hidden" name="txtArticleID" value="${requestScope.txtArticleID}"/>
                                                        <button type="submit" name="action" value="Like">${requestScope.LIKE} Likes</button> 
                                                    </c:if>            
                                                </form>
                                                <form action="MainController" method="POST">
                                                    <c:if test="${sessionScope.ROLE != 'admin'}">
                                                        <input type="hidden" name="txtArcImage" value="${requestScope.txtArcImage}"/>
                                                        <input type="hidden" name="txtAcc" value="${requestScope.txtAcc}"/>
                                                        <input type="hidden" name="txtAccName" value="${requestScope.txtAccName}"/>
                                                        <input type="hidden" name="txtArcDate" value="${requestScope.txtArcDate}"/>
                                                        <input type="hidden" name="txtArcTitle" value="${requestScope.txtArcTitle}"/>
                                                        <input type="hidden" name="txtArcDes" value="${requestScope.txtArcDes}"/>
                                                        <input type="hidden" name="txtArticleID" value="${requestScope.txtArticleID}"/>
                                                        <button type="submit" name="action" value="Dislike">${requestScope.DISLIKE} Dislikes</button>
                                                    </c:if>
                                                </form>
                                                <c:if test="${sessionScope.ROLE == 'admin'}">
                                                    <button>${requestScope.LIKE} Likes</button>
                                                </c:if>
                                                <c:if test="${sessionScope.ROLE == 'admin'}">
                                                    <button>${requestScope.DISLIKE} Dislikes</button>
                                                </c:if>
                                                <br/>
                                                Comment:<br/>
                                                <c:if test="${requestScope.LIST_COMMENT != null}">
                                                    <c:if test="${not empty requestScope.LIST_COMMENT}" var="checkList">             
                                                        <c:forEach items="${requestScope.LIST_COMMENT}" var="comment">
                                                            <form action="MainController" method="POST">
                                                                <br/>
                                                                ${comment.accID.accName} : ${comment.commentContent}
                                                                <c:if test="${sessionScope.ROLE == 'admin'}">
                                                                    <input type="hidden" name="txtArcImage" value="${requestScope.txtArcImage}"/>
                                                                    <input type="hidden" name="txtAcc" value="${requestScope.txtAcc}"/>
                                                                    <input type="hidden" name="txtAccName" value="${requestScope.txtAccName}"/>
                                                                    <input type="hidden" name="txtArcDate" value="${requestScope.txtArcDate}"/>
                                                                    <input type="hidden" name="txtArcTitle" value="${requestScope.txtArcTitle}"/>
                                                                    <input type="hidden" name="txtArcDes" value="${requestScope.txtArcDes}"/>
                                                                    <input type="hidden" name="txtArticleID" value="${requestScope.txtArticleID}"/>
                                                                    <input type="hidden" name="txtCmtID" value="${comment.cmtID}"/>
                                                                    <input type="submit" name="action" value="Del" onclick="return confirm('Are you sure ?')"/>
                                                                </c:if>
                                                                <c:if test="${comment.accID.accID == sessionScope.EMAILID}"><input type="hidden" name="txtImageArt" value="${requestScope.txtArcImage}"/>
                                                                    <input type="hidden" name="txtArcImage" value="${requestScope.txtArcImage}"/>
                                                                    <input type="hidden" name="txtAcc" value="${requestScope.txtAcc}"/>
                                                                    <input type="hidden" name="txtAccName" value="${requestScope.txtAccName}"/>
                                                                    <input type="hidden" name="txtArcDate" value="${requestScope.txtArcDate}"/>
                                                                    <input type="hidden" name="txtArcTitle" value="${requestScope.txtArcTitle}"/>
                                                                    <input type="hidden" name="txtArcDes" value="${requestScope.txtArcDes}"/>
                                                                    <input type="hidden" name="txtArticleID" value="${requestScope.txtArticleID}"/>
                                                                    <input type="hidden" name="txtCmtID" value="${comment.cmtID}"/>
                                                                    <input type="submit" name="action" value="Del" onclick="return confirm('Are you sure ?')"/>
                                                                </c:if>
                                                            </form>
                                                        </c:forEach>            
                                                    </c:if>

                                                    <c:if test="${!checkList}">
                                                        <c:if test="${sessionScope.ROLE != 'admin'}">
                                                            <font color="red">Be the first to comment on this content.</font>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                                <br/>
                                                <form action="MainController" method="POST">
                                                    <c:if test="${sessionScope.ROLE != 'admin'}">
                                                        <input type="text" name="txtCmt" placeholder="Write a comment ..."/>
                                                        <input type="hidden" name="txtArcImage" value="${requestScope.txtArcImage}"/>
                                                        <input type="hidden" name="txtAcc" value="${requestScope.txtAcc}"/>
                                                        <input type="hidden" name="txtAccName" value="${requestScope.txtAccName}"/>
                                                        <input type="hidden" name="txtArcDate" value="${requestScope.txtArcDate}"/>
                                                        <input type="hidden" name="txtArcTitle" value="${requestScope.txtArcTitle}"/>
                                                        <input type="hidden" name="txtArcDes" value="${requestScope.txtArcDes}"/>
                                                        <input type="hidden" name="txtArticleID" value="${requestScope.txtArticleID}"/>
                                                        <input type="submit" name="action" value="Post Comment"/>
                                                    </c:if>
                                                </form>
                                                <form action="MainController" method="POST">       
                                                    <c:if test="${sessionScope.ROLE == 'admin'}">
                                                        <input type="hidden" name="articleID" value="${requestScope.articleID}"/>
                                                        <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure ?')"/>
                                                    </c:if>
                                                    <c:if test="${requestScope.txtAccID.accID == sessionScope.EMAILID}">
                                                        <input type="hidden" name="articleID" value="${requestScope.articleID}"/>
                                                        <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure ?')"/>
                                                    </c:if>
                                                </form> 
                                            </div>
                                        </div>	
                                    </div>
                                </div>
                            </div>	
                        </div>
                    </div>
                </div>
            </div>	
        </section>
    </div>
    <div class="side-panel">

    </div><!-- side panel -->		
    <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="js/main.min.js"></script>
    <script src="js/script.js"></script>
    <script src="js/map-init.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8c55_YHLvDHGACkQscgbGLtLRdxBDCfI"></script>
</body>
</html>
