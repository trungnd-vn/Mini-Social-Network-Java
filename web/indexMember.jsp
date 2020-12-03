<%-- 
    Document   : index
    Created on : Sep 15, 2020, 8:42:04 PM
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
        <title>T-FaceBook</title>
        <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16"> 

        <link rel="stylesheet" href="css/main.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">
    </head>
    <body>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript">
            $(function() {
            $("#testform").validate({
            rules: {
            txtPostTitle: {
            required: true,
                    rangelength: [6, 100]
            }
            txtPostDescription: {
            required: true
            }

            txtFile: {
            accept: "bmp|png|jpg"
            }
            }
            });
        </script>
        <div class="theme-layout">

            <div class="responsive-header">
                <div class="mh-head first Sticky">
                    <span class="mh-btns-left">
                        <a class="" href="#menu"><i class="fa fa-align-justify"></i>                                        
                        </a>

                        <form action="MainController" method="POST">
                            <input type="hidden" value="${sessionScope.EMAILID}" name="txtAccID"/>
                            <input type="submit" value="Log Out" name="action"/>
                        </form>
                    </span>
                    <span class="mh-text">
                        <a href="SearchArticleController?pageIDPaging=1&txtSearch=" title=""><img src="images/logo2.png" alt=""></a>
                            ${sessionScope.EMAILID}
                    </span>
                    <span class="mh-btns-right">
                        <form action="emailform.jsp" method="POST">
                            <input type="hidden" value="${sessionScope.EMAILID}" name="txtEmailVerify"/>
                            <input type="submit" value="Activate Account" name="action" />
                        </form>
                    </span>
                </div>
            </div><!-- responsive header -->

            <div class="topbar stick">
                <div class="logo">
                    <a title="" href="SearchArticleController?pageIDPaging=1&txtSearch="><img src="images/logo.png" alt=""></a>
                        ${sessionScope.EMAILID}
                </div>

                <div class="top-area">
                    <form action="MainController" method="POST">
                        <input type="hidden" value="${sessionScope.EMAILID}" name="txtAccID"/>
                        <input type="submit" value="Log Out" name="action"/>
                    </form>
                    <form action="emailform.jsp" method="POST">
                        <input type="hidden" value="${sessionScope.EMAILID}" name="txtEmailVerify"/>
                        <input type="submit" value="Activate Account" name="action" />
                    </form>
                </div>
            </div><!-- topbar -->

            <section>
                <div class="mh-head second">
                    <form action="MainController" method="POST" class="mh-form">
                        <input placeholder="Search a post" type="text" name="txtSearch" value="${param.txtSearch}"/>
                        <input type="hidden" name="txtCheck" value="True">
                        <input type="submit" name="action" value="Search" class="fa fa-search"/>
                    </form>  
                </div>
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
                                        <div class="central-meta">
                                            <div class="new-postbox">
                                                <div class="newpst-input">
                                                    <h3>${sessionScope.NAME}</h3>
                                                    <form name="testform" action="PostController" id="testform" enctype="multipart/form-data" method="POST">
                                                        Title: <input type="text" name="txtPostTitle" value="" placeholder="What do you think today ?"/><br/><br/>
                                                        Description: <input type="text" name="txtPostDescription" value="" placeholder="More and more..."/><br/>
                                                        <div class="attachments">
                                                            <ul>
                                                                <i class="fa fa-image"></i>
                                                                <label class="fileContainer">
                                                                    <input type="file" name="txtFile"/>
                                                                </label>     
                                                                <li>
                                                                    <input type="submit" value="Post" name="action"/>
                                                                </li>                                                                                        
                                                            </ul>
                                                        </div>
                                                    </form>
                                                    ${requestScope.NOTI}
                                                </div>
                                            </div>
                                        </div><!-- add post new box -->
                                        <div class="loadMore">
                                            <div class="central-meta item">
                                                <div class="user-post">
                                                    <center>
                                                        <c:if test="${requestScope.LIST_ARTICLE != null}">
                                                            <c:if test="${not empty requestScope.LIST_ARTICLE}" var="checkList">             
                                                                <c:forEach items="${requestScope.LIST_ARTICLE}" var="article">
                                                                    <form action="MainController" method="POST">
                                                                        <button name="action" value="ShowDetails" style="text-align: left; width: 100%; height: 100%; border-radius: 15px;">
                                                                            <h2>${article.accID.accName}</h2> 
                                                                            ${article.articleDate}<br/><br/>
                                                                            ${article.articleTitle}<br/><br/>
                                                                            ${article.articleDescription}<br/><br/>
                                                                            <c:if test="${article.articleImage != null}">
                                                                                <img src="images/${article.articleImage}" style="width: 100%; height: 100%; border-radius: 15px;"/>                     
                                                                            </c:if>
                                                                            <input type="hidden" name="txtArticleID" value="${article.articleID}"/>
                                                                            <input type="hidden" name="txtAcc" value="${article.accID}"/>
                                                                            <input type="hidden" name="txtAccName" value="${article.accID.accName}"/>
                                                                            <input type="hidden" name="txtArcDate" value="${article.articleDate}"/>
                                                                            <input type="hidden" name="txtArcTitle" value="${article.articleTitle}"/>
                                                                            <input type="hidden" name="txtArcDes" value="${article.articleDescription}"/>
                                                                            <input type="hidden" name="txtArcImage" value="${article.articleImage}"/>
                                                                            <c:if test="${article.accID.roleName == admin}">
                                                                                <input type="submit" name="action" value="Delete"/>
                                                                            </c:if>
                                                                            <c:if test="${article.accID.accID == sessionScope.EMAILID}">
                                                                                <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure ?')"/>
                                                                            </c:if>
                                                                        </button>
                                                                        <br/><br/>
                                                                    </form>
                                                                </c:forEach>            
                                                            </c:if>

                                                            <c:if test="${!checkList}">
                                                                <font color="red">Not Found Any Article</font>
                                                            </c:if>
                                                        </c:if>
                                                    </center>

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

            <footer>
                <div class="container">
                    <div class="row">
                        <c:forEach begin="1" end="${requestScope.ARTICLE_COUNT}" var="page" varStatus="counter">
                            <c:url value="SearchArticleController" var="pageNum">
                                <c:param name="pageIDPaging" value="${counter.count}"/>
                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                            </c:url>
                            <a href="${pageScope.pageNum}">
                                <span style="margin-right: 2rem">${page}</span>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </footer><!-- footer -->

        </div>
        <div class="side-panel">

        </div><!-- side panel -->		

        <script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="js/main.min.js"></script>
        <script src="js/script.js"></script>
        <script src="js/map-init.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8c55_YHLvDHGACkQscgbGLtLRdxBDCfI"></script>


    </body>
</html>
