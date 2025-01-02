package org.guanzon.cas.inv.services;

import org.guanzon.appdriver.base.GRider;
import org.guanzon.appdriver.base.LogWrapper;
import org.guanzon.cas.inv.Inv_Master;
import org.guanzon.cas.inv.Inventory;
import org.guanzon.cas.inv.InventoryClassification;

public class InvControllers {

    public InvControllers(GRider applicationDriver, LogWrapper logWrapper) {
        poGRider = applicationDriver;
        poLogWrapper = logWrapper;
    }

    public Inventory Inventory() {
        if (poGRider == null) {
            poLogWrapper.severe("InvControllers.Inventory: Application driver is not set.");
            return null;
        }

        if (poInventory != null) {
            return poInventory;
        }

        poInventory = new Inventory();
        poInventory.setApplicationDriver(poGRider);
        poInventory.setWithParentClass(true);
        poInventory.setLogWrapper(poLogWrapper);
        poInventory.initialize();
        poInventory.newRecord();
        return poInventory;
    }

    public Inv_Master InventoryMaster() {
        if (poGRider == null) {
            poLogWrapper.severe("InvControllers.InventoryMaster: Application driver is not set.");
            return null;
        }

        if (poInvMaster != null) {
            return poInvMaster;
        }

        poInvMaster = new Inv_Master();
        poInvMaster.setApplicationDriver(poGRider);
        poInvMaster.setWithParentClass(true);
        poInvMaster.setLogWrapper(poLogWrapper);
        poInvMaster.initialize();
        poInvMaster.newRecord();
        return poInvMaster;
    }

    private final GRider poGRider;
    private final LogWrapper poLogWrapper;

    private Inventory poInventory;
    private Inv_Master poInvMaster;
}
