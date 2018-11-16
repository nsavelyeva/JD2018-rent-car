<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

        Details of Vehicle # ${requestScope.vehicle.id}:

<table>
<tr><th width="300" align="right">ID</th><td>${requestScope.vehicle.id}</td></tr>
<tr><th width="300" align="right">Description</th><td>${requestScope.vehicle.description}</td></tr>
<tr><th width="300" align="right">Manufacturer</th><td>${requestScope.vehicle.model.getManufacturer()}</td></tr>
<tr><th width="300" align="right">Model</th><td>${requestScope.vehicle.model.getModel()}</td></tr>
<tr><th width="300" align="right">Transmission</th><td>${requestScope.vehicle.transmission.toString()}</td></tr>
<tr><th width="300" align="right">Color</th><td>${requestScope.vehicle.color.getColor()}</td></tr>
<tr><th width="300" align="right">Produced Year</th><td>${requestScope.vehicle.producedYear}</td></tr>
<tr><th width="300" align="right">Full Price</th><td>${requestScope.vehicle.fullPrice}</td></tr>
<tr><th width="300" align="right">Day Price</th><td>${requestScope.vehicle.dayPrice}</td></tr>
<tr><th width="300" align="right">Comment</th><td>${requestScope.vehicle.comment}</td></tr>
<tr><th width="300" align="right">Passengers Capacity</th><td>${requestScope.car.passengersCapacity}</td></tr>
<tr><th width="300" align="right">Trunk Capacity</th><td>${requestScope.car.trunkCapacity}</td></tr>
<tr><th width="300" align="right">Carrying Capacity</th><td>${requestScope.lorry.carryingCapacity}</td></tr>

<tr><th width="300" align="right"><br><i>Entry Audit</i></th><td></td></tr>
<tr><th width="300" align="right">Created Date</th><td>${requestScope.vehicle.audit.getCreatedDate()}</td></tr>
<tr><th width="300" align="right">Updated Date</th><td>${requestScope.vehicle.audit.getUpdatedDate()}</td></tr>
</table>
<br>

<%@ include file="include/end-html.jsp" %>