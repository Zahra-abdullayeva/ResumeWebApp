package com.company.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//user sehifesine direkt girende session yoxdusa, login olmamisansa, exception atmasin sadece sehife acilmasin deye bunu yaziriq
@WebFilter(filterName = "JSPFileFilter",urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) {
      try{
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
      //login sehifeye muraciet etmek istesem icaze verecek, eger digerlerine girmey isteyende
        // yoxluyur log in olmamisamsa asagidakini icra edir.

        if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser")==null){
            resp.sendRedirect("login");
        }else {
            chain.doFilter(request,response);
        }
        }catch (Exception ex){
          ex.printStackTrace();
      }
}

    @Override
    public void destroy() {

    }
}
