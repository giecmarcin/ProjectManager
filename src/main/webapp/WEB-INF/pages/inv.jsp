<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:if test="${param.success eq true}">--%>
<%--<div class="alerts">--%>
<%--<div class="alert alert-success">Rejestracja zakończona pomyślnie! Możesz się teraz zalogować.</div>--%>
<%--</div>--%>
<%--</c:if>--%>

<div class="modal fade" id="invitation">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"><spring:message code="page.register.header"/></h3>
            </div>
            <div class="modal-body">
                <form:form commandName="user" action="/register" class="form-horizontal">
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label"><spring:message
                                code="page.register.email"/></label>

                        <div class="col-sm-10">
                            <form:input path="email" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label"><spring:message
                                code="page.register.password"/></label>

                        <div class="col-sm-10">
                            <form:password path="password" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><spring:message
                                code="page.register.name"/></label>

                        <div class="col-sm-10">
                            <form:input path="name" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><spring:message
                                code="page.register.lastname"/></label>

                        <div class="col-sm-10">
                            <form:input path="lastname" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" value="<spring:message code="page.register.btn.register"/>"
                                       class="btn btn-lg btn-primary pull-right"/>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
