<%-- 
    Document   : gameWon
    Created on : 26.04.2010, 09:35:24
    Author     : christian.kondler
--%>
<jsp:useBean id="memoryBean" class="model.MemoryBean" scope="session"/>
<jsp:useBean id="userBean" class="model.UserBean" scope="session"/>
<jsp:setProperty name="memoryBean" property="*"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Gratulation!</h1>
        <p>
            Sie haben gewonnen.<br />
            Ihre benötigte Zeit beträgt <%=memoryBean.getCurrentTime() %>.
        </p>
        <form action="MemServlet" method="get">
            <input type="button" onclick="javascript:history.back()" name="back" value="Zurück" />
            <input type="submit" name="invalidate" value="Neues Spiel" />
        </form>
        <!--<a href="./table.jsp">Noch mal spielen</a>-->
    </body>
</html>
