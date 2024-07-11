package example;

import com.example.PriceProcessor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PriceProcessorTest {

    @Test
    public void testSampleData() {
        List<String[]> allData = Arrays.asList(
                new String[]{"FLTR", "9/9/2023", "16401.02"},
                new String[]{"FLTR", "10/9/2023", "16384.62"},
                new String[]{"FLTR", "11/9/2023", "16482.92"},
                new String[]{"FLTR", "12/9/2023", "16351.06"},
                new String[]{"FLTR", "13/9/2023", "16220.25"},
                new String[]{"FLTR", "14/9/2023", "16171.59"},
                new String[]{"FLTR", "15/9/2023", "16106.91"},
                new String[]{"FLTR", "16/9/2023", "16058.58"},
                new String[]{"FLTR", "17/9/2023", "16283.4"},
                new String[]{"FLTR", "18/9/2023", "16397.39"},
                new String[]{"FLTR", "19/9/2023", "16561.36"},
                new String[]{"FLTR", "20/9/2023", "16644.17"},
                new String[]{"FLTR", "21/9/2023", "16793.97"},
                new String[]{"FLTR", "22/9/2023", "16760.38"},
                new String[]{"FLTR", "23/9/2023", "16401.02"},
                new String[]{"FLTR", "24/9/2023", "16384.62"},
                new String[]{"FLTR", "25/9/2023", "16482.92"},
                new String[]{"FLTR", "26/9/2023", "16351.06"},
                new String[]{"FLTR", "27/9/2023", "16220.25"},
                new String[]{"FLTR", "28/9/2023", "16171.59"},
                new String[]{"FLTR", "29/9/2023", "16106.91"},
                new String[]{"FLTR", "30/9/2023", "16058.58"},
                new String[]{"FLTR", "01/10/2023", "16283.4"},
                new String[]{"FLTR", "02/10/2023", "16397.39"},
                new String[]{"FLTR", "03/10/2023", "16561.36"},
                new String[]{"FLTR", "04/10/2023", "16644.17"},
                new String[]{"FLTR", "05/10/2023", "16793.97"},
                new String[]{"FLTR", "06/10/2023", "16760.38"},
                new String[]{"FLTR", "07/10/2023", "16401.02"},
                new String[]{"FLTR", "07/10/2023", "344566.02"},
                new String[]{"FLTR", "07/10/2023", "34534.02"}
        );

        List<String[]> sampledData = PriceProcessor.sampleData(allData);

        assertEquals(30, sampledData.size());
    }


    @Test
    public void testDetectOutliers() {
        List<String[]> sampleData = Arrays.asList(
                new String[]{"FLTR", "9/9/2023", "16401.02"},
                new String[]{"FLTR", "10/9/2023", "16401.23"},
                new String[]{"FLTR", "11/9/2023", "16482.92"},
                new String[]{"FLTR", "12/9/2023", "16351.06"},
                new String[]{"FLTR", "13/9/2023", "16220.25"},
                new String[]{"FLTR", "14/9/2023", "16171.59"},
                new String[]{"FLTR", "15/9/2023", "16106.91"},
                new String[]{"FLTR", "16/9/2023", "16058.58"},
                new String[]{"FLTR", "17/9/2023", "16283.4"},
                new String[]{"FLTR", "18/9/2023", "16397.39"},
                new String[]{"FLTR", "19/9/2023", "16561.36"},
                new String[]{"FLTR", "20/9/2023", "16644.17"},
                new String[]{"FLTR", "21/9/2023", "16793.97"},
                new String[]{"FLTR", "22/9/2023", "16760.38"},
                new String[]{"FLTR", "23/9/2023", "16401.02"},
                new String[]{"FLTR", "24/9/2023", "16384.62"},
                new String[]{"FLTR", "25/9/2023", "16482.92"},
                new String[]{"FLTR", "26/9/2023", "16351.06"},
                new String[]{"FLTR", "27/9/2023", "16220.25"},
                new String[]{"FLTR", "28/9/2023", "560000.59"},// outlier
                new String[]{"FLTR", "29/9/2023", "16106.91"},
                new String[]{"FLTR", "30/9/2023", "16058.58"},
                new String[]{"FLTR", "01/10/2023", "16283.4"},
                new String[]{"FLTR", "02/10/2023", "16397.39"},
                new String[]{"FLTR", "03/10/2023", "16561.36"},
                new String[]{"FLTR", "04/10/2023", "16644.17"},
                new String[]{"FLTR", "05/10/2023", "16293.97"},
                new String[]{"FLTR", "05/10/2023", "16793.23"},
                new String[]{"FLTR", "05/10/2023", "16533.97"},
                new String[]{"FLTR", "05/10/2023", "16523.00"},
                new String[]{"FLTR", "05/10/2023", "16653.93"}
        );

        List<String[]> outliers = PriceProcessor.detectOutliers(sampleData);

        for (String[] outlier : outliers) {
            System.out.println(Arrays.toString(outlier));
        }
        assertEquals(1, outliers.size());
        assertEquals("33945.95", outliers.get(0)[3]);
        assertEquals("526054.64 %", outliers.get(0)[4] + " %");
    }
}
