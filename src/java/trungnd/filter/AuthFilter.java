/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HOME
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;
    private static List<String> guest;
    private static List<String> member;
    private static List<String> admin;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthFilter() {
        guest = new ArrayList<>();
        guest.add("error.jsp");
        guest.add("createAccount.jsp");
        guest.add("loginPage.jsp");
        guest.add("MainController");
        guest.add("SignUpController");
        guest.add("LoginController");
        guest.add("logincss.css");
        guest.add("style_login.css");
        guest.add("style_login.css.map");

        member = new ArrayList<>();
        member.add("error.jsp");
        member.add("createAccount.jsp");
        member.add("loginPage.jsp");
        member.add("MainController");
        member.add("SignUpController");
        member.add("LoginController");
        member.add("logincss.css");
        member.add("style_login.css");
        member.add("style_login.css.map");
        member.add("error.jsp");
        member.add("createAccount.jsp");
        member.add("loginPage.jsp");
        member.add("MainController");
        member.add("SignUpController");
        member.add("LoginController");
        member.add("indexMember.jsp");
        member.add("result.jsp");
        member.add("showDetails.jsp");
        member.add("DeleteCommentController");
        member.add("DeletePostController");
        member.add("DislikeController");
        member.add("LikeController");
        member.add("LogOutController");
        member.add("PostCommentController");
        member.add("PostController");
        member.add("SearchArticleController");
        member.add("ShowDetailsController");
        member.add("color.css");
        member.add("index.css");
        member.add("main.min.css");
        member.add("responsive.css");
        member.add("strip.css");
        member.add("style.css");
        
        
        
        admin = new ArrayList<>();
        admin.add("error.jsp");
        admin.add("createAccount.jsp");
        admin.add("loginPage.jsp");
        admin.add("MainController");
        admin.add("SignUpController");
        admin.add("LoginController");
        admin.add("logincss.css");
        admin.add("style_login.css");
        admin.add("style_login.css.map");
        admin.add("error.jsp");
        admin.add("createAccount.jsp");
        admin.add("loginPage.jsp");
        admin.add("MainController");
        admin.add("SignUpController");
        admin.add("LoginController");
        admin.add("indexAdmin.jsp");
        admin.add("result.jsp");
        admin.add("showDetails.jsp");
        admin.add("DeleteCommentController");
        admin.add("DeletePostController");
        admin.add("LogOutController");
        admin.add("SearchArticleController");
        admin.add("ShowDetailsController");
        admin.add("color.css");
        admin.add("index.css");
        admin.add("main.min.css");
        admin.add("responsive.css");
        admin.add("strip.css");
        admin.add("style.css");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String resource = uri.substring(index + 1).trim();
        System.out.println("Resource: " + resource);
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("ROLE") == null) {
            if (guest.contains(resource) || resource.endsWith("js") || resource.endsWith("css") || resource.endsWith("woff2") || resource.endsWith("woff") || resource.endsWith("ttf") || resource.endsWith("jpg") || resource.endsWith("png") || resource.endsWith("jfif")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("loginPage.jsp");
            }
        } else {
            String role = (String) session.getAttribute("ROLE");
            if (role != null) {
                if (role.equals("member")) {
                    if (member.contains(resource) || resource.endsWith("js") || resource.endsWith("css") || resource.endsWith("woff2") || resource.endsWith("woff") || resource.endsWith("ttf") || resource.endsWith("jpg") || resource.endsWith("png") || resource.endsWith("jpeg") || resource.endsWith("jfif") || resource.endsWith("map") || resource.endsWith("svg") || resource.endsWith("eot")  || resource.endsWith("scss")) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("SearchArticleController");
                    }
                } else if (role.equals("admin") || resource.endsWith("js") || resource.endsWith("css") || resource.endsWith("woff2") || resource.endsWith("woff") || resource.endsWith("ttf") || resource.endsWith("jpg") || resource.endsWith("png") || resource.endsWith("jpeg") || resource.endsWith("jfif") || resource.endsWith("map") || resource.endsWith("svg") || resource.endsWith("eot")  || resource.endsWith("scss")) {
                    if (admin.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("SearchArticleController");
                    }
                }
            } else {
                res.sendRedirect("loginPage.jsp");
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
