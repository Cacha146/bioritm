/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bio_ritm_tomcat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author sasha
 */
@ManagedBean(name = "Bean")
@SessionScoped

public class Bean implements Serializable {

    private Date user_date;
    private List<Ritm> list_ritms = new ArrayList();

    public Date getUserDate() {
        return user_date;
    }

    public void setUserDate(Date date) {
        this.user_date = date;
    }

    private int count_days_bettwen_dates(Date date1, Date date2) {
        long difference = date1.getTime() - date2.getTime();
        long number_of_days = difference / (24 * 60 * 60 * 1000);
        return (int) number_of_days;
    }

    public void calculateBioRitms() throws ParseException {
        Date now = Calendar.getInstance().getTime();
        now.setTime(now.getTime() - (long) (10 * 24 * 60 * 60 * 1000));
        int days = count_days_bettwen_dates(user_date, now) - 10;
        for (int i = 0; i < 21; i++) {

            Ritm ritm = new Ritm();
            ritm.setPhysical((Math.sin(2 * Math.PI * days / 23)) * 100);
            ritm.setEmotional((Math.sin(2 * Math.PI * days / 28)) * 100);
            ritm.setIntellectual((Math.sin(2 * Math.PI * days / 33)) * 100);
            ritm.setDate("" + now.getDate() + "." + (now.getMonth()+1));
            days++;
            now.setTime(now.getTime() + (long) (24 * 60 * 60 * 1000));
            list_ritms.add(ritm);

        }
        int i = 0;
        i++;
    }

    public LineChartModel getResponse() throws ParseException {
        calculateBioRitms();
        createLineModels();

        return lineModel;

    }
    private LineChartModel lineModel;

    private void createLineModels() {
        lineModel = initCategoryModel();
        lineModel.setTitle("Биоритмы");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Дата"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("%");    
        yAxis.setMin(-110);
        yAxis.setMax(110);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries physical = new ChartSeries();
        physical.setLabel("Физическии");
        ChartSeries emotional = new ChartSeries();
        emotional.setLabel("Эмоциональный");
        ChartSeries intellectual = new ChartSeries();
        intellectual.setLabel("Интелектуальный");
        for (Ritm ritm : list_ritms) {
            physical.set(ritm.getDate(), ritm.getPhysical());
            emotional.set(ritm.getDate(), ritm.getEmotional());
            intellectual.set(ritm.getDate(), ritm.getIntellectual());
        }

        model.addSeries(physical);
        model.addSeries(emotional);
        model.addSeries(intellectual);

        return model;
    }

}
