package projectapis.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projectapis.conceptual.OptimizedFactorialAPIImplementation;

import java.io.IOException;
import java.io.PrintWriter;

public class QuickServlet extends HttpServlet {

    private final OptimizedFactorialAPIImplementation factorialAPI = new OptimizedFactorialAPIImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        // allow the frontend fetch from any origin
        resp.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = resp.getWriter();
        String nParam = req.getParameter("n");
        int n = 0;

        try {
            n = Integer.parseInt(nParam);
        } catch (NumberFormatException e) {
            // default to 0
        }

        long sum = factorialAPI.computeDigitFactorialSum(n);

        System.out.println("Received n=" + n + ", sum=" + sum);

        out.print("[" + sum + "]");
        out.flush();
    }
}
