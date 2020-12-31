
    <%@page import="main.Context"%>
    <%@page import="dao.inter.UserDaoInter"%>
    <%@page import="entity.User"%>
    <%@ page import="java.util.List" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/users.css">
        <script src="https://kit.fontawesome.com/134db80b0b.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="js/users.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>
    <%
                    UserDaoInter userDao= Context.instanceUserDao();
                    String name= request.getParameter("name");
                 String surname= request.getParameter("surname");
                 String nationalityStr=request.getParameter("nid");
                 Integer nationalityId=null;
                 if (nationalityStr!=null && !nationalityStr.trim().isEmpty()){
                     nationalityId=Integer.parseInt(nationalityStr);
                 }
                     List<User> list= userDao.getAll(name, surname,nationalityId);

                %>
    <div class="container">
        <div class="log_out" style="margin-left: 1000px">
            <form action="logout">
                <input type="submit"  name="logout" value="LOGOUT" style="background-color: chartreuse; color: white">
            </form>
        </div>
        <div class="row">
            <div class="col-4">
                <form action="users.jsp" method="GET">
                    <div class="form-group">
                    <label for="name">name: </label>
                    <input placeholder="enter your name" class="form-control" type="text" name="name" value=""/>
                    </div>
                    <div class="form-group">
                    <label for="surname">surname: </label>
                    <input placeholder="enter your surname" class="form-control" type="text" name="surname" value=""/>
                    </div>
                    <input visible="true" class="btn btn-primary" type="submit" name="search" value="Search" id="btnSearch"/>

                </form>
            </div>
            <br>
        </div>
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th>name</th>
                        <th>surname</th>
                        <th>nationality</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                   <% for(User u: list){%>
                    <tr>
                        <td><%=u.getName()%></td>
                        <td><%=u.getSurname()%></td>
                        <td><%=u.getNationality().getName()==null?"N/A":u.getNationality().getName()%></td>
                        <td style="width: 5px">
                            <form action="userDetail" method="GET">
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input type="hidden" name="action" value="update"/>
                            <button class="btn btn-secondary" type="submit" value="Update">
                                <i class="fas fa-edit"></i>
                            </button>
                            </form>
                        </td>
                        <td style="width: 5px">
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button class="btn btn-danger" type="submit" value="Delete"
                                data-toggle="modal" data-target="#exampleModal"
                                onclick="setIdForDelete(<%=u.getId()%>)">
                                    <i class="far fa-trash-alt"></i>
                                </button>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

                </div>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Are you sure?
                            </div>
                            <div class="modal-footer">
                                <form action="userDetail" method="post">
                                    <input type="hidden" name="id" value="" id="idForDelete">
                                    <input type="hidden" name="action" value="delete">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" class="btn btn-danger" value="Delete">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </body>
    </html>
