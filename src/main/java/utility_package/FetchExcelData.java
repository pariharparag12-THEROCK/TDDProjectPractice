package utility_package;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchExcelData {

	public static String getExcelFileData(int rownumber, int columnnumber) throws EncryptedDocumentException, IOException {
		
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\TestDATA.xlsx";
		
		FileInputStream fis = new FileInputStream(path);
		
		String getStringCellData = WorkbookFactory.create(fis).getSheet("Sheet1").getRow(rownumber).getCell(columnnumber).getStringCellValue();
		
		return getStringCellData;
	}
}
