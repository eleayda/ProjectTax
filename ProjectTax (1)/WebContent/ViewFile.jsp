<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultat</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>


<c:if test="${nettoIncCompany gt nettoIncWorker}">
<h2>

Du tjänar mer som konsult!</h2>
<h3>Din netto* inkomst som konsult per År:${nettoIncCompany}kr<br/> 
Din netto* inkomst som anställd per År: ${nettoIncWorker}kr<br/>
</h3>
<em>*efter skatt</em>
<jsp:include page="details.jsp">
<jsp:param value="details" name="details"/>
</jsp:include>
</c:if>
<c:if test="${nettoIncCompany lt nettoIncWorker}">

<h2>Du tjänar mer som anställd!</h2>
<h3>Din netto* inkomst som konsult per År: ${nettoIncCompany}kr <br/>
Din netto* inkomst som anställd per År:${nettoIncWorker}kr<br/>
</h3>
<em>*efter skatt</em>
<jsp:include page="details.jsp">
<jsp:param value="details" name="details"/>
</jsp:include>
</c:if>
<c:if test="${nettoIncCompany eq nettoIncWorker}">

<h2>Du tjänar ungefär lika mycket.</h2>
<h3>
Din netto* inkomst som konsult per År:${nettoIncCompany}kr <br/>
Din netto* inkomst som anställd per År: ${nettoIncWorker}kr<br/>
</h3>
<em>*efter skatt</em>


<jsp:include page="details.jsp">
<jsp:param value="details" name="details"/>
</jsp:include>
</c:if>
<br/>
<br/>
<a href = "calculator.html"><button type="button" id="menu"><h2>Till Inkomst Räknaren</h2></button></a>
<br/>
<br/>

<a href="index.html"> Tillbaka till startsidan  </a>
<br/>
<br/>
${developer}
</body>
</html>