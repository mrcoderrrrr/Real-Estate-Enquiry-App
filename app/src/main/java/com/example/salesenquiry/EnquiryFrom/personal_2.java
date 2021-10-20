package com.example.salesenquiry.EnquiryFrom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesenquiry.R;
import com.google.android.material.textfield.TextInputEditText;

import static android.content.ContentValues.TAG;

public class personal_2 extends AppCompatActivity {
    Spinner occupation;
    TextView next, previous, gendertxt, statustxt, personaltxt;
    RadioGroup gender, status;
    RadioButton  male, female, married, unmarried;
    TextInputEditText CompanyName, Designation, WorkNature, BLocation;
    String companyname, designation, worknature, busiloc, occupationss;
    String occupations[] = {"Salary", "Business", "Professional", "Retired", "Student"};
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String getGenderVal="0", getStatusval="0";
    String genederData =" ", statusData=" ";
    Bundle bundle;
    public final static int REQUEST_CODE_DATA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal2);
        personaltxt = findViewById(R.id.personaltxt);
        gendertxt = findViewById(R.id.gendertxt);
        statustxt = findViewById(R.id.statustxt);
        gender = findViewById(R.id.genderval);
        status = findViewById(R.id.statusval);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        married = findViewById(R.id.married);
        unmarried = findViewById(R.id.unmarried);
        occupation = findViewById(R.id.spinner);
        CompanyName = findViewById(R.id.CompanyName);
        Designation = findViewById(R.id.designation);
        WorkNature = findViewById(R.id.worknature);
        BLocation = findViewById(R.id.busiloc);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        bundle=getIntent().getExtras();
        //Occupation Spineer
        occupationfeild();
        //Intents
        nextclick();
        previousclick();
