<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${param.success eq true}">
    <div class="alerts">
        <div class="alert alert-success">Status zmieniono pomyślnie .</div>
    </div>
</c:if>

<div class="register-panel">
    <div class="col-md-10 col-xs-offset-1">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="page.addTask.header"/></h3>
            </div>
            <div class="panel-body">

                <form:form modelAttribute="statusOfTask" class="form-horizontal">
                <div class="form-group">
                    <label for="status" class="col-sm-2 control-label">Status: </label>

                    <div class="col-sm-10">
                        <form:select path="status" class="form-control" id="sell">
                            <form:option value="" label="...."/>
                            <form:options items="${statuses}"/>
                        </form:select>
                        <form:errors path="status" cssClass="text-danger"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <div class="col-sm-12" id="btnSaveTask">
                        <input type="submit" value="<spring:message code="page.save"/>"
                               class="btn btn-md btn-primary pull-right"/>
                    </div>
                </div>
            </div>
        </div>
        </form:form>
    </div>
</div>

