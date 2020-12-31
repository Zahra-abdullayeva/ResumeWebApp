<%-- 
    Document   : user
    Created on : Nov 18, 2020, 4:16:12 PM
    Author     : Zahra
--%>

<%@page import="main.Context"%>
<%@page import="dao.inter.UserDaoInter"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User u= (User) request.getAttribute("user");
         %>
        <div> 
             <form action="userDetail" method="POST">
                 <input type="hidden" name="action" value="update">
                 <input type="hidden" name="id" value="<%=u.getId() %>">
            <label for="name">name: </label>
            <input type="text" name="name" value="<%=u.getName() %>">
            <br>
            <label for="surname">surname: </label>
            <input type="text" name="surname" value="<%=u.getSurname() %>">
            <input type="submit" name="save" value="Save">
        
        </form>
            </div>
    <%
                 //1ci ifde deyeirki id-ni teyin et, id yoxdusa specify deyir. eger id varsa else-ye daxil olur. ve hemin id-li adami axtarir.hemin id-li adam yoxdursa deyiki yoxdur.eger bele adam varsa o biri ellse cap olunur
                //yeniki ya parameter gondermirem yada gonderirem ama ele bir user olmur.
    %>
    </body>
</html>
