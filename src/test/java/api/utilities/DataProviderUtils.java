package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;

public class DataProviderUtils {

    private static final String FILE_PATH = "TestData/userdata.xlsx";
    private static final String SHEET_NAME = "Sheet1";

    /**
     * Returns all data as Object[][]
     */
    @DataProvider(name = "userDataArray")
    public static Object[][] getUserData() {
        return ExcelUtils.getExcelDataAsArray(FILE_PATH, SHEET_NAME);
    }

    /**
     * Returns only the Username column as Object[][]
     */
    @DataProvider(name = "usernamesOnly")
    public static Object[][] getOnlyUsernames() {
        Object[][] fullData = ExcelUtils.getExcelDataAsArray(FILE_PATH, SHEET_NAME);
        Object[][] usernameData = new Object[fullData.length][1];

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            Row headerRow = sheet.getRow(0);

            int usernameColIndex = -1;
            for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                if ("Username".equalsIgnoreCase(headerRow.getCell(j).getStringCellValue())) {
                    usernameColIndex = j;
                    break;
                }
            }

            if (usernameColIndex == -1) {
                throw new RuntimeException("Username column not found in Excel header.");
            }

            for (int i = 0; i < fullData.length; i++) {
                usernameData[i][0] = fullData[i][usernameColIndex];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usernameData;
    }
}

