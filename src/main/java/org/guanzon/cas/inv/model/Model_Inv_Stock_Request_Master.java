package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.InventoryClassification;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.cas.inv.services.InvModels;
import org.guanzon.cas.parameter.model.Model_Branch;
import org.guanzon.cas.parameter.model.Model_Inv_Location;
import org.guanzon.cas.parameter.model.Model_Warehouse;
import org.guanzon.cas.parameter.services.ParamModels;
import org.json.simple.JSONObject;

public class Model_Inv_Stock_Request_Master extends Model{      
    //reference objects
    Model_Branch poBranch;
    Model_Warehouse poWarehouse;
    Model_Inventory poInventory;
    Model_Inv_Location poLocation;
    
    @Override
    public void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            
            //assign default values
            poEntity.updateObject("dAcquired", "0000-00-00");
            poEntity.updateObject("dBegInvxx", "0000-00-00");
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
            poEntity.updateObject("dLastTran", "0000-00-00");
            poEntity.updateString("cPrimaryx", Logical.NO);
            poEntity.updateString("cRecdStat", RecordStatus.ACTIVE);
            //end - assign default values

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = "sStockIDx";
            ID2 = "sBranchCd";
            
            //initialize reference objects
            ParamModels model = new ParamModels(poGRider);
            poBranch = model.Branch();
            poWarehouse = model.Warehouse();
            poLocation = model.InventoryLocation();
            
            poInventory = new InvModels(poGRider).Inventory();
            //end - initialize reference objects
            
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
        return setValue("sLocatnID", briefDescription);
    }
    
    public String getLocationId(){
        return (String) getValue("sLocatnID");
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
    
    @Override
    public String getNextCode() {
        return "";
    }
    
    @Override
    public JSONObject openRecord(String Id1) {
        JSONObject loJSON = new JSONObject();
        loJSON.put("result", "error");
        loJSON.put("message", "This feature is not supported.");
        return loJSON;
    }
    
    //reference object models
    public Model_Branch Branch() {
        if (!"".equals((String) getValue("sBranchCd"))) {
            if (poBranch.getEditMode() == EditMode.READY
                    && poBranch.getBranchCode().equals((String) getValue("sBranchCd"))) {
                return poBranch;
            } else {
                poJSON = poBranch.openRecord((String) getValue("sBranchCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poBranch;
                } else {
                    poBranch.initialize();
                    return poBranch;
                }
            }
        } else {
            poBranch.initialize();
            return poBranch;
        }
    }
    
    public Model_Warehouse Warehouse() {
        if (!"".equals((String) getValue("sWHouseID"))) {
            if (poWarehouse.getEditMode() == EditMode.READY
                    && poWarehouse.getWarehouseId().equals((String) getValue("sWHouseID"))) {
                return poWarehouse;
            } else {
                poJSON = poWarehouse.openRecord((String) getValue("sWHouseID"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poWarehouse;
                } else {
                    poWarehouse.initialize();
                    return poWarehouse;
                }
            }
        } else {
            poWarehouse.initialize();
            return poWarehouse;
        }
    }
    
    public Model_Inv_Location Location() {
        if (!"".equals((String) getValue("sLocatnID"))) {
            if (poLocation.getEditMode() == EditMode.READY
                    && poLocation.getLocationId().equals((String) getValue("sLocatnID"))) {
                return poLocation;
            } else {
                poJSON = poLocation.openRecord((String) getValue("sLocatnID"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poLocation;
                } else {
                    poLocation.initialize();
                    return poLocation;
                }
            }
        } else {
            poLocation.initialize();
            return poLocation;
        }
    }
    
    public Model_Inventory Inventory() {
        if (!"".equals((String) getValue("sStockIDx"))) {
            if (poInventory.getEditMode() == EditMode.READY
                    && poInventory.getStockId().equals((String) getValue("sStockIDx"))) {
                return poInventory;
            } else {
                poJSON = poInventory.openRecord((String) getValue("sStockIDx"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poInventory;
                } else {
                    poInventory.initialize();
                    return poInventory;
                }
            }
        } else {
            poInventory.initialize();
            return poInventory;
        }
    }
    //end - reference object models
}