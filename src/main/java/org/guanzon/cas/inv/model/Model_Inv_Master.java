package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.InventoryClassification;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.cas.parameter.Parameters;
import org.guanzon.cas.parameter.model.Model_Inv_Location;
import org.guanzon.cas.parameter.model.Model_Warehouse;
import org.json.simple.JSONObject;

public class Model_Inv_Master extends Model{
    Parameters poParams;
    Model_Inventory poInventory;
    Model_Warehouse poWarehouse;
    Model_Inv_Location poInvLocation;
    
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
            
            poParams = new Parameters(poGRider, logwrapr);
            poWarehouse = poParams.Warehouse().getModel();
            poInvLocation = poParams.InventoryLocation().getModel();
            
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
    
    public JSONObject setWarehouseId(String warehouseId){
        return setValue("sWHouseID", warehouseId);
    }
    
    public String getWarehouseId(){
        return (String) getValue("sWHouseID");
    }
    
    public JSONObject setLocationId(String briefDescription){
        return setValue("sBriefDsc", briefDescription);
    }
    
    public String getLocationId(){
        return (String) getValue("sBriefDsc");
    }
    
    public JSONObject setBinId(String binId){
        return setValue("sBinNumbr", binId);
    }
    
    public String getBinId(){
        return (String) getValue("sBinNumbr");
    }
    
    public JSONObject setDateAcquired(Date dateAcquired){
        return setValue("dAcquired", dateAcquired);
    }
    
    public Date getDateAcquired(){
        return (Date) getValue("dAcquired");
    }
    
    public JSONObject setBeginningInventoryDate(Date beginningInventoryDate){
        return setValue("dBegInvxx", beginningInventoryDate);
    }
    
    public Date getBeginningInventoryDate(){
        return (Date) getValue("dBegInvxx");
    }
    
    public JSONObject setBeginningInventoryQuantity(int beginningInventoryQuantity){
        return setValue("nBegQtyxx", beginningInventoryQuantity);
    }
    
    public int getBeginningInventoryQuantity(){
        return (int) getValue("nBegQtyxx");
    }   
    
    public JSONObject setQuantityOnHand(int quantityOnHand){
        return setValue("nBegQtyxx", quantityOnHand);
    }
    
    public int getQuantityOnHand(){
        return (int) getValue("nBegQtyxx");
    }  
    
    public JSONObject setLedgerCount(int ledgerCount){
        return setValue("nLedgerNo", ledgerCount);
    }
    
    public int getLedgerCount(){
        return (int) getValue("nLedgerNo");
    }   
    
    public JSONObject setMinimumLevel(int minimumInventoryLevel){
        return setValue("nMinLevel", minimumInventoryLevel);
    }
    
    public int getMinimumLevel(){
        return (int) getValue("nMinLevel");
    }   
    
    public JSONObject setMaximumLevel(int maximumInventoryLevel){
        return setValue("nMaxLevel", maximumInventoryLevel);
    }
    
    public int getMaximumLevel(){
        return (int) getValue("nMaxLevel");
    }   
    
    public JSONObject setAverageMonthlySale(int averageMonthlySale){
        return setValue("nMaxLevel", averageMonthlySale);
    }
    
    public int getAverageMonthlySales(){
        return (int) getValue("nMaxLevel");
    }   
    
    public JSONObject setAverageCost(double averageCost){
        return setValue("nAvgCostx", averageCost);
    }
    
    public double getAverageCost(){
        return (double) getValue("nAvgCostx");
    }   
    
    public JSONObject setInventoryClassification(String inventoryClassification){
        return setValue("cClassify", inventoryClassification);
    }
    
    public String getInventoryClassification(){
        return (String) getValue("cClassify");
    }
    
    public JSONObject setBackOrderQuantity(int backOrderQuantity){
        return setValue("nBackOrdr", backOrderQuantity);
    }
    
    public int getBackOrderQuantity(){
        return (int) getValue("nBackOrdr");
    }   
    
    public JSONObject setReserveOrderQuantity(int reserveOrderQuantity){
        return setValue("nResvOrdr", reserveOrderQuantity);
    }
    
    public int getReserveOrderQuantity(){
        return (int) getValue("nResvOrdr");
    }   
    
    public JSONObject setFloatQuantity(int reserveQuantity){
        return setValue("nFloatQty", reserveQuantity);
    }
    
    public int getFloatQuantity(){
        return (int) getValue("nFloatQty");
    }   
    
    public JSONObject setLastTransactionDate(Date lastTransactionDate){
        return setValue("dLastTran", lastTransactionDate);
    }
    
    public Date getLastTransactionDate(){
        return (Date) getValue("dLastTran");
    }
    
    public JSONObject isPrimary(boolean isPrimary){
        return setValue("cPrimaryx", isPrimary ? "1" : "0");
    }

    public boolean isPrimary(){
        return ((String) getValue("cPrimaryx")).equals("1");
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