package com.ssg.webmvc_member.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@WebFilter("/*")
public class UTF8Filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
