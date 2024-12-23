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
import org.json.simple.JSONObject;

public class Model_Inventory extends Model {

    private Model_Category poCategory;
    private Model_Category_Level2 poCategoryLevel2;
    private Model_Category_Level3 poCategoryLevel3;
    private Model_Category_Level4 poCategoryLevel4;
    private Model_Brand poBrand;
    private Model_Model poModel;
    private Model_Color poColor;
    private Model_Measure poMeasure;
    private Model_Inv_Type poInventoryType;

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

            //initialize other connections
            //Category 
            poCategory = new Model_Category();
            poCategory.setApplicationDriver(poGRider);
            poCategory.setXML("Model_Category");
            poCategory.setTableName("Category");
            poCategory.initialize();

            //Category Level 2
            poCategoryLevel2 = new Model_Category_Level2();
            poCategoryLevel2.setApplicationDriver(poGRider);
            poCategoryLevel2.setXML("Model_Category_Level2");
            poCategoryLevel2.setTableName("Category_Level3");
            poCategoryLevel2.initialize();

            //Category Level 3
            poCategoryLevel3 = new Model_Category_Level3();
            poCategoryLevel3.setApplicationDriver(poGRider);
            poCategoryLevel3.setXML("Model_Category_Level3");
            poCategoryLevel3.setTableName("Category_Level3");
            poCategoryLevel3.initialize();

            //Category Level 4
            poCategoryLevel4 = new Model_Category_Level4();
            poCategoryLevel4.setApplicationDriver(poGRider);
            poCategoryLevel4.setXML("Model_Category_Level4");
            poCategoryLevel4.setTableName("Category_Level4");
            poCategoryLevel4.initialize();

            //Brand
            poBrand = new Model_Brand();
            poBrand.setApplicationDriver(poGRider);
            poBrand.setXML("Model_Brand");
            poBrand.setTableName("Brand");
            poBrand.initialize();

            //Model
            poModel = new Model_Model();
            poModel.setApplicationDriver(poGRider);
            poModel.setXML("Model_Model");
            poModel.setTableName("Model");
            poModel.initialize();

            //Color
            poColor = new Model_Color();
            poColor.setApplicationDriver(poGRider);
            poColor.setXML("Model_Color");
            poColor.setTableName("Color");
            poColor.initialize();

            //Measure
            poMeasure = new Model_Measure();
            poMeasure.setApplicationDriver(poGRider);
            poMeasure.setXML("Model_Measure");
            poMeasure.setTableName("Measure");
            poMeasure.initialize();

