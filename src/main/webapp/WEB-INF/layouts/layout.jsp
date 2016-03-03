<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-with, initial-scale=1.0"/>
    <link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"
          rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/styles.css" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
    <title><tiles:getAsString name="title"/></title>
</head>

<body>
Language : <a href="?lang=pl">Polish</a> | <a href="?lang=en">English</a>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <a href="${pageContext.servletContext.contextPath}/" class="navbar-brand">Project Manager</a>

        <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <%--<li><a href="#">Produkty</a></li>--%>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                                code="page.layout.managment"/><b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                                <%--<li><a href="#"><spring:message code="page.layout.projects"/></a></li>--%>
                            <li><a href="${pageContext.request.contextPath}/users"><spring:message
                                    code="page.layout.btn.users"/> </a></li>
                        </ul>
                    </li>
                </sec:authorize>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                            code="page.layout.projects"/><b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/projects/your"><spring:message
                                code="page.layout.btn.own"/></a></li>

                        <li><a href="${pageContext.request.contextPath}/projects/other""><spring:message
                                code="page.layout.btn.other"/> </a></li>


                    </ul>
                </li>

                <li><a href="#">O PM</a></li>
                <li><a href="${pageContext.request.contextPath}/invitations/all/"><spring:message
                        code="page.layout.invitation"/></a></li>

                <c:if test="${pageContext['request'].userPrincipal != null}">
                    <li><a href="#"><sec:authentication property="principal.username"/></a></li>
                    <li><a href="<c:url value="/logout"/>"><spring:message code="page.layout.btn.signout"/></a></li>
                </c:if>
                <c:if test="${pageContext['request'].userPrincipal == null}">
                    <tiles:insertAttribute name="registerModal"/>
                    <li><a href="#" data-toggle="modal" data-target="#register"><spring:message
                            code="page.register.btn.register"/></a></li>

                    <%--<tiles:insertAttribute name="loginModal"/>--%>
                    <%--<li><a href="#" data-toggle="modal" data-target="#login"><spring:message--%>
                    <%--code="page.layout.btn.sigin"/></a></li>--%>
                    <li><a href="${pageContext.request.contextPath}/login"><spring:message
                            code="page.layout.btn.sigin"/> </a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>