package com.example.salesenquiry.EnquiryFrom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesenquiry.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.temporal.TemporalAdjusters;

import static android.content.ContentValues.TAG;

public class need_require extends AppCompatActivity {
    Spinner budget;
    TextView next, previous, configurationtxt, budgettxt, purchasetxt, HomeLoan, Residantial, needtxt;
    EditText specify;
    TextInputEditText BankName;
    CheckBox one, two, three, other;
    String config_one = "", config_two = "", config_there = "", config_other = "";
    String getOne,getTwo, getThree,getOther;
    RadioGroup getLoan, getResident, getPurchase;
    RadioButton self_fund, housing_loan, approve, no_approve, owned, rent;
    String budgets[] = {"< 1Cr.", "1.0 - 1.25 Cr", "1.25 – 1.5 Cr", "1.5 – 2 Cr", "2 cr & above"};
    String clientbudget = " ";
    String Residentalval = " ", Loanval = " ", Purchaseval = " ", configurationval = " ";
    String getPurchaseval = " ", getResidentalval = " ", getLoanVal = " ";
    public final static int REQUEST_CODE_DATA = 1;
    Bundle bundle;
    int FormId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_require);
        needtxt = findViewById(R.id.needtxt);
        configurationtxt = findViewById(R.id.configrationtxt);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        other = findViewById(R.id.other);
        specify = findViewById(R.id.specify);
        budgettxt = findViewById(R.id.budgettxt);
        budget = findViewById(R.id.spinner);
        purchasetxt = findViewById(R.id.purchasetxt);
        self_fund = findViewById(R.id.self_fund);
        housing_loan = findViewById(R.id.housing_loan);
        approve = findViewById(R.id.approve);
        no_approve = findViewById(R.id.noapprove);
        HomeLoan = findViewById(R.id.loan);
        Residantial = findViewById(R.id.residental);
        owned = findViewById(R.id.owned);
        rent = findViewById(R.id.rent);
        BankName = findViewById(R.id.bankname);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        getPurchase = findViewById(R.id.purchaseval);
        getLoan = findViewById(R.id.loangroup);
        getResident = findViewById(R.id.typegroup);
        bundle = getIntent().getExtras();
      //  FormId= getIntent().getIntExtra("ID", 0);

        buttonclick();
        //Configuration Value
        configurationbox();
        //budget value
        //if(FormId < 0 ){
           budgetitem();
        //}
        //purchase value
        purchaseVal();
        //get Home Value
        loangrouptxt();
        //Residental Type
        typegrouptxt();
//        //Update Data
        UpdateFormData();
        //Set Spinner


    }

    //Update Form Data
    private void UpdateFormData() {
        try {
            //Nature Of Purchase
            getPurchaseval = getIntent().getStringExtra("PURCHASE");
            Log.d("getPurchase", getPurchaseval);
            if (getPurchaseval.equals("Self_Funding")) {
                self_fund.setChecked(true);
                housing_loan.setChecked(false);
            } else if (getPurchaseval.equals("Housing_Loan")) {
                self_fund.setChecked(false);
                housing_loan.setChecked(true);
            }
            //Residental
            getResidentalval = getIntent().getStringExtra("RESIDENTAL");
            if (getResidentalval.equals("Owned")) {
                owned.setChecked(true);
                rent.setChecked(false);
            } else if (getResidentalval.equals("Rented")) {
                owned.setChecked(false);
                rent.setChecked(true);
            }
            //Home Loan
            getLoanVal = getIntent().getStringExtra("HOMELOAN");
            if (getLoanVal.equals("Approve")) {
                approve.setChecked(true);
                no_approve.setChecked(false);
            } else if (getLoanVal.equals("No_Approve")) {
                approve.setChecked(false);
                no_approve.setChecked(true);
            }
            //Cofiguration
            getOne=getIntent().getStringExtra("CONFIG_ONE");
            getTwo=getIntent().getStringExtra("CONFIG_TWO");
            getThree=getIntent().getStringExtra("CONFIG_THREE");
            getOther=getIntent().getStringExtra("CONFIG_OTHER");
            Log.d("ConfigValA",getOther);
            if (getOne.equals("1BHK")){
                one.setChecked(true);
            }
            if (getTwo.equals("2BHK")){
                two.setChecked(true);
            }
            if (getThree.equals("3BHK")){
                three.setChecked(true);
            }
            if (getOther.equals("OTHER")){
                other.setChecked(true);
            }

            specify.setText(getIntent().getStringExtra("SPECIFY"));
            BankName.setText(getIntent().getStringExtra("BANKNAME"));
            Residentalval = getIntent().getStringExtra("RESIDENTAL");
            //budgetitem(getIntent().getStringExtra("BUDGET"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //Configuration
    private void configurationbox() {
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (one.isChecked()) {
                    config_one ="1BHK";
                }
            }
        });
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (two.isChecked()) {
                    config_two ="2BHK";
                }
            }
        });
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (three.isChecked()) {
                    config_there ="3BHK";
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (other.isChecked()) {
                    config_other ="OTHER";
                }
            }
        });
    }

    //Budget value
    private void budgetitem() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.budget_item, budgets);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        budget.setAdapter(arrayAdapter);
//
//        if(value != null){
//            int spinnerPosition = arrayAdapter.getPosition(value);
//            Log.d("position",""+spinnerPosition);
//            budget.setSelection(spinnerPosition);
//        }
        budget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clientbudget = (budgets[position].toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                clientbudget = "Null";
            }
        });
    }

    //Get purchase value
    private void purchaseVal() {
        getPurchase.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.self_fund == checkedId) {
                    Purchaseval = "Self_Funding";
                } else if (R.id.housing_loan == checkedId) {
                    Purchaseval = "Housing_Loan";
                } else {
                    Purchaseval = "Null";
                }
            }
        });
    }

    //Home Loan
    private void loangrouptxt() {
        getLoan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.approve == checkedId) {
                    Loanval = "Approve";
                } else if (R.id.noapprove == checkedId) {
                    Loanval = "No_Approve";
                } else {
                    Loanval = "Null";
                }
            }
        });
    }

    //Current Residental Type
    private void typegrouptxt() {
        getResident.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.owned == checkedId) {
                    Residentalval = "Owned";
                } else if (R.id.rent == checkedId) {
                    Residentalval = "Rented";
                } else {
                    Residentalval = "Null";
                }
            }
        });
    }

    //Next Previous
    private void buttonclick() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("DetailsKey", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                try {
                    ed.putString("CONFIG_ONE", config_one);
                    ed.putString("CONFIG_TWO", config_two);
                    ed.putString("CONFIG_THREE", config_there);
                    ed.putString("CONFIG_OTHER", config_other);
                    ed.putString("SPECIFY", specify.getText().toString());
                    ed.putString("BUDGETS", clientbudget);
                    ed.putString("PURCHASE", Purchaseval);
                    ed.putString("LOAN", Loanval);
                    ed.putString("BANKNAME", BankName.getText().toString());
                    ed.putString("RESIDENTAL", Residentalval);
                    ed.apply();
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                Log.d("GENDER_VAL",""+sp.getString("GENDER",""));
                Intent intent = new Intent(getApplicationContext(), about_project.class);
                //about project
                //Update DAta
                intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
                intent.putExtra("SOURCE_ADV",getIntent().getStringExtra("SOURCE_ADV"));
                intent.putExtra("NEWSPAPER_ADV",getIntent().getStringExtra("NEWSPAPER_ADV"));
                intent.putExtra("NEWSPAPER_INSERT",getIntent().getStringExtra("NEWSPAPER_INSERT"));
                intent.putExtra("HORDING", getIntent().getStringExtra("HORDING"));
                intent.putExtra("ADVERTISMENT", getIntent().getStringExtra("ADVERTISMENT"));
                intent.putExtra("TELECALLING", getIntent().getStringExtra("TELECALLING"));
                intent.putExtra("BROKER_FNAME",getIntent().getStringExtra("BROKER_FNAME"));
                intent.putExtra("BROKER_LNAME",getIntent().getStringExtra("BROKER_LNAME"));
                //bundle data
                BundleData(intent);
                startActivityForResult(intent, REQUEST_CODE_DATA);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Bundle Data
                BundleData(intent);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    //Bundle Data
    private void BundleData(Intent intent) {
        bundle.putString("CONFIGURATION", configurationval);
        bundle.putString("SPECIFY", specify.getText().toString());
        bundle.putString("BUDGETS", clientbudget);
        bundle.putString("PURCHASE", Purchaseval);
        bundle.putString("LOAN", Loanval);
        bundle.putString("BANKNAME", BankName.getText().toString());
        bundle.putString("RESIDENTAL", Residentalval);
        intent.putExtras(bundle);
    }

}