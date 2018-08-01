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
 
      <h3>Buy Product</h3>
 
      <p style="color: red;">${errorString}</p>
 		<p>Your Order, click ckeck out to confirm order</p>
      <c:if test="${not empty product}">
         <form method="POST" action="${pageContext.request.contextPath}/deleteProduct">
            <input type="hidden" name="code" value="${product.code}" />
            <table border="1">
               <tr>
                  <td>Code</td>
                  <td>${product.code}</td>
               </tr>
               <tr>
                  <td>Name</td>
                  <td>${product.name}</td>
               </tr>
               <tr>
                  <td>Price</td>
                  <td>${product.price}</td>
               </tr>
               <tr>
                  <td colspan = "2">
                     <input type="submit" value="Check Out" />
                      
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>