<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="yourProjects">
    <div class="col-md-10 col-xs-offset-1">
        <br/>

        <div class="panel panel-default">
            <div class="panel-heading">
                <b align="left">
                    <a href=" <spring:url value="/projects/add" /> "
                            /><b></b><spring:message code="page.yourProject.new"/></b>
                </a>
                </p>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th><spring:message code="page.yourProjects.name"/></th>
                        <%--<th><spring:message code="page.yourProjects.description"/></th>--%>
                        <th><spring:message code="page.yourProjects.dateStart"/></th>
                        <th><spring:message code="page.yourProject.dateEnd"/></th>
                        <th><spring:message code="page.yourProject.details"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${yourProjects!=null}">
                        <c:forEach items="${yourProjects}" var="project">
                            <tr>
                                <td>${project.id}</td>
                                <td>${project.name}</td>
                                    <%--<td>${project.description}</td>--%>
                                <td>${project.dateStart}</td>
                                <td>${project.dateEnd}</td>
                                <td>

                                    <div class="btn-group" role="group" aria-label="...">
                                        <a
                                                href=" <spring:url value="/projects/add/?id=${project.id}" /> "
                                                class="btn btn-primary btn-md">Edytuj
                                        </a>

                                        <a
                                                href=" <spring:url value="/projects/project?id=${project.id}" /> "
                                                class="btn btn-primary btn-md"><spring:message
                                                code="page.yourProject.details"/>
                                        </a>


                                        <a
                                                href=" <spring:url value="/task/add?idProject=${project.id}" /> "
                                                class="btn btn-primary btn-md">Dodaj zadanie
                                        </a>

                                            <%--<tiles:insertAttribute name="invModal"/>--%>
                                            <%--<a href="#" data-toggle="modal" data-target="#invitation" class="btn btn-primary">--%>
                                            <%--Zaproszenie--%>
                                            <%--</a>--%>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>

            </div>
            <%--<div class="panel-footer">--%>
            <%--<a href="#" class="btn btn-danger btn-sm">Zgoda</a><a href="#" class="btn btn-default btn-sm">Odmowa</a>--%>
            <%--</div>--%>
        </div>
    </div>
</div>