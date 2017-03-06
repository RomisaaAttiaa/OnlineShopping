<%-- 
    Document   : logged_header
    Created on : 01-Mar-2017, 19:58:41
    Author     : toqae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body>
        <div id="header">
            <div class="container">
                
                <!-- Navbar ================================================== -->
                <div id="logoArea" class="navbar">
                    <a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <div class="navbar-inner">
                        <a class="brand" href="home.html"><img src="themes/images/logo.png" alt="Bootsshop"/></a>
                        <form class="form-inline navbar-search" method="post" action="products.html" >
                            <input id="srchFld" class="srchTxt" type="text" />
                            <select class="srchTxt">
                                <option>All</option>
                                <option>CLOTHES </option>
                                <option>FOOD AND BEVERAGES </option>
                                <option>HEALTH & BEAUTY </option>
                                <option>SPORTS & LEISURE </option>
                                <option>BOOKS & ENTERTAINMENTS </option>
                            </select> 
                            <button type="submit" id="submitButton" class="btn btn-primary">Search</button>
                        </form>
                        <ul id="topMenu" class="nav pull-right">
                            <li class=""><a href="contact.html">Contact-Us</a></li>
                            <li class=""><a href="edit_profile.jsp">Edit-Profile</a></li>
                            <li class="">
                          
                            <li class=""><a href="SignOutServlet">SignOut</a></li>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End====================================================================== -->

    </body>
</html>
