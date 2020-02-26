package kane.exercise.excelpoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 非线程安全
 *
 * @author kane
 */
public class SimpleWorkbook {
    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = ".xlsx";
    private final URL url;
    private final FileInputStream fis;
    private final Workbook wb;
    private Sheet sheet;

    public SimpleWorkbook(File file) throws IOException {
        this(file.toURI().toURL());
    }

    public SimpleWorkbook(URL url) throws IOException {
        this.url = url;
        this.fis = new FileInputStream(url.getPath());
        if (url.getPath().toLowerCase().endsWith(OFFICE_EXCEL_XLSX)) {
            this.wb = new XSSFWorkbook(fis);
        } else if (url.getPath().toLowerCase().endsWith(OFFICE_EXCEL_XLS)) {
            this.wb = new HSSFWorkbook(fis);
        } else {
            throw new IllegalArgumentException("excel type not support");
        }
    }

    public Workbook getWorkbook() {
        return this.wb;
    }

    public void switchToSheet(int sheetIndex) {
        this.sheet = wb.getSheetAt(sheetIndex);
    }

    public void switchToSheet(String sheetName) {
        this.sheet = wb.getSheetAt(wb.getSheetIndex(sheetName));
    }

    public void switchToActiveSheet() {
        this.sheet = wb.getSheetAt(wb.getActiveSheetIndex());
    }

    public Cell getCell(String coordinate) {
        CellAddress cellAddress = new CellAddress(coordinate);
        return getCell(cellAddress);
    }

    public Cell getCell(int row, int col) {
        CellAddress cellAddress = new CellAddress(row, col);
        return getCell(cellAddress);
    }

    public Cell getCell(CellAddress cellAddress) {
        int rowNum = cellAddress.getRow();
        int colNum = cellAddress.getColumn();
        return CellUtil.getCell(CellUtil.getRow(rowNum, sheet), colNum);
    }

    public void setValue(String coordinate, double value){
        setValue(new CellAddress(coordinate), value);
    }

    public void setValue(int row, int col, double value){
        setValue(new CellAddress(row, col), value);
    }

    public void setValue(CellAddress cellAddress, double value) {
        Cell cell = getCell(cellAddress);
        cell.setCellValue(value);
    }

    public void setValue(String coordinate, Date value){
        setValue(new CellAddress(coordinate), value);
    }

    public void setValue(int row, int col, Date value){
        setValue(new CellAddress(row, col), value);
    }

    public void setValue(CellAddress cellAddress, Date value) {
        Cell cell = getCell(cellAddress);
        cell.setCellValue(value);
    }

    public void setValue(String coordinate, Calendar value){
        setValue(new CellAddress(coordinate), value);
    }

    public void setValue(int row, int col, Calendar value){
        setValue(new CellAddress(row, col), value);
    }

    public void setValue(CellAddress cellAddress, Calendar value) {
        Cell cell = getCell(cellAddress);
        cell.setCellValue(value);
    }

    public void setValue(String coordinate, RichTextString value){
        setValue(new CellAddress(coordinate), value);
    }

    public void setValue(int row, int col, RichTextString value){
        setValue(new CellAddress(row, col), value);
    }

    public void setValue(CellAddress cellAddress, RichTextString value) {
        Cell cell = getCell(cellAddress);
        cell.setCellValue(value);
    }

    public void setValue(String coordinate, String value){
        setValue(new CellAddress(coordinate), value);
    }

    public void setValue(int row, int col, String value){
        setValue(new CellAddress(row, col), value);
    }

    public void setValue(CellAddress cellAddress, String value) {
        Cell cell = getCell(cellAddress);
        cell.setCellValue(value);
    }

    public void save(URL url) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(url.getPath())) {
            wb.write(fos);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (wb != null) {
                wb.close();
            }
        }
    }
}