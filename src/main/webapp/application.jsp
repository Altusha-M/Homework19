<%@ page contentType="text/html;charset=UTF-16" language="java" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<html>
<body>
<h2>MyApp</h2>
<%
    ArrayList<String> sArray = (ArrayList<String>) request.getAttribute("answer");

    Iterator<String> it = sArray.iterator();
    while (it.hasNext()) {
        out.println(it.next() + "<br />");
    }

    System.out.println("hello");
%>
</body>
</html>