import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import javax.servlet.ServletContext;

public class RequestInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Client information
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        // Server information
        String serverName = request.getServerName();
        String serverOS = System.getProperty("os.name") + " (" + System.getProperty("os.version") + ")";
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        ServletContext context = getServletContext();
        Enumeration<String> servlets = context.getServletNames();

        out.println("<html><head><title>Request Info</title></head><body>");
        out.println("<h2>Client Information</h2>");
        out.println("<p><b>IP Address:</b> " + clientIP + "</p>");
        out.println("<p><b>Browser:</b> " + userAgent + "</p>");

        out.println("<h2>Server Information</h2>");
        out.println("<p><b>Server Name:</b> " + serverName + "</p>");
        out.println("<p><b>Operating System:</b> " + serverOS + "</p>");
        out.println("<p><b>Server IP:</b> " + serverIP + "</p>");

        out.println("<h2>Loaded Servlets</h2>");
        out.println("<ul>");
        while (servlets.hasMoreElements()) {
            out.println("<li>" + servlets.nextElement() + "</li>");
        }
        out.println("</ul>");

        out.println("</body></html>");
    }
}
