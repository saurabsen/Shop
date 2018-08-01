<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Order Confirmed</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Order Confirmed</h3>
      <button> <a href="${pageContext.request.contextPath}/productList">Order More</a></button>
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>