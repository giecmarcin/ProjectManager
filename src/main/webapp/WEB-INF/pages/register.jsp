<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:if test="${param.success eq true}">--%>
<%--<div class="alerts">--%>
<%--<div class="alert alert-success">Rejestracja zakończona pomyślnie! Możesz się teraz zalogować.</div>--%>
<%--</div>--%>
<%--</c:if>--%>

<div class="modal fade" id="register">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"><spring:message code="page.register.header"/></h3>
            </div>
            <div class="modal-body" id="target">
                <div id="testval"></div>
                <%--<form:form commandName="user" cssClass="form-horizontal registrationForm">--%>
                <form:form commandName="user" action="/register" cssClass="form-horizontal">
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label"><spring:message
                                code="page.register.email"/></label>

                        <div class="col-sm-10">
                            <form:input path="email" cssClass="form-control"/>
                            <form:errors path="email"/>
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
                            <div class="col-sm-12" id="btnRegister">
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

<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script>
    $('#btnSaveTask').hide();
    $('form').validate({
        rules: {
            email: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            password: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            name: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            lastname: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
        },
        messages: {
            email: "<spring:message code="min3max50"/>",
            password: "<spring:message code="min3max50"/>",
            name: "<spring:message code="min3max50"/>",
            lastname: "<spring:message code="min3max50"/>"
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
            $('#btnRegister').hide();
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
            $('#btnRegister').show();
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
</script>