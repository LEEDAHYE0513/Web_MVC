package com.ssg.webmvc_member.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
@Log4j2
public class LogoutController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log.info("log out ...........");
        HttpSession session = request.getSession();

        session.removeAttribute("logInfo");
        session.invalidate();
        response.sendRedirect("/login");
    }
}
