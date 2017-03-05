<%--
    Document   : home
    Created on : 01-Mar-2017, 20:00:39
    Author     : toqae
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Online Shopping cart</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--Less styles -->
        <!-- Other Less css file //different less files has different color scheam
             <link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
             <link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
             <link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
        -->
        <!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
        <script src="themes/js/less.js" type="text/javascript"></script> -->

        <!-- Bootstrap style -->
        <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
        <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
        <!-- Bootstrap style responsive -->
        <link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
        <link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Google-code-prettify -->
        <link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
        <!-- fav and touch icons -->
        <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
        <style type="text/css" id="enject"></style>
    </head>
    <body>

        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <c:if test="${empty sessionScope.logged}">
                        <jsp:include page="not_logged_header.jsp"/>
                    </c:if>

                    <c:if test="${!empty sessionScope.logged}">
                        <jsp:include page="logged_header.jsp"/>
                    </c:if>

                    <!-- Sidebar ================================================== -->
                    <div id="sidebar" class="span3">
                        <div class="well well-small"><a id="myCart" href="cart.jsp"><img src="themes/images/ico-cart.png" alt="cart">
                                <c:if test="${!empty sessionScope.products}">
                                    <c:out value="${fn:length(sessionScope.products)}"/>
                                </c:if>

                                <c:if test="${empty sessionScope.products}">
                                    0
                                </c:if>
                                Items in your cart

                                <c:if test="${!empty sessionScope.logged}">
                                    <span class="badge badge-warning pull-right"><c:out value="${logged.creditLimit}"/> $</span>
                                </c:if>

                            </a></div>
                        <ul id="sideManu" class="nav nav-tabs nav-stacked">
                            <li><a href="HomeServlet"> All</a></li>
                            <li><a href="HomeServlet?category=electronics"> ELECTRONICS</a></li>
                            <li><a href="HomeServlet?category=clothes"> CLOTHES</a></li>
                            <li><a href="HomeServlet?category=books">BOOKS</a></li>
                        </ul>
                        <br/>
                    </div>
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <h4>Category Products </h4>
                        <ul class="thumbnails">
                            <c:forEach items="${requestScope.homeProducts}" var="product">

                                <li class="span3">
                                    <div class="thumbnail">
                                        <a  href='product_details.html?pId="${product.id}"'><img src="${product.url}" alt=""/></a>
                                        <div class="caption">
                                            <h5><c:out value="${product.name}"/></h5>
                                            <p>
                                                <c:out value="${product.description}"/>
                                            </p>

                                            <h4 style="text-align:center"><a class="btn" href="ProductDetails?pId=${product.id}"> <i class="icon-zoom-in"></i></a> <a class="btn" href="AddCartServlet?pId=${product.id}&pQuantity=1">Add to <i class="icon-shopping-cart"></i></a></h4>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>

                            <!--                            <li class="span3">
                                                            <div class="thumbnail">
                                                                <a  href="product_details.html"><img src="themes/images/products/7.jpg" alt=""/></a>
                                                                <div class="caption">
                                                                    <h5>Product name</h5>
                                                                    <p>
                                                                        Lorem Ipsum is simply dummy text.
                                                                    </p>
                                                                    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a  href="product_details.html"><img src="themes/images/products/8.jpg" alt=""/></a>
                                                                <div class="caption">
                                                                    <h5>Product name</h5>
                                                                    <p>
                                                                        Lorem Ipsum is simply dummy text.
                                                                    </p>
                                                                    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a  href="product_details.html"><img src="themes/images/products/9.jpg" alt=""/></a>
                                                                <div class="caption">
                                                                    <h5>Product name</h5>
                                                                    <p>
                                                                        Lorem Ipsum is simply dummy text.
                                                                    </p>
                                                                    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a  href="product_details.html"><img src="themes/images/products/10.jpg" alt=""/></a>
                                                                <div class="caption">
                                                                    <h5>Product name</h5>
                                                                    <p>
                                                                        Lorem Ipsum is simply dummy text.
                                                                    </p>
                                                                    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a  href="product_details.html"><img src="themes/images/products/11.jpg" alt=""/></a>
                                                                <div class="caption">
                                                                    <h5>Product name</h5>
                                                                    <p>
                                                                        Lorem Ipsum is simply dummy text.
                                                                    </p>
                                                                    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
                                                                </div>
                                                            </div>
                                                        </li>-->
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <!-- Footer ================================================================== -->
        <div  id="footerSection">
            <div class="container">
                <div class="row">
                    <div class="span3">
                        <h5>ACCOUNT</h5>
                        <a href="login.jsp">YOUR ACCOUNT</a>
                        <a href="login.jsp">PERSONAL INFORMATION</a>
                        <a href="login.jsp">ADDRESSES</a>
                        <a href="login.jsp">DISCOUNT</a>
                        <a href="login.jsp">ORDER HISTORY</a>
                    </div>
                    <div class="span3">
                        <h5>INFORMATION</h5>
                        <a href="contact.jsp">CONTACT</a>
                        <a href="register.html">REGISTRATION</a>
                        <a href="legal_notice.html">LEGAL NOTICE</a>
                        <a href="tac.html">TERMS AND CONDITIONS</a>
                        <a href="faq.html">FAQ</a>
                    </div>
                    <div class="span3">
                        <h5>OUR OFFERS</h5>
                        <a href="#">NEW PRODUCTS</a>
                        <a href="#">TOP SELLERS</a>
                        <a href="special_offer.html">SPECIAL OFFERS</a>
                        <a href="#">MANUFACTURERS</a>
                        <a href="#">SUPPLIERS</a>
                    </div>
                    <div id="socialMedia" class="span3 pull-right">
                        <h5>SOCIAL MEDIA </h5>
                        <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
                        <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
                        <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
                    </div>
                </div>
                <p class="pull-right">&copy; Bootshop</p>
            </div><!-- Container End -->
        </div>
        <!-- Placed at the end of the document so the pages load faster ============================================= -->
        <script src="themes/js/jquery.js" type="text/javascript"></script>
        <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="themes/js/google-code-prettify/prettify.js"></script>

        <script src="themes/js/bootshop.js"></script>
        <script src="themes/js/jquery.lightbox-0.5.js"></script>

        <!-- Themes switcher section ============================================================================================= -->
        <div id="secectionBox">
            <link rel="stylesheet" href="themes/switch/themeswitch.css" type="text/css" media="screen" />
            <script src="themes/switch/theamswitcher.js" type="text/javascript" charset="utf-8"></script>
            <div id="themeContainer">
                <div id="hideme" class="themeTitle">Style Selector</div>
                <div class="themeName">Oregional Skin</div>
                <div class="images style">
                    <a href="themes/css/#" name="bootshop"><img src="themes/switch/images/clr/bootshop.png" alt="bootstrap business templates" class="active"></a>
                    <a href="themes/css/#" name="businessltd"><img src="themes/switch/images/clr/businessltd.png" alt="bootstrap business templates" class="active"></a>
                </div>
                <div class="themeName">Bootswatch Skins (11)</div>
                <div class="images style">
                    <a href="themes/css/#" name="amelia" title="Amelia"><img src="themes/switch/images/clr/amelia.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="spruce" title="Spruce"><img src="themes/switch/images/clr/spruce.png" alt="bootstrap business templates" ></a>
                    <a href="themes/css/#" name="superhero" title="Superhero"><img src="themes/switch/images/clr/superhero.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="cyborg"><img src="themes/switch/images/clr/cyborg.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="cerulean"><img src="themes/switch/images/clr/cerulean.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="journal"><img src="themes/switch/images/clr/journal.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="readable"><img src="themes/switch/images/clr/readable.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="simplex"><img src="themes/switch/images/clr/simplex.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="slate"><img src="themes/switch/images/clr/slate.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="spacelab"><img src="themes/switch/images/clr/spacelab.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="united"><img src="themes/switch/images/clr/united.png" alt="bootstrap business templates"></a>
                    <p style="margin:0;line-height:normal;margin-left:-10px;display:none;"><small>These are just examples and you can build your own color scheme in the backend.</small></p>
                </div>
                <div class="themeName">Background Patterns </div>
                <div class="images patterns">
                    <a href="themes/css/#" name="pattern1"><img src="themes/switch/images/pattern/pattern1.png" alt="bootstrap business templates" class="active"></a>
                    <a href="themes/css/#" name="pattern2"><img src="themes/switch/images/pattern/pattern2.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern3"><img src="themes/switch/images/pattern/pattern3.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern4"><img src="themes/switch/images/pattern/pattern4.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern5"><img src="themes/switch/images/pattern/pattern5.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern6"><img src="themes/switch/images/pattern/pattern6.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern7"><img src="themes/switch/images/pattern/pattern7.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern8"><img src="themes/switch/images/pattern/pattern8.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern9"><img src="themes/switch/images/pattern/pattern9.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern10"><img src="themes/switch/images/pattern/pattern10.png" alt="bootstrap business templates"></a>

                    <a href="themes/css/#" name="pattern11"><img src="themes/switch/images/pattern/pattern11.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern12"><img src="themes/switch/images/pattern/pattern12.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern13"><img src="themes/switch/images/pattern/pattern13.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern14"><img src="themes/switch/images/pattern/pattern14.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern15"><img src="themes/switch/images/pattern/pattern15.png" alt="bootstrap business templates"></a>

                    <a href="themes/css/#" name="pattern16"><img src="themes/switch/images/pattern/pattern16.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern17"><img src="themes/switch/images/pattern/pattern17.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern18"><img src="themes/switch/images/pattern/pattern18.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern19"><img src="themes/switch/images/pattern/pattern19.png" alt="bootstrap business templates"></a>
                    <a href="themes/css/#" name="pattern20"><img src="themes/switch/images/pattern/pattern20.png" alt="bootstrap business templates"></a>

                </div>
            </div>
        </div>
        <span id="themesBtn"></span>
    </body>
</html>
