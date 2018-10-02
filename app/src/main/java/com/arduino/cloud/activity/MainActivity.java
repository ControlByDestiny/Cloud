package com.arduino.cloud.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arduino.cloud.R;
import com.chinamobile.iot.onenet.edp.toolbox.EdpClient;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void pieChartTest(){
        pieChart = findViewById(R.id.test_pie);
        pieChart.setCenterText("60%");
        pieChart.setCenterTextColor(Color.BLUE);
        pieChart.setCenterTextSize(24.0f);
        ArrayList<PieEntry> pieEntryList = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#f17548"));
        colors.add(Color.parseColor("#FF9933"));
        // /饼图实体 PieEntry
        PieEntry CashBalance = new PieEntry(70, "现金余额 1500");
        PieEntry ConsumptionBalance = new PieEntry(30, "消费余额 768");
        pieEntryList.add(CashBalance);
        pieEntryList.add(ConsumptionBalance);
        //饼状图数据集 PieDataSet
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "资产总览");
        pieDataSet.setSliceSpace(3f);
        //设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(10f);
        //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);
        //为DataSet中的数据匹配上颜色集(饼图Item颜色)
        // 最终数据 PieData
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        //设置是否显示数据实体(百分比，true:以下属性才有意义)
        pieData.setValueTextColor(Color.BLUE);
        //设置所有DataSet内数据实体（百分比）的文本颜色
        pieData.setValueTextSize(12f);
        //设置所有DataSet内数据实体（百分比）的文本字体大小
        pieData.setValueTypeface(Typeface.DEFAULT);
        //设置所有DataSet内数据实体（百分比）的文本字体样式
        pieData.setValueFormatter(new PercentFormatter());
        //设置所有DataSet内数据实体（百分比）的文本字体格式
        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
        Legend l = pieChart.getLegend();
        l.setEnabled(true);
        //是否启用图列（true：下面属性才有意义）
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.DEFAULT);
        //设置图例的形状
        l.setFormSize(10);
        // 设置图例的大小
        l.setFormToTextSpace(10f);
        // 设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);
        // 设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        l.setXEntrySpace(10f);
        // 设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);
        // 设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);
        // 设置比例块Y轴偏移量
        l.setTextSize(14f);
        // 设置图例标签文本的大小
        l.setTextColor(Color.parseColor("#ff9933"));
        // 设置图例标签文本的颜色
    }
}
