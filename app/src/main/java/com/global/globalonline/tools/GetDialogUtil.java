package com.global.globalonline.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.global.globalonline.R;
import com.global.globalonline.dao.TishiResDao;


/**
 * Created by thinkPad on 2015/6/24.
 */
public class GetDialogUtil {









   /* public static void ShuRuYanZhengMa(final Activity act, String title , final YanZhengMaDao yanzhengDao) {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dig_def_ex_yanzhengma, null);


        final TextView tv_title = (TextView) view.findViewById(R.id.title);
        final EditText yanzhengma = (EditText) view.findViewById(R.id.yanzhengma);
        final TextView btn_queding = (TextView) view.findViewById(R.id.btn_queding);
        final TextView btn_quxiao = (TextView) view.findViewById(R.id.btn_quxiao);
        tv_title.setText(title);

        btn_queding.setText("确定");
        btn_quxiao.setText("取消");

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yanzhengStr = yanzhengma.getText() != null?yanzhengma.getText().toString():"";
                if (!StringUtils.isBlank(yanzhengStr)) {

                    yanzhengDao.yanzhengma(yanzhengStr);
                } else {
                    Toast.makeText(act, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                finalDialog.cancel();
            }
        });

        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalDialog.cancel();

            }
        });


    }

    public static void ShuRuYanZhengMaMap(final Activity act, String title , final YanZhengMaDao yanzhengDao) {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dig_def_ex_yanzhengma, null);


        final TextView tv_title = (TextView) view.findViewById(R.id.title);
        final EditText yanzhengma = (EditText) view.findViewById(R.id.yanzhengma);
        final TextView btn_queding = (TextView) view.findViewById(R.id.btn_queding);
        final TextView btn_quxiao = (TextView) view.findViewById(R.id.btn_quxiao);
        tv_title.setText(title);

        btn_queding.setText("确定");
        btn_quxiao.setText("取消");

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yanzhengStr = yanzhengma.getText() != null?yanzhengma.getText().toString():"";
                if (!StringUtils.isBlank(yanzhengStr)) {
                    // querenshouhuo(view.getTag().toString(),yanzhengStr);
                    //yanzheng(yanzhengStr,view.getTag().toString());
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("check_code",yanzhengStr);
                    yanzhengDao.yanzhengma(map);
                } else {
                    Toast.makeText(act, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                finalDialog.cancel();

            }
        });

        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalDialog.cancel();

            }
        });


    }
*/

    public static void tishi(final Activity act, String title, String text , final TishiResDao tishiResDao) {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.act_dig_def_tishi, null);


        final TextView tv_title = (TextView) view.findViewById(R.id.title);
        final TextView textView = (TextView) view.findViewById(R.id.text);
        final TextView btn_queding = (TextView) view.findViewById(R.id.btn_queding);
        final TextView btn_quxiao = (TextView) view.findViewById(R.id.btn_quxiao);


        if (title == null) {
            title = act.getResources().getString(R.string.act_base_tishi);
        }
        tv_title.setText(title);
        textView.setText(text);

        btn_queding.setText(act.getResources().getString(R.string.act_base_queding));
        btn_quxiao.setText(act.getResources().getString(R.string.act_base_quxiao));


        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tishiResDao.getTiShi("");
                finalDialog.cancel();
            }
        });

        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalDialog.cancel();

            }
        });
    }

    public static void tishi(final Activity act, String title, String text,String queding_text , String quxiao_text,final TishiResDao queding,final TishiResDao quxiao) {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.act_dig_def_tishi, null);


        final TextView tv_title = (TextView) view.findViewById(R.id.title);
        final TextView textView = (TextView) view.findViewById(R.id.text);
        final TextView btn_queding = (TextView) view.findViewById(R.id.btn_queding);
        final TextView btn_quxiao = (TextView) view.findViewById(R.id.btn_quxiao);


        if(title == null){
            title  = act.getResources().getString(R.string.act_base_tishi);
        }
        tv_title.setText(title);
        textView.setText(text);

        btn_queding.setText(queding_text);
        btn_quxiao.setText(quxiao_text);


        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        final AlertDialog finalDialog = dialog;
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queding.getTiShi("");
                finalDialog.cancel();
            }
        });

        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quxiao.getTiShi("");
                finalDialog.cancel();

            }
        });


    }

    public static Dialog loading(final Activity act, String text) {

        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.act_dig_def_loading, null);
        Dialog dialog=new Dialog(act,R.style.dialog_sel);
        dialog.setCanceledOnTouchOutside(false);

        final ImageView iv_loding = (ImageView) view.findViewById(R.id.iv_loding);
        final TextView tv_tishi = (TextView) view.findViewById(R.id.tv_tishi);
        Glide.with(act).load(R.drawable.loading).into(iv_loding);

        tv_tishi.setText(text);
        dialog.setContentView(view);

        try {
            dialog.show();
        } catch (Exception e) {
            dialog=null;
        }
        return  dialog;

    }



}
