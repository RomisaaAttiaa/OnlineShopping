<%-- 
    Document   : sidebar
    Created on : Mar 5, 2017, 8:21:06 PM
    Author     : Eslam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <body>
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
    </body>
</html>
