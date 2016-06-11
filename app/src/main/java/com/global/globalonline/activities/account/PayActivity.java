package com.global.globalonline.activities.account;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.PayResultsActivity_;
import com.global.globalonline.base.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_pay)
public class PayActivity extends BaseActivity {
    @ViewById
    Button bt_tijiao;

    @Click({R.id.bt_tijiao})
    void clik(View view){

        switch (view.getId()){
            case R.id.btn_tijiao:
                Intent intent = new Intent(this, PayResultsActivity_.class);
                startActivity(intent);
                break;
        }

    }

}
