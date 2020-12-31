    package com.company.resume.controller;

    import at.favre.lib.crypto.bcrypt.BCrypt;
    import com.company.resume.util.ControllerUtil;
    import dao.inter.UserDaoInter;
    import entity.User;
    import main.Context;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    @WebServlet(name = "LoginController", urlPatterns = {"/login"})
    public class LoginController extends HttpServlet {

        private UserDaoInter userDao = Context.instanceUserDao();
        private  BCrypt.Verifyer verifyer= BCrypt.verifyer();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try{String email=req.getParameter("email");
            String password=req.getParameter("password");

            User user=userDao.findByEmail(email);
            if (user==null){//email-e gore axtaracaq eger ele bil user emaili yoxdusa error gelir.
                throw new IllegalArgumentException("User doesn't exist!");
            }
                BCrypt.Result rs=verifyer.verify(password.toCharArray(),user.getPassword().toCharArray());
            if (!rs.verified){//eger bele email varsa userin daxil etdiyi parol ile userin oz parolunu muqayise edir
                //dogrudursa login olursan deyilse error
                throw new IllegalArgumentException("password is incorrect!!!");
            }
            req.getSession().setAttribute("loggedInUser",user);
            resp.sendRedirect("users");}
            catch (Exception ex){
                ControllerUtil.errorPage(resp,ex);
            }
        }
    }