/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * This activity shows the order chosen.  The order is sent as data
 * with the intent to launch this activity.
 */
public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    private CheckBox check1, check2, check3, check4, check5;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);
        addListenerOnButtonClick();


        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int
            i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void addListenerOnButtonClick() {
        check1 = (CheckBox) findViewById(R.id.chkChocolate);
        check2 = (CheckBox) findViewById(R.id.chkSprinkle);
        check3 = (CheckBox) findViewById(R.id.chkCrushed);
        check4 = (CheckBox) findViewById(R.id.chkCherries);
        check5 = (CheckBox) findViewById(R.id.chkOrio);
        button = (Button) findViewById(R.id.btnShow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                result.append("Toppings: ");
                if (check1.isChecked()) {
                    result.append(getString(R.string.chk_chocalate_syrup) + " ");
                }
                if (check2.isChecked()) {
                    result.append(getString(R.string.chk_sprinkles) + " ");
                }
                if (check3.isChecked()) {
                    result.append(getString(R.string.chk_crushed_nuts) + " ");
                }
                if (check4.isChecked()) {
                    result.append(getString(R.string.chk_cherries) + " ");
                }
                if (check5.isChecked()) {
                    result.append(getString(R.string.chk_orio_cookies_crumbles));
                }
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}



