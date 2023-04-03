<%@ page import="com.mysql.cj.protocol.Resultset" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mihirsanjaysawant
  Date: 01/04/23
  Time: 10:41 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JDBC-SERVLET</title>
</head>
<body>
<% ResultSet resultSet = (ResultSet) request.getAttribute("users");%>
<%

   while(resultSet.next()){
       out.println("<br/>" + resultSet.getInt(1) + " " +resultSet.getString(2)+ " " + resultSet.getString(3));
    }%>
</body>
</html>
