package DefaultPackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UPM_TestCase_Library extends UPM_Function_Library		//UPM_Object_Mapping_Library
{
	
	public static UPM_TestCase_Library TestCase_Library_Instance;
	UPM_TestCase_Library()
	{
		
	}
	
	public static UPM_TestCase_Library Get_TestCase_Instance()
	{
		if(TestCase_Library_Instance == null)
		{
			return TestCase_Library_Instance = new UPM_TestCase_Library();
		}
		else
		{
			return TestCase_Library_Instance;
		}
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public String TC_N;
	
	public void Execute_TC(String TC_Name,String Param1) throws NoSuchMethodException, 

	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		try
		{
			TC_N = TC_Name;
			Method Execute = getClass().getMethod(TC_Name,String.class);
			Execute.invoke(this,Param1);
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Execute_TC(String TC_Name,String Param1,String Param2) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		try
		{
			
			TC_N = TC_Name;
			Method Execute = getClass().getMethod(TC_Name,String.class,String.class);
			Execute.invoke(this,Param1,Param2);
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	public void Execute_TC(String TC_Name,String Param1,String Param2,String Param3) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		try 
		{
			
		
		TC_N = TC_Name;
		Method Execute = getClass().getMethod(TC_Name,String.class, String.class, String.class);
		
		Execute.invoke(this,Param1,Param2,Param3);
		} catch (Exception e) 
		{
	
		e.printStackTrace();
	}	
	}
	public void Execute_TC(String TC_Name,String Param1,String Param2,String Param3,String Param4) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
	try 
		{
		
		TC_N = TC_Name;
		Method Execute = getClass().getMethod(TC_Name,String.class, String.class, String.class,String.class);
		Execute.invoke(this,Param1,Param2,Param3,Param4);
		}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	

	//Start Region Test Cases
	
//	public void TC_001() throws Exception
//	{
//		
//		String TC= "TestData_Configuration.xls";
//		UPM_Configuration_Library.Create_New_Instance = true;
//		String UPM_First_Name_Val = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_First_Name_TxtBox");
//		UPM_Configuration_Library.Create_New_Instance = false;
//		String UPM_Language_Val = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Language_DrpDwn");
//		String UPM_Verify_Error_Val = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Partner_Search_Error_Lbl");
//		String UPM_Industry = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Industry_TxtBox");
//		String UPM_Legal_Form = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Legal_Form_TxtBox");
//		String UPM_Address_Type = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Address_Type_DrpDwn");
//		String UPM_Address_City = UPM_Configuration_Library.GetVal(TC, TC_N,"UPM_Address_City_TxtBox");
//		String UPM_Address_HouseNo = UPM_Configuration_Library.GetVal(TC, TC_N,"UPM_Address_HouseNo_TxtBox");
//		String UPM_Address_Street = UPM_Configuration_Library.GetVal(TC, TC_N,"UPM_Address_Street_TxtBox");
//		String UPM_Address_ZipCode = UPM_Configuration_Library.GetVal(TC, TC_N,"UPM_Address_ZipCode_TxtBox");
//		String UPM_IBAN = UPM_Configuration_Library.GetVal(TC, TC_N,"UPM_IBAN_TxtBox");
//		
//		UPM_Report_Library.Start_Report(TC_N, "Partner Creation");
//		UPM_Function_Library.UPM_Launch_Browser();
//		UPM_Page_Library.UPM_Login();
//		UPM_Function_Library.UPM_Set_Text(UPM_First_Name_TxtBox, UPM_First_Name_Val,"Search with the partner's first name");
//		UPM_Function_Library.UPM_Select_DropDownList(UPM_Language_DrpDwn, UPM_Language_Val,"Select the language");
//		Thread.sleep(1500);
//		//WebDriverWait wait = new WebDriverWait(UPM_Function_Library.ObjDriver, 1000);
//		//wait.until(ExpectedConditions.elementToBeClickable(UPM_Function_Library.ObjDriver.findElement(By.name(UPM_Find_Btn)));
//		UPM_Function_Library.UPM_Click_Object(UPM_Find_Btn,"Click on find button");
//		Thread.sleep(2000);
//		UPM_Function_Library.UPM_Verify_WebElement_Actual(UPM_Partner_Search_Error_Lbl,"Verify Error Message", UPM_Verify_Error_Val);
//		UPM_Function_Library.UPM_Click_Object(UPM_Create_Prospect_Btn,"Click on Create Prospect button to create a new partner");
//		UPM_Function_Library.UPM_Set_Text(UPM_Industry_TxtBox, UPM_Industry,"Enter the industry");
//		UPM_Function_Library.UPM_Set_Text(UPM_Legal_Form_TxtBox, UPM_Legal_Form,"Enter the legal form");
//		UPM_Function_Library.UPM_Select_DropDownList(UPM_Address_Type_DrpDwn, UPM_Address_Type,"Select the address type");
//		UPM_Function_Library.UPM_Set_Text(UPM_Address_City_TxtBox , UPM_Address_City,"Enter the city");
//		UPM_Function_Library.UPM_Set_Text(UPM_Address_HouseNo_TxtBox, UPM_Address_HouseNo,"Enter the house number");
//		UPM_Function_Library.UPM_Set_Text(UPM_Address_Street_TxtBox, UPM_Address_Street,"Enter the street address");
//		UPM_Function_Library.UPM_Set_Text(UPM_Address_ZipCode_TxtBox, UPM_Address_ZipCode,"Enter the zip code");
//		UPM_Function_Library.UPM_Set_CheckBox(UPM_Postal_Firmensitz_ChkBox, true, "Check Postal Firmensitz CheckBox to select it as the main address");
//		UPM_Function_Library.UPM_Click_Object(UPM_Address_Apply_Btn, "Click on Apply button");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Bank_Details_Lbl, "Expand bank details panel");
//		UPM_Function_Library.UPM_Set_Text(UPM_IBAN_TxtBox, UPM_IBAN,"Enter the IBAN");
//		Thread.sleep(2000);
//		UPM_Function_Library.UPM_Click_Object(UPM_Pop_OK_Btn, "Click on OK button");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Rollen_Tab, "Goto Rollen tab");
//		UPM_Function_Library.UPM_Set_CheckBox(UPM_Rollenzuordnn_UCS_Kunde_ChkBox, true, "Check the UCS-Partner Kunde CheckBox");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Hierarchie_Tab, "Goto Hierarchie Tab");
//		UPM_Function_Library.UPM_Verify_WebElement_ReadOnly(UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen_DrpDwn, "Verify whether Struktur Anzeigen DropDown is readOnly");
//		UPM_Function_Library.UPM_Verify_WebElement_Disabled(UPM_Hierarchie_Partnerstruktur_Display_Btn, "Verify whether Display button is disabled");
//		UPM_Function_Library.UPM_Click_Object(UPM_Save_Btn, "Click on save button");
//		Thread.sleep(3500);
//		UPM_Function_Library.Close_All_Active_Browser();
//		UPM_Report_Library.End_Test();
//	}
//	
//	public void TC_002() throws Exception
//	{
//		String TC= "TestData_Configuration.xls";
//		UPM_Configuration_Library.Create_New_Instance = true;
//		String UPM_PartnerNr = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_PartnerNr_TxtBox");
//		UPM_Configuration_Library.Create_New_Instance = false;
//		String UPM_Language = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Language_DrpDwn");
//		String UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen_DrpDwn");
//		String UPM_Popup_Details_Struktur_Verify_Error_Val = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Popup_Details_Struktur_Verify_Error_Lbl");
//		String UPM_Popup_Details_Struktur_Strukturename_Val = UPM_Configuration_Library.GetVal(TC, TC_N, "UPM_Popup_Details_Struktur_Strukturename_TxtBox");
//		
//		UPM_Report_Library.Start_Report("TC_002", "Named Structure");
//		UPM_Function_Library.UPM_Launch_Browser();
//		UPM_Page_Library.UPM_Login();
//		Thread.sleep(3000);
//		UPM_Function_Library.UPM_Select_DropDownList(UPM_Language_DrpDwn, UPM_Language, "Select the language");
//		Thread.sleep(1000);
//		UPM_Function_Library.UPM_Set_Text(UPM_PartnerNr_TxtBox, UPM_PartnerNr, "Enter an existing partner number");
//		UPM_Function_Library.UPM_Click_Object(UPM_Find_Btn, "Click on Find button to open the partner details");
//		Thread.sleep(3500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Hierarchie_Tab, "Navigate to Hierarchy tab");
//		UPM_Function_Library.UPM_Select_DropDownList(UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen_DrpDwn, UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen, "Select the named Structure type from 'Struktur Anzeigen' drop down");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Hierarchie_Partnerstruktur_Plus_Btn, "Click on the "+" button to create substructure");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Popup_Details_Struktur_OK_Btn, "Click on 'OK' in the pop-up screen without entering any details");
//		Thread.sleep(1500);
//		//UPM_Function_Library.UPM_Verify_WebElement_Actual(UPM_Popup_Details_Struktur_Verify_Error_Lbl, "Verify the error message",UPM_Popup_Details_Struktur_Verify_Error_Val);
//		UPM_Function_Library.UPM_Set_Text(UPM_Popup_Details_Struktur_Strukturename_TxtBox, UPM_Popup_Details_Struktur_Strukturename_Val, "Enter the name of new structure");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Popup_Details_Struktur_OK_Btn, "Click on 'OK' button");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Popup_Details_Struktur_Cancel_Btn, "Click button 'cancel' to close the Struktur window");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Click_Object(UPM_Hierarchie_Partnerstruktur_Table_Plus_Btn, "Click '+' button to link partner in tree structure");
//		Thread.sleep(1500);
//		UPM_Function_Library.UPM_Set_Text(UPM_PartnerNr_TxtBox, UPM_PartnerNr, "Enter the existing partner number to link it as the Parent partner");
//		UPM_Function_Library.UPM_Click_Object(UPM_Find_Btn, "Click on Find button to open the partner details");
//		
//		//for(int i = 1;i<=5;i++)
//		//{
//			UPM_Function_Library.UPM_Click_Object(UPM_Hierarchie_Partnerstruktur_Table_Child_Plus_Btn, "Click on "+" button to add child node");
//			UPM_Function_Library.UPM_Set_Text(UPM_PartnerNr_TxtBox, UPM_PartnerNr, "Enter a child partner");
//			UPM_Function_Library.UPM_Click_Object(UPM_Find_Btn, "Click on Find button to open the partner details");
//			
//		//}

		
//		
////		UPM_Function_Library.Close_All_Active_Browser();
////		UPM_Report_Library.End_Test();
//	}
	
	public void TC_003() throws Exception
	{	
		UPM_Report_Library.Start_Report("TC_003", "Sample Test 003");
		UPM_Function_Library.UPM_Launch_Browser("","");
		UPM_Page_Library.UPM_Login("","");
		UPM_Function_Library.Close_All_Active_Browser();
		UPM_Report_Library.End_Test();
	}
	
	public void TC_004() throws Exception
	{
		UPM_Report_Library.Start_Report("TC_004", "Sample Test 004");
		UPM_Function_Library.UPM_Launch_Browser("","");
		UPM_Page_Library.UPM_Login("","");
		UPM_Function_Library.Close_All_Active_Browser();
		UPM_Report_Library.End_Test();
	}
	
	public void TC_005() throws Exception
	{
		UPM_Report_Library.Start_Report("TC_005", "Sample Test 005");
		UPM_Function_Library.UPM_Launch_Browser("","");
		UPM_Page_Library.UPM_Login("","");
		UPM_Function_Library.Close_All_Active_Browser();
		UPM_Report_Library.End_Test();
	}
	
	public void TC_006() throws Exception
	{
		UPM_Report_Library.Start_Report("TC_006", "Sample Test 006");
		UPM_Function_Library.UPM_Launch_Browser("","");
		UPM_Page_Library.UPM_Login("","");
		UPM_Function_Library.Close_All_Active_Browser();
		UPM_Report_Library.End_Test();
	}
	//End Region

	
}
