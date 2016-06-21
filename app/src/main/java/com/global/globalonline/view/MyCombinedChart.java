package com.global.globalonline.view;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.global.globalonline.R;
import com.global.globalonline.bean.CombinedChartEntity;
import com.global.globalonline.listener.OnValueSelectedListener;
import com.global.globalonline.tools.MyChartHighlighter;
import com.global.globalonline.tools.MyCustomXAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by kqw on 2016/5/16.
 * MyCombinedChart
 */
public class MyCombinedChart extends CombinedChart implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG = "MyCombinedChart";

    private boolean isTranslate;
    private final int mWidth;
    private final Vibrator mVibrator;
    private MyChartHighlighter myChartHighlighter;
    private CombinedChartEntity mCombinedChartEntity;
    private OnValueSelectedListener mOnValueSelectedListener;

    private Handler invalidate = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

    public MyCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initChart();

        mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);

        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWidth = windowManager.getDefaultDisplay().getWidth();
    }

    private void initChart() {
        setDescription("");
        setBackgroundColor(getResources().getColor(R.color.zishousuobeijng));
        //setBackgroundColor(Color.WHITE);
        setDrawGridBackground(false);
        setDrawBarShadow(false);

        // 取消Y轴缩放动画
        setScaleYEnabled(false);

        // 自动缩放调整
        setAutoScaleMinMaxEnabled(true);

        /*
        * Y轴
        * ******************************************************************************/
        YAxis left = getAxisLeft();
        // 左侧Y轴坐标
        left.setDrawLabels(true);
        // 左侧Y轴
        left.setDrawAxisLine(true);
        // 横向线
        left.setDrawGridLines(true);
        // 纵轴数据显示在图形内部
        left.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 不显示右侧Y轴
        getAxisRight().setEnabled(false);

        /*
        * X轴
        * ******************************************************************************/
        XAxis xAxis = getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 格式化X轴时间
        xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());

        /*
        * 图形触摸监听
        * *********************************************************************************/
        setOnChartGestureListener(this);

        /*
        * 被选择监听（高亮监听）
        * **********************************************************************************/
        setOnChartValueSelectedListener(this);

        // 用来将像素转成index
        myChartHighlighter = new MyChartHighlighter(this);

    }

    /**
     * 填充数据
     *
     * @param entity 数据实体
     */
    public void setData(CombinedChartEntity entity) {
        mCombinedChartEntity = entity;
        ArrayList<String> timeY = new ArrayList<>();
        for (int i = 0; i < entity.getK().size(); i++) {
            timeY.add(entity.getK().get(i).get(0) + "");
        }
        CombinedData data = new CombinedData(timeY);
        data.setData(generateCandleData(entity));
        data.setData(generateLineData(entity));
        //        data.setData(generateBarData(empty));
        //        data.setData(generateBubbleData());
        //        data.setData(generateScatterData());
        setData(data);

        notifyDataSetChanged();

        // 最多显示60组数据
        setVisibleXRangeMaximum(60);
        // 最少显示30组数据

        setVisibleXRangeMinimum(30);

        // 移动到最右侧数据
        moveViewToX(entity.getK().size() - 1);
        /*
         延迟100毫秒执行invalidate
        为了解决控件使用setAutoScaleMinMaxEnabled方法后的一个小bug
          */
        invalidate.sendEmptyMessageDelayed(0, 100);
    }

    protected CandleData generateCandleData(CombinedChartEntity entity) {
        CandleData d = new CandleData();
        ArrayList<CandleEntry> entries = new ArrayList<>();
        for (int index = 0; index < entity.getK().size(); index++) {
            //float count = Float.parseFloat(entity.getK().get(index).get(5));
           // if(count > 0) {
                float a = Float.parseFloat(entity.getK().get(index).get(1));
                float b = Float.parseFloat(entity.getK().get(index).get(2));
                float c = Float.parseFloat(entity.getK().get(index).get(3));
                float dd = Float.parseFloat(entity.getK().get(index).get(4));
                //entries.add(new CandleEntry(index, a, b, c, dd));
                entries.add(new CandleEntry(index, b, c, a, dd));
           // }
        }
        CandleDataSet set1 = new CandleDataSet(entries, "K线");


        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);

        set1.setDecreasingColor(Color.GREEN); /*收>开*/
        set1.setDecreasingPaintStyle(Paint.Style.FILL_AND_STROKE);

        set1.setIncreasingColor(Color.RED); /*开>收*/
        set1.setIncreasingPaintStyle(Paint.Style.FILL_AND_STROKE);


        set1.setNeutralColor(Color.BLUE);
        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

       // set.setDrawValues(false);
        d.addDataSet(set1);
        return d;
    }

    private LineData generateLineData(CombinedChartEntity entity) {
        LineData d = new LineData();
        d.addDataSet(getLineDataSet(5, entity));
        d.addDataSet(getLineDataSet(10, entity));
        d.addDataSet(getLineDataSet(30, entity));
        return d;
    }

    private LineDataSet getLineDataSet(int ma, CombinedChartEntity empty) {

        ArrayList<Entry> entries = new ArrayList<>();
        for (int index = ma - 1; index < empty.getK().size(); index++) {
            long sum = 0;
            for (int m = 0; m < ma; m++) {
                sum += Double.parseDouble(empty.getK().get(index - m).get(3));
            }
            sum /= ma;
            entries.add(new Entry(sum, index));
        }
        LineDataSet set = new LineDataSet(entries, "MA " + ma);
        // 不显示横向高亮线
        set.setDrawHorizontalHighlightIndicator(false);
        set.setColor(5 == ma ? Color.rgb(240, 0, 70) : 10 == ma ? Color.rgb(0, 0, 70) : Color.rgb(100, 100, 255));
        set.setLineWidth(1f);
        set.setDrawCircles(false);
        set.setDrawCubic(false);
        set.setDrawHorizontalHighlightIndicator(false);

        set.setValueTextSize(10f);
        set.setDrawValues(false);

        set.setValueTextColor(Color.rgb(240, 238, 70));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return set;
    }

    /*
    * Gesture callbacks
    * Start
    * *******************************************************************************/
    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        isTranslate = false;
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        setDragEnabled(true);
        getData().setHighlightEnabled(false);

        if (null != mOnValueSelectedListener) {
            mOnValueSelectedListener.end();
        }
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        if (!isTranslate) {
            Toast.makeText(getContext().getApplicationContext(), "长按\n震动50毫秒\n可以左右滑动  查看数据", Toast.LENGTH_SHORT).show();
            // 震动50毫秒
            mVibrator.vibrate(50);
            setDragEnabled(false);
            getData().setHighlightEnabled(true);

            float x = me.getRawX();
            // TODO 通过像素换算index  高亮显示
            int index = myChartHighlighter.getXIndex(x);
            highlightValue(index, 0);

            if (null != mOnValueSelectedListener) {
                mOnValueSelectedListener.start();
                float open = Float.parseFloat(mCombinedChartEntity.getK().get(index).get(1) );
                float  close = Float.parseFloat(mCombinedChartEntity.getK().get(index).get(4));
                float  high = Float.parseFloat(mCombinedChartEntity.getK().get(index).get(2));
                float  low = Float.parseFloat(mCombinedChartEntity.getK().get(index).get(3));
                mOnValueSelectedListener.data(open, close, high, low);
            }
        }
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        isTranslate = true;
    }
    /* End *******************************************************************************/

    /*
    * Selection callbacks
    * Start
    * *******************************************************************************/
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        // TODO index转换成像素
//        float f = me.getRawX();
//        if (f < mWidth / 2) {
//            Log.i(TAG, "显示在右侧");
//        } else {
//            Log.i(TAG, "显示在左侧");
//        }

        try {
            assert null != mCombinedChartEntity;
            float open = Float.parseFloat(mCombinedChartEntity.getK().get(e.getXIndex()).get(1)) ;
            float close =  Float.parseFloat(mCombinedChartEntity.getK().get(e.getXIndex()).get(4)) ;
            float high =  Float.parseFloat(mCombinedChartEntity.getK().get(e.getXIndex()).get(2)) ;
            float low =  Float.parseFloat(mCombinedChartEntity.getK().get(e.getXIndex()).get(3));
            if (null != mOnValueSelectedListener) {
                mOnValueSelectedListener.data(open, close, high, low);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected");
    }
    /* End *******************************************************************************/

    public void setOnValueSelectedListener(OnValueSelectedListener listener) {
        mOnValueSelectedListener = listener;
    }
}
