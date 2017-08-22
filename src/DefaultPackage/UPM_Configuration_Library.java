package DefaultPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import com.relevantcodes.extentreports.LogStatus;

public class UPM_Configuration_Library {
	static boolean Create_New_Instance = false;
	static HSSFWorkbook Obj_Workbook;
	static HSSFSheet Obj_Sheet;
	static HSSFCell Obj_Cell;
	static HSSFCell Obj_Cell1;
	static HSSFRow row;
	public static FileInputStream fileInputStream;
	int iterator;
	public static int Driver_Itrator;
	static String Cellvalue;
	static File Obj_directory;
	public static int Screen_Number;
	public static String WorkBook_Path;
	//@@@@@@@@@@@Singleton Implementation@@@@@@@@@@@@@@@@@@
	public static UPM_Configuration_Library Config_Instance;
	UPM_Configuration_Library()
	{
	}
	public static UPM_Configuration_Library  Get_Config_Instance()
	{
		if(Config_Instance == null)
		{
			return Config_Instance = new UPM_Configuration_Library();
		}
		else
		{
			return Config_Instance;
		}
	}
	//@@@@@@@@@@@````````````````````````@@@@@@@@@@@@@@@@@@
	
	public static int Get_Sheet_Count(String WorkBook_Name) throws Exception
	{
		if (Create_New_Instance)
		{
			Obj_Workbook = Get_Active_WorkBook(WorkBook_Name);
		}
		return Obj_Workbook.getNumberOfSheets();
	}
	
	public static String Get_SheetName_By_Header(String WorkBook_Name,String Header) throws Exception
	{
		String ret = null;
		try
		{
		int i,j;
		
		UPM_Configuration_Library.Create_New_Instance = true;
		int x = Get_Sheet_Count(WorkBook_Name);
		UPM_Configuration_Library.Create_New_Instance = false;
		for (i = 0;i<=x;i++)
		{
			Obj_Sheet = Get_Active_Sheet_By_Index(WorkBook_Name,i);
			Cellvalue = null;
			row = Obj_Sheet.getRow(0);
			int col_cnt = row.getPhysicalNumberOfCells();
			for(j=0;j<col_cnt;j++)
			{
				Obj_Cell = row.getCell(j);
				Cellvalue = Obj_Cell.getStringCellValue();
				if(Header.compareTo(Cellvalue)==0)
				{
					ret = Obj_Sheet.getSheetName();
					return ret;
				}
			}
		}
		return ret;
		}
		catch(NullPointerException e)
		{
			Sound_Beep();
			e.printStackTrace();
			UPM_Function_Library.Exception_Content();
		}
		return ret;
	}
	
	public static int Get_ColumnIndex_By_Header(String WorkBook_Name,String Sheet_Name,String Header) throws Exception
	{
		@SuppressWarnings("unused")
		int i,j;
		int ret = 0;
		Obj_Sheet = Get_Active_Sheet(WorkBook_Name,Sheet_Name);
		Cellvalue = null;
		row = Obj_Sheet.getRow(0);
		int col_cnt = row.getPhysicalNumberOfCells();
		for(j=0;j<col_cnt;j++)
		{
			Obj_Cell = row.getCell(j);
			Cellvalue = Obj_Cell.getStringCellValue();
			if(Header.compareTo(Cellvalue)==0)
			{
				ret = j;
				return ret;
			}
		}
		return ret;
	}
	
	public static int Get_Sheet_Row_Count(String WorkBook_Name,String Sheet_Name) throws Exception 
	{
		Obj_Sheet = Get_Active_Sheet(WorkBook_Name,Sheet_Name);
		return Obj_Sheet.getLastRowNum();
	}
	
