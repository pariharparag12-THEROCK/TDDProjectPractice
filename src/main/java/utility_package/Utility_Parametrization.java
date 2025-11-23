package utility_package;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utility_Parametrization {

	/*String path;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	Row row;
	Cell cell;
	
	public Utility_Parametrization(String path) {
		
		this.path = path;
	}
	
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum(); //getLastRowNum() = starting from Zero
		fis.close();
		workbook.close();		
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		fis.close();
		workbook.close();
		return cellcount;	
	}
	
	
	public String getCellDataString(String sheetname, int rownum, int colnum) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		
		String data;
		
		try {
			
			data =formatter.formatCellValue(cell);
		}
		
		catch(Exception e) {
			data="";
		}
		
		
		fis.close();
		workbook.close();	
		return data;

	}*/
	
	
	
	String path;
	FileInputStream fis;
	
	
	public Utility_Parametrization(String path) {
		
		this.path =path;
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
		
		int rowcount = WorkbookFactory.create(fis).getSheet(sheetname).getLastRowNum();

		return rowcount;

	}
	
	public int getCellCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
				
		int cellcount = WorkbookFactory.create(fis).getSheet(sheetname).getRow(rownum).getLastCellNum();

		return cellcount;
		
	}
	
	
	public String getCellData(String sheetname, int rownum, int cellnum) throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream(path);
		
		//String StringData = WorkbookFactory.create(fis).getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		
		Cell cell = WorkbookFactory.create(fis).getSheet(sheetname).getRow(rownum).getCell(cellnum);
		
		DataFormatter formatter = new DataFormatter();
		
		String data = "";
		
		try{
			
			data = formatter.formatCellValue(cell);
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		return data;
	}
	
	
	
}
