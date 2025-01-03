package org.guanzon.cas.inv.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.guanzon.appdriver.agent.services.Model;
import org.guanzon.appdriver.base.MiscUtil;
import org.guanzon.appdriver.constant.EditMode;
import org.guanzon.appdriver.constant.Logical;
import org.guanzon.appdriver.constant.RecordStatus;
import org.guanzon.cas.parameter.model.Model_Brand;
import org.guanzon.cas.parameter.model.Model_Category;
import org.guanzon.cas.parameter.model.Model_Category_Level2;
import org.guanzon.cas.parameter.model.Model_Category_Level3;
import org.guanzon.cas.parameter.model.Model_Category_Level4;
import org.guanzon.cas.parameter.model.Model_Color;
import org.guanzon.cas.parameter.model.Model_Inv_Type;
import org.guanzon.cas.parameter.model.Model_Measure;
import org.guanzon.cas.parameter.model.Model_Model;
import org.guanzon.cas.parameter.services.ParamModels;
import org.json.simple.JSONObject;

public class Model_Inventory_Serial extends Model {
    private Model_Inventory poInventory;

    @Override
    public void initialize() {
        try {
            poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());

            poEntity.last();
            poEntity.moveToInsertRow();

            MiscUtil.initRowSet(poEntity);

            //assign default values

            poEntity.updateString("cSoldStat", RecordStatus.INACTIVE);
            //end - assign default values

             poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = poEntity.getMetaData().getColumnLabel(1);

            //initialize other connections

            poInventory = new Model_Inventory();
            poInventory.setApplicationDriver(poGRider);
            poInventory.setXML("Model_Inventory");
            poInventory.setTableName("Inventory");
            poInventory.initialize();
            
            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            logwrapr.severe(e.getMessage());
            System.exit(1);
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

    public JSONObject setSerialId(String serialId) {
        return setValue("sSerialID", serialId);
    }

    public String getSerialId() {
        return (String) getValue("sSerialID");
    }

    public JSONObject setBranchCode(String branchCode) {
        return setValue("sBranchCd", branchCode);
    }

    public String getBranchCode() {
        return (String) getValue("sBranchCd");
    }

    public JSONObject setSerialOne(String serialOne) {
        return setValue("sSerial01", serialOne);
    }

    public String getSerialOne() {
        return (String) getValue("sSerial01");
    }

    public JSONObject setSerialTwo(String serialTwo) {
        return setValue("sSerial02", serialTwo);
    }

    public String getSerialTwo() {
        return (String) getValue("sSerial02");
    }

    public JSONObject setUnitPrice(String unitPrice) {
        return setValue("nUnitPrce", unitPrice);
    }

    public String getUnitPrice() {
        return (String) getValue("nUnitPrce");
    }

    public JSONObject setStockId(String stockId) {
        return setValue("sStockIDx", stockId);
    }

    public String getStockId() {
        return (String) getValue("sStockIDx");
    }

    public JSONObject setLocationId(String locationId) {
        return setValue("cLocation", locationId);
    }

    public String getLocationId() {
        return (String) getValue("cLocation");
    }
    
    public JSONObject setSoldStatus(String soldStatus) {
        return setValue("cSoldStat", soldStatus);
    }

    public String getSoldStatus() {
        return (String) getValue("cSoldStat");
    }

    public JSONObject setUnitType(String unitType) {
        return setValue("cUnitType", unitType);
    }

    public String getUnitType() {
        return (String) getValue("cUnitType");
    }

    public JSONObject setCompnyId(String companyId) {
        return setValue("sCompnyID", companyId);
    }

    public String getCompnyId() {
        return (String) getValue("sCompnyID");
    }

    public JSONObject setWarranty(String warranty) {
        return setValue("sWarranty", warranty);
    }

    public String getWarranty() {
        return (String) getValue("sWarranty");
    }

    public JSONObject setModifiedDate(Date modifiedDate) {
        return setValue("dModified", modifiedDate);
    }

    public Date getModifiedDate() {
        return (Date) getValue("dModified");
    }

    @Override
    public String getNextCode() {
        return MiscUtil.getNextCode(getTable(), ID, true, poGRider.getConnection(), poGRider.getBranchCode());
    }
}
