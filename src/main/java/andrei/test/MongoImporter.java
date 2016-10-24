package andrei.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * Created by andrei on 10/12/16.
 */
public class MongoImporter {

    DB db = new DB(new MongoClient("localhost"), "fileStorage");
    DBCollection fileData = db.createCollection("fileData", null);
    DBCollection fileNames = db.createCollection("fileNames", null);

    public MongoImporter() {
        fileNames.createIndex(new BasicDBObject("fileNames", 1), "fileNameIndex", true);
        System.out.println("Initializing mongo importer");
    }

    public void storeFile(String filename, InputStream in) throws IOException {
        WriteResult result = fileNames.insert(new BasicDBObject("filename", filename));
        Object fileRef = result.getUpsertedId();
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(in));
        for (String line = lineNumberReader.readLine(); line != null; line = lineNumberReader.readLine()) {
            BasicDBObject dbObject = new BasicDBObject();
            dbObject.append("lineNum", lineNumberReader.getLineNumber());
            dbObject.append("line", line);
            dbObject.append("ref", fileRef);
            fileData.insert(dbObject);
        }
    }

    public List<String> getFileList() {
        List<String> fileList = new ArrayList<>();
        DBCursor cursor = fileNames.find(new BasicDBObject(), new BasicDBObject("filename", 1));
        for (DBObject object: cursor) {
            fileList.add(object.get("filename").toString());
        }
        return fileList;
    }
}
