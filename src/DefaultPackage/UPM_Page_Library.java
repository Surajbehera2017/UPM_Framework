package DefaultPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class UPM_Page_Library extends UPM_Object_Mapping_Library{
	
	//@@@@@@@@@@@Singleton Implementation@@@@@@@@@@@@@@@@@@
	public static UPM_Page_Library UPM_Page_Instance;
	public String Parent = null;
	UPM_Page_Library()
	{
		
	}
	public static UPM_Page_Library Get_UPM_Page_Instance()
	{
		if(UPM_Page_Instance == null)
		{
			return UPM_Page_Instance = new UPM_Page_Library();
		}
		else
		{
			return UPM_Page_Instance;
		}
	}
	//@@@@@@@@@@@````````````````````````@@@@@@@@@@@@@@@@@@
	
	public static void drunken_function(String Credentials,String Desc) throws Exception
	{
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:btnLoadALL']", "1000", "Click Weitere Treffer button");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='promptForLoadAll_Form:promptForLoadAll_02']", "1000", "Click Übernehmen button");
		int row_cnt = UPM_Function_Library.UPM_Get_WebTable_Row_Count_desc("xpath=//tbody[@id='upmstart1:partner_datatable:tb']", "yes", "Suchergebnis");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:btnDruckenPartnerDet']", "1000", "Click Drucken button");
		//UPM_Function_Library.Activate_Browsers();
		//UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//a[contains(@id,'menu_form_id:m1')]/span", "1000", "Click Konfiguration button");
		int row_cnt2 = UPM_Function_Library.UPM_Get_WebTable_Row_Count_desc("xpath=//tbody[@id='PrintPartnerSearchResult:partner_datatable:tb']", "yes", "Partner Search Results Table");
		if(row_cnt==row_cnt2)
		{			
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "The row count of the table: Partner Search Results Table and Suchergebnis table are same", LogStatus.PASS, true);			
		}
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='PrintPartnerSearchResult:print_closePrintId']", "1000", "Click Schließen button");
			
	}
		
	public static void UPM_Login(String Credentials,String Desc) throws Exception
	{
		
		//http://upmsit2.dls.deutsche-leasing.de:81/AS/manual-login-upm;USER=UPM_R1;PASSWORD=Upmsit2014;loginbox_submit

		String[] Credentials_Details = Credentials.split(";");
		String[] Locator_User = Credentials_Details[1].split("=");
		String[] Locator_Pwd = Credentials_Details[2].split("=");
		//@@@@@@@@@@@@@@@@@@@@@@Variable Declaration@@@@@@@@@@@@@@@@@@@@@@@@@
		
		UPM_Function_Library.UPM_Launch_Browser("yes", "Launch Browser");
		UPM_Function_Library.UPM_NavigateTo(Credentials_Details[0],"Navigate to URL");
		UPM_Function_Library.Activate_Latest_Browser("yes","");
		
		UPM_Function_Library.ObjDriver.findElement(By.id(Locator_User[0])).sendKeys(Locator_User[1]);
		UPM_Function_Library.ObjDriver.findElement(By.id(Locator_Pwd[0])).sendKeys(Locator_Pwd[1]);
		UPM_Function_Library.ObjDriver.findElement(By.id(Credentials_Details[3])).click();
		
		//UPM_Function_Library.UPM_Set_Text("id="+Locator_User[0], Locator_User[1],"Enter the user id");
		//UPM_Function_Library.UPM_Set_Text("id="+Locator_Pwd[0], Locator_Pwd[1],"Enter the password");
		//UPM_Function_Library.UPM_Click_Object("id="+Credentials_Details[3],"yes","Click Login button");
		//UPM_Function_Library.Wait_For_Div();
		Thread.sleep(1000);
	}
	
	public static void UPM_Verify_Crefo_Search_Screen(String Event,String Desc) throws Exception
	{
		if(!Event.matches("NA"))
		{
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_FN_TXT_')]","yes","Verify FirstName TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_LN_TXT_')]","yes","Verify LastName TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_ST_TXT_')]","yes","Verify Street/House Nr TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_PLZ_TXT_')]","yes","Verify Zipcode TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_ORT_TXT_')]","yes","Verify City TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'CR_CRNR_TXT_')]","yes","Verify Crefo-Nr. TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebTable_Headers("xpath=//table[@id='form1:creFoDataList_']" ,"Crefo-Nr.;Firmenname1;Firmenname2;Strasse / Haus Nr.;PLZ;Ort;LKZ","Desc");
		}
	}
	
	public static void UPM_Verify_Default_Search_Screen(String Event,String Desc) throws Exception
	{
		if(!Event.matches("NA"))
		{
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_PANR_TXT')]","yes","Verify Partner TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_COD_TX')]","yes","Verify Matchcode TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_FN_TXT')]","yes","Verify Firma/Firstname TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_LN_TXT')]","yes","Verify Firma/Lastname TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_CREF_TXT')]","yes","Verify Crefo-Nr. TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_BCNR_TXT')]","yes","Verify Company-Nr TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_STR_TXT')]","yes","Verify Street,House-Nr TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_PLZ_TXT')]","yes","Verify Pincode, City TextBox Enabled");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_WERT_TXT')]","yes","Verify Value TextBox Enabled");
			UPM_Function_Library.UPM_Verify_DropDownList_Selected_Option("xpath=//*[contains(@id,'PAR_SER_SUC_PS_CBX')]","Beides(Aktiv/InAktiv)","Verify Status DropDownList Value");
			UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[contains(@id,'PAR_SER_SUC_ROLE_CBX')]","yes","Verify Role DropDownList enabled");
			UPM_Function_Library.UPM_Verify_DropDownList_Selected_Option("xpath=//*[contains(@id,'PAR_SER_SUC_INV')]","Bestand","Verify Datenbereich DropDownList Value");
			UPM_Function_Library.UPM_Verify_DropDownList_Selected_Option("xpath=//*[contains(@id,'PAR_SER_SPRACHE')]","Deutsch","Verify Language DropDownList Value");
		}
	}
	
	public static void UPM_private_prtnr_create(String prtnr_name,String Desc) throws Exception
	{
		if(!prtnr_name.matches("NA"))
		{
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:PAR_SER_SUC_FN_TXT']", prtnr_name,"Enter Firma/Vorname");
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:PAR_SER_SUC_LN_TXT']", "Test1","Enter Firma/Nachname");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:PAR_SEARCH_ID']", "1000", "Click Suchen button");	
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:PAR_SEARCH_ADD_ID']", "1000", "Click on Partner Erstellen button");
		UPM_Function_Library.UPM_Select_DropDownList("xpath=//*[@id='upmstart1:PAR_DET_DAT_PART_CBX2_0']", "privat", "Select specified option in Partnerart DropdownList");
		UPM_Function_Library.UPM_Select_DropDownList_ByValue("xpath=//*[@id='upmstart1:PAR_DET_DAT_LKZZ_CBX_0']", "DE", "Select specified option in LKZ DropdownList");
		UPM_Function_Library.UPM_Select_DropDownList("xpath=//*[@id='upmstart1:PAR_DET_DAT_ADT_CBX_0']", "Anschrift", "Select specified option in Adresstyp DropdownList");
		UPM_Function_Library.UPM_Set_CheckBox("xpath=//*[@id='upmstart1:PAR_DET_DAT_PF_CHBX_0']", "yes", "Check Postal.Firmensitz CheckBox");
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:id_Address_Feld1']", "Seilerstr.","Enter Straße");
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:id_Address_Feld2']", "22","Enter Haus Nr.");
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:id_Address_Feld5']", "20359","Enter PLZ");
		UPM_Function_Library.UPM_Set_Text("xpath=//*[@id='upmstart1:id_Address_Feld6']", "Hamburg","Enter Ort");
		UPM_Function_Library.UPM_Direct_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:aktualisierenaddressbuttonid')]", "500", "Click on Ubernehmen");
		UPM_Function_Library.Soft_Wait("500","Explicit wait");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'ROLEDETAILS_TAB_ID_')]", "1000", "Navigate to Rollen tab");
		UPM_Function_Library.UPM_Set_CheckBox("xpath=//*[contains(@id,'upmstart1:PAT_DET_ROL_CHK_')and contains(@name,'PAT_DET_ROL_CHK_')]", "yes", "Check Kunde CheckBox");
		UPM_Function_Library.Soft_Wait("500","Explicit wait");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:speichernbuttonid')]", "500", "Click on Speichern button");
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//td[contains(text(),'Details')]", "500", "Click on Details tab");
		
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//div[contains(text(),'Bankverbindungen')]", "500", "Click on the Bankverbindungen to expand the bank details menue");
		String value = "DE97760501010240122952->DE15663623450000014206";
		String[] data = value.split("->");
		
		
		for(int loop = 0;loop<70 ;loop++)
		{
			UPM_Function_Library.UPM_Set_Text("xpath=//input[contains(@id,'upmstart1:PAR_DET_DAT_IBAN')]", data[loop], "Enter the IBAN details");			
			if(UPM_Function_Library.ObjDriver.findElement(By.xpath("//div[@class='ErrorMessage']")).getText()!= null)
			{
				
				WebElement ele = UPM_Function_Library.ObjDriver.findElement(By.xpath("//div[@class='ErrorMessage']"));
				String error_message = ele.getText();
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Getting the following error message: "+error_message, LogStatus.INFO, true);
				
			}
			else
			{
				UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='iban_Modal_Form:ibnbic_Modal_BTN_Ok']", "500", "Click on OK");
				UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:aktualisierenbankbuttonid_0']", "500", "Click on Ubarnahmen button");		
				UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:speichernbuttonid')]", "500", "Click on Speichern button");
			}

		}
		
		UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:speichernbuttonid')]", "500", "Click on Speichern button");
		
		
		/*String value =UPM_Function_Library.UPM_Get_Text_Update("xpath=//div[@class='InfoMessage']","yes", "Get value");
		
		String val1 = value.substring(11, 18);
		
		UPM_Configuration_Library.Set_Active_Cell_Value_overwrite(1, val1);*/
		}
	}
	
	public static void UPM_dataentry(String value,String Desc) throws Exception
	{
		if(!value.matches("NA"))
		{
			//UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//div[contains(text(),'Bankverbindungen')]", "500", "Click on the Bankverbindungen to expand the bank details menue");
			//String value = "DE97760501010240122952->DE15663623450000014206";
			String[] data = value.split("->");
									
			for(int loop = 0;loop<2 ;loop++)
			{
				UPM_Function_Library.UPM_Set_Text("xpath=//input[contains(@id,'upmstart1:PAR_DET_DAT_IBAN')]", data[loop], "Enter the IBAN details");			
				if(UPM_Function_Library.ObjDriver.findElement(By.xpath("//div[@class='ErrorMessage']")).getText()!= null)
				{
					
					WebElement ele = UPM_Function_Library.ObjDriver.findElement(By.xpath("//div[@class='ErrorMessage']"));
					String error_message = ele.getText();
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "Getting the following error message: "+error_message, LogStatus.INFO, true);
					
				}
				else
				{
					UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='iban_Modal_Form:ibnbic_Modal_BTN_Ok']", "500", "Click on OK");
					UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[@id='upmstart1:aktualisierenbankbuttonid_0']", "500", "Click on Ubarnahmen button");		
					UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:speichernbuttonid')]", "500", "Click on Speichern button");
				}

			}			
			UPM_Function_Library.UPM_Click_Object_With_Wait("xpath=//*[contains(@id,'upmstart1:speichernbuttonid')]", "500", "Click on Speichern button");
		}
		
	}
	
	//Navigation : Konfiguration -> Partnerhierarchie -> Pflege Verknüpfungsart
