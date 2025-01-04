package org.guanzon.cas.inv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.base.SQLUtil;
import org.guanzon.appdriver.base.LogWrapper;
import org.guanzon.appdriver.constant.EditMode;
import org.json.simple.JSONObject;

public class InvSerial {    
    GRider poGRider;
    String psParent;
    
    JSONObject poJSON;
    LogWrapper poLogWrapper;
    
    InventorySerial poInventorySerial;
    List<InventorySerialLedger> poInventorySerialLedger;
    
    public InvSerial(GRider applicationDriver,
                    String parentClass,
                    LogWrapper logWrapper){
        
        poGRider = applicationDriver;
        psParent = parentClass;
        poLogWrapper = logWrapper;
        
        poInventorySerial = new InventorySerial();
        poInventorySerial.setApplicationDriver(poGRider);
        poInventorySerial.setWithParentClass(true);
//        poClient.setLogWrapper(poLogWrapper);
        poInventorySerial.initialize();
        
        poInventorySerialLedger = new ArrayList<>();
    }
        
    public InventorySerial Master(){
        return poInventorySerial;
    }
    
    public InventorySerialLedger SerialLedger(int row){
        return poInventorySerialLedger.get(row);
    }
    
    
    public int getSerialLedgerCount(){
//        OpenSerialLedger(poInventorySerial.getModel().getss);
        return poInventorySerialLedger.size();
    }    
    
   
    
    public JSONObject addSerialLedger(){
        poJSON = new JSONObject();
        
        if (poInventorySerialLedger.isEmpty()){
            poInventorySerialLedger.add(serialLedger());            
        } else {
            if (!poInventorySerialLedger.get(poInventorySerialLedger.size()-1).getModel().getSerialId().isEmpty()){
                poInventorySerialLedger.add(serialLedger());
            } else {
                poJSON.put("result", "error");
                poJSON.put("message", "Unable to add serialLedger.");
                return poJSON;
            }
        }
        
        poJSON.put("result", "success");
        return poJSON;
    }
    
    
    public JSONObject deleteSerialLedger(int row){
        poJSON = new JSONObject();
        
        if (poInventorySerialLedger.isEmpty()){
            poJSON.put("result", "error");
            poJSON.put("result", "Unable to delete ledger. Mobile list is empty.");
            return poJSON;
        }
        
        if (row >= poInventorySerialLedger.size()){
            poJSON.put("result", "error");
            poJSON.put("result", "Unable to delete ledger. Row is more than the mobile list.");
            return poJSON;
        }
        
        if (poInventorySerialLedger.get(row).getEditMode() != EditMode.ADDNEW){
            poJSON.put("result", "error");
            poJSON.put("result", "Unable to delete old mobile. You can deactivate the record instead.");
            return poJSON;
        }
        
        poInventorySerialLedger.remove(row);
        poJSON.put("result", "success");
        return poJSON;
    }
    
    public JSONObject New(){
        poJSON = poInventorySerial.newRecord();
        if (!"success".equals((String) poJSON.get("result"))) return poJSON;
        
        poInventorySerialLedger.clear();
        poJSON = addSerialLedger();
        if (!"success".equals((String) poJSON.get("result"))) return poJSON;
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;
    }
    
    public JSONObject Save(){
        int lnCtr;
        
        if (psParent.isEmpty()) 
            poGRider.beginTrans();
        
        //assign modified info
        poInventorySerial.getModel().setModifiedDate(poGRider.getServerDate());
        
        //save client master
        poJSON = poInventorySerial.saveRecord();
        
        if (!"success".equals((String) poJSON.get("result"))){
            if (psParent.isEmpty()) poGRider.rollbackTrans();
            return poJSON;
        }
        
        //save mobile
        if (!poInventorySerialLedger.isEmpty()){            
            for(lnCtr = 0; lnCtr <= poInventorySerialLedger.size()-1; lnCtr++){
                if ((poInventorySerialLedger.get(lnCtr).getEditMode() == EditMode.ADDNEW ||
                        poInventorySerialLedger.get(lnCtr).getEditMode() == EditMode.UPDATE) &&
                            !poInventorySerialLedger.get(lnCtr).getModel().getSerialId().isEmpty()){

                    if (poInventorySerialLedger.get(lnCtr).getEditMode() == EditMode.ADDNEW){
                        poInventorySerialLedger.get(lnCtr).getModel().setSerialId(poInventorySerial.getModel().getSerialId());
                    }
                    
                    poInventorySerialLedger.get(lnCtr).getModel().setModifiedDate(poInventorySerial.getModel().getModifiedDate());
                    
                    //save
                    poJSON = poInventorySerialLedger.get(lnCtr).saveRecord();

                    if (!"success".equals((String) poJSON.get("result"))){
                        if (psParent.isEmpty()) poGRider.rollbackTrans();
                        return poJSON;
                    }
                }
            }
        }
        
        if (psParent.isEmpty()) 
            poGRider.commitTrans();
        
        poJSON = new JSONObject();
        poJSON.put("result", "success");
        return poJSON;        
    }

    private InventorySerialLedger serialLedger(){
        InventorySerialLedger object = new InventorySerialLedger();
        object.setApplicationDriver(poGRider);
        object.setWithParentClass(true);
//        object.setLogWrapper(poLogWrapper);
        object.initialize();
        object.newRecord();
        return object;
    }
    public JSONObject OpenSerialLedger(String fsValue) {
    StringBuilder lsSQL = new StringBuilder("SELECT " +
                "  sSerialID, " +
                "  sBranchCd, " +
                "  nLedgerNo, " +
                "  dTransact, " +
                "  sSourceCd, " +
                "  cSoldStat, " +
                "  cLocation " +
                "FROM Inv_Serial_Ledger");

    // Add condition to the query
    lsSQL.append(MiscUtil.addCondition("", "sSerialID = " + SQLUtil.toSQL(fsValue)));
    lsSQL.append(" ORDER BY nLedgerNo");

    System.out.println("Executing SQL: " + lsSQL.toString());

    ResultSet loRS = poGRider.executeQuery(lsSQL.toString());
    poJSON = new JSONObject();

    try {
        int lnctr = 0;

        if (MiscUtil.RecordCount(loRS) > 0) {
            poInventorySerialLedger = new ArrayList<>();
            while (loRS.next()) {
                // Print the result set
                System.out.println("sSerialID: " + loRS.getString("sSerialID"));
                System.out.println("sBranchCd: " + loRS.getString("sBranchCd"));
                System.out.println("nLedgerNo: " + loRS.getInt("nLedgerNo"));
                System.out.println("dTransact: " + loRS.getDate("dTransact"));
                System.out.println("sSourceCd: " + loRS.getString("sSourceCd"));
                System.out.println("cSoldStat: " + loRS.getString("cSoldStat"));
                System.out.println("cLocation: " + loRS.getString("cLocation"));
                System.out.println("------------------------------------------------------------------------------" );

                poInventorySerialLedger.add(serialLedger());
                poInventorySerialLedger.get(poInventorySerialLedger.size() - 1)
                        .openRecord(loRS.getString("sSerialID"));

                lnctr++;
            }

            System.out.println("Records found: " + lnctr);
            poJSON.put("result", "success");
            poJSON.put("message", "Record loaded successfully.");

        } else {
            poInventorySerialLedger = new ArrayList<>();
            addSerialLedger();
            poJSON.put("result", "error");
            poJSON.put("continue", true);
            poJSON.put("message", "No record found.");
        }
    } catch (SQLException e) {
        System.err.println("SQL error: " + e.getMessage());
        poJSON.put("result", "error");
        poJSON.put("message", e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
        poJSON.put("result", "error");
        poJSON.put("message", "An unexpected error occurred.");
    } finally {
        // Ensure that the ResultSet is closed in any case
        MiscUtil.close(loRS);
    }

    return poJSON;
}

    
}
