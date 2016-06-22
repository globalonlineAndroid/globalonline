package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(2, "bean");
        schema.setDefaultJavaPackageDao("dao");
        initUserBean(schema);
        new DaoGenerator().generateAll(schema, args[0]);

    }
    private static void initUserBean(Schema schema) {
        Entity dataBean = schema.addEntity("DataSource");
        dataBean.setTableName("data");
        dataBean.addIdProperty();
        dataBean.addStringProperty("id_");
        dataBean.addStringProperty("name");
        dataBean.addStringProperty("ename");
        dataBean.addStringProperty("code");
        dataBean.addStringProperty("sort_id");
        dataBean.addStringProperty("shortname");
        dataBean.addStringProperty("word");
        dataBean.addStringProperty("logo");
        dataBean.addStringProperty("miniconfirm");
        dataBean.addStringProperty("withdraw_fee");
        dataBean.addStringProperty("minwithdrawbtc");
        dataBean.addStringProperty("maxwithdrawbtc");
        dataBean.addStringProperty("recharge_fee");
        dataBean.addStringProperty("draw_fee");
        dataBean.addStringProperty("bankname");
        dataBean.addStringProperty("bankno");
        dataBean.addStringProperty("bankadd");
        dataBean.addStringProperty("status");
        dataBean.addStringProperty("iswithdraw");
        dataBean.addStringProperty("module");
    }


}
