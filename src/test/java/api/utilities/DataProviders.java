package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		ExcelUtility excelUtility=new ExcelUtility(path);
		
		int totalRows=excelUtility.getRowCount("Sheet1");
		int totalCells=excelUtility.getCellCount("Sheet1", 1);
		
		String apiData[][]=new String[totalRows][totalCells];
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCells;j++) {
				apiData[i-1][j]=excelUtility.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		ExcelUtility excelUtility=new ExcelUtility(path);
		
		int totalRows=excelUtility.getRowCount("Sheet1");
		
		String apiData[]=new String[totalRows];
		
		for(int i=1;i<=totalRows;i++) {
				apiData[i-1]=excelUtility.getCellData("Sheet1", i, 1);
		}
		return apiData;
	}

}
