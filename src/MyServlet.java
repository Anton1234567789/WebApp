import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Антон on 09.10.2016.
 */
@WebServlet(urlPatterns = "/MyServlet",description = "My description")
public class MyServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MyServlet.doGet();");

        String name = req.getParameter("firstName");
        String surname = req.getParameter("secondName");
        String[] jobs= req.getParameterValues("job");
        String gender = req.getParameter("gender");
        if (gender==null){
            gender="-";
        }
        String age18 = req.getParameter("age18");
        if (age18==null){
            age18="-";
        }
        System.out.println("Name: " + name + "<br>");
        System.out.println("Surname: " + surname + "<br>");
        System.out.println("Value role: " + jobs.length);
        for (String job : jobs){
            System.out.print(job + ",");
        }
        System.out.println();
        System.out.println("Pol: " + gender);
        System.out.println("Age +18? : "+age18);

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<h1> Profile: </h1>");
        out.println("Name: "+name +"<br>");
        out.println("Surname: " + surname+ "<br>");
//        out.println("Prof: " + jobs+"<br>");
        out.println("Value role: " + Arrays.deepToString(jobs)+"<br>");
        out.println("Pol: " + gender+"<br>");
        out.println("Age +18? : "+age18);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MyServlet.doPost();");

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("firstName");
        String surname = req.getParameter("secondName");
        System.out.println(name);
        System.out.println(surname);

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<h1>Hello of MyServlet.POST"+" "+name+" "+surname+"</h1>");
        out.println("<br>Again");
        out.close();
    }
}
