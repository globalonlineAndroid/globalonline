package com.global.globalonline.tools;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.global.globalonline.base.GetConfiguration;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.CoutryBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/5/29.
 */
public class GetSelectBouncedUtil {





    public static void get(Activity activity, final TextView textView, String mouble, String defId){

        textView.setKeyListener(null);

       final OptionsPickerView pvOptions;
         ArrayList<String> options = new ArrayList<String>();
         final ArrayList<CoutryBean> coutrys = new ArrayList<CoutryBean>();
       DBHelper dbHelper = DBHelper.getInstance(activity);


        List<DataSource> list = dbHelper.getByModekeyList(mouble);
        for (int i = 0; i < list.size(); i++) {

            DataSource data = list.get(i);
            if(!"".equals(defId) && data.getId_().equals(defId)){
                textView.setText(data.getName());
                textView.setTag(data.getId_());
            }
            CoutryBean cou = new CoutryBean();
            //cou.setCode(data.getCode());
            cou.setName(data.getName());
            cou.setEname(data.getEname());
            cou.setId(data.getId_());
            coutrys.add(cou);
            options.add(cou.getName());
        }



        pvOptions = new OptionsPickerView(activity);
        pvOptions.setPicker(options);
        pvOptions.setTitle("请选择");
        pvOptions.setCyclic(false);
        if(mouble.equals(StaticBase.BANKTYPE)) {
            pvOptions.setSelectOptions(Integer.parseInt(defId));
        }else {
            pvOptions.setSelectOptions(Integer.parseInt(defId) - 1);
        }

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = coutrys.get(options1).getName();
                String id = coutrys.get(options1).getId();
                textView.setText(tx);
                textView.setTag(id);
                //vMasker.setVisibility(View.GONE);
            }
        });


        //点击弹出选项选择器
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    public static String getBankName(Activity activity,String mouble, String id_){
        String bankName = "";

        DBHelper dbHelper = DBHelper.getInstance(activity);

        DataSource dataSource = dbHelper.getByModeOrId(mouble,id_).get(0);

        bankName = GetConfiguration.isZh()?dataSource.getName():dataSource.getEname();


        return bankName;
    }

}
