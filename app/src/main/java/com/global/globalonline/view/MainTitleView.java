package com.global.globalonline.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.global.globalonline.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sgf on 2015/6/19 0019.
 */
@EViewGroup(R.layout.act_view_titlebar)
public class MainTitleView extends LinearLayout {
    @ViewById
    TextView back, title, operation;
    private String titletxt;
    private String backtxt;
    private String operationtxt;
    private int oper_size;
    private int back_bground,oper_bground;



    private Context context;


    public MainTitleView(Context con, AttributeSet attrs) {
        super(con, attrs);
        context = con;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.topBarView);
        this.titletxt = (String) typedArray.getText(R.styleable.topBarView_titletxt);
        this.backtxt = (String) typedArray.getText(R.styleable.topBarView_backtxt);
        this.back_bground=typedArray.getResourceId(R.styleable.topBarView_back_bground,0);
        this.oper_bground=typedArray.getResourceId(R.styleable.topBarView_oper_bground, 0);
        this.operationtxt = (String) typedArray.getText(R.styleable.topBarView_operationtxt);
        this.oper_size=typedArray.getInt(R.styleable.topBarView_oper_size,0);
        typedArray.recycle();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterViews
    void init() {

        if(operationtxt != null && !operationtxt.equals("")){
            operation.setText(operationtxt);
        }else {
            operation.setVisibility(INVISIBLE);
        }
        if(titletxt != null && !titletxt.equals("")){
            title.setText(titletxt);
        }
        if(backtxt != null && !backtxt.equals("")){
            if(backtxt.equals("no")){
                back.setVisibility(GONE);
            }
            back.setText(backtxt);
        }
        //返回按钮样式取消
    /*    if(back_bground!=0){
            back.setCompoundDrawables(null, null, null, null);
          //  Drawable drawable=Drawable.createFromPath(back_bground);

            back.setBackgroundResource(back_bground);
            //back.setHeight();
            
        }*/
        if(oper_bground!=0){
            operation.setBackgroundResource(oper_bground);
        }
        if(oper_size!=0){
            operation.setTextSize(oper_size);
        }
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }





    public TextView getTtitle() {
        return title;
    }

    public void setTitletxt(String titletxt) {
        this.title.setText(titletxt);
    }

    public String getBacktxt() {
        return backtxt;
    }

    public void setBacktxt(String backtxt) {
        this.backtxt = backtxt;
    }

    /*public String getOperationtxt() {
        return operationtxt;
    }*/

    public void setOperationtxt(String operationtxt) {
        operation.setText(operationtxt);
    }
    public void setClickOperation(OnClickListener click){
        this.operation.setOnClickListener(click);
    }
    public TextView getOperation(){
        return operation;
    }
    public TextView getBack(){
        return back;
    }
}