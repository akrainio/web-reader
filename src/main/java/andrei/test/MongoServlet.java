package andrei.test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by andrei on 10/21/16.
 */
public class MongoServlet extends HttpServlet{
    protected MongoImporter mongoImporter;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        mongoImporter = (MongoImporter) context.getAttribute("db");
        if (mongoImporter == null) {
            mongoImporter = new MongoImporter();
            context.setAttribute("db", mongoImporter);
        }
    }
}
