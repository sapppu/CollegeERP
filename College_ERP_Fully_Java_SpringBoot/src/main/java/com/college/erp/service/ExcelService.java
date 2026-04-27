package com.college.erp.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Shared Excel import/export utility used across all modules.
 */
@Service
public class ExcelService {

    // ── EXPORT ──────────────────────────────────────────────────────────

    /**
     * Write an Excel workbook to the HTTP response as a downloadable .xlsx file.
     */
    public void exportToExcel(HttpServletResponse response,
                              String fileName,
                              String sheetName,
                              String[] headers,
                              List<Object[]> rows) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook();
             OutputStream out = response.getOutputStream()) {

            Sheet sheet = workbook.createSheet(sheetName);

            // Header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font whiteFont = workbook.createFont();
            whiteFont.setBold(true);
            whiteFont.setColor(IndexedColors.WHITE.getIndex());
            whiteFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(whiteFont);

            // Header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data rows
            int rowIdx = 1;
            for (Object[] rowData : rows) {
                Row row = sheet.createRow(rowIdx++);
                for (int i = 0; i < rowData.length; i++) {
                    Cell cell = row.createCell(i);
                    if (rowData[i] == null) {
                        cell.setCellValue("");
                    } else if (rowData[i] instanceof Number) {
                        cell.setCellValue(((Number) rowData[i]).doubleValue());
                    } else {
                        cell.setCellValue(rowData[i].toString());
                    }
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
        }
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    /**
     * Open a workbook from a MultipartFile. Supports .xlsx and .xls.
     */
    public Workbook openWorkbook(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        }
        return new XSSFWorkbook(is);
    }

    /**
     * Map header names to column indices using alias arrays.
     * aliases[i] = array of possible header name variants for field i.
     * Returns int[] where result[i] = column index for field i.
     */
    public int[] mapColumns(Row headerRow, String[][] aliases) {
        int[] map = new int[aliases.length];
        for (int i = 0; i < map.length; i++) map[i] = i; // defaults

        for (int c = 0; c <= headerRow.getLastCellNum(); c++) {
            Cell cell = headerRow.getCell(c);
            if (cell == null) continue;
            String val = getCellString(headerRow, c).toLowerCase()
                    .replace(" ", "").replace("_", "").replace(".", "");
            for (int e = 0; e < aliases.length; e++) {
                for (String alias : aliases[e]) {
                    if (val.equals(alias)) {
                        map[e] = c;
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * Safely read a cell value as a String.
     */
    public String getCellString(Row row, int colIndex) {
        if (colIndex < 0) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                double d = cell.getNumericCellValue();
                if (d == Math.floor(d) && !Double.isInfinite(d)) {
                    yield String.valueOf((long) d);
                }
                yield String.valueOf(d);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> "";
        };
    }

    /**
     * Safely read a cell value as a Double.
     */
    public Double getCellDouble(Row row, int colIndex) {
        if (colIndex < 0) return null;
        Cell cell = row.getCell(colIndex);
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case STRING -> {
                try { yield Double.parseDouble(cell.getStringCellValue().trim()); }
                catch (NumberFormatException e) { yield null; }
            }
            default -> null;
        };
    }

    /**
     * Validate file extension.
     */
    public boolean isValidExcelFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && (fileName.endsWith(".xlsx") || fileName.endsWith(".xls"));
    }

    // ── Import result container ─────────────────────────────────────────

    public static class ImportResult {
        private final int successCount;
        private final int totalRows;
        private final List<String> errors;

        public ImportResult(int successCount, int totalRows, List<String> errors) {
            this.successCount = successCount;
            this.totalRows = totalRows;
            this.errors = errors;
        }

        public int getSuccessCount()  { return successCount; }
        public int getTotalRows()     { return totalRows; }
        public int getFailedCount()   { return totalRows - successCount; }
        public List<String> getErrors() { return errors; }
        public boolean hasErrors()    { return !errors.isEmpty(); }
    }
}
