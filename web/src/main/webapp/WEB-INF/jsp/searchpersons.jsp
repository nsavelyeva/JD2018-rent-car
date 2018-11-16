<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<h2>Person search form</h2>

<form>
    E-mail: <input type="text" name="email" value="">
    <br>
    Gender: <select name="gender">
        <option value="">Any</option>
        <option value="MALE">MALE</option>
        <option value="FEMALE">FEMALE</option>
        <option value="UNSURE_YET">UNSURE_YET</option>
        <option value="TRANSGENDERED_TO_MALE">TRANSGENDERED_TO_MALE</option>
        <option value="TRANSGENDERED_TO_FEMALE">TRANSGENDERED_TO_FEMALE</option>
    </select>
    <br>
    <input type="checkbox" name="foreigners"> Foreigners only
    <br>
    Limit: <input type="text" name="limit" value="5">
    <br>
    Page: <input type="text" name="page" value="1">
    <br>
    <input type="submit">
</form>

<%@ include file="include/end-html.jsp" %>
