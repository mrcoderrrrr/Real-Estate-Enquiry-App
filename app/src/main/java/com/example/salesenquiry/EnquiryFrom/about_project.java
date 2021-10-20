package com.example.salesenquiry.EnquiryFrom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesenquiry.Database.FormDB;
import com.example.salesenquiry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.jar.Attributes;

public class about_project extends AppCompatActivity {
    Spinner newsadv, newsinsert, sourceadv;
    TextView pleasure, aboutproject, newsadvtxt, newsinserttxt, sourcetxt;
    TextInputEditText hording, digital, telecalling, source, broker_fname, broker_lname;
    TextInputLayout hordingLay, digitalLay, telecallingLay, broker_fname_Lay, broker_lname_Lay;
    String hordindloc, digitals, brokers_lname, brokers_fname, telecallings;
    FormDB formDB;
    SharedPreferences sp;
    Button submit, submitbut, updateData;
    Dialog submit_dialog, update_dialog;
    DataModel dataModel;
    DatabaseReference dbreference;
    Cursor cursor;
    String FName, LName, Locality, City, Timetocall, Phone, Altphone, Email,
            Gender, Status, Occupation, Company_name, Designation, Work_nature, Business_location,
            Config_One, Config_Two, Config_Three, Config_Other, Specify, Budget, Purchase, Loan, Bankname, Residantal,
            Source_Adv, Newspaper_Adv, Newspaper_Insert, Hording, Advertisement, Telecalling, Broker_Fname, Broker_lname;
    int Pincode;
    String newsAdvOpt[] = {"Times Of India", "Mumbai Mirror,Mid-Day", "Navbhart Times", "Maharashtra Times", "Mumbai Samachar", "Gujrat Samachar", "Any Others"};
    String newsInsertOpt[] = {"Times Of India", "Mumbai Mirror,Mid-Day", "Navbhart Times", "Maharashtra Times", "Mumbai Samachar", "Gujrat Samachar", "Any Others"};
    ArrayList<DataModel> dataView = new ArrayList<DataModel>();
    FirebaseDatabase db;
    FirebaseAuth firebaseAuth;
    String newsadvval, newsinsertval, sourceval;
    //Source Spinner
    String sourceOpt[] = {"News Paper", "Inserts", "Hordings", "Digital Advertisement", "Tele Calling", "Broker Name", "Direct Reference"};
    int FormId;
    Bundle bundle;
    FirebaseUser user;
    long count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_project);
        pleasure = findViewById(R.id.pleasuretxt);
        aboutproject = findViewById(R.id.aboutproject);
        newsadvtxt = findViewById(R.id.newsadvtxt);
        newsinserttxt = findViewById(R.id.newsinserttxt);
        newsadv = findViewById(R.id.newsadv);
        newsinsert = findViewById(R.id.newsinsert);
        hording = findViewById(R.id.hording);
        digital = findViewById(R.id.digital);
        telecalling = findViewById(R.id.telecalling);
        sourceadv = findViewById(R.id.sourceadv);
        broker_fname = findViewById(R.id.broker_fname);
        broker_lname = findViewById(R.id.broker_lname);
        //layput
        hordingLay = findViewById(R.id.hordingLayout);
        digitalLay = findViewById(R.id.digitalLayout);
        telecallingLay = findViewById(R.id.teleLayout);
        broker_fname_Lay = findViewById(R.id.broker_fname_lay);
        broker_lname_Lay = findViewById(R.id.broker_lname_lay);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        submit = findViewById(R.id.submit);
        submit_dialog = new Dialog(this);
        update_dialog = new Dialog(this);
        //insert data in sqlite
        formDB = new FormDB(this);
        //firebase data get
        cursor = new FormDB(this).FetchCustData();
        db = FirebaseDatabase.getInstance();

        FormId = getIntent().getIntExtra("ID", 0);
        bundle = getIntent().getExtras();

        submitbut();
//        //Update Form
        UpdateFormData();
//Spinner


        if (FormId < 0) {
            SourceSpinner("");
            //Newspaper Advertisment Spineer
            NewspaperAdv("");
            //Newspaper Insert Spinner
            NewsaperInsert("");
        }

        Log.d("SOURCE_ADV_VAL", "" + getIntent().getStringExtra("SOURCE_ADV"));

    }

    //Source Spinner
    public void showElements(String name) {
        if (name == "News Paper") {
            newsadvtxt.setVisibility(View.VISIBLE);
            newsadv.setVisibility(View.VISIBLE);

            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);
            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);
            telecallingLay.setVisibility(View.GONE);
            broker_fname_Lay.setVisibility(View.GONE);
            broker_lname_Lay.setVisibility(View.GONE);
            telecalling.setVisibility(View.GONE);
            broker_fname.setVisibility(View.GONE);
            broker_lname.setVisibility(View.GONE);

        } else if (name == "Inserts") {
            newsinserttxt.setVisibility(View.VISIBLE);
            newsinsert.setVisibility(View.VISIBLE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);
            telecallingLay.setVisibility(View.GONE);
            broker_fname_Lay.setVisibility(View.GONE);
            broker_lname_Lay.setVisibility(View.GONE);
            telecalling.setVisibility(View.GONE);
            broker_fname.setVisibility(View.GONE);
            broker_lname.setVisibility(View.GONE);

        } else if (name == "Hordings") {
            hording.setVisibility(View.VISIBLE);
            hordingLay.setVisibility(View.VISIBLE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);

            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);
            telecallingLay.setVisibility(View.GONE);
            broker_fname_Lay.setVisibility(View.GONE);
            broker_lname_Lay.setVisibility(View.GONE);
            telecalling.setVisibility(View.GONE);
            broker_fname.setVisibility(View.GONE);
            broker_lname.setVisibility(View.GONE);
        } else if (name == "Digital Advertisement") {
            digital.setVisibility(View.VISIBLE);
            digitalLay.setVisibility(View.VISIBLE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);

            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
            telecallingLay.setVisibility(View.GONE);
            broker_fname_Lay.setVisibility(View.GONE);
            broker_lname_Lay.setVisibility(View.GONE);
            telecalling.setVisibility(View.GONE);
            broker_fname.setVisibility(View.GONE);
            broker_lname.setVisibility(View.GONE);
        } else if (name == "Tele Calling") {
            telecallingLay.setVisibility(View.VISIBLE);
            broker_fname_Lay.setVisibility(View.VISIBLE);
            broker_lname_Lay.setVisibility(View.VISIBLE);
            telecalling.setVisibility(View.VISIBLE);
            broker_fname.setVisibility(View.VISIBLE);
            broker_lname.setVisibility(View.VISIBLE);

            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);

            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
        } else if (name == "Broker Name") {
            telecallingLay.setVisibility(View.VISIBLE);
            broker_fname_Lay.setVisibility(View.VISIBLE);
            broker_lname_Lay.setVisibility(View.VISIBLE);
            telecalling.setVisibility(View.VISIBLE);
            broker_fname.setVisibility(View.VISIBLE);
            broker_lname.setVisibility(View.VISIBLE);

            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);

            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
        } else {
            telecallingLay.setVisibility(View.VISIBLE);
            broker_fname_Lay.setVisibility(View.VISIBLE);
            broker_lname_Lay.setVisibility(View.VISIBLE);
            telecalling.setVisibility(View.VISIBLE);
            broker_fname.setVisibility(View.VISIBLE);
            broker_lname.setVisibility(View.VISIBLE);

            digital.setVisibility(View.GONE);
            digitalLay.setVisibility(View.GONE);

            newsadvtxt.setVisibility(View.GONE);
            newsadv.setVisibility(View.GONE);
            newsinserttxt.setVisibility(View.GONE);
            newsinsert.setVisibility(View.GONE);

            hording.setVisibility(View.GONE);
            hordingLay.setVisibility(View.GONE);
        }
    }

    //Newspaper Advertisment
    private void NewspaperAdv(String value) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.newspaper_advertise, newsAdvOpt);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newsadv.setAdapter(arrayAdapter);
        if (value != null) {
            int spinnerPosition = arrayAdapter.getPosition(value);
            Log.d("position", "" + spinnerPosition);
            newsadv.setSelection(spinnerPosition);
        }
        newsadv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newsadvval = (newsAdvOpt[position].toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newsadvval = "Null";
            }
        });
    }

    //Insert Newspaper
    private void NewsaperInsert(String value) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.newspaper_insert, newsInsertOpt);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newsinsert.setAdapter(arrayAdapter);

        if (value != null) {
            int spinnerPosition = arrayAdapter.getPosition(value);
            Log.d("position", "" + spinnerPosition);
            newsinsert.setSelection(spinnerPosition);
        }
        newsinsert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newsinsertval = (newsInsertOpt[position].toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newsinsertval = "Null";
            }
        });
    }

    //Source Spinner
    public void SourceSpinner(String value) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.source_spinner, sourceOpt);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceadv.setAdapter(arrayAdapter);

        if (value != null) {
            int spinnerPosition = arrayAdapter.getPosition(value);
            Log.d("position", "" + spinnerPosition);
            sourceadv.setSelection(spinnerPosition);
//            showElements(value);
        }


