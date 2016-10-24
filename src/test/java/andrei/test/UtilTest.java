package andrei.test;

import java.util.Collections;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import static andrei.test.Util.map;
import static andrei.test.Util.page;
import static andrei.test.Util.pair;
import static andrei.test.Util.tag;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;

/**
 * Created by andrei on 10/17/16.
 */
public class UtilTest {
    @Test
    public void testEmptyTag() {
        assertEquals("<tag>\n</tag>", tag("tag", emptyMap()));
    }

    @Test
    public void testTagWithContent() {
        assertEquals("<tag>\n" + "first\n" + "</tag>", tag("tag", "first"));
        assertEquals("<tag>\n" + "first\n" + "second\n" + "third\n" + "</tag>", tag("tag", "first",
                "second", "third"));
    }

    @Test
    public void testTagWithAttributes() {
        Map<String, String> attributes = map(pair("k1", "v1"), pair("k2", "v2"), pair("k3", "v3"));
        assertEquals("<tag k3=\"v3\" k1=\"v1\" k2=\"v2\">\n" + "first\n" + "second\n" + "</tag>",
                tag("tag", attributes, "first", "second"));
        assertEquals("<tag key=\"valu&quot;e\">\n" + "</tag>", tag("tag", map(pair("key",
                "valu\"e"))));
    }

    @Test
    public void testPage() {
        assertEquals("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" +
                "<meta charset=\"UTF-8\">\n" + "<title>\n" + "PageTitle\n" + "</title>\n" +
                "</head>\n" + "<body>\n" + "PageBody\n" + "</body>\n" + "</html>", page("PageTitle",
                "PageBody"));
    }

}
