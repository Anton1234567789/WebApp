import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ParametrsServlet", loadOnStartup = 0)
public class ParametrsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String user = req.getParameter("user");
        HttpSession session = req.getSession();
        ServletContext context = req.getServletContext();
        if (user!=""&& user!=null){
            session.setAttribute("user",user);
            context.setAttribute("user",user);
        }

//        session.setMaxInactiveInterval(10);
        PrintWriter out = resp.getWriter();
        out.println("Request parametr: " + user+"<br>");
        out.println("Session parametr: " + session.getAttribute("user")+"<br>");
        out.println("Context parametr: " + context.getAttribute("user")+"<br>");
        out.println("<a href='http://localhost:8080/Parameters.html'>Back</a><br>");
        out.close();

        System.out.println("session time in seconds "+ session.getMaxInactiveInterval());


    }
}
