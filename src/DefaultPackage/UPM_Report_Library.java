package DefaultPackage; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.LocalDateTime;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UPM_Report_Library {
	public static ExtentReports ExtendReport_Instance;
	public static ExtentTest ExtTest_Instance;
	public static int Test_Step_Number;
	public static String Report_Base_Path;
	public static String Report_Suite_Path;
	public static String Report_Suite_HTML_Path;
	public static String Report_Suite_HTMLs_Path;
	public static String Report_Suite_SS_Path;
	public static String Report_Suite_SS_F_Path;
	public static UPM_Report_Library UPM_Report_Library_Instance;
	public static String SS_Folder = null;
	public static Boolean Error_Flag;
	String path;
	UPM_Report_Library()
	{
		
	}
	
	public static UPM_Report_Library Get_UPM_Report_Library_Instance()
	{
		
		if(UPM_Report_Library_Instance == null)
		{
			return UPM_Report_Library_Instance = new UPM_Report_Library();
		}
		else
		{
			return UPM_Report_Library_Instance;
		}
	}

	public static ExtentReports Get_Extends(String Test_Name) throws Exception
	{
		if (ExtendReport_Instance == null)
		{
			Report_Base_Path = UPM_Configuration_Library.GetPath() + "//Report";
			UPM_Configuration_Library.Check_Create_Directory(Report_Base_Path);
			Report_Suite_Path = Report_Base_Path + "//UPM_Suite"; 
			UPM_Configuration_Library.Check_Create_Directory(Report_Suite_Path);
			Report_Suite_HTML_Path = Report_Suite_Path + "//UPM_Suite_HTML";
			UPM_Configuration_Library.Check_Create_Directory(Report_Suite_HTML_Path);
			//Report_Suite_HTMLs_Path = Report_Suite_HTML_Path + "//UPM_Suite_HTML_"+Test_Name+"_"+UPM_Function_Library.Get_UPM_Function_Instance().Get_TimeStamp();
			Report_Suite_HTMLs_Path = Report_Suite_HTML_Path + "//UPM_Suite_HTML_"+Test_Name;
			Report_Suite_SS_F_Path = Report_Suite_Path + "//UPM_Suite_SS"; 
			UPM_Configuration_Library.Check_Create_Directory(Report_Suite_SS_F_Path);
			ExtendReport_Instance = new ExtentReports(Report_Suite_HTMLs_Path + ".html",true);
			ExtendReport_Instance.addSystemInfo(Driver_Script.Str_Browser,Driver_Script.Str_Version).
								  addSystemInfo("Selenium Version", "2.48.2");
			ExtendReport_Instance.config().documentTitle("UPM TEST AUTOMATION REPORT").reportName("UNIFIED PARTNER").reportHeadline("MANAGEMENT TEST AUTOMATION ANALYSIS");
			Error_Flag = false;
		}
		else
		{
			return ExtendReport_Instance;
		}
		return ExtendReport_Instance;
	}
	
	public static ExtentTest Start_Report(String Test_Name,String Test_Description) throws Exception
	{
		//if (ExtTest_Instance == null)
		//{
			//SS_Folder = Report_Suite_SS_Path+"/" + UPM_Function_Library.Get_UPM_Function_Instance()
			//.Get_TimeStamp();
			Test_Step_Number = 1;
			ExtTest_Instance = Get_Extends(Test_Name).startTest(Test_Name, Test_Description);
	//	}
		return ExtTest_Instance;
	}
	
	public static ExtentTest Get_Report()
	{
		return ExtTest_Instance;
	}
	
	public static void Add_Step(int Step, String Description, LogStatus Status, boolean ScreenShot) throws Exception
	{
		String StepName = "Step " + Step ; 
		if(ScreenShot)
		{	
			//For KNE Execution-Delete after
			//String path = Report_Suite_SS_F_Path + "//UPM_Suite_SS_" +UPM_Function_Library.Get_UPM_Function_Instance().Get_TimeStamp()+".png";
			
			LocalDateTime now = new LocalDateTime();
			int day = now.getDayOfMonth();
			int mon = now.getMonthOfYear();
			int year = now.getYear();
			//System.out.println(day+"-"+mon+"-"+year);
			String date = day+"-"+mon+"-"+year;
			
			File file1 = new File("\\"+"\\EBii339F0\\19652_ShareFolder\\UPM_Execution_Status_Do_Not_Delete\\"+date);
			
			if(!file1.exists())
			{
			file1.mkdir();
			}
			
			String path="\\"+"\\EBii339F0\\19652_ShareFolder\\UPM_Execution_Status_Do_Not_Delete\\"+date+"\\"+UPM_Function_Library.Get_UPM_Function_Instance().Get_TimeStamp()+".png";
			UPM_Function_Library.UPM_Capture_Screen_Shot(path);
			
			Get_Report().log(Status, StepName,Description + Get_Report().addScreenCapture(path));

		}
		else
		{
			Get_Report().log(Status, StepName, Description);
		}
		Test_Step_Number = Test_Step_Number + 1;
	}
	
	public static void End_Test() throws Exception
	{
	/*	if(UPM_Report_Library.Error_Flag)
		{
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Fail" , LogStatus.FAIL,false)	;			
		}*/
		
		Test_Step_Number = 0;
		ExtendReport_Instance.endTest(ExtTest_Instance);
		
	}
	public static void End_Report() throws Exception
	{	
		if(Driver_Script.Separate_reports.equalsIgnoreCase("yes"))
		{
			ExtendReport_Instance.flush();
			UPM_Report_Library_Instance = null;	
			//ExtendReport_Instance.close();
		}
		else
		{
			ExtendReport_Instance.flush();
		}
	
		try {
	
			File gb_Obj_directory = new File(".");
			String Project_path = gb_Obj_directory.getCanonicalPath();	
            FileWriter writer = new FileWriter(Project_path+"/Result_path.txt");
            writer.write("");
            String path=Report_Suite_HTMLs_Path.toString();
            String path2;
            path2=path.substring(62);
            System.out.println(path2);
            writer.write(path2);
            writer.close();
            
   
            
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
}
