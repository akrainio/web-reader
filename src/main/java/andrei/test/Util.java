package andrei.test;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 10/15/16.
 */
public class Util {
    private Util(){}

    public static String tag(String tag, Map<String, String> attributes, String... contents) {
        assert !tag.isEmpty();
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        for (Map.Entry<String, String> pair: attributes.entrySet()) {
            sb.append(" ").append(pair.getKey()).append("=\"");
            String escaped = StringEscapeUtils.escapeHtml(pair.getValue());
            sb.append(escaped).append("\"");
        }
        sb.append(">");
        for (String content: contents) {
            sb.append("\n").append(content);
        }
        sb.append("\n</").append(tag).append(">");
        return sb.toString();
    }

    public static String tag(String tag, String... contents) {
        return tag(tag, Collections.emptyMap(), contents);
    }

    public static String page(String title, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append(
                tag("html", map(pair("lang", "en")),
                        tag("head",
                                "<meta charset=\"UTF-8\">",
                                tag("title", title)
                        ),
                        tag("body", body)
                ));
        return sb.toString();
    }

    static class Pair<K, V> {
        public final K key;
        public final V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static <K, V> Pair<K, V> pair(K key, V value) {
        return new Pair<>(key, value);
    }

    public static <K, V> Map<K, V> map(Pair<K, V>... pairs) {
        Map<K, V> result = new HashMap<K, V>(pairs.length);
        for (Pair<K, V> pair: pairs) {
            result.put(pair.key, pair.value);
        }
        return result;
    }
}