//        //updateData
     UpdateFormData();
        //gender RadioButton
        gendervalget();
        //status Radiobutton
        statusvalget();

    }

    //
    //Update Form Value
    private void UpdateFormData() {
       try {
           genederData = getIntent().getStringExtra("GENDER");
           if (genederData.equals("Male")) {
               male.setChecked(true);
               female.setChecked(false);
           } else if (genederData.equals("Female")) {
               female.setChecked(true);
               male.setChecked(false);
           }
           statusData = getIntent().getStringExtra("STATUS");
           if (statusData.equals("Married")) {
               married.setChecked(true);
               unmarried.setChecked(false);
           } else if (statusData.equals("Unmarried")) {
               unmarried.setChecked(true);
               married.setChecked(false);
           }
           CompanyName.setText(getIntent().getStringExtra("COMPANY_NAME"));
           Designation.setText(getIntent().getStringExtra("DESIGNATION"));
           WorkNature.setText(getIntent().getStringExtra("WORK_NATURE"));
           BLocation.setText(getIntent().getStringExtra("BUSINESS_LOCATION"));
       }
       catch (NullPointerException e){
           e.printStackTrace();
       }
    }

    //validation
    public boolean validation() {
        if (!male.isChecked() && !female.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please Select the Gender", Toast.LENGTH_LONG).show();
            return false;
        } else if (!married.isChecked() && !unmarried.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please Select the Status", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    //Gender value get
    private void gendervalget() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.male == checkedId) {
                getGenderVal="Male";
                }
                else if (R.id.female == checkedId) {
                getGenderVal="Female";
                }
                else {
                    getGenderVal="Null";
                }
            }
        });
    }

    //Status Value get
    private void statusvalget() {
        status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.married == checkedId) {
                    getStatusval = "Married";
                } else if (R.id.unmarried == checkedId) {
                  getStatusval="Unmarried";
                }
                else {
                    getStatusval="Null";
                }
            }
        });
    }


    //previous activity
    private void previousclick() {
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Bundle Data
                BundleData(intent);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();;
            }
        });
    }


    //Next Activity
    private void nextclick() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validation()) {
                    return;
                }
                try {
                    companyname = CompanyName.getText().toString();
                    designation = Designation.getText().toString();
                    worknature = WorkNature.getText().toString();
                    busiloc = BLocation.getText().toString();
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                sp = getSharedPreferences("DetailsKey", MODE_PRIVATE);
                ed = sp.edit();
                ed.putString("GENDER", getGenderVal);
                Log.d("GENDER_VAL",""+getGenderVal);
                ed.putString("STATUS", getStatusval);
                ed.putString("COMPANY_NAME", companyname);
                ed.putString("OCCUPATION", occupationss);
                ed.putString("DESIGNATION", designation);
                ed.putString("WORKNATURE", worknature);
                ed.putString("BUSINESS_LOC", busiloc);
                ed.apply();
                Log.d("ValueOf",""+sp.getString("FNAME","")+sp.getString("LNAME","")+
                        sp.getString("LOCALITY",""));
                Intent intent = new Intent(getApplicationContext(),need_require.class);
                //Update Data Value
                intent.putExtra("ID", getIntent().getStringExtra("ID"));
                //need and requirement
                intent.putExtra("CONFIG_ONE",getIntent().getStringExtra("CONFIG_ONE"));
                intent.putExtra("CONFIG_TWO",getIntent().getStringExtra("CONFIG_TWO"));
                intent.putExtra("CONFIG_THREE",getIntent().getStringExtra("CONFIG_THREE"));
                intent.putExtra("CONFIG_OTHER",getIntent().getStringExtra("CONFIG_OTHER"));
                intent.putExtra("SPECIFY", getIntent().getStringExtra("SPECIFY"));
                intent.putExtra("BUDGET", getIntent().getStringExtra("BUDGET"));
                intent.putExtra("HOMELOAN", getIntent().getStringExtra("HOMELOAN"));
                intent.putExtra("BANKNAME", getIntent().getStringExtra("BANKNAME"));
                intent.putExtra("PURCHASE", getIntent().getStringExtra("PURCHASE"));
                intent.putExtra("RESIDENTAL", getIntent().getStringExtra("RESIDENTAL"));
                //about project
                intent.putExtra("SOURCE_ADV",getIntent().getStringExtra("SOURCE_ADV"));
                intent.putExtra("NEWSPAPER_ADV", getIntent().getStringExtra("NEWSPAPER_ADV"));
                intent.putExtra("NEWSPAPER_INSERT", getIntent().getStringExtra("NEWSPAPER_INSERT"));
                intent.putExtra("HORDING", getIntent().getStringExtra("HORDING"));
                intent.putExtra("ADVERTISMENT", getIntent().getStringExtra("ADVERTISMENT"));
                intent.putExtra("TELECALLING", getIntent().getStringExtra("TELECALLING"));
                intent.putExtra("BROKER_FNAME",getIntent().getStringExtra("BROKER_FNAME"));
                intent.putExtra("BROKER_LNAME",getIntent().getStringExtra("BROKER_LNAME"));
                //Bundle Data
                BundleData(intent);
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_CODE_DATA);
            }
        });

    }

    //Bundle Data
    private void BundleData(Intent intent) {
        bundle.putString("GENDER",getGenderVal);
        bundle.putString("STATUS",getStatusval);
        bundle.putString("COMPANY_NAME", companyname);
        bundle.putString("OCCUPATION", occupationss);
        bundle.putString("DESIGNATION", designation);
        bundle.putString("WORKNATURE", worknature);
        bundle.putString("BUSINESS_LOC", busiloc);
        intent.putExtras(bundle);
    }


    private void occupationfeild() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.occupation_item, occupations);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        occupation.setAdapter(arrayAdapter);
        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                occupationss = (occupations[position].toString());
                // occupation.setSelection(getIntent().getBooleanExtra("OCCUPATION",true)[position].toString(),true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                occupationss = "Null";
            }
        });
    }
}