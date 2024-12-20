package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.InventoryClassification;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.json.simple.JSONObject;

public class Model_Inv_Master extends Model{
    @Override
    public void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            
            //assign default values
            poEntity.updateObject("dAcquired", "1900-01-01");
            poEntity.updateObject("dBegInvxx", "1900-01-01");
            poEntity.updateObject("nBegQtyxx", 0);
            poEntity.updateObject("nQtyOnHnd", 0);
            poEntity.updateObject("nLedgerNo", 0);
            poEntity.updateObject("nMinLevel", 0);
            poEntity.updateObject("nMaxLevel", 0);
            poEntity.updateObject("nAvgMonSl", 0);
            poEntity.updateObject("nAvgCostx", 0.00);
            poEntity.updateString("cClassify", InventoryClassification.NEW_ITEMS);
            poEntity.updateObject("nBackOrdr", 0);
            poEntity.updateObject("nResvOrdr", 0);
            poEntity.updateObject("nFloatQty", 0);
            poEntity.updateObject("dLastTran", "1900-01-01");
            poEntity.updateString("cPrimaryx", Logical.NO);
            poEntity.updateString("cRecdStat", RecordStatus.ACTIVE);
            //end - assign default values

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = "sStockIDx";
            ID2 = "sBranchCd";
            
            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            logwrapr.severe(e.getMessage());
            System.exit(1);
        }
    }
    
    public JSONObject setStockId(String stockId){
        return setValue("sStockIDx", stockId);
    }
    
    public String getStockId(){
        return (String) getValue("sStockIDx");
    }
    
    public JSONObject setBranchCode(String branchCode){
        return setValue("sBranchCd", branchCode);
    }
    
    public String getBranchCode(){
        return (String) getValue("sBranchCd");
    }
    
    public JSONObject setDescription(String description){
        return setValue("sDescript", description);
    }
    
    public String getDescription(){
        return (String) getValue("sDescript");
    }
    
    public JSONObject setBriefDescription(String briefDescription){
        return setValue("sBriefDsc", briefDescription);
    }
    
    public String getBriefDescription(){
        return (String) getValue("sBriefDsc");
    }
    
    public JSONObject setAlternateBarCode(String alternateBarCode){
        return setValue("sAltBarCd", alternateBarCode);
    }
    
    public String getAlternateBarCode(){
        return (String) getValue("sAltBarCd");
    }
    
    public JSONObject setCategoryFirstLevelId(String cagetoryId){
        return setValue("sCategCd1", cagetoryId);
    }
    
    public String getCategoryFirstLevelId(){
        return (String) getValue("sCategCd1");
    }
    
    public JSONObject setCategoryIdSecondLevel(String cagetoryId){
        return setValue("sCategCd2", cagetoryId);
    }
    
    public String getCategoryIdSecondLevel(){
        return (String) getValue("sCategCd2");
    }
    
    public JSONObject setCategoryIdThirdLevel(String cagetoryId){
        return setValue("sCategCd3", cagetoryId);
    }
    
    public String getCategoryIdThirdLevel(){
        return (String) getValue("sCategCd3");
    }
    
    public JSONObject setCategoryIdFourthLevel(String cagetoryId){
        return setValue("sCategCd4", cagetoryId);
    }
    
    public String getCategoryIdFourthLevel(){
        return (String) getValue("sCategCd4");
    }
    
    public JSONObject setBrandId(String brandId){
        return setValue("sBrandIDx", brandId);
    }
    
    public String getBrandId(){
        return (String) getValue("sBrandIDx");
    }
    
    public JSONObject setModelId(String brandId){
        return setValue("sModelIDx", brandId);
    }
    
    public String getModelId(){
        return (String) getValue("sModelIDx");
    }
    
    public JSONObject setColorId(String brandId){
        return setValue("sColorIDx", brandId);
    }
    
    public String getColorId(){
        return (String) getValue("sColorIDx");
    }
    
    public JSONObject setMeasurementId(String measurementId){
        return setValue("sMeasurID", measurementId);
    }
    
    public String getMeasurementId(){
        return (String) getValue("sMeasurID");
    }
    
    public JSONObject setInventoryTypeId(String inventoryTypeId){
        return setValue("sInvTypCd", inventoryTypeId);
    }
    
    public String getInventoryTypeId(){
        return (String) getValue("sInvTypCd");
    }
    
    public JSONObject setCost(double cost){
        return setValue("nUnitPrce", cost);
    }
    
    public double getCost(){
        return (double) getValue("nUnitPrce");
    }
    
    public JSONObject setSellingPrice(double sellingPrice){
        return setValue("nSelPrice", sellingPrice);
    }
    
    public double getSellingPrice(){
        return (double) getValue("nSelPrice");
    }
    
    public JSONObject setDiscountRateLevel1(double discountRate){
        return setValue("nDiscLev1", discountRate);
    }
    
    public double getDiscountRateLevel1(){
        return (double) getValue("nDiscLev1");
    }
    
    public JSONObject setDiscountRateLevel2(double discountRate){
        return setValue("nDiscLev2", discountRate);
    }
    
    public double getDiscountRateLevel2(){
        return (double) getValue("nDiscLev2");
    }
    
    public JSONObject setDiscountRateLevel3(double discountRate){
        return setValue("nDiscLev3", discountRate);
    }
    
    public double getDiscountRateLevel3(){
        return (double) getValue("nDiscLev3");
    }
    
    public JSONObject setDealerDiscountRate(double discountRate){
        return setValue("nDealrDsc", discountRate);
    }
    
    public double getDealerDiscountRate(){
        return (double) getValue("nDealrDsc");
    }
    
    public JSONObject setMinimumInventoryLevel(int quantity){
        return setValue("nMinLevel", quantity);
    }
    
    public int getMinimumInventoryLevel(){
        return (int) getValue("nMinLevel");
    }
    
    public JSONObject setMaximumInventoryLevel(int quantity){
        return setValue("nMaxLevel", quantity);
    }
    
    public int getMaximumInventoryLevel(){
        return (int) getValue("nMaxLevel");
    }
    
    public JSONObject isComboInventory(boolean isComboInventory){
        return setValue("cComboInv", isComboInventory ? "1" : "0");
    }

    public boolean isComboInventory(){
        return ((String) getValue("cComboInv")).equals("1");
    }
    
    public JSONObject isWithPromo(boolean isWithPromo){
        return setValue("cWthPromo", isWithPromo ? "1" : "0");
    }

    public boolean isWithPromo(){
        return ((String) getValue("cWthPromo")).equals("1");
    }
    
    public JSONObject isSerialized(boolean isSerialized){
        return setValue("cSerialze", isSerialized ? "1" : "0");
    }

    public boolean isSerialized(){
        return ((String) getValue("cSerialze")).equals("1");
    }
    
    public JSONObject setUnitType(String unitType){
        return setValue("cUnitType", unitType);
    }
    
    public String getUnitType(){
        return (String) getValue("cUnitType");
    }
    
    public JSONObject setInventoryStatus(String inventoryStatus){
        return setValue("cInvStatx", inventoryStatus);
    }
    
    public String getInventoryStatus(){
        return (String) getValue("cInvStatx");
    }
    
    public JSONObject setShelfLife(int days){
        return setValue("nShlfLife", days);
    }
    
    public int getShelfLife(){
        return (int) getValue("nShlfLife");
    }
    
    public JSONObject setSupersededId(String supersededId){
        return setValue("sSupersed", supersededId);
    }
    
    public String getSupersededId(){
        return (String) getValue("sSupersed");
    }
    
    public JSONObject setRecordStatus(String recordStatus){
        return setValue("cRecdStat", recordStatus);
    }
    
    public String getRecordStatus(){
        return (String) getValue("cRecdStat");
    }
    
    public JSONObject setModifyingId(String modifyingId){
        return setValue("sModified", modifyingId);
    }
    
    public String getModifyingId(){
        return (String) getValue("sModified");
    }
    
    public JSONObject setModifiedDate(Date modifiedDate){
        return setValue("dModified", modifiedDate);
    }
    
    public Date getModifiedDate(){
        return (Date) getValue("dModified");
    }
}