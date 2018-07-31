package team.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Checks if there's a logged user set in session
 */
public class SessionCheckFilter implements Filter {

    private String contextPath;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        contextPath = fc.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("LOGIN_USER") == null) {
            res.sendRedirect(contextPath + "/login.jsp");
        } else {
            String userType = (String) req.getSession().getAttribute("LOGIN_USER");
            if (!userType.equals("MANAGER")){
                res.sendRedirect(contextPath + "/login.jsp");
            }
            fc.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
