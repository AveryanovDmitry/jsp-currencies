<%@page import="java.math.BigDecimal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>
<html lang="en">
<c:set var="from" value="from" scope="request"/>
<c:set var="rangeValue" value="${param.get(from)}" scope="request"/>
<c:set var="listCurrencies" value="${currencies.getExchangeRates(rangeValue)}" scope="request"/>
<head>
    <title>Exchange rates for currency</title>
</head>
<style>
    body {
        font-family: "Courier New", serif;
        font-size: 16pt;
    }
</style>
<body>
<h1>Exchange Rates for ${rangeValue}</h1>
<c:forEach var="currencyWithValue" items="${listCurrencies}">
    <c:if test="${!currencyWithValue.getKey().equals(rangeValue)}">
        <tr>
            <td>${currencyWithValue.getKey()}</td>
            <td>${currencyWithValue.getValue()}</td>
        </tr>
    </c:if>
</c:forEach>
</body>
</html>