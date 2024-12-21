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
    
    private final Map<String, Model> relatedModels = new HashMap<>();

    @Override
    public void initialize() {
        try {
            initializeEntity();
            initializeRelatedModels();
            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            logwrapr.severe(e.getMessage());
            System.exit(1);
        }
    }

    private void initializeEntity() throws SQLException {
        poEntity = MiscUtil.xml2ResultSet(System.getProperty("sys.default.path.metadata") + XML, getTable());
        poEntity.last();
        poEntity.moveToInsertRow();
        MiscUtil.initRowSet(poEntity);

        // Assign default values
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

        poEntity.insertRow();
        poEntity.moveToCurrentRow();
        poEntity.absolute(1);

        ID = poEntity.getMetaData().getColumnLabel(1);
    }

    private void initializeRelatedModels() {
        registerModel("Category", new Model_Category(), "Model_Category", "Category");
        registerModel("Category_Level2", new Model_Category_Level2(), "Model_Category_Level2", "Category_Level2");
        registerModel("Category_Level3", new Model_Category_Level3(), "Model_Category_Level3", "Category_Level3");
        registerModel("Category_Level4", new Model_Category_Level4(), "Model_Category_Level4", "Category_Level4");
        registerModel("Brand", new Model_Brand(), "Model_Brand", "Brand");
        registerModel("Model", new Model_Model(), "Model_Model", "Model");
        registerModel("Color", new Model_Color(), "Model_Color", "Color");
        registerModel("Measure", new Model_Measure(), "Model_Measure", "Measure");
        registerModel("InventoryType", new Model_Inv_Type(), "Model_Inv_Type", "Inv_Type");
    }

    private void registerModel(String key, Model model, String xml, String tableName) {
        model.setApplicationDriver(poGRider);
        model.setXML(xml);
        model.setTableName(tableName);
        model.initialize();
        relatedModels.put(key, model);
    }

    public Model getModel(String key, String idKey, String idValue) {
        Model model = relatedModels.get(key);
        if (model == null) return null;

        if (!idValue.isEmpty() && model.getEditMode() == EditMode.READY && idValue.equals(getValue(idKey))) {
            return model;
        }

        poJSON = model.openRecord(idValue);
        if ("success".equals(poJSON.get("result"))) {
            return model;
        } else {
            model.initialize();
            return model;
        }
    }

    public Model_Category Category() {
        return (Model_Category) getModel("Category", "sCategrCd", (String) getValue("sCategrCd"));
    }

    public Model_Category_Level2 CategoryLevel2() {
        return (Model_Category_Level2) getModel("Category_Level2", "sCategrCd", (String) getValue("sCategrCd"));
    }

    public Model_Category_Level3 CategoryLevel3() {
        return (Model_Category_Level3) getModel("Category_Level3", "sCategrCd", (String) getValue("sCategrCd"));
    }

    public Model_Category_Level4 CategoryLevel4() {
        return (Model_Category_Level4) getModel("Category_Level4", "sCategrCd", (String) getValue("sCategrCd"));
    }

    public Model_Brand Brand() {
        return (Model_Brand) getModel("Brand", "sBrandIdx", (String) getValue("sBrandIdx"));
    }

    public Model_Model ModelDetails() {
        return (Model_Model) getModel("Model", "sModelIDx", (String) getValue("sModelIDx"));
    }

    public Model_Color Color() {
        return (Model_Color) getModel("Color", "sColorIDx", (String) getValue("sColorIDx"));
    }

    public Model_Measure Measure() {
        return (Model_Measure) getModel("Measure", "sMeasurID", (String) getValue("sMeasurID"));
    }

    public Model_Inv_Type InventoryType() {
        return (Model_Inv_Type) getModel("InventoryType", "sInvTypCd", (String) getValue("sInvTypCd"));
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
