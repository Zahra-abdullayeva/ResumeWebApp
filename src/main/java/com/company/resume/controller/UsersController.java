    package com.company.resume.controller;

    import dao.inter.UserDaoInter;
    import entity.User;
    import main.Context;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    @WebServlet(name = "UsersController", urlPatterns = {"/users"})
    public class UsersController extends HttpServlet {

        private UserDaoInter userDao=Context.instanceUserDao();

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("users.jsp").forward(req,resp); }}
