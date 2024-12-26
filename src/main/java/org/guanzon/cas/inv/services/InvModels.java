package org.guanzon.cas.inv.services;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.cas.inv.model.Model_Inv_Classification_Master;
import org.guanzon.cas.inv.model.Model_Inv_Master;
import org.guanzon.cas.inv.model.Model_Inventory;

public class InvModels {
    public InvModels(GRider applicationDriver){
        poGRider = applicationDriver;
    }
    
    public Model_Inventory Inventory(){
        if (poGRider == null){
            System.err.println("InvModels.Inventory: Application driver is not set.");
            return null;
        }
        
        if (poInventory == null){
            poInventory = new Model_Inventory();
            poInventory.setApplicationDriver(poGRider);
            poInventory.setXML("Model_Inventory");
            poInventory.setTableName("Inventory");
            poInventory.initialize();
        }

        return poInventory;
    }
    
    public Model_Inv_Master InventoryMaster(){
        if (poGRider == null){
            System.err.println("InvModels.InventoryMaster: Application driver is not set.");
            return null;
        }
        
        if (poInvMaster == null){
            poInvMaster = new Model_Inv_Master();
            poInvMaster.setApplicationDriver(poGRider);
            poInvMaster.setXML("Model_Inv_Master");
            poInvMaster.setTableName("Inv_Master");
            poInvMaster.initialize();
        }

        return poInvMaster;
    }
    
    public Model_Inv_Classification_Master InventoryLedger(){
        if (poGRider == null){
            System.err.println("InvModels.InventoryLedger: Application driver is not set.");
            return null;
        }
        
        if (poInvLedger == null){
            poInvLedger = new Model_Inv_Classification_Master();
            poInvLedger.setApplicationDriver(poGRider);
            poInvLedger.setXML("Model_Inv_Ledger");
            poInvLedger.setTableName("Inv_Ledger");
            poInvLedger.initialize();
        }

        return poInvLedger;
    }
    
    private final GRider poGRider;
    
    private Model_Inventory poInventory;
    private Model_Inv_Master poInvMaster;
    private Model_Inv_Classification_Master poInvLedger;
}