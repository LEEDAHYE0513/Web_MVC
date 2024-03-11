package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("login get...");
        request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request,response);
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.info("login post called ...");

            String id = request.getParameter("id"); request.getParameter("id");   //사용자가 제출한 폼 데이터에서 사용자의 아이디를 가져오는 것
            String pw = request.getParameter("pw");
            try{

                MemberDTO memberDTO = MemberService.INSTANCE.login(id, pw);
                HttpSession session = request.getSession();
                session.setAttribute("loginInfo",memberDTO);
                response.sendRedirect("/member/listMembers.do");
            }catch (Exception e){
                response.sendRedirect("/login?result=error");
            }
        }
}