	public static HSSFWorkbook Get_Active_WorkBook(String WorkBook_Name) throws Exception
	{
		if(Create_New_Instance)
		{
		String Str_Basepath = GetPath();
		@SuppressWarnings("unused")
		int Count = 0;
		WorkBook_Path = Str_Basepath + "\\TestData\\" + WorkBook_Name;
		fileInputStream = new FileInputStream(WorkBook_Path );
		POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
		Obj_Workbook = new HSSFWorkbook((fsFileSystem));
		}
		return Obj_Workbook;
	}
	
	public static HSSFSheet Get_Active_Sheet(String WorkBook_Name,String Sheet_Name) throws Exception
	{
		Obj_Workbook = Get_Active_WorkBook(WorkBook_Name);
		Obj_Sheet = Obj_Workbook.getSheet(Sheet_Name);
		return Obj_Sheet;
	}
	
	public static HSSFSheet Get_Active_Sheet_By_Index(String WorkBook_Name,Integer Sheet_Index) throws Exception
	{
		Obj_Workbook = Get_Active_WorkBook(WorkBook_Name);
		Obj_Sheet = Obj_Workbook.getSheetAt(Sheet_Index);
		return Obj_Sheet;
	}
	
	public static String GetCell_Value(String WorkBook_Name,String Sheet_Name,int RowNumber, int CellNumber) throws Exception 
	{
		Cellvalue = null;
		Obj_Sheet = Get_Active_Sheet(WorkBook_Name,Sheet_Name);
		row = Obj_Sheet.getRow(RowNumber);
		Obj_Cell = row.getCell(CellNumber);
		int x = Obj_Cell.getCellType();
		if(x == 0)
		{
			Cellvalue = String.valueOf((int)Obj_Cell.getNumericCellValue()) ;
		}
		if(x == 1)
		{
			Cellvalue = Obj_Cell.getStringCellValue();
		}
		if(x == 3)
		{
			Cellvalue = "";
		}
		return Cellvalue;
	}
	
	public static void SetCell_Value(String WorkBook_Name,String Sheet_Name,int RowNumber, int CellNumber,String Set_Val) throws Exception 
	{
		Cellvalue = null;
		Obj_Sheet = Get_Active_Sheet(WorkBook_Name,Sheet_Name);
		row = Obj_Sheet.getRow(RowNumber);
		Obj_Cell = row.getCell(CellNumber);
		Obj_Cell.setCellValue(Set_Val);
	}
	
	public static void Set_Active_Cell_Value(int Incremental_Row_Number,String Set_Val) throws Exception 
	{
		int RowNumber = Obj_Cell.getRowIndex();
		int ColNumber = Obj_Cell.getColumnIndex();
		RowNumber = RowNumber+Incremental_Row_Number;
		String Cellvalue;
		Cellvalue = Set_Val;
		row = Obj_Sheet.getRow(RowNumber);
		Obj_Cell = row.getCell(ColNumber);
		if (Obj_Cell.getStringCellValue().isEmpty())
		{
			//Obj_Cell = row.createCell(ColNumber);  
			Obj_Cell.setCellType(1);
			Obj_Cell.setCellValue(Cellvalue.toString());
		}
		else
		{
			Cellvalue = Obj_Cell.getStringCellValue()+";" + Cellvalue;
			Obj_Cell.setCellValue(Cellvalue.toString());
		}
		fileInputStream.close();
		
		FileOutputStream outFile =new FileOutputStream(new File(WorkBook_Path));
		Obj_Workbook.write(outFile);
		outFile.close();
	}
	
	public static void Set_Active_Cell_Value_overwrite(int Incremental_Row_Number,String Set_Val) throws Exception 
	{
		int RowNumber = Obj_Cell.getRowIndex();
		int ColNumber = Obj_Cell.getColumnIndex();
		RowNumber = RowNumber+Incremental_Row_Number;
		String Cellvalue;
		Cellvalue = Set_Val;
		row = Obj_Sheet.getRow(RowNumber);
		Obj_Cell = row.getCell(ColNumber);
		
		if(!(Obj_Cell.getStringCellValue().isEmpty()))
		{	
			Obj_Cell.setCellValue(Cellvalue.toString());
		}
		fileInputStream.close();
		
		FileOutputStream outFile =new FileOutputStream(new File(WorkBook_Path));
		Obj_Workbook.write(outFile);
		outFile.close();
	}
	
