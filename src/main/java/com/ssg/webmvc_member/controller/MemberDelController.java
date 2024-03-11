package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Member;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "memberDelController", urlPatterns = "/member/delMember.do")
@Log4j2
public class MemberDelController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd");
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("member/del/ Post ...");
        String id = request.getParameter("id");
        try {
            memberService.remove(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/member/listMembers.do");
    }
}
