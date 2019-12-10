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

<div id="chartContainer" style="height: 300px; width: 100%;">
</div>
<script type="text/javascript">
    window.onload = function () {

        var dps =   [];

        var chart = new CanvasJS.Chart("chartContainer", {

            theme: "light2",

            title:{
                text: "Glucose measurement chartr"
            },

            data: [  //array of dataSeries
                { //dataSeries - first quarter
                    /*** Change type "column" to "bar", "area", "line" or "pie"***/
                    type: "area",
                    name: "",
                    showInLegend: false,
                    dataPoints: dps
                },

            ],
            /** Set axisY properties here*/
            axisY:{
                prefix: "",
                suffix: "mg/l",
                includeZero: true
            }
        });

        <c:forEach items="${glucoseList}" var="dataPoint">
        date = ("${dataPoint.date.toString()}");
        yValue = parseInt("${dataPoint.glucose}");
        dps.push({
            label: date,
            y : yValue,
        });
        </c:forEach>

        chart.render();
    }
</script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

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
