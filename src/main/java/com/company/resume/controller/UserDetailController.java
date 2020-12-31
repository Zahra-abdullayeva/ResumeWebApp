
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

@WebServlet(name = "UserDetailController", urlPatterns = {"/userDetail"})//bu jsp-ye qayidan cavabi control edir
public class UserDetailController extends HttpServlet {

    private UserDaoInter userDao=Context.instanceUserDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int id= Integer.valueOf(request.getParameter("id"));//null qaytarmamamq ucun, hemde balaca int-e  null menimsede bilmirsen 
        String action=request.getParameter("action");
        if (action.equals("update")){
        String name= request.getParameter("name");//bu ekrandaki browserdeki addir menimsedirik oz deyishenime ki update ede bilek
         String surname= request.getParameter("surname");
         System.out.println("name= "+name);
         System.out.println("surname= "+surname);
         
         User user= userDao.getById(id);//bu update ucundur
            user.setName(name);
            user.setSurname(surname);
            userDao.updateUser(user);}
        else if (action.equals("delete")){
            userDao.removeUser(id);
        }
            response.sendRedirect("users");//tezeden seni yoneldir jspye ki yenilenmish adi gostersin ekranda
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         try {
             String userIDStr = req.getParameter("id");//clientin mene id-nin gonderib gondermediyini teyin etdim
             if (userIDStr == null || userIDStr.trim().isEmpty()) {
                 throw new IllegalArgumentException("id is not specified");
             }
             Integer userId = Integer.parseInt(userIDStr);// clientin daxil etdiyi id oturur bura
             UserDaoInter userDao = Context.instanceUserDao();
             User u = userDao.getById(userId);//o id-ye esasen user gelir. ordan user haqqinda qayidan melumatmlar u-ya menimsenilir
             if (u == null) {//baxiriq ki bele bir user varmi?olmasa catch-a dushur, o da bizi error.jsp-e gonderir.
                 throw new IllegalArgumentException(" There is no user with this id");
             }
             req.setAttribute("user",u);//eger ele bir user varsa gelir bura, clientin search barda id=1 yazib get etdiyi sorgunun icine bir
             //obyekt qoyub adini deyirik "user" olsun, tapdigimiz u adami menimsetdik "user"e ele bil
             req.getRequestDispatcher("userDetail.jsp").forward(req,resp);
             //burda bu userDetail.jsp file-a kecid elde edirem ve deyirem ki mene gonderilen req ve responsu forward ele bu userDetail.jsp-ye
             //userDetail url-ne gonderilen req ve resp-i forward ele userDetail.jsp-ye, o da senin requestin nsayesinde bir response generate edecek
             //ve userDetail.jsp-in yox, sadece userDetail-in responsu imish kimi gosterecey.
         }      catch (Exception ex){
                    ex.printStackTrace();
                    resp.sendRedirect("error?msg="+ex.getMessage());
            }


    }

}
