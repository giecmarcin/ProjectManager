<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="yourProjects">
    <div class="col-md-10 col-xs-offset-1">
        <c:if test="${projects!=null}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b align="left">
                        Zaproszenia
                    </b>
                    </p>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nazwa projektu</th>
                            <th>Właściciel</th>
                            <th>Data</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="p" items="${projects}" varStatus="status">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.emailOfCreator}</td>
                                <td><c:out value="${invitations[status.index].date}"/></td>
                                <td>
                                    <p>
                                        <a id="${p.id}"
                                           href=" <spring:url value="/invitations/all/${p.id}/${invitations[status.index].id}" /> "
                                           class="btn btn-primary"><spring:message code="page.allInvitation.confirm"/>
                                        </a>

                                            <%--<a--%>
                                            <%--href=" <spring:url value="/task/add?idProject=${project.id}" /> "--%>
                                            <%--class="btn btn-primary">Dodaj zadanie--%>
                                            <%--</a>--%>
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                    <%--<div class="panel-footer">--%>
                    <%--<a href="#" class="btn btn-danger btn-sm">Zgoda</a><a href="#" class="btn btn-default btn-sm">Odmowa</a>--%>
                    <%--</div>--%>
            </div>
        </c:if>
    </div>
</div>