package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "memberModController", urlPatterns = "/member/modMember.do")
@Log4j2
public class MemberModController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/member/mod Get ...");

        // 리스트에서 수정 버튼을 클릭했을 경우 그에 해당하는 회원 id를 가져와서 사용!!!
        try{
            String id = request.getParameter("id");
            MemberDTO memberDTO = memberService.get(id);
            request.setAttribute("dto",memberDTO);
            request.getRequestDispatcher("/WEB-INF/member/modMembers.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException("modity get error");
        }
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
            memberService.modify(memberDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/member/listMembers.do");
    }


}