//set the default according to value


        sourceadv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceval = (sourceOpt[position].toString());
                showElements(sourceval);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newsinsertval = "Null";
            }
        });

    }

    ////Form Update Data
    private void UpdateFormData() {
//    newspaper.setText(getIntent().getStringExtra("ENTER_NEWSPAPER"));
        hording.setText(getIntent().getStringExtra("HORDING"));
        digital.setText(getIntent().getStringExtra("ADVERTISMENT"));
        telecalling.setText(getIntent().getStringExtra("TELECALLING"));
        broker_fname.setText(getIntent().getStringExtra("BROKER_FNAME"));
        broker_lname.setText(getIntent().getStringExtra("BROKER_LNAME"));
        Log.d("sourceValue", "" + getIntent().getStringExtra("SOURCE_ADV"));
        Log.d("sourceValue1", "" + getIntent().getStringExtra("NEWSPAPER_ADV"));
        Log.d("sourceValue2", "" + getIntent().getStringExtra("NEWSPAPER_INSERT"));
        SourceSpinner(getIntent().getStringExtra("SOURCE_ADV"));
        NewsaperInsert(getIntent().getStringExtra("NEWSPAPER_ADV"));
        NewspaperAdv(getIntent().getStringExtra("NEWSPAPER_INSERT"));
    }

    //Submit Details Dialog Box
    private void SubmitDialog() {
        submit_dialog.setContentView(R.layout.details_submit);
        submit_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submitbut = submit_dialog.findViewById(R.id.detailsubmit);
        submitbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.salesenquiry.welcome.class);
                startActivityForResult(intent, 2);
            }
        });
        submit_dialog.show();
    }

    //Update Dialog Box
//Submit Details Dialog Box
    private void UpdateDialog() {
        update_dialog.setContentView(R.layout.update_dialog);
        update_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateData = update_dialog.findViewById(R.id.dataupdate);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.salesenquiry.welcome.class);
                startActivity(intent);
                finish();
            }
        });
        update_dialog.show();
    }

    //Submit Button
    private void submitbut() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hordindloc = hording.getText().toString();
                digitals = digital.getText().toString();
                brokers_fname = broker_fname.getText().toString();
                brokers_lname = broker_lname.getText().toString();
                telecallings = telecalling.getText().toString();
                sp = getSharedPreferences("DetailsKey", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("SOURCE_ADV", sourceval);
                ed.putString("NEWSPAPER_ADV", newsadvval);
                ed.putString("NEWSPAPER_INSERT", newsinsertval);
                ed.putString("HORDINGS", hordindloc);
                ed.putString("DIGITAL_ADV", digitals);
                ed.putString("BROKER_FNAME", brokers_fname);
                ed.putString("BROKER_LNAME", brokers_lname);
                ed.putString("TELECALLING", telecallings);
                ed.apply();
                FName = sp.getString("FNAME", "");
                LName = sp.getString("LNAME", "");
                Locality = sp.getString("LOCALITY", "");
                City = sp.getString("CITY", "");
                Pincode = sp.getInt("PINCODE", 0);
                Timetocall = sp.getString("TIMER", "");
                Phone = sp.getString("PHONE", "");
                Altphone = sp.getString("ALTPHONE", "");
                Email = sp.getString("EMAIL", "");
                Gender = sp.getString("GENDER", "");
                Log.d("GENDER_VAL", "" + sp.getString("GENDER", ""));
                Status = sp.getString("STATUS", "");
                Occupation = sp.getString("OCCUPATION", "");
                Company_name = sp.getString("COMPANY_NAME", "");
                Designation = sp.getString("DESIGNATION", "");
                Work_nature = sp.getString("WORKNATURE", "");
                Business_location = sp.getString("BUSINESS_LOC", "");
                Config_One = sp.getString("CONFIG_ONE", "");
                Config_Two = sp.getString("CONFIG_TWO", "");
                Config_Three = sp.getString("CONFIG_THREE", "");
                Config_Other = sp.getString("CONFIG_OTHER", "");
                Specify = sp.getString("SPECIFY", "");
                Budget = sp.getString("BUDGETS", "");
                Purchase = sp.getString("PURCHASE", "");
                Loan = sp.getString("LOAN", "");
                Bankname = sp.getString("BANKNAME", "");
                Residantal = sp.getString("RESIDENTAL", "");
                Source_Adv = sp.getString("SOURCE_ADV", "");
                Newspaper_Adv = sp.getString("NEWSPAPER_ADV", "");
                Newspaper_Insert = sp.getString("NEWSPAPER_INSERT", "");
                Hording = sp.getString("HORDINGS", "");
                Advertisement = sp.getString("DIGITAL_ADV", "");
                Telecalling = sp.getString("TELECALLING", "");
                Broker_Fname = sp.getString("BROKER_FNAME", "");
                Broker_lname = sp.getString("BROKER_LNAME", "");
                Log.d("ValueOfForm", "" + FName + LName + Locality + City + Pincode + Timetocall + Phone + Altphone + Email +
                        Gender + Status + Occupation + Company_name + Designation + Work_nature + Business_location +
                        Config_One + Config_Two + Config_Three + Config_Other + Specify + Budget + Loan + Bankname + Purchase + Residantal +
                        Newspaper_Adv + Newspaper_Insert + Hording + Advertisement + Telecalling + Broker_Fname + Broker_lname);
//Insert Value in Database
//insert data
                //firebaseUpdate();
                // UpdateDialog();
                // firestoredata();
                //firestoredata();
                //Boolean checkValue = formDB.checkId(FormId);
                Log.d("CheckId", "" + FormId);
                if (FormId > 0) {
                    Boolean updateFormData = formDB.UpdateFormData(FormId, FName, LName, Locality, City, Pincode, Timetocall, Phone, Altphone, Email,
                            Gender, Status, Occupation, Company_name, Designation, Work_nature, Business_location,
                            Config_One, Config_Two, Config_Three, Config_Other, Specify, Budget, Loan, Bankname, Purchase, Residantal,
                            Source_Adv, Newspaper_Adv, Newspaper_Insert, Hording, Advertisement, Telecalling, Broker_Fname, Broker_lname);
                    if (updateFormData == true) {
                        if (!Phone.equals(0)) {
                            firebaseUpdate();
                            UpdateDialog();
                        } else {
                            Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Details Are Not Updatted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Boolean insertFormData = formDB.InsertFormData(FName, LName, Locality, City, Pincode, Timetocall, Phone, Altphone, Email,
                            Gender, Status, Occupation, Company_name, Designation, Work_nature, Business_location,
                            Config_One, Config_Two, Config_Three, Config_Other, Specify, Budget, Loan, Bankname, Purchase, Residantal,
                            Source_Adv, Newspaper_Adv, Newspaper_Insert, Hording, Advertisement, Telecalling, Broker_Fname, Broker_lname);
                    if (insertFormData == true) {
                        SubmitDialog();

                        firestoredata();
                        Log.d("DATAMODEL", "" + dataModel.id);
                    } else {
                        Toast.makeText(getApplicationContext(), "Details Are Not Submitted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    //ERROR========================================================================================
    //Firebase Value Store
    private void firestoredata() {
        dbreference = db.getReference("Sales Enquiry").child("Customer Data").child(user.getUid());//KEY SET USING NUMBER BECAUSE ID WAS NOT INCREMENT
        /*ERROR*/
       dbreference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
               if (snapshot.exists()){
                   count=(snapshot.getChildrenCount());
               }
           }

           @Override
           public void onCancelled(@NonNull @NotNull DatabaseError error) {

           }
       });

        dataModel = new DataModel();
       dataModel.setId((int) (count+1));
        dataModel.setFNAME(sp.getString("FNAME", ""));
        dataModel.setLNAME(sp.getString("LNAME", ""));
        dataModel.setLOCALITY(sp.getString("LOCALITY", ""));
        dataModel.setCITY(sp.getString("CITY", ""));
        dataModel.setPINCODE(sp.getInt("PINCODE", 0));
        dataModel.setTIME_TO_CALL(sp.getString("TIMER", ""));
        dataModel.setPHONE(sp.getString("PHONE", ""));
        dataModel.setALTPHONE(sp.getString("ALTPHONE", ""));
        dataModel.setEMAIL(sp.getString("EMAIL", ""));
        //Personal Details
        dataModel.setGENDER(sp.getString("GENDER", ""));
        dataModel.setSTATUS(sp.getString("STATUS", ""));
        dataModel.setOCCUPATION(sp.getString("OCCUPATION", ""));
        dataModel.setCOMPANY_NAME(sp.getString("COMPANY_NAME", ""));
        dataModel.setDESIGNATION(sp.getString("DESIGNATION", ""));
        dataModel.setWORK_NATURE(sp.getString("WORKNATURE", ""));
        dataModel.setBUSINESS_LOCATION(sp.getString("BUSINESS_LOC", ""));
        //Need And Requirment
        dataModel.setCONFIG_ONE(sp.getString("CONFIG_ONE", ""));
        dataModel.setCONFIG_TWO(sp.getString("CONFIG_TWO", ""));
        dataModel.setCONFIG_THREE(sp.getString("CONFIG_THREE", ""));
        dataModel.setCONFIG_OTHER(sp.getString("CONFIG_OTHER", ""));
        dataModel.setSPECIFY(sp.getString("SPECIFY", ""));
        dataModel.setBUDGET(sp.getString("BUDGETS", ""));
        dataModel.setLOAN(sp.getString("LOAN", ""));
        dataModel.setBANKNAME(sp.getString("BANKNAME", ""));
        dataModel.setPURCHASE(sp.getString("PURCHASE", ""));
        dataModel.setRESIDENTAL(sp.getString("RESIDENTAL", ""));
        //About Project
        dataModel.setSOURCE_ADV(sp.getString("SOURCE_ADV", ""));
        dataModel.setNEWSPAPER_ADV(sp.getString("NEWSPAPER_ADV", ""));
        dataModel.setNEWSPAPER_INSERT(sp.getString("NEWSPAPER_INSERT", ""));
        dataModel.setHORDING(sp.getString("HORDINGS", ""));
        dataModel.setADVERTISEMENT(sp.getString("DIGITAL_ADV", ""));
        dataModel.setTELECALLING(sp.getString("TELECALLING", ""));
        dataModel.setBROKER_FNAME(sp.getString("BROKER_FNAME", ""));
        dataModel.setBROKER_LNAME(sp.getString("BROKER_LNAME", ""));
        dataView.add(dataModel);
        dbreference.child(String.valueOf(count+1)).setValue(dataModel);
    }

    //Update Firebasedata
    public void firebaseUpdate() {
        HashMap hashMap = new HashMap();
        //personal Details
        hashMap.put("fname", FName);
        hashMap.put("lname", LName);
        hashMap.put("locality", Locality);
        hashMap.put("city", City);
        hashMap.put("pincode", Pincode);
        hashMap.put("time_TO_CALL", Timetocall);
        hashMap.put("phone", Phone);
        hashMap.put("altphone", Altphone);
        hashMap.put("email", Email);
        hashMap.put("gender", Gender);
        hashMap.put("status", Status);
        hashMap.put("occupation", Occupation);
        hashMap.put("company_NAME", Company_name);
        hashMap.put("work_NATURE", Work_nature);
        hashMap.put("designation", Designation);
        hashMap.put("business_LOCATION", Business_location);
        //Need And Requirement
        hashMap.put("config_ONE", Config_One);
        hashMap.put("config_TWO", Config_Two);
        hashMap.put("config_THREE", Config_Three);
        hashMap.put("config_OTHER", Config_Other);
        hashMap.put("specify", Specify);
        hashMap.put("budget", Budget);
        hashMap.put("loan", Loan);
        hashMap.put("bankname", Bankname);
        hashMap.put("residental", Residantal);
        //ABOUT PROJECT
        hashMap.put("newspaper_ADV", Newspaper_Adv);
        hashMap.put("newspaper_INSERT", Newspaper_Insert);
        hashMap.put("hording", Hording);
        hashMap.put("advertisement", Advertisement);
        hashMap.put("telecalling", Telecalling);
        hashMap.put("broker_FNAME", Broker_Fname);
        hashMap.put("broker_LNAME", Broker_lname);
        FirebaseDatabase.getInstance().getReference("Sales Enquiry")
                .child("Customer Data")
                .child(sp.getString("PHONE", ""))
                .updateChildren(hashMap)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task task) {
                        Toast.makeText(getApplicationContext(), "Update data", Toast.LENGTH_LONG).show();
                    }
                });
    }

    //onBackPressedButton
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        //Bundle Data
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}