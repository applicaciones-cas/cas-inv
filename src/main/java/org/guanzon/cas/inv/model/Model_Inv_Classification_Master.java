package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.TransactionStatus;
import org.guanzon.cas.parameter.model.Model_Branch;
import org.guanzon.cas.parameter.model.Model_Category;
import org.guanzon.cas.parameter.model.Model_Category_Level2;
import org.guanzon.cas.parameter.services.ParamModels;
import org.json.simple.JSONObject;

public class Model_Inv_Classification_Master extends Model{      
    //reference objects
    Model_Branch poBranch;
    Model_Category poIndustry;
    Model_Category_Level2 poCategory;
    
    @Override
    public void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
            
            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);
            
            //assign default values
            poEntity.updateObject("nTotlSale", 0);
            poEntity.updateObject("cTranStat", TransactionStatus.STATE_OPEN);
            poEntity.updateObject("dProcessd", "0000-00-00 00:00:00");
            poEntity.updateObject("dPostedxx", "0000-00-00 00:00:00");
            //end - assign default values

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = "sIndstCdx";
            ID2 = "sCategrCd";
            ID3 = "sBranchCd";
            ID4 = "sPeriodxx";

            //initialize reference objects
            ParamModels model = new ParamModels(poGRider);
            poBranch = model.Branch();
            poIndustry = model.Category();
            poCategory = model.Category2();
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
    
    public JSONObject setLedgerNo(String ledgerNo){
        return setValue("nLedgerNo", ledgerNo);
    }
    
    public int getLedgerNo(){
        return (int) getValue("nLedgerNo");
    }
    
    public JSONObject setTransactionDate(Date transactionDate){
        return setValue("dTransact", transactionDate);
    }
    
    public Date getTransactionDate(){
        return (Date) getValue("dTransact");
    }
    
    public JSONObject setSourceCode(String sourceCode){
        return setValue("sSourceCd", sourceCode);
    }
    
    public String getSourceCode(){
        return (String) getValue("sSourceCd");
    }
    
    public JSONObject setSourceNo(String sourceNumber){
        return setValue("sSourceNo", sourceNumber);
    }
    
    public String getSourceNo(){
        return (String) getValue("sSourceNo");
    }
    
    public JSONObject setQuantityIn(int quantity){
        return setValue("nQtyInxxx", quantity);
    }
    
    public String getQuantityIn(){
        return (String) getValue("nQtyInxxx");
    }
    
    public JSONObject setQuantityOut(int quantity){
        return setValue("nQtyOutxx", quantity);
    }
    
    public String getQuantityOut(){
        return (String) getValue("nQtyOutxx");
    }
    
    public JSONObject setQuantityOrder(int quantity){
        return setValue("nQtyOrder", quantity);
    }
    
    public String getQuantityOrder(){
        return (String) getValue("nQtyOrder");
    }
    
    public JSONObject setQuantityIssued(int quantity){
        return setValue("nQtyIssue", quantity);
    }
    
    public String getQuantityIssued(){
        return (String) getValue("nQtyIssue");
    }
    
    public JSONObject setCost(double cost){
        return setValue("nPurPrice", cost);
    }
    
    public int getCost(){
        return (int) getValue("nPurPrice");
    }
    
    public JSONObject setSellingPrice(double sellingPrice){
        return setValue("nUnitPrce", sellingPrice);
    }
    
    public int getSellingPrice(){
        return (int) getValue("nUnitPrce");
    }
    
    public JSONObject setQuantityOnHand(int quantity){
        return setValue("nQtyOnHnd", quantity);
    }
    
    public String getQuantityOnHand(){
        return (String) getValue("nQtyOnHnd");
    }
        
    public JSONObject setExpirationDate(Date modifiedDate){
        return setValue("dExpiryxx", modifiedDate);
    }
    
    public Date getExpirationDate(){
        return (Date) getValue("dExpiryxx");
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
    public JSONObject openRecord(String id) {
        JSONObject loJSON = new JSONObject();
        loJSON.put("result", "error");
        loJSON.put("message", "This feature is not supported.");
        return loJSON;
    }
    
    @Override
    public JSONObject openRecord(String Id1, Object Id2) {
        JSONObject loJSON = new JSONObject();
        loJSON.put("result", "error");
        loJSON.put("message", "This feature is not supported.");
        return loJSON;
    }
    
    @Override
    public JSONObject openRecord(String Id1, Object Id2, Object Id3) {
        JSONObject loJSON = new JSONObject();
        loJSON.put("result", "error");
        loJSON.put("message", "This feature is not supported.");
        return loJSON;
    }
    
    @Override
    public JSONObject openRecord(String Id1, Object Id2, Object Id3, Object Id4) {
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
    
    //end - reference object models
}