import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
    private static final long serialVersionUID = 1l;
    private Map<Integer,String> users = new ConcurrentHashMap<Integer, String>();
    private AtomicInteger counter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        String user = users.get(id);
        if (user==null){
            user="";
        }

        resp.setContentType("text/html; charset= UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3>User: " +user+"</h3><br>");
        out.print("<a href='javascript:history.back();'>Back</a>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        Integer id = null;
        if(!users.containsValue(name)){
            id = counter.incrementAndGet();
            users.put(id,name);
        }

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (id==null){
            out.println("<h3>user: "+ name+" already exist</h3>");
        }else {
            out.println("<h3>Create user: "+ name+" with id = "+counter.get()+" </h3>");
        }
        out.print("<br>");
        out.println("<a href='http://localhost:8080/user.html'>On main</a>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        users.put(1,"Vasya");
        users.put(2,"Ivan");
        users.put(3,"Vova");
        counter = new AtomicInteger(3);
    }
}
