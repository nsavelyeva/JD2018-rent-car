<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<h2>Vehicle search form</h2>

<form>
    Cost per day up to: <input type="text" name="price" value="">
    <br>
    Transmission: <select name="transmission">
        <option value="">Any</option>
        <option value="AUTOMATIC">AUTOMATIC</option>
        <option value="MECHANIC">MECHANIC</option>
    </select>
    <br>
    <input type="checkbox" name="available"> Available today
    <br>
    Limit: <input type="text" name="limit" value="5">
    <br>
    Page: <input type="text" name="page" value="1">
    <br>
    <input type="submit">
</form>

<%@ include file="include/end-html.jsp" %>
