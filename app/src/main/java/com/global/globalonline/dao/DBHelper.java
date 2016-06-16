package com.global.globalonline.dao;

import android.content.Context;

import com.global.globalonline.base.MyApplication;
import com.global.globalonline.db.bean.DataSource;
import com.global.globalonline.db.dao.DaoSession;
import com.global.globalonline.db.dao.DataSourceDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by thinkPad on 2015/7/17.
 */
public class DBHelper {

    private static Context mContext;
    private static DBHelper instance;
    private DataSourceDao dataSourceDao;

    public DBHelper()
    {
    }

    public static DBHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DBHelper();
            if (mContext == null)
            {
                mContext = context;
            }
            // 数据库对象
            DaoSession daoSession = MyApplication.getDaoSession(mContext.getApplicationContext());
            instance.dataSourceDao = daoSession.getDataSourceDao();
        }
        return instance;
    }


    public DataSource getDataSource(long id){

       return dataSourceDao.loadByRowId(id);
    }


    /** 添加数据 */
    public void addDataTable(DataSource item)
    {
        dataSourceDao.insertOrReplace(item);

    }
    /*添加数据*/
    public void addDataTable(List<DataSource> item)
    {
        for (DataSource ds:item){
            dataSourceDao.insert(ds);
        }
    }
    /** 查询 */
    public List<DataSource> getDataInfoList()
    {
        QueryBuilder<DataSource> qb = dataSourceDao.queryBuilder();
        return qb.list();
    }

    /** 查询 */
    public List<DataSource> getDataInfo()
    {
        return dataSourceDao.loadAll();
    }

    /** 查询 */
    public boolean isSaved(int Id)
    {
        QueryBuilder<DataSource> qb = dataSourceDao.queryBuilder();
        qb.where(DataSourceDao.Properties.Id.eq(Id));
        qb.buildCount().count();

        return qb.buildCount().count() > 0 ? true : false;
    }


    /* 根据 modekey 查询列表
   *  order 按某个字段排序  记得首字母大写
   * */
    public List<DataSource> getModekeyList(String modekey, Property order)
    {

        List<DataSource> dataSourcesList = dataSourceDao.queryBuilder()
                .where(DataSourceDao.Properties.Module.eq(modekey))
                .orderAsc(order).list();
        return dataSourcesList;
    }


    /* 根据 Module 查询列表
      *
      * */
    public List<DataSource> getByModekeyList(String modekey)
    {

        List<DataSource> dataSourceList = new ArrayList<DataSource>();
        if(modekey!=null) {
            dataSourceList = dataSourceDao.queryBuilder()
                    .where(DataSourceDao.Properties.Module.eq(modekey)
                           // ,DataSourceDao.Properties.Name.eq(name)
                    )
                    //.orderDesc(DataSourceDao.Properties.Arg3)
                    .list();
        }
        return dataSourceList;
    }

    /* 根据 Module 查询列表
     *
     * */
    public List<DataSource> getByModeOrId(String modekey,String id_)
    {

        List<DataSource> dataSourceList = new ArrayList<DataSource>();
        if(modekey!=null) {
            dataSourceList = dataSourceDao.queryBuilder()
                    .where(DataSourceDao.Properties.Module.eq(modekey)
                             ,DataSourceDao.Properties.Id_.eq(id_)
                    )
                    .list();
        }
        return dataSourceList;
    }
    /** 按id删除 */
    public void clearByIdData(long Id)
    {
        QueryBuilder<DataSource> qb = dataSourceDao.queryBuilder();
        DeleteQuery<DataSource> bd = qb.where(DataSourceDao.Properties.Id.eq(Id)).buildDelete();
        bd.executeDeleteWithoutDetachingEntities();
    }
    /** 删除所有 */
    public void clearAllData()
    {
        dataSourceDao.deleteAll();
    }
    /** 按mode删除 */
    public void clearByModeData(String mode)
    {
        QueryBuilder<DataSource> qb = dataSourceDao.queryBuilder();
        DeleteQuery<DataSource> bd = qb.where(DataSourceDao.Properties.Module.eq(mode)).buildDelete();
        bd.executeDeleteWithoutDetachingEntities();
    }


}
