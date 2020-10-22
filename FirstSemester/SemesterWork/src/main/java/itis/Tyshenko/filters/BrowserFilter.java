package itis.Tyshenko.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "BrowserFilter", value = "/*")
public class BrowserFilter implements Filter {

    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("User-Agent").toLowerCase();
        if (header.contains("edge")) {
            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.println("Are you genius?");
            printWriter.flush();
            printWriter.close();
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        config = null;
    }
}
