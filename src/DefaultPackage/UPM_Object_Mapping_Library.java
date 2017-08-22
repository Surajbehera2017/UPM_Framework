package DefaultPackage;

public class UPM_Object_Mapping_Library {
	

public static UPM_Object_Mapping_Library UPM_Object_Mapping_Library_Instance;
UPM_Object_Mapping_Library()
{
	
}
public static UPM_Object_Mapping_Library Get_UPM_Object_Mapping_Instance()
{
	if(UPM_Object_Mapping_Library_Instance == null)
	{
		UPM_Object_Mapping_Library_Instance = new UPM_Object_Mapping_Library();
	}
	return UPM_Object_Mapping_Library_Instance;
}
//Page Objects Declarations
	public static String UPM_URL;
	public static String UPM_UserName_TxtBox = "id=USER";
	public static String UPM_Password_TxtBox ="id=PASSWORD";
	public static String UPM_Login_Btn ="id=loginbox_submit";
	public static String UPM_PartnerNr_TxtBox = "xpath=//*[contains(@id,'PAR_SER_SUC_PANR_TXT')]";
	public static String UPM_First_Name_TxtBox ="xpath=//*[contains(@id,'PAR_SER_SUC_FN_TXT')]";
	public static String UPM_Language_DrpDwn ="xpath=//*[contains(@id,'PAR_SER_SPRACHE')]";		
	public static String UPM_Find_Btn ="xpath=//*[contains(@id,'PAR_SEARCH_ID')]";
	public static String UPM_Partner_Search_Error_Lbl ="xpath=//*[@id='upmstart1:j_id57']/dl/dt/span/div[1]";
	public static String UPM_Create_Prospect_Btn ="xpath=//*[contains](@id),'PAR_SEARCH_ADD_ID'";
	public static String UPM_Industry_TxtBox ="xpath=//*[contains(@id,'PAR_DET_DAT_INDU_TXT_0')]";
	public static String UPM_Legal_Form_TxtBox ="xpath=//*[contains(@id,'PAR_DET_DAT_LFC_TXT_0')]";
	public static String UPM_Address_Type_DrpDwn ="xpath=//*[contains(@id,'PAR_DET_DAT_ADT_CBX_0')]";
	public static String UPM_Address_Street_TxtBox ="xpath=//*[contains(@id,'id_Address_Feld1')]";
	public static String UPM_Address_HouseNo_TxtBox ="xpath=//*[contains(@id,'id_Address_Feld2')]";
	public static String UPM_Address_ZipCode_TxtBox ="xpath=//*[contains(@id,'id_Address_Feld5')]";
	public static String UPM_Address_City_TxtBox ="xpath=//*[contains(@id,'id_Address_Feld6')]";
	public static String UPM_Postal_Firmensitz_ChkBox ="xpath=//*[contains(@id,'PAR_DET_DAT_PF_CHBX_0')]";
	public static String UPM_Address_Apply_Btn = "xpath=//*[contains(@id,'aktualisierenaddressbuttonid_0')]";
	public static String UPM_Bank_Details_Lbl ="xpath=//*[contains(@id,'Panel_Details_Bankvenbindungen_div_0_header')]";
	public static String UPM_IBAN_TxtBox ="xpath=//*[contains(@id,'PAR_DET_DAT_IBAN_TXT_0')]";
	public static String UPM_Pop_OK_Btn ="xpath=//*[contains(@id,'ibnbic_Modal_BTN_Ok')]";
	public static String UPM_Rollen_Tab ="id=upmstart1:UPM_07_ROLEDETAILS_TAB_ID_0_lbl')]";
	public static String UPM_Rollenzuordnn_UCS_Kunde_ChkBox ="id=upmstart1:PAT_DET_ROL_CHK_0:0')]";
	public static String UPM_Rollenzuordnn_UCS_Dienstleister_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:1')]";
	public static String UPM_Rollenzuordnn_UCS_Hersteller_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:2')]";
	public static String UPM_Rollenzuordnn_UCS_UCSPartner_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:3')]";
	public static String UPM_Rollenzuordnn_UCS_Lieferant_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:4')]";
	public static String UPM_Rollenzuordnn_UCS_Finanzierungspartner_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:5')]";
	public static String UPM_Rollenzuordnn_UCS_Objektnutzer_ChkBox ="xpath=//*[contains(@id,'PAT_DET_ROL_CHK_0:6')]";
	public static String UPM_Hierarchie_Tab = "xpath=//*[contains(@id,'HIERARCHY_TAB')]";
	public static String UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen_DrpDwn ="xpath=//*[contains(@id,'STRUCT_TYP')]";
	public static String UPM_Hierarchie_Partnerstruktur_Struktur_Anzeigen_DrpDwn2 = "xpath=//*[contains(@id,'SUB_STRUCT_TYP')]";
	public static String UPM_Hierarchie_Partnerstruktur_Display_Btn ="xpath=//*[contains(@id,'ANZ_BTN')]";
	public static String UPM_Hierarchie_Partnerstruktur_Plus_Btn = "xpath=//*[contains(@id,'addStruct_enabled')]";	
	public static String UPM_Hierarchie_Partnerstruktur_Find_Btn = "xpath=//*[contains(@id,'SRCH_STRUCT')]";	
	public static String UPM_Hierarchie_Partnerstruktur_PlusPlus_Btn = "xpath=//*[contains(@id,'Erweitern_STRUCT')]";	
	public static String UPM_Hierarchie_Partnerstruktur_Table_Plus_Btn = "xpath=//*[contains(@id,'addNewNode_enabled')]";	
	public static String UPM_Hierarchie_Partnerstruktur_Table_Child_Plus_Btn = "xpath=//*[contains(@id,'PAR_KONZ_TREE_ADD')][position()=i]";//"xpath=//*[contains(@id,'TREE_ADD')]";
	public static String UPM_Hierarchie_Partnerstruktur_Table_Child_Plus_Popup_Child_RadioBtn = "xpath=//*[@id='addPrtOrChild_Form:j_id1084:0']";
	public static String UPM_Hierarchie_Partnerstruktur_Table_Child_Plus_Popup_Parent_RadioBtn = "xpath=//*[@id='addPrtOrChild_Form:j_id1084:1']";
	public static String UPM_Popup_Details_Struktur_StruktureID_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_STRUID_TXT')]";	
	public static String UPM_Popup_Details_Struktur_Strukturename_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_STRUNAME_TXT')]";	
	public static String UPM_Popup_Details_Struktur_BuBa_ID_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_BBID_TXT')]";	
	public static String UPM_Popup_Details_Struktur_BuBa_PostalCode_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_BPLZ_TXT')]";
	public static String UPM_Popup_Details_Struktur_BuBa_City_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_BBO_TXT')]";	
	public static String UPM_Popup_Details_Struktur_BuBa_Street_DrpDwn = "xpath=//*[contains(@id,'DET_SUBSTRUCT_BBS_TXT')]";
	public static String UPM_Popup_Details_Struktur_BuBa_Bundesland_DrpDwn = "xpath=//*[contains(@id,'DET_SUBSTRUCT_BBU_TXT')]";
	public static String UPM_Popup_Details_Struktur_Valid_From_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_FRM_DATEInputDate')]";	
	public static String UPM_Popup_Details_Struktur_Valid_Till_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_TO_DATEInputDate')]";	
	public static String UPM_Popup_Details_Struktur_Note_TxtBox = "xpath=//*[contains(@id,'DET_SUBSTRUCT_TO_DATEInputDate')]";	
	public static String UPM_Popup_Details_Struktur_OK_Btn = "xpath=//*[contains(@id,'DET_SUBSTRUCT_SAV_BTN')]";	
	public static String UPM_Popup_Details_Struktur_Cancel_Btn = "xpath=//*[contains(@id,'DET_SUBSTRUCT_SCH_BTN')]";	
	public static String UPM_Popup_Details_Struktur_Modify_Btn = "xpath=//*[contains(@id,'DET_AND_BTN')]";	
	public static String UPM_Popup_Details_Struktur_Delete_Btn = "xpath=//*[contains(@id,'DET_LOS_BTN')]";
	//public static String UPM_Popup_Details_Struktur_Verify_Error_Lbl = "class=ErrorMessage";
	public static String UPM_Popup_Details_Struktur_Verify_Error_Lbl ="xpath=//*[@id='addSubStructTypeLazyModalContainer:AddSubStruct:j_id[0-9]{4}']/dl/dt/span/div[1]";
	public static String UPM_Popup_Delete_Warning_ChildPartner_Yes_Btn = "id=Delete_ChildPartner_Form:DEL_CHILD_PARTNR";

	public static String UPM_Save_Btn ="xpath=//*[contains(@id,'speichernbuttonid')]";	
	public static String UPM_Close_Btn = "xpath=//*[contains(@id,'schliebenbuttonid')]";	

	
	

	

}
