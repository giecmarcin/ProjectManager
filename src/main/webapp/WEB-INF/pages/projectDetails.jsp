<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
</sec:authorize>
<div class="projectDetails">

    <div class="col-md-10 col-xs-offset-1"><!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#description" aria-controls="description" role="tab"
                                                      data-toggle="tab"><spring:message
                    code="page.projectDetails.description"/> </a>
            </li>
            <li role="presentation"><a href="#tasks" aria-controls="tasks" role="tab" data-toggle="tab"><spring:message
                    code="page.projectDetails.tasks"/> </a>
            </li>
            <li role="presentation"><a href="#users" aria-controls="users" role="tab"
                                       data-toggle="tab"><spring:message code="page.projectDetails.users"/> </a></li>
            <%--<li role="presentation"><a href="#settings" aria-controls="settings" role="tab"--%>
            <%--data-toggle="tab">Settings</a></li>--%>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="description">
                <br/>

                <div class="panel panel-default">
                    <div class="panel-heading">

                        <%--<div id="projectName" class="pull-left"--%>
                        <%--style="color: dodgerblue; font-size: 16px; font-weight: 600; margin-top: 0px; margin-right: 10px;">--%>
                        ${projectDetails.name}
                        <%--</div>--%>
                        <div id="date" class="pull-right"
                             style="color: dodgerblue; font-size: 16px; font-weight: 600; margin-top: 0px; margin-right: 10px;">${projectDetails.dateStart}
                            - ${projectDetails.dateEnd}</div>
                    </div>
                    <div class="panel-body">
                        ${projectDetails.description}
                    </div>
                    <%--<div class="panel-footer">--%>
                    <%--<a href="#" class="btn btn-danger btn-sm">Zgoda</a><a href="#" class="btn btn-default btn-sm">Odmowa</a>--%>
                    <%--</div>--%>
                </div>


            </div>
            <div role="tabpanel" class="tab-pane" id="tasks">
                <br/>
                <c:if test="${username == projectDetails.emailOfCreator}">
                    <a
                            href=" <spring:url value="/task/add?idProject=${projectDetails.id}" /> "
                    >Dodaj zadanie
                    </a>
                </c:if>
                <br/>
                <c:forEach items="${tasksInProject}" var="task">
                    <br/>

                    <div class="alert alert-info" role="alert" data-toggle="collapse" href="#${task.id}"
                         aria-expanded="false" aria-controls="collapseExample">

                        <a role="button" data-toggle="collapse" href="#${task.id}"
                           aria-expanded="false" aria-controls="collapseExample">
                            <c:choose>
                                <c:when test="${task.status  == 'Finished'}">
                                    <s> ${task.name}: ${task.status}</s>
                                </c:when>

                                <c:when test="${task.status == 'Zakończone'}">
                                    <s> ${task.name}: ${task.status}</s>
                                </c:when>

                                <c:otherwise>
                                    ${task.name}: ${task.status}
                                </c:otherwise>
                            </c:choose>
                        </a>
                        </a>
                    </div>
                    <div class="collapse" id="${task.id}">

                        <div class="panel panel-default">
                            <div class="panel-heading">

                                <b>${task.dateStart} ${task.dateEnd} -
                                    <a
                                            href=" <spring:url value="/projects/other/project/status?idProject=${projectDetails.id}&idTask=${task.id}" /> "
                                    >Zmień status
                                    </a>
                                </b>

                                <c:if test="${username == projectDetails.emailOfCreator}">
                                    <p align="right"><a
                                            href=" <spring:url value="/task/add?idProject=${projectDetails.id}&idTask=${idTask=task.id}" /> "
                                    >Edytuj
                                    </a>
                                    </p>
                                </c:if>
                            </div>

                            <div class="panel-body">
                                <h4><span class="label label-primary"><spring:message
                                        code="page.projectDetails.description"/>
                                </span></h4>
                                    ${task.description}
                            </div>

                            <div class="panel-footer">
                                <!-- Table -->
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Imie</th>
                                        <th>Nazwisko</th>
                                        <th>Email</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${task.listOfUsers}" var="user">
                                        <tr>
                                            <td>${user.name}</td>
                                            <td>${user.lastname}</td>
                                            <td>
                                                <a href="mailto:${user.email}">${user.email}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </c:forEach>

            </div>
            <div role="tabpanel" class="tab-pane" id="users">
                <br/>

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Użytkownicy
                        <c:if test="${username == projectDetails.emailOfCreator}">
                            -
                            <a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-primary">
                                Zaproszenie
                            </a>
                        </c:if>

                    </div>
                    <!-- Default panel contents -->
                    <div class="panel-body">
                        <p>
                            <!-- Table -->
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Imie</th>
                                <th>Nazwisko</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${projectDetails.users}" var="user">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.lastname}</td>
                                    <td>${user.email}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </p>
                    </div>
                </div>
            </div>
            <%--<div role="tabpanel" class="tab-pane" id="settings">4</div>--%>
        </div>

    </div>
</div>


</div>

<form:form modelAttribute="newInvitation" cssClass="form-horizontal blogForm">
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Zaproszenie</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="emailOfUser" class="col-sm-2 control-label">Email:</label>

                        <div class="col-sm-10">
                            <form:input path="emailOfUser" cssClass="form-control"/>
                            <form:errors path="emailOfUser"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="page.close"/></button>
                    <input type="submit" class="btn btn-primary" value="<spring:message code="page.save"/>"/>
                </div>
            </div>
        </div>
    </div>
</form:form>

<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
<script>
    $('form').validate({
        rules: {
            emailOfUser: {
                required: true,
                email: true,
                minlength: 3,
                maxlength: 100,
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
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