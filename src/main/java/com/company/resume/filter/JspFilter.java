package com.company.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JSPFilter",urlPatterns = {"*.jsp"})// bu o demekdiki jsp yazilan hersheyde ishe dush, jspile nese cagiranda browserde meselen.
public class JspFilter implements Filter {
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) {
        HttpServletResponse resp= (HttpServletResponse) response;
        try {
            resp.sendRedirect("error?msg=not found"); }
        catch (IOException e) {
            e.printStackTrace(); }}

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
