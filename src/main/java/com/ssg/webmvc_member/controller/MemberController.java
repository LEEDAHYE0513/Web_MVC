package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.java.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MemberController", urlPatterns = "/member/listMembers.do")
@Log
public class MemberController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberController Get");

        try {
            List<MemberDTO> memberDTOList = memberService.memberList();
            request.setAttribute("dtoList",memberDTOList);
            request.getRequestDispatcher("/WEB-INF/member/listMembers.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException("MemberController error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberController Post");
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
