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
        resp.setHeader("Access-Control-Allow-Origin", "*");

        int number = 0;
        String nParam = req.getParameter("n");
        if (nParam != null && !nParam.isBlank()) {
            try {
                int parsed = Integer.parseInt(nParam);
                if (parsed >= 0) {
                    number = parsed;
                }
            } catch (NumberFormatException ignored) {
                // leave number as 0
            }
        }

        long sum = factorialAPI.computeDigitFactorialSum(number);

        System.out.println("Received n=" + number + ", sum=" + sum);

        try (PrintWriter out = resp.getWriter()) {
            out.print("[" + sum + "]");
        }
    }
}