	public static String Get_Header_Value_By_SheetIndex(String WorkBook_Name,Integer Sheet_Index, int CellNumber) throws Exception 
	{
		Cellvalue = null;
		Obj_Sheet = Get_Active_Sheet_By_Index(WorkBook_Name,Sheet_Index);
		row = Obj_Sheet.getRow(0);
		int col_cnt = row.getPhysicalNumberOfCells();
		int i=0;
		for (i=1;i<=col_cnt;i++)
		{
		//Obj_Cell =  // getCell(CellNumber);
		Cellvalue = Obj_Cell.getStringCellValue();
		}
		return Cellvalue;
	}
	
	public static String GetPath() throws Exception {
		String Bpath;
		Obj_directory = new File(".");
		Bpath = Obj_directory.getCanonicalPath();
		return Bpath;
	}
	
	public static String GetLocator(String WorkBook_Name,String Sheet_Name,String Col_Name_Row) throws Exception 
	{
		int i = 0;
		int iterator = 0;
		int UsedCellCount = 0;
		
		try 
		{
			UsedCellCount = Get_Sheet_Row_Count(WorkBook_Name, Sheet_Name);
			
			for (iterator = 1; iterator <= (UsedCellCount); iterator++) {
				row = Obj_Sheet.getRow(iterator);
				Obj_Cell = row.getCell(0);
				Obj_Cell1 = row.getCell(1);
				if (Obj_Cell.getStringCellValue().equals(Col_Name_Row)) 
				{
					Obj_Cell1 = row.getCell(1);
					i = 1;
					break;
				}
			}
			if (i == 0) 
			{
				System.out.println("Excel Value : " + Col_Name_Row + "Not Found");
				throw new Exception();
			}
		} 
		catch (Exception e) 
		{
			Sound_Beep();
			System.out.println("Excel Value : " + Col_Name_Row);
			throw new Exception(e);
		}
		return Obj_Cell1.getStringCellValue();
	}
	
	public static String Get_Latest_Xcl_From_Download_Location() throws IOException
	{
		 File dir = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads");
		    File[] files = dir.listFiles();
		    if (files == null || files.length == 0) {
		        return null;
		    }

		    File lastModifiedFile = files[0];
		    for (int i = 1; i < files.length; i++) {
		       if (lastModifiedFile.lastModified() < files[i].lastModified()) 
		       {
		           lastModifiedFile = files[i];
		       }
		    }
		    return lastModifiedFile.getCanonicalPath();
	}
	