//	public static void UPM_Pflege_Verknüpfungsart(String Property_Details,String Desc)
//	{
//		UPM_Function_Library.UPM_MouseOver(Element_Property, Event, Description);
//		int i;
//		String[] Property_Details_Array = Property_Details.split(";");
//		
//		for(i=1;i<=2;i++)
//		{
//			UPM_Function_Library.UPM_Click_Object(Object_Name_Pro, Event, Desc) 	AddButtonId
//		}
//		
//		
//		Soft_Wait
//		UPM_Select_DropDownList	xpath=//*[@id='form1:id_inTxt_01_OperationInputPanel']
//		UPM_Set_Text	xpath=//*[@id='form1:id_inTxt_02_OperationInputPanel']
//		UPM_Click_Object	xpath=//*[@id='form1:SaveButtonId']
//		UPM_Click_Object	AddButtonId
//		Soft_Wait
//		UPM_Select_DropDownList	xpath=//*[@id='form1:id_inTxt_01_OperationInputPanel']
//		UPM_Set_Text	xpath=//*[@id='form1:id_inTxt_02_OperationInputPanel']
//		UPM_Click_Object	xpath=//*[@id='form1:SaveButtonId']
//		//UPM_Function_Library.UPM_Click_Object(Object_Name_Pro, "yes", "Click on create new button");
//		
//	}
	
	//Navigation: 
	// Details = Strukturname;BuBa ID;BuBa Postal Code;BuBa City;BuBa Street;Struktur Valid From;Struktur Valid Till
	//Example : UPM_Details_Struktur("Test;123;;Bad Homberg;;;07/13/2016","Description")
	public static void UPM_Verify_All_Tabs_In_Partner_Screen(String Event, String Desc) throws Exception
	{
		if(!Event.matches("NA"))
		{
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_02_PARTNERALLDETAILS_TAB_0_lbl']","yes","Verify Details Tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_07_ROLEDETAILS_TAB_ID_0_lbl']","yes","Verify Rollen Tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_10_RATINGDETAILS_TAB_ID_0_lbl']","yes","Verify Rating tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_09_HIERARCHY_TAB_ID_0_lbl']","yes","Verify Hierarchie tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_47_ENGAGEMENTDETAILS_TAB_ID_0_lbl']","yes","Verify Engagement tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_77_HISTORY_TAB_ID_0_lbl']","yes","Verify Historie tab Enabled");
	
		}
	}
	
	public static void UPM_Verify_All_Tabs_Except_ROLE_RATING_ENGAGEMENT_In_Partner_Screen(String Event, String Desc) throws Exception
	{
		if(!Event.matches("NA"))
		{
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_02_PARTNERALLDETAILS_TAB_0_lbl']","yes","Verify Details Tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_09_HIERARCHY_TAB_ID_0_lbl']","yes","Verify Hierarchie tab Enabled");
		UPM_Function_Library.UPM_Verify_WebElement_Enabled("xpath=//*[@id='upmstart1:UPM_77_HISTORY_TAB_ID_0_lbl']","yes","Verify Historie tab Enabled");
	
		}
	}
	
	
}