            //Inventory Type
            poInventoryType = new Model_Inv_Type();
            poInventoryType.setApplicationDriver(poGRider);
            poInventoryType.setXML("Model_Inv_Type");
            poInventoryType.setTableName("Inv_Type");
            poInventoryType.initialize();
            //end - initialize other connections

            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            logwrapr.severe(e.getMessage());
            System.exit(1);
        }
    }

    public Model_Category Category() {
        if (!"".equals((String) getValue("sCategrCd"))) {
            if (poCategory.getEditMode() == EditMode.READY
                    && poCategory.getCategoryId().equals((String) getValue("sCategrCd"))) {
                return poCategory;
            } else {
                poJSON = poCategory.openRecord((String) getValue("sCategrCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poCategory;
                } else {
                    poCategory.initialize();
                    return poCategory;
                }
            }
        } else {
            poCategory.initialize();
            return poCategory;
        }
    }

    public Model_Category_Level2 CategoryLevel2() {
        System.out.println("xxxx = " + (String) getValue("sCategrCd") ); 
        if (!"".equals((String) getValue("sCategrCd"))) {
            if (poCategoryLevel2.getEditMode() == EditMode.READY
                    && poCategoryLevel2.getCategoryId().equals((String) getValue("sCategrCd"))) {
                return poCategoryLevel2;
            } else {
                poJSON = poCategoryLevel2.openRecord((String) getValue("sCategrCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poCategoryLevel2;
                } else {
                    poCategory.initialize();
                    return poCategoryLevel2;
                }
            }
        } else {
            poCategoryLevel2.initialize();
            return poCategoryLevel2;
        }
    }

    public Model_Category_Level3 CategoryLevel3() {
        if (!"".equals((String) getValue("sCategrCd"))) {
            if (poCategoryLevel3.getEditMode() == EditMode.READY
                    && poCategoryLevel3.getCategoryId().equals((String) getValue("sCategrCd"))) {
                return poCategoryLevel3;
            } else {
                poJSON = poCategoryLevel3.openRecord((String) getValue("sCategrCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poCategoryLevel3;
                } else {
                    poCategory.initialize();
                    return poCategoryLevel3;
                }
            }
        } else {
            poCategoryLevel3.initialize();
            return poCategoryLevel3;
        }
    }

    public Model_Category_Level4 CategoryLevel4() {
        if (!"".equals((String) getValue("sCategrCd"))) {
            if (poCategoryLevel4.getEditMode() == EditMode.READY
                    && poCategoryLevel4.getCategoryId().equals((String) getValue("sCategrCd"))) {
                return poCategoryLevel4;
            } else {
                poJSON = poCategoryLevel4.openRecord((String) getValue("sCategrCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poCategoryLevel4;
                } else {
                    poCategory.initialize();
                    return poCategoryLevel4;
                }
            }
        } else {
            poCategoryLevel4.initialize();
            return poCategoryLevel4;
        }
    }

    public Model_Brand Brand() {
        if (!"".equals((String) getValue("sBrandIdx"))) {
            if (poBrand.getEditMode() == EditMode.READY
                    && poBrand.getBrandId().equals((String) getValue("sBrandIdx"))) {
                return poBrand;
            } else {
                poJSON = poBrand.openRecord((String) getValue("sBrandIdx"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poBrand;
                } else {
                    poCategory.initialize();
                    return poBrand;
                }
            }
        } else {
            poBrand.initialize();
            return poBrand;
        }
    }

    public Model_Model Model() {
        if (!"".equals((String) getValue("sModelIDx"))) {
            if (poModel.getEditMode() == EditMode.READY
                    && poModel.getBrandId().equals((String) getValue("sModelIDx"))) {
                return poModel;
            } else {
                poJSON = poModel.openRecord((String) getValue("sModelIDx"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poModel;
                } else {
                    poCategory.initialize();
                    return poModel;
                }
            }
        } else {
            poModel.initialize();
            return poModel;
        }
    }

    public Model_Color Color() {
        if (!"".equals((String) getValue("sColorIDx"))) {
            if (poColor.getEditMode() == EditMode.READY
                    && poColor.getColorId().equals((String) getValue("sColorIDx"))) {
                return poColor;
            } else {
                poJSON = poColor.openRecord((String) getValue("sColorIDx"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poColor;
                } else {
                    poCategory.initialize();
                    return poColor;
                }
            }
        } else {
            poColor.initialize();
            return poColor;
        }
    }

    public Model_Measure Measure() {
        if (!"".equals((String) getValue("sMeasurID"))) {
            if (poMeasure.getEditMode() == EditMode.READY
                    && poMeasure.getMeasureId().equals((String) getValue("sMeasurID"))) {
                return poMeasure;
            } else {
                poJSON = poMeasure.openRecord((String) getValue("sMeasurID"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poMeasure;
                } else {
                    poCategory.initialize();
                    return poMeasure;
                }
            }
        } else {
            poMeasure.initialize();
            return poMeasure;
        }
    }

    public Model_Inv_Type InventoryType() {
        if (!"".equals((String) getValue("sInvTypCd"))) {
            if (poInventoryType.getEditMode() == EditMode.READY
                    && poInventoryType.getInventoryTypeId().equals((String) getValue("sInvTypCd"))) {
                return poInventoryType;
            } else {
                poJSON = poInventoryType.openRecord((String) getValue("sInvTypCd"));

                if ("success".equals((String) poJSON.get("result"))) {
                    return poInventoryType;
                } else {
                    poCategory.initialize();
                    return poInventoryType;
                }
            }
        } else {
            poInventoryType.initialize();
            return poInventoryType;
        }
    }
    
    public JSONObject setStockId(String stockId) {
        return setValue("sStockIDx", stockId);
    }

    public String getStockId() {
        return (String) getValue("sStockIDx");
    }

    public JSONObject setBarCode(String barCode) {
        return setValue("sBarCodex", barCode);
    }

    public String getBarCode() {
        return (String) getValue("sBarCodex");
    }

    public JSONObject setDescription(String description) {
        return setValue("sDescript", description);
    }

    public String getDescription() {
        return (String) getValue("sDescript");
    }

    public JSONObject setBriefDescription(String briefDescription) {
        return setValue("sBriefDsc", briefDescription);
    }

    public String getBriefDescription() {
        return (String) getValue("sBriefDsc");
    }

    public JSONObject setAlternateBarCode(String alternateBarCode) {
        return setValue("sAltBarCd", alternateBarCode);
    }

    public String getAlternateBarCode() {
        return (String) getValue("sAltBarCd");
    }

    public JSONObject setCategoryFirstLevelId(String cagetoryId) {
        return setValue("sCategCd1", cagetoryId);
    }

    public String getCategoryFirstLevelId() {
        return (String) getValue("sCategCd1");
    }

    public JSONObject setCategoryIdSecondLevel(String cagetoryId) {
        return setValue("sCategCd2", cagetoryId);
    }

    public String getCategoryIdSecondLevel() {
        return (String) getValue("sCategCd2");
    }

    public JSONObject setCategoryIdThirdLevel(String cagetoryId) {
        return setValue("sCategCd3", cagetoryId);
    }

    public String getCategoryIdThirdLevel() {
        return (String) getValue("sCategCd3");
    }

    public JSONObject setCategoryIdFourthLevel(String cagetoryId) {
        return setValue("sCategCd4", cagetoryId);
    }

    public String getCategoryIdFourthLevel() {
        return (String) getValue("sCategCd4");
    }

    public JSONObject setBrandId(String brandId) {
        return setValue("sBrandIDx", brandId);
    }

    public String getBrandId() {
        return (String) getValue("sBrandIDx");
    }

    public JSONObject setModelId(String brandId) {
        return setValue("sModelIDx", brandId);
    }

    public String getModelId() {
        return (String) getValue("sModelIDx");
    }

    public JSONObject setColorId(String brandId) {
        return setValue("sColorIDx", brandId);
    }

    public String getColorId() {
        return (String) getValue("sColorIDx");
    }

    public JSONObject setMeasurementId(String measurementId) {
        return setValue("sMeasurID", measurementId);
    }

    public String getMeasurementId() {
        return (String) getValue("sMeasurID");
    }

    public JSONObject setInventoryTypeId(String inventoryTypeId) {
        return setValue("sInvTypCd", inventoryTypeId);
    }

    public String getInventoryTypeId() {
        return (String) getValue("sInvTypCd");
    }

    public JSONObject setCost(double cost) {
        return setValue("nUnitPrce", cost);
    }

    public double getCost() {
        return (double) getValue("nUnitPrce");
    }

    public JSONObject setSellingPrice(double sellingPrice) {
        return setValue("nSelPrice", sellingPrice);
    }

    public double getSellingPrice() {
        return (double) getValue("nSelPrice");
    }

    public JSONObject setDiscountRateLevel1(double discountRate) {
        return setValue("nDiscLev1", discountRate);
    }

    public double getDiscountRateLevel1() {
        return (double) getValue("nDiscLev1");
    }

    public JSONObject setDiscountRateLevel2(double discountRate) {
        return setValue("nDiscLev2", discountRate);
    }

    public double getDiscountRateLevel2() {
        return (double) getValue("nDiscLev2");
    }

    public JSONObject setDiscountRateLevel3(double discountRate) {
        return setValue("nDiscLev3", discountRate);
    }

    public double getDiscountRateLevel3() {
        return (double) getValue("nDiscLev3");
    }

    public JSONObject setDealerDiscountRate(double discountRate) {
        return setValue("nDealrDsc", discountRate);
    }

    public double getDealerDiscountRate() {
        return (double) getValue("nDealrDsc");
    }

    public JSONObject setMinimumInventoryLevel(int quantity) {
        return setValue("nMinLevel", quantity);
    }

    public int getMinimumInventoryLevel() {
        return (int) getValue("nMinLevel");
    }

    public JSONObject setMaximumInventoryLevel(int quantity) {
        return setValue("nMaxLevel", quantity);
    }

    public int getMaximumInventoryLevel() {
        return (int) getValue("nMaxLevel");
    }

    public JSONObject isComboInventory(boolean isComboInventory) {
        return setValue("cComboInv", isComboInventory ? "1" : "0");
    }

    public boolean isComboInventory() {
        return ((String) getValue("cComboInv")).equals("1");
    }

    public JSONObject isWithPromo(boolean isWithPromo) {
        return setValue("cWthPromo", isWithPromo ? "1" : "0");
    }

    public boolean isWithPromo() {
        return ((String) getValue("cWthPromo")).equals("1");
    }

    public JSONObject isSerialized(boolean isSerialized) {
        return setValue("cSerialze", isSerialized ? "1" : "0");
    }

    public boolean isSerialized() {
        return ((String) getValue("cSerialze")).equals("1");
    }

    public JSONObject setUnitType(String unitType) {
        return setValue("cUnitType", unitType);
    }

    public String getUnitType() {
        return (String) getValue("cUnitType");
    }

    public JSONObject setInventoryStatus(String inventoryStatus) {
        return setValue("cInvStatx", inventoryStatus);
    }

    public String getInventoryStatus() {
        return (String) getValue("cInvStatx");
    }

    public JSONObject setShelfLife(int days) {
        return setValue("nShlfLife", days);
    }

    public int getShelfLife() {
        return (int) getValue("nShlfLife");
    }

    public JSONObject setSupersededId(String supersededId) {
        return setValue("sSupersed", supersededId);
    }

    public String getSupersededId() {
        return (String) getValue("sSupersed");
    }

    public JSONObject setRecordStatus(String recordStatus) {
        return setValue("cRecdStat", recordStatus);
    }

    public String getRecordStatus() {
        return (String) getValue("cRecdStat");
    }

    public JSONObject setModifyingId(String modifyingId) {
        return setValue("sModified", modifyingId);
    }

    public String getModifyingId() {
        return (String) getValue("sModified");
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