	public static void Verify_Excel_Contains_Data(String Expected_Text,String Desc) throws Exception
	{
		HSSFWorkbook Obj_Workbook1;
		HSSFSheet Obj_Sheet1;
		HSSFCell Obj_Cell1;
		HSSFRow row1;
		String WorkBook_Path1 ;
		int iterator = 0;
		int iterator1 = 0;
		int Row_Cnt = 0;
		int Col_Cnt = 0;
		Boolean Exist_Flg;
		Exist_Flg = false;
		WorkBook_Path1 = Get_Latest_Xcl_From_Download_Location();
		fileInputStream = new FileInputStream(WorkBook_Path1 );
		POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
		Obj_Workbook1 = new HSSFWorkbook((fsFileSystem));
		Obj_Sheet1 = Obj_Workbook1.getSheet("Sheet1");
		Row_Cnt = Obj_Sheet1.getLastRowNum();
		
		for (iterator = 1; iterator <= (Row_Cnt); iterator++) 
		{
			
			row1 = Obj_Sheet1.getRow(iterator);
			if(!(row1==null))
			{
			Col_Cnt = row1.getPhysicalNumberOfCells();
			for(iterator1 = 0; iterator1 <= (Col_Cnt); iterator1++)
			{
				Obj_Cell1 = row1.getCell(iterator1);
				if(!(Obj_Cell1==null))
				{
				if(Obj_Cell1.getStringCellValue().equals(Expected_Text))
				{
					Exist_Flg = true;
					break;
				}
				}
			}
			if(Exist_Flg)
			{
				break;
			}
			}
		}
			if(Exist_Flg)
			{
				if(!Desc.isEmpty())
				{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ ", Expected Value : " + Expected_Text, LogStatus.PASS, true);
				}
			}
			else
			{
				if(!Desc.isEmpty())
				{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ ", Expected Value : " + Expected_Text, LogStatus.FAIL, true);
				UPM_Function_Library.Exception_Content();
				}
			}
	}
	public static String GetVal(String WorkBook_Name,String Sheet_Name,String Col_Name_Row) throws Exception 
	{
		int i = 0;
		int iterator = 0;
		int UsedCellCount = 0;
		String ret = null;
		try 
		{
			UsedCellCount = Get_Sheet_Row_Count(WorkBook_Name, Sheet_Name);
			
			for (iterator = 1; iterator <= (UsedCellCount); iterator++) {
				row = Obj_Sheet.getRow(iterator);
				Obj_Cell = row.getCell(0);
				Obj_Cell1 = row.getCell(1);
				if (Obj_Cell.getStringCellValue().equals(Col_Name_Row)) 
				{
					Obj_Cell1 = row.getCell(1);
					i = 1;
					break;
				}
			}
			if (i == 0) 
			{
				System.out.println("Excel Value : " + Col_Name_Row + "Not Found");
				throw new Exception();
			}
		} 
		catch (Exception e) 
		{
			Sound_Beep();
			System.out.println("Excel Value : " + Col_Name_Row + " :");
			throw new Exception(e);
		}
		int x = Obj_Cell1.getCellType();
		if(x == 0)
		{
			ret = String.valueOf((int)Obj_Cell1.getNumericCellValue()) ;
		}
		if(x == 1)
		{
			ret = Obj_Cell1.getStringCellValue();
		}
		return ret;
	}
	
	//UPDATION REQUIRED
	public static void SetVal(String WorkBook_Name,String Sheet_Name,String Col_Name_Row) throws Exception 
	{
		int i = 0;
		int iterator = 0;
		int UsedCellCount = 0;
		try 
		{
			UsedCellCount = Get_Sheet_Row_Count(WorkBook_Name, Sheet_Name);
			
			for (iterator = 1; iterator <= (UsedCellCount); iterator++) {
				row = Obj_Sheet.getRow(iterator);
				Obj_Cell = row.getCell(0);
				Obj_Cell1 = row.getCell(1);
				if (Obj_Cell.getStringCellValue().equals(Col_Name_Row)) 
				{
					Obj_Cell1 = row.getCell(1);
					i = 1;
					break;
				}
			}
			if (i == 0) 
			{
				System.out.println("Excel Value : " + Col_Name_Row + "Not Found");
				throw new Exception();
			}
		} 
		catch (Exception e) 
		{
			Sound_Beep();
			System.out.println("Excel Value : " + Col_Name_Row + " :");
			throw new Exception(e);
		}
		int x = Obj_Cell1.getCellType();
		if(x == 0)
		{
		}
		if(x == 1)
		{
		}
	}
	
	public static void Check_Create_Directory(String Pth)
	{
		File f = new File(Pth);
		if(!f.exists())
		{
			f.mkdir();
		}
	}
	
	public static void Get_Iterator(int Local_Iteratator)
	{
		Driver_Itrator = Local_Iteratator;
	}
	
	public static int Set_Iterator()
	{
		return Driver_Itrator;
	}
	
	public static void Sound_Beep() throws InterruptedException
	{
		for(int i=0;i<=100;i++)
		{
			java.awt.Toolkit.getDefaultToolkit().beep();
			Thread.sleep(100);
		}
	}
}
