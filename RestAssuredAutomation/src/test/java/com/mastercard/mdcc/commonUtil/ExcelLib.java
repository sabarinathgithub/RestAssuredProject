package com.mastercard.mdcc.commonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib {

	FileInputStream fi;
	XSSFWorkbook wb;
	XSSFSheet sh;
	XSSFRow row;
	XSSFCell cell;
		
	public int getRowCount(String xlFile, String sheetName) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String xlFile, String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int cellCount = sh.getRow(rowNum).getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String xlFile, String sheetName, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rowNum);
		cell = row.getCell(cellNum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}catch(Exception e) {
			data = "";
		}		
		wb.close();
		fi.close();
		return data;		
	}
	
	public void setCellData(String xlFile, String sheetName, int rowNum, int cellNum, String data) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rowNum);
		cell = row.createCell(cellNum, CellType.STRING);
		row.getCell(cellNum).setCellValue(data);
		wb.close();
		fi.close();
	}

}
