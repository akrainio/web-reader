package andrei.test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;



public class FileUploadServlet extends MongoServlet {
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

        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            FileItem fileNameItem = null;
            FileItem fileStreamItem = null;
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    writer.println(item.getFieldName());
                    fileNameItem = item;
                } else {
                    fileStreamItem = item;
                }
            }
            assert fileNameItem != null: "did not receive a file name";
            assert fileStreamItem != null: "did not receive an input stream";
            try (InputStream inputStream = fileStreamItem.getInputStream()) {
                mongoImporter.storeFile(fileNameItem.getString(), inputStream);
            }
            writer.println("</body></html>");

        } catch (FileUploadException e) {
            throw new ServletException(e);
        }
    }
}
