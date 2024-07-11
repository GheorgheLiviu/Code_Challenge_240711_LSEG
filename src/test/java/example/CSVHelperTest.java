package example;

import com.example.CSVHelper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVHelperTest {

    @Test
    public void testReadCSV() throws IOException {
        String filePath = "src/test/resources/mockFile.csv";
        List<String[]> result = CSVHelper.readCSV(filePath);


        assertEquals(2, result.size());
        assertEquals("LVU", result.get(0)[0]);
        assertEquals("18-09-1995", result.get(0)[1]);
        assertEquals("399.99", result.get(0)[2]);
    }

    @Test
    public void testWriteCSV() throws IOException {
        String filePath = "src/test/resources/output/outputTest.csv";
        List<String[]> data = Arrays.asList(
                new String[]{"LVU", "18-09-1995", "399.23"},
                new String[]{"ALT", "23-09-1123", "409.02"}
        );

        CSVHelper.writeCSV(filePath, data);


        List<String[]> result = CSVHelper.readCSV(filePath);
        assertEquals(data.size(), result.size());
        assertEquals(data.get(0)[0], result.get(0)[0]);
        assertEquals(data.get(0)[1], result.get(0)[1]);
        assertEquals(data.get(0)[2], result.get(0)[2]);

    }
}
