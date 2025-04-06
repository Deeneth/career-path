import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CareerRecommendationServlet extends HttpServlet {
    // example dataset for domains available in colleges
    private static final String[][] CAREER_DOMAINS = {
            {"Engineering", "IIT Madras - ₹200,000", "NIT Trichy - ₹150,000", "Anna University - ₹100,000"},
            {"Medical Sciences", "AIIMS Delhi - ₹50,000", "CMC Vellore - ₹100,000", "JIPMER Puducherry - ₹75,000"},
            {"Arts and Humanities", "Delhi University - ₹30,000", "JNU - ₹35,000", "University of Madras - ₹25,000"},
            {"Business and Management", "IIM Ahmedabad - ₹230,000", "XLRI Jamshedpur - ₹200,000", "ISB Hyderabad - ₹250,000"},
            {"Computer Science and AI", "IIT Hyderabad - ₹180,000", "IIIT Bangalore - ₹160,000", "BITS Pilani - ₹200,000"}
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect user input from the form
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String syllabus = request.getParameter("syllabus");
        int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
        int cutoffMarks = Integer.parseInt(request.getParameter("cutoffMarks"));

        // Recommendation logic based on cutoff marks
        String recommendation;
        if (cutoffMarks >= 90) {
            recommendation = "Engineering";
        } else if (cutoffMarks >= 85) {
            recommendation = "Medical Sciences";
        } else if (cutoffMarks >= 75) {
            recommendation = "Arts and Humanities";
        } else {
            recommendation = "Business and Management";
        }

        // Generate output HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<div style='font-family:Arial, sans-serif;max-width:800px;margin:auto;padding:20px;border:1px solid #ddd;border-radius:8px;background:#eef5ff;'>");
        out.println("<h2>Thank you, " + name + "!</h2>");
        out.println("<p>Based on your cutoff marks, we recommend the career path: <b>" + recommendation + "</b>.</p>");
        out.println("<h3>Top Colleges:</h3><ul>");

        for (String[] domain : CAREER_DOMAINS) {
            if (domain[0].equals(recommendation)) {
                for (int i = 1; i < domain.length; i++) {
                    out.println("<li>" + domain[i] + "</li>");
                }
            }
        }
        out.println("</ul></div>");
        out.println("</body></html>");
    }
}