package com.global.globalonline.base;

import android.app.Application;
import android.content.Context;

import com.global.globalonline.bean.UserBean;
import com.global.globalonline.db.dao.DaoMaster;
import com.global.globalonline.db.dao.DaoSession;

/**
 * Created by lijl on 16/5/23.
 */
public class MyApplication extends Application {

    public static UserBean userBean;

    private static Context context;


    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context)
    {
        if (daoMaster == null)
        {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,"zthz_gongtongpeisong", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }
    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }



}
