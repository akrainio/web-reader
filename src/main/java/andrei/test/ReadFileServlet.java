package andrei.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static andrei.test.Util.map;
import static andrei.test.Util.page;
import static andrei.test.Util.pair;
import static andrei.test.Util.tag;

/**
 * Created by andrei on 10/15/16.
 */
public class ReadFileServlet extends MongoServlet{
    @Override
    protected void doGet(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        String param = req.getParameter("filename");
        if (param == null) {
            chooseFile(resp);
        }

    }
    private void chooseFile(HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        List<String> files = mongoImporter.getFileList();
        StringBuilder stringBuilder = new StringBuilder();
        for (String file: files) {
            stringBuilder.append(tag("option", map(pair("value", file)), file)).append("\n");
        }
        writer.println(page("Choose File",
                tag("form",
                        map(
                                pair("action", "read-file"),
                                pair("method", "get")
                        ),
                        "Choose file:",
                        tag("select",
                                map(pair("name", "filename")),
                                stringBuilder.toString()
                        ),
                        tag("input", map(pair("type", "submit"), pair("value", "submit")))
                )
        ));
    }

}
