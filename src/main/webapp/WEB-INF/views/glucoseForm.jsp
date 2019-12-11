<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


		<h1>Add New Measurement</h1>
       <form:form method="post" action="save">
      	<table >
            <form:hidden path="id" />
         <tr>  
          <td>Date & Time : </td>
          <td><form:input path="date"  placeholder="Date" type="datetime-local"/></td>
         </tr>
            <tr>
          <td>Glucose Level mg/l :</td>
          <td><form:input path="glucose" autocomplete="false"/></td>
         </tr>

            <tr>
                <td> Choose state: </td>
                <td>

                <form:select path="mesurmentStates">
                    <form:option value="0" label="Select" />
                    <form:option value="AFTER_MEAL" label=" After meal" />
                    <form:option value="AFTER_SPORT" label=" After sport" />
                    <form:option value="AT_NIGHT" label=" At night" />
                    <form:option value="BEFORE_MEAL" label=" Before meal" />
                    <form:option value="BEFORE_SLEEP" label=" Before sleep" />
                    <form:option value="BEFORE_SPORT" label=" Before sport" />
                    <form:option value="FASTING" label=" Fasting" />

                </form:select>

                </td>


            </tr>

         
         <tr>  
          <td> </td>  
          <td><input type="submit" value="Save" /></td>  
         </tr>  
        </table>  
       </form:form>
<br/>
<td>
    <form:form method="post" action="refresh">
        <input type="submit" class="button" name="refresh" value="Refresh"/>
    </form:form>
    <br/>
    <form:form method="post" action="homePage">
        <input type="submit" class="button" name="homePage" value="Home Page"/>
    </form:form>
    <br/>
</td>
