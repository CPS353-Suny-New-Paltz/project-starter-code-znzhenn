package projectapis.web;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import projectapis.conceptual.OptimizedFactorialAPIImplementation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        // create jetty server on port 8080
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new ComputeServlet()), "/quick");

        server.start();
        System.out.println("Server started at http://localhost:8080/");
        server.join();
    }

    public static class ComputeServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final OptimizedFactorialAPIImplementation engine = new OptimizedFactorialAPIImplementation();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            int n;
            try {
                n = Integer.parseInt(req.getParameter("n"));
            } catch (Exception e) {
                n = 0;
            }
            long result = engine.computeDigitFactorialSum(n);
            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(new long[]{ result }));
        }
    }
}
