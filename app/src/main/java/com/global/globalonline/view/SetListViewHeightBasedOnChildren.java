package com.global.globalonline.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lijl on 16/6/19.
 */
public class SetListViewHeightBasedOnChildren {


    public static void setList(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() +1)) + 60;

       /* ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除*/

        listView.setLayoutParams(params);
    }
}
