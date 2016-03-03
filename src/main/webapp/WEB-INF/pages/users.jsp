<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="users">
    <div class="col-md-10 col-xs-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <p>
                    <b align="left">
                        <tiles:insertAttribute name="registerModal"/>
                        <a href="#" data-toggle="modal" data-target="#register"><spring:message
                                code="page.users.new"/></a>
                    </b>
                </p>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th><spring:message code="page.register.name"/></th>
                        <th><spring:message code="page.register.lastname"/></th>
                        <th><spring:message code="page.register.email"/></th>
                        <th><spring:message code="page.layout.roles"/></th>
                        <th><spring:message code="page.users.management"/></th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.lastname}</td>
                            <td>${user.email}</td>
                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    ${role.name}
                                </c:forEach>
                            </td>
                            <td>
                                <a href="<spring:url value="users/edit/?idUser=${user.id}"/>">Edytuj</a>
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
    </div>
</div>
