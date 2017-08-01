package com.dm.utils.utils;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 8/1/17.
 */

public class UiUtils {

    /**
     * Gets the String value of checked radio button from radio group
     */
    public static String getValueFromCheckedRadioButton(RadioGroup rg) {
        String selection = "";
        try {
            if (rg.getCheckedRadioButtonId() != -1) {
                int id = rg.getCheckedRadioButtonId();
                View radioButton = rg.findViewById(id);
                int radioId = rg.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                selection = (String) btn.getText();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return selection;
    }

    public static void setSelectedRadioButton(RadioGroup rg, String value) {
        int count = rg.getChildCount();
        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View o = rg.getChildAt(i);
            if (o instanceof RadioButton) {
                listOfRadioButtons.add((RadioButton) o);
            }
        }
        if (!CollectionUtils.isEmpty(listOfRadioButtons)) {
            for (RadioButton rb : listOfRadioButtons) {
                if ((rb.getText()).equals(value)) {
                    rb.setChecked(true);
                    break;
                }
            }
        }
    }

    public static String getSelectedFromSpinner(AppCompatSpinner spinner) {
        try {
            return spinner.getSelectedItem().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static void setSpinnerAdapter(Context context, AppCompatSpinner spinner, List<String> list) {
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getIndexOfValue(Spinner spinner, String value) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                index = i;
                break;
            }
        }
        return index;
    }
}