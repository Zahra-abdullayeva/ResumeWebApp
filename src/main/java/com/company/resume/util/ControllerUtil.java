package com.company.resume.util;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {
    public static void errorPage(HttpServletResponse response, Exception ex) {
    try {   ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
