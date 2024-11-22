package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import java.util.HashMap;

class TagFrequencyCounterTest {

    @Test
    void testCountTags() {
        String htmlContent = """
                <html>
                    <head><title>Test Page</title></head>
                    <body>
                        <div><p>Hello World!</p></div>
                        <div><p>Another paragraph.</p></div>
                    </body>
                </html>
                """;
        TagFrequencyCounter counter = new TagFrequencyCounter();

        Map<String, Integer> expectedTagCount = new HashMap<>();
        expectedTagCount.put("html", 1);
        expectedTagCount.put("/html", 1);
        expectedTagCount.put("head", 1);
        expectedTagCount.put("/head", 1);
        expectedTagCount.put("title", 1);
        expectedTagCount.put("/title", 1);
        expectedTagCount.put("body", 1);
        expectedTagCount.put("/body", 1);
        expectedTagCount.put("div", 2);
        expectedTagCount.put("/div", 2);
        expectedTagCount.put("p", 2);
        expectedTagCount.put("/p", 2);

        Map<String, Integer> resultTagCount = counter.countTagsFromHtml(htmlContent);

        assertEquals(expectedTagCount, resultTagCount, "Tag count should match expected values");
    }
}
