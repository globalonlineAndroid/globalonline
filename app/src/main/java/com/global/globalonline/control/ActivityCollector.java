package com.global.globalonline.control;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 15/9/24.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static  void addActivity(Activity activity){
        activities.add(activity);
    }
    public  static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity : activities){
            activity.finish();
        }
    }
    public static  void back(){
        activities.get(activities.size()-1).finish();
        removeActivity(activities.get(activities.size()-1));

    }
    public static  void removeLast(){
        if(activities.size()>1) {
            activities.get(activities.size() - 1).finish();
        }

    }


}
