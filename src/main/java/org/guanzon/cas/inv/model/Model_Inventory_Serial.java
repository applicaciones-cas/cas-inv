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

            poEntity.updateString("cSoldStat", RecordStatus.INACTIVE);
            //end - assign default values

            poEntity.insertRow();
            poEntity.moveToCurrentRow();

            poEntity.absolute(1);

            ID = poEntity.getMetaData().getColumnLabel(1);

            //initialize other connections
            ParamModels model = new ParamModels(poGRider);
            poCategory = model.Category();
            poCategoryLevel2 = model.Category2();
            poCategoryLevel3 = model.Category3();
            poCategoryLevel4 = model.Category4();
            poBrand = model.Brand();
            poModel = model.Model();
            poColor = model.Color();
            poMeasure = model.Measurement();
            poInventoryType = model.InventoryType();
            
            //Inventory 
            poInventory = new Model_Inventory();
            poInventory.setApplicationDriver(poGRider);
            poInventory.setXML("Model_Inventory");
            poInventory.setTableName("Inventory");
            poInventory.initialize(); 
            //end - initialize other connections

            pnEditMode = EditMode.UNKNOWN;
        } catch (SQLException e) {
            logwrapr.severe(e.getMessage());
            System.exit(1);
        }
    }

    public Model_Inventory Inventory() {
        System.out.println("xxxx = " + (String) getValue("sStockIDx") ); 
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
    
    public Model_Category Category() {
        if (!"".equals((String) getValue("sCategCd1"))) {
            if (poCategory.getEditMode() == EditMode.READY
                    && poCategory.getCategoryId().equals((String) getValue("sCategCd1"))) {
                return poCategory;
            } else {
                poJSON = poCategory.openRecord((String) getValue("sCategCd1"));

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
        if (!"".equals((String) getValue("sCategCd2"))) {
            if (poCategoryLevel2.getEditMode() == EditMode.READY
                    && poCategoryLevel2.getCategoryId().equals((String) getValue("sCategCd2"))) {
                return poCategoryLevel2;
            } else {
                poJSON = poCategoryLevel2.openRecord((String) getValue("sCategCd2"));

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
        if (!"".equals((String) getValue("sCategCd3"))) {
            if (poCategoryLevel3.getEditMode() == EditMode.READY
                    && poCategoryLevel3.getCategoryId().equals((String) getValue("sCategCd3"))) {
                return poCategoryLevel3;
            } else {
                poJSON = poCategoryLevel3.openRecord((String) getValue("sCategCd3"));

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
        if (!"".equals((String) getValue("sCategCd4"))) {
            if (poCategoryLevel4.getEditMode() == EditMode.READY
                    && poCategoryLevel4.getCategoryId().equals((String) getValue("sCategCd4"))) {
                return poCategoryLevel4;
            } else {
                poJSON = poCategoryLevel4.openRecord((String) getValue("sCategCd4"));

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
        if (!"".equals((String) getValue("sBrandIDx"))) {
            if (poBrand.getEditMode() == EditMode.READY
                    && poBrand.getBrandId().equals((String) getValue("sBrandIDx"))) {
                return poBrand;
            } else {
                poJSON = poBrand.openRecord((String) getValue("sBrandIDx"));

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
        System.out.println("InventoryType = " + (String) getValue("sInvTypCd") ); 
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
