<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="test">

    <br/>
    <p align="center">
        <br/> <b><span class="label label-danger">Odmowa dostępu!!</span></b>
        <br/><img src="${pageContext.servletContext.contextPath}/resources/access-denied.png" alt="some_text">
    </p>
</div>

