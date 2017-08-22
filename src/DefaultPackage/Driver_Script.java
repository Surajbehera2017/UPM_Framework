package DefaultPackage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.TemporaryFilesystem;

import com.relevantcodes.extentreports.LogStatus;


public class Driver_Script {
	
	static UPM_Configuration_Library Config;
	static UPM_TestCase_Library TestCase_Config;
	
	public static String Separate_reports;
	static UPM_Global_Variable GV;
	public static String Basepath;
	static String Str_OS;
	static String Str_TestcaseId;
	static String Str_Browser;
	static String Str_Version;
	static String Str_Profile_Path;
	static String Str_ExecutionStatus;
	static String Str_TC_Name="";
	static String Str_TC_ExecutionStatus;
	static WebDriver ObjBrowser;
	public static int Iterate = 0;
	public static Boolean Set_Iterator_Flg = true;
	static Integer TS_Iterator;
	static int Row_Cnt;
	static String Keyword = null;
	static String Description = null;
	static boolean Flag=false;
	
	public static void main(String args[]) throws Exception
	{
		
	    
		//FOR TEST
		
		
		//UPM_Configuration_Library.Verify_Excel_Contains_Data("Test UPM role test test test test test test test test test test test test test test test test test Check test 2","Dsdsd");
		//String x = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
		//System.getProperty("user.name");
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//		Str_OS = "Win 7";
//		Str_Browser = "FF";
//		Str_Version = "34" ;
//		Str_Profile_Path = "C:/Program Files/Mozilla Firefox/firefox.exe" ;
//		Str_ExecutionStatus = "yes";
//		UPM_Report_Library.Start_Report(Str_TC_Name, "Ding");
//		UPM_Function_Library.UPM_Login("http://upmsit2.deutsche-leasing.de:81/AS/upm/;USER=UPM_R1;PASSWORD=Upmsit2014;loginbox_submit","Desc");
//		UPM_Function_Library.UPM_Select_DropDownList("xpath=//*[contains(@id,'PAR_SER_SUC_ROLE_CBX')]", "UCS Kunde", "Select option from Rolle dropdown");
//		UPM_Function_Library.UPM_Click_Object("xpath=//*[contains(@id,'PAR_SEARCH_ID')]","yes","Des");
		
		//UPM_Function_Library.Activate_Latest_Browser("yes","Activating New Browser");
		//	UPM_Function_Library.UPM_Click_Image_By_Index( "xpath=//*[@src='/AS/upm/images/ic_trash0.gif']","0","yes","Click Deete ");
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@		
	
		
		int Browser_Config_Row_Cnt = 0;
		int TC_Config_Row_Cnt = 0;
		int iterator;
		int iterator1;
		String ALM_status="";
		String TD_Config_Excel;
		String BC_Sheet;
		String TC_Sheet;
		String Reference_sheet;
		
		TD_Config_Excel = "TestData_Configuration.xls";
		
		Reference_sheet = "Reference";
		BC_Sheet = "Browser_Configuration";
		TC_Sheet = "TestCase_Configuration";
		
		UPM_Configuration_Library.Create_New_Instance = true;
		
		Separate_reports = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel, Reference_sheet,3,3);
		System.out.println(Separate_reports);
		Browser_Config_Row_Cnt = UPM_Configuration_Library.Get_Sheet_Row_Count(TD_Config_Excel,BC_Sheet);
		UPM_Report_Library.Get_UPM_Report_Library_Instance();
		for (iterator = 1; iterator <= (Browser_Config_Row_Cnt); iterator++) 
		{
			UPM_Configuration_Library.Create_New_Instance = false;
			Str_OS = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel,BC_Sheet,iterator,1);
			Str_Browser = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel,BC_Sheet,iterator, 2);
			Str_Version = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel,BC_Sheet,iterator, 3);
			Str_Profile_Path = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel,BC_Sheet,iterator, 4);
			Str_ExecutionStatus = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel,BC_Sheet,iterator, 5);
			
			if (Str_ExecutionStatus.trim().toLowerCase().equals("yes"))
			{
				UPM_Configuration_Library.Create_New_Instance = false;
				
				//Reading from Execute_from text file
				
				        try {
				            FileReader reader = new FileReader(UPM_Configuration_Library.GetPath()+"/Execute_from.txt");
				            int character;
				 
				            while ((character = reader.read()) != -1) 
				            	{
				            	ALM_status=ALM_status+(char)character;
				                System.out.print((char) character);
				            	}
				            reader.close();
		
				       
				            if(ALM_status.toString().equalsIgnoreCase("ALM"))
				            {
				            	
				            	FileReader TC_reader = new FileReader(UPM_Configuration_Library.GetPath()+"/TC_NAME.txt");
					            int character2;
					 
					            while ((character2 = TC_reader.read()) != -1) 
					            	{
					            	Str_TC_Name=Str_TC_Name+(char)character2;
					            	}
					            	TC_reader.close();
					            	System.out.print(Str_TC_Name);
				            		UPM_Report_Library.Start_Report(Str_TC_Name, "");
				            		Execution(TD_Config_Excel,Str_TC_Name);
				            		UPM_Report_Library.End_Test();
				            		UPM_Function_Library.ObjDriver.quit();
				            		UPM_Report_Library.End_Report();
				            		//UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Str_TC_Name,"","");									
				            		TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
				            	
				            }
				            	
				            else
				            	{
				            		UPM_Configuration_Library.Create_New_Instance = false;
				      				TC_Config_Row_Cnt = UPM_Configuration_Library.Get_Sheet_Row_Count(TD_Config_Excel, TC_Sheet);
				      				for (iterator1 = 1; iterator1 <= (TC_Config_Row_Cnt); iterator1++) 
				      				{
				      					if(Separate_reports.equalsIgnoreCase("yes"))
				      					{
				      						UPM_Report_Library.Get_UPM_Report_Library_Instance();
				      					}
				      					UPM_Configuration_Library.Create_New_Instance = false;
				      					Str_TC_Name = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel, TC_Sheet,iterator1, 0);
				      					Str_TC_ExecutionStatus = UPM_Configuration_Library.GetCell_Value(TD_Config_Excel, TC_Sheet,iterator1, 1);
				      					if (Str_TC_ExecutionStatus.trim().toLowerCase().equals("yes"))
				      					{
				      						UPM_Report_Library.Start_Report(Str_TC_Name, "");
				      						Execution(TD_Config_Excel,Str_TC_Name);
				      						if(TS_Iterator<=Row_Cnt && Flag==false)
								        	{
				      							Flag=true;
								        		File gb_Obj_directory = new File(".");
								        		String Project_path = gb_Obj_directory.getCanonicalPath();	
								        		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
								                writer1.write("");
								                writer1.write("fail");
								                writer1.close();
								                UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail, Step Count: "+TS_Iterator+"\n"+Keyword+"\n"+Description , LogStatus.FAIL,true);
								                System.out.println(Keyword+","+Description);
								        		
								        	}
				      						UPM_Report_Library.End_Test();
					      						UPM_Function_Library.ObjDriver.quit();
				      						UPM_Report_Library.End_Report();
				      						
				      						//UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Str_TC_Name,"","");
				      						
				      					}
				      					
				      				}
				      				
				      				TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
				            	  }				            	
				           
				        	} 
				        	catch (Exception e)
				        	{
				        		e.printStackTrace();
				        		UPM_Function_Library.Exception_Content();
				        		if(UPM_Report_Library.Error_Flag)
				        		{
				        			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail: \n"+e , LogStatus.FAIL,false);			
				        		}
				        		else if(TS_Iterator<=Row_Cnt && Flag==false)
					        	{
					        		File gb_Obj_directory = new File(".");
					        		String Project_path = gb_Obj_directory.getCanonicalPath();	
					        		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
					                writer1.write("");
					                writer1.write("fail");
					                writer1.close();
					                UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail, Step Count: "+TS_Iterator+"\n"+Keyword+"\n"+Description , LogStatus.FAIL,true);
					                System.out.println(Keyword+","+Description);
					        		
					        	}
				        		//if does'nt work then delete the below Lines
				        		UPM_Report_Library.End_Test();
	      						UPM_Function_Library.ObjDriver.quit();
	      						UPM_Report_Library.End_Report();
	      						TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
				        	}
				}
		}
}	
	
	
	
	public static void Execution(String TestData_Excel,String TC_Name) throws Exception
	{
		
	try
	{
	String Sheet_Name = null;
	Sheet_Name = UPM_Configuration_Library.Get_SheetName_By_Header(TestData_Excel,TC_Name);
	System.out.println(TC_Name);
	
	int ColumnIndex;
	
	String Locator = null;
	String[] Val;
	//String Val = null;
	UPM_Report_Library.Error_Flag = false;
	Row_Cnt = UPM_Configuration_Library.Get_Sheet_Row_Count(TestData_Excel,Sheet_Name);
	System.out.println(Row_Cnt);
	ColumnIndex = UPM_Configuration_Library.Get_ColumnIndex_By_Header(TestData_Excel,Sheet_Name,TC_Name);
	System.out.println(ColumnIndex);
	
	for(TS_Iterator = 1;TS_Iterator<=Row_Cnt;TS_Iterator++)
	{
		Keyword = UPM_Configuration_Library.GetCell_Value(TestData_Excel,Sheet_Name,TS_Iterator, 0);
		Description = UPM_Configuration_Library.GetCell_Value(TestData_Excel,Sheet_Name,TS_Iterator, 1);
		Locator = UPM_Configuration_Library.GetCell_Value(TestData_Excel,Sheet_Name,TS_Iterator, 2);
		Val = UPM_Configuration_Library.GetCell_Value(TestData_Excel,Sheet_Name,TS_Iterator, ColumnIndex).split("->");
		int Val_Size;
		String Actual_Val;;
		Val_Size = Val.length - 1;
		if(Val_Size>0)
		{
			Actual_Val = Val[Iterate];  //maximum integer value supported:2147483647
		}
		else
		{
			Actual_Val = Val[0];
		}
		if((Keyword.matches("Get_Iterator")) || (Keyword.matches("Set_Iterator")))
		{	
			if(Keyword.matches("Get_Iterator"))
			{	
				if(!Actual_Val.matches("NA"))
				{
					UPM_Configuration_Library.Get_Iterator(TS_Iterator);
					Iterate = 0;
					Set_Iterator_Flg = true;
				}
			}
			if(Keyword.matches("Set_Iterator"))
			{	
				if(!Actual_Val.matches("NA"))
				{
				
					if(Iterate == Integer.parseInt(Actual_Val))
					{
						//TS_Iterator = UPM_Configuration_Library.Set_Iterator();
						Set_Iterator_Flg = false;
						
					}
					else
					{
						if(Set_Iterator_Flg)
						{
							TS_Iterator = UPM_Configuration_Library.Set_Iterator();							
							Iterate = Iterate + 1;
						}
					}
				}
				
			}
		}
		else
		{
			
			if(!Keyword.isEmpty() &&  !Locator.isEmpty() && !Actual_Val.isEmpty())
			{				
				/*if(UPM_Report_Library.Error_Flag)
				{
					break;
				}*/
				UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Keyword,Locator,Actual_Val.toString() ,Description);
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			}
			if(!Keyword.isEmpty() &&  !Locator.isEmpty() && Actual_Val.isEmpty())
			{
				
			/*	if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			*/	
				UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Keyword,Locator,Description);
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			}
			if(!Keyword.isEmpty() &&  Locator.isEmpty() && !Actual_Val.isEmpty())
			{
			/*	
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			*/
				UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Keyword,Actual_Val,Description);
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			}
			if(!Keyword.isEmpty() &&  Locator.isEmpty() && Actual_Val.isEmpty())
			{
			/*	
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			*/	
				UPM_TestCase_Library.Get_TestCase_Instance().Execute_TC(Keyword,Description);
				if(UPM_Report_Library.Error_Flag)
				{
					break;
				}
			}
			
			//updating the status to Execute_from.txt file for reporting
			
		}
		
	}
	if(UPM_Report_Library.Error_Flag)
	{
		Flag=true;
		File gb_Obj_directory = new File(".");
		String Project_path = gb_Obj_directory.getCanonicalPath();	
		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
        writer1.write("");
        writer1.write("fail");
        writer1.close();
        UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail, Step Count: "+TS_Iterator+"\n"+Keyword+"\n"+Description , LogStatus.FAIL,true);
        System.out.println(Keyword+","+Description);
	}
	else if(TS_Iterator<=Row_Cnt)
	{
		Flag=true;
		File gb_Obj_directory = new File(".");
		String Project_path = gb_Obj_directory.getCanonicalPath();	
		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
        writer1.write("");
        writer1.write("fail");
        writer1.close();
        UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail, Step Count: "+TS_Iterator+"\n"+Keyword+"\n"+Description , LogStatus.FAIL,true);
        System.out.println(Keyword+","+Description);
		
	}
	else
	{
		File gb_Obj_directory = new File(".");
		String Project_path = gb_Obj_directory.getCanonicalPath();	
		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
        writer1.write("");
        writer1.write("Pass");
        writer1.close();	
	}
	}
	
	catch(Exception e)
	{
	if(UPM_Report_Library.Error_Flag)
	{
		Flag=true;
		File gb_Obj_directory = new File(".");
		String Project_path = gb_Obj_directory.getCanonicalPath();	
		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
        writer1.write("");
        writer1.write("fail");
        writer1.close();     
        System.out.println(Keyword+","+Description);        
        UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail: \n"+Keyword+"\n"+Description+"\n"+e , LogStatus.FAIL,false);		
		
	}
	
	else if(TS_Iterator<=Row_Cnt)
	{
		Flag=true;
		File gb_Obj_directory = new File(".");
		String Project_path = gb_Obj_directory.getCanonicalPath();	
		FileWriter writer1 = new FileWriter(Project_path+"/Execute_from.txt");
        writer1.write("");
        writer1.write("fail");
        writer1.close();
        UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail, Step Count: "+TS_Iterator+"\n"+Keyword+"\n"+Description+"Exception:" +e, LogStatus.FAIL,true);
        System.out.println(Keyword+","+Description);
		
	}
	
	System.out.println(e);
	}
	
   }
}
