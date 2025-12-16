package projectapis.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projectapis.conceptual.OptimizedFactorialAPIImplementation;

import java.io.IOException;
import java.io.PrintWriter;

public class QuickServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private final OptimizedFactorialAPIImplementation factorialAPI = new OptimizedFactorialAPIImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String nParam = req.getParameter("n");
        int n = 0;
        try {
            n = Integer.parseInt(nParam);
        } catch (NumberFormatException e) {
            // default to 0
        }

        long sum = factorialAPI.computeDigitFactorialSum(n);

        out.print("[" + sum + "]");
        out.flush();
    }
}
