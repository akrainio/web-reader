package andrei.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FileUploadServlet extends HttpServlet {
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Date date = new Date();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>" + date + "</body></html>");
    }
}
