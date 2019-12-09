<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>View Glucose Measurement List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Date & Time </th>
        <th>Level</th>
        <th>State</th>
        <th>Actions</th>

    </tr>
    <c:forEach var="glucose" items="${glucoseList}">
        <tr>
            <td>${glucose.id}</td>
            <td>${glucose.date}</td>
            <td>${glucose.glucose}</td>
            <td>${glucose.mesurmentStates}</td>

            <td>
                <form:form method="post" action="delete">
                    <input type="hidden" id="id" name="id" value="${glucose.id}"/>
                    <input type="submit" class="button" name="Delete" value="delete"/>
                </form:form>
                <form:form method="post" action="edit">
                    <input type="hidden" id="id" name="id" value="${glucose.id}"/>
                    <input type="submit" class="button" name="Edit" value="edit"/>
                </form:form>

            </td>
        </tr>
    </c:forEach>


</table>
<br/>
    <td>
        <form:form method="post" action="refresh">
            <input type="submit" class="button" name="test" value="Refresh"/>
        </form:form>
        <br/>
        <form:form method="post" action="homePage">
            <input type="submit" class="button" name="start" value="Home Page"/>
        </form:form>
        <br/>
    </td>
