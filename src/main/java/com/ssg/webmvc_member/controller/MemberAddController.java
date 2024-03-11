package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "memberAddController", urlPatterns = "/member/addMember.do")
@Log4j2
public class MemberAddController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/member/add Get ...");

        request.getRequestDispatcher("/WEB-INF/member/memberForm.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDTO memberDTO = MemberDTO.builder()
                .id(request.getParameter("id"))
                .pw(request.getParameter("pw"))
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .build();
        System.out.println(memberDTO);
        try{
            memberService.add(memberDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/member/listMembers.do");
    }
}
