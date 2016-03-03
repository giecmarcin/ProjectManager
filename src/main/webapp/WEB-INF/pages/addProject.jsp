<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% response.sendRedirect("addProject.jsp");%>
<c:if test="${param.success eq true}">
    <div class="alerts">
        <div class="alert alert-success">Projekt dodany pomyślnie! .</div>
    </div>
</c:if>

<div class="register-panel">
    <div class="col-md-10 col-xs-offset-1">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="page.addProject.header"/></h3>
            </div>
            <div class="panel-body">

                <form:form modelAttribute="newProject" class="form-horizontal">
                    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Nazwa</label>

                        <div class="col-sm-10">
                            <form:input path="name" cssClass="form-control"/>
                            <form:errors path="name" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">Opis</label>

                        <div class="col-sm-10">
                            <form:textarea path="description" cssClass="form-control"/>
                            <form:errors path="description" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateStart" class="col-sm-2 control-label">Data: </label>

                        <div class="col-sm-10">
                            <form:input type="date" path="dateStart" cssClass="form-control"/>
                            <form:errors path="dateStart" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateEnd" class="col-sm-2 control-label">Data: </label>

                        <div class="col-sm-10">
                            <form:input type="date" path="dateEnd" cssClass="form-control"/>
                            <form:errors path="dateEnd" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">Status: </label>

                        <div class="col-sm-10">
                            <form:select path="status" class="form-control" id="sell">
                                <%--<form:option value="Rozpoczęty" label="Rozpoczęty"/>--%>
                                <%--<form:option value="Wstrzymany" label="Wstrzymany"/>--%>
                                <%--<form:option value="Zakończony" label="Zakończony"/>--%>
                                <form:options items="${statuses}"/>
                            </form:select>
                            <form:errors path="status" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12" id="btnSaveProject">
                            <input type="submit" value="<spring:message code="page.save"/>"
                                   class="btn btn-lg btn-primary pull-right"/>
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
    $('#btnSaveProject').hide();
    $('form').validate({
        rules: {
            name: {
                required: true,
                minlength: 3,
                maxlength: 50
            },
            description: {
                required: true,
                minlength: 3,
                maxlength: 255
            },
            dateStart: {
                require: true,
                date: true
            },
            dateEnd: {
                require: true,
                date: true
            },
            status: {
                require: true
            }
        },
        messages: {
            name: "<spring:message code="min3max50"/>",
            description: "<spring:message code="min3max255"/>",
            dateStart: "<spring:message code="validationNotEmpty"/>",
            dateEnd: "<spring:message code="validationNotEmpty"/>",
            status: "<spring:message code="validationNotEmpty"/>"
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
            $('#btnSaveProject').hide();
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
            $('#btnSaveProject').show();
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