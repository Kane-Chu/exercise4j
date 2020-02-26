package kane.exercise.excelpoi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author kane
 */
@Slf4j
public class SimpleTest {

    private String path = this.getClass().getClassLoader().getResource("G2200.xlsx").getPath();


    private FileInputStream fis;
    private Workbook wb;

    //    @Before
    public void init() {
        try {
            this.fis = new FileInputStream(path);
            this.wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            log.error("error!", e);
        }
    }

    //    @After
    public void save() {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            wb.write(fos);
        } catch (IOException e) {
            log.error("error!", e);
        }
    }


    @Test
    public void setMergedCellValue() throws Exception {

        Sheet sheet = wb.getSheetAt(0);

        FileInputStream fis = new FileInputStream(path);
        Row row = sheet.getRow(1);
        if (row == null) {
            row = sheet.createRow(1);
        }
        Cell cell = row.getCell(2);
        CellStyle cellStyle = cell.getCellStyle();
        if (cell == null) {
            cell = row.createCell(2);
        }
        cell.setCellValue("2222");
        cell = row.getCell(7);
        if (cell == null) {
            cell = row.createCell(7);
        }
        cell.setCellValue("dddd");
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.close();
    }

    private Cell getCell(Sheet sheet, String coordinate) {
        CellAddress cellAddress = new CellAddress(coordinate);
        int rowNum = cellAddress.getRow();
        int colNum = cellAddress.getColumn();
        return CellUtil.getCell(CellUtil.getRow(rowNum, sheet), colNum);
    }


}