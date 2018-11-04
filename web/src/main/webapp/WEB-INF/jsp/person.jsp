<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

Details of Person # ${requestScope.person.id}:

<table>
    <tr><th width="300" align="right">ID</th><td>${requestScope.person.id}</td></tr>
    <tr><th width="300" align="right">Login</th><td>${requestScope.person.login}</td></tr>
    <tr><th width="300" align="right">Password</th><td>${requestScope.person.password}</td></tr>
    <tr><th width="300" align="right">E-mail</th><td>${requestScope.person.email}</td></tr>

    <tr><th width="300" align="right"><br><i>Person Data</i></th><td></td></tr>
    <tr><th width="300" align="right">First Name</th><td>${requestScope.person.personData.firstName}</td></tr>
    <tr><th width="300" align="right">Last Name</th><td>${requestScope.person.personData.lastName}</td></tr>
    <tr><th width="300" align="right">Middle Name</th><td>${requestScope.person.personData.middleName}</td></tr>
    <tr><th width="300" align="right">Birth Date</th><td>${requestScope.person.personData.birthDate}</td></tr>
    <tr><th width="300" align="right">Gender</th><td>${requestScope.person.personData.gender}</td></tr>
    <tr><th width="300" align="right">Passport</th><td>${requestScope.person.personData.passport}</td></tr>

    <tr><th width="300" align="right"><br><i>Address</i></th><td></td></tr>
    <tr><th width="300" align="right">Country</th><td>${requestScope.person.personData.address.street.city.country.country}</td></tr>
    <tr><th width="300" align="right">City</th><td>${requestScope.person.personData.address.street.city.city}</td></tr>
    <tr><th width="300" align="right">Street</th><td>${requestScope.person.personData.address.street.street}</td></tr>
    <tr><th width="300" align="right">Building</th><td>${requestScope.person.personData.address.building}</td></tr>
    <tr><th width="300" align="right">Flat</th><td>${requestScope.person.personData.address.flat}</td></tr>

    <tr><th width="300" align="right"><br><i>Driving License</i></th><td></td></tr>
    <tr><th width="300" align="right">Serial Number</th><td>${requestScope.person.personData.drivingLicense.serialNumber}</td></tr>
    <tr><th width="300" align="right">Category</th><td>${requestScope.person.personData.drivingLicense.category}</td></tr>
    <tr><th width="300" align="right">Issued Date</th><td>${requestScope.person.personData.drivingLicense.drivingPeriod.getCreatedDate()}</td></tr>
    <tr><th width="300" align="right">Expire Date</th><td>${requestScope.person.personData.drivingLicense.drivingPeriod.getUpdatedDate()}</td></tr>

    <tr><th width="300" align="right"><br><i>Entry Audit</i></th><td></td></tr>
    <tr><th width="300" align="right">Created Date</th><td>${requestScope.person.audit.getCreatedDate()}</td></tr>
    <tr><th width="300" align="right">Updated Date</th><td>${requestScope.person.audit.getUpdatedDate()}</td></tr>
</table>
<br>

<%@ include file="include/end-html.jsp" %>