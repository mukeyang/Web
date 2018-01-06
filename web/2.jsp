<%--
  Created by IntelliJ IDEA.
  User: CS
  Date: 2018/1/6
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<font color="red">
    <%
        String dat = application.getInitParameter("dat");
        System.out.println("chen="+application.getAttribute("chen"));
        System.out.println("dat="+dat);
        out.write(dat);
    %>
</font>

</body>
</html>
