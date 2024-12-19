package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.json.simple.JSONObject;

public class Model_Inventory extends Model{
    @Override
    public void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            
            //assign default values
            poEntity.updateObject("nUnitPrce", 0.00);
            poEntity.updateObject("nSelPrice", 0.00);
            poEntity.updateObject("nDiscLev1", 0.00);
            poEntity.updateObject("nDiscLev2", 0.00);
            poEntity.updateObject("nDiscLev3", 0.00);
            poEntity.updateObject("nDealrDsc", 0.00);
            poEntity.updateObject("nMinLevel", 0);
            poEntity.updateObject("nMaxLevel", 0);
            poEntity.updateObject("nShlfLife", 0);
            poEntity.updateString("cComboInv", Logical.NO);
            poEntity.updateString("cWthPromo", Logical.NO);
            poEntity.updateString("cSerialze", Logical.NO);
            poEntity.updateString("cUnitType", Logical.NO);
            poEntity.updateString("cInvStatx", RecordStatus.ACTIVE);
            poEntity.updateString("cRecdStat", RecordStatus.ACTIVE);
            //end - assign default values

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = poEntity.getMetaData().getColumnLabel(1);
            
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
    
    public JSONObject setBarCode(String barCode){
        return setValue("sBarCodex", barCode);
    }
    
    public String getBarCode(){
        return (String) getValue("sBarCodex");
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
    
    @Override
    public String getNextCode(){
        return MiscUtil.getNextCode(getTable(), ID, true, poGRider.getConnection(), poGRider.getBranchCode()); 
    }
}