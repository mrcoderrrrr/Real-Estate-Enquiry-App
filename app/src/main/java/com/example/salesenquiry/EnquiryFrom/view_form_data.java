package com.example.salesenquiry.EnquiryFrom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.salesenquiry.Database.FormDB;
import com.example.salesenquiry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class view_form_data extends AppCompatActivity {
    RecyclerView rcv;
    ArrayList<DataModel> dataView = new ArrayList<DataModel>();
    DataModel dataModel;
    Cursor cursor;
    MyDataAdapter adapter;
    DatabaseReference reference;
    SharedPreferences sp;
    FirebaseDatabase db;
FirebaseAuth firebaseauth;
FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form_data);
        rcv = findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        cursor = new FormDB(this).FetchCustData();
        db=FirebaseDatabase.getInstance();
        //  FetchData();
        firebaseauth= FirebaseAuth.getInstance();
        user=firebaseauth.getCurrentUser();
        FetchFirebaseValue();
    }

    private void FetchFirebaseValue() {
       reference = db.getReference("Sales Enquiry").child("Customer Data").child(user.getUid());
        reference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataModel = new DataModel();
                    //Personal Details
                    Log.d("FNAMEVAL",""+dataSnapshot.child("fname").getValue().toString());
                    dataModel.setFNAME(dataSnapshot.child("fname").getValue().toString());
                    dataModel.setLNAME(dataSnapshot.child("lname").getValue().toString());
                    dataModel.setLOCALITY(dataSnapshot.child("locality").getValue().toString());
                    dataModel.setCITY(dataSnapshot.child("city").getValue().toString());
                    dataModel.setTIME_TO_CALL(dataSnapshot.child("time_TO_CALL").getValue().toString());
                    dataModel.setPINCODE(Integer.parseInt(dataSnapshot.child("pincode").getValue().toString()));
                    dataModel.setPHONE(dataSnapshot.child("phone").getValue().toString());
                    dataModel.setALTPHONE(dataSnapshot.child("altphone").getValue().toString());
                    dataModel.setEMAIL(dataSnapshot.child("email").getValue().toString());
                    dataModel.setGENDER(dataSnapshot.child("gender").getValue().toString());
                    dataModel.setSTATUS(dataSnapshot.child("status").getValue().toString());
                    dataModel.setOCCUPATION(dataSnapshot.child("occupation").getValue().toString());
                    dataModel.setCOMPANY_NAME(dataSnapshot.child("company_NAME").getValue().toString());
                    dataModel.setWORK_NATURE(dataSnapshot.child("designation").getValue().toString());
                    dataModel.setWORK_NATURE(dataSnapshot.child("work_NATURE").getValue().toString());
                    dataModel.setBUSINESS_LOCATION(dataSnapshot.child("business_LOCATION").getValue().toString());
                    //Need And Requirement
                    dataModel.setCONFIG_ONE(dataSnapshot.child("config_ONE").getValue().toString());
                    dataModel.setCONFIG_TWO(dataSnapshot.child("config_TWO").getValue().toString());
                    dataModel.setCONFIG_THREE(dataSnapshot.child("config_THREE").getValue().toString());
                    dataModel.setCONFIG_OTHER(dataSnapshot.child("config_OTHER").getValue().toString());
                    dataModel.setSPECIFY(dataSnapshot.child("specify").getValue().toString());
                    dataModel.setBUDGET(dataSnapshot.child("budget").getValue().toString());
                    dataModel.setPURCHASE(dataSnapshot.child("purchase").getValue().toString());
                    dataModel.setLOAN(dataSnapshot.child("loan").getValue().toString());
                    dataModel.setBANKNAME(dataSnapshot.child("bankname").getValue().toString());
                    dataModel.setRESIDENTAL(dataSnapshot.child("residental").getValue().toString());
                    //About Poject
                    dataModel.setNEWSPAPER_ADV(dataSnapshot.child("newspaper_ADV").getValue().toString());
                    dataModel.setNEWSPAPER_INSERT(dataSnapshot.child("newspaper_INSERT").getValue().toString());
                    dataModel.setHORDING(dataSnapshot.child("hording").getValue().toString());
                    dataModel.setADVERTISEMENT(dataSnapshot.child("advertisement").getValue().toString());
                    dataModel.setTELECALLING(dataSnapshot.child("telecalling").getValue().toString());
                    dataModel.setBROKER_FNAME(dataSnapshot.child("broker_FNAME").getValue().toString());
                    dataModel.setBROKER_LNAME(dataSnapshot.child("broker_LNAME").getValue().toString());
                    dataView.add(dataModel);
                }
                adapter = new MyDataAdapter(dataView, getApplicationContext());
                rcv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }


//    private void FetchData() {
//        while (cursor.moveToNext()) {
//            dataModel = new DataModel();
//            dataModel.setId(cursor.getInt(0));
//            dataModel.setFNAME(cursor.getString(1));
//            dataModel.setLNAME(cursor.getString(2));
//            dataModel.setLOCALITY(cursor.getString(3));
//            dataModel.setCITY(cursor.getString(4));
//            dataModel.setPINCODE(cursor.getInt(5));
//            dataModel.setTIME_TO_CALL(cursor.getString(6));
//            dataModel.setPHONE(cursor.getString(7));
//            dataModel.setALTPHONE(cursor.getString(8));
//            dataModel.setEMAIL(cursor.getString(9));
//            //Personal Details
//            dataModel.setGENDER(cursor.getString(10));
//            dataModel.setSTATUS(cursor.getString(11));
//            dataModel.setOCCUPATION(cursor.getString(12));
//            dataModel.setCOMPANY_NAME(cursor.getString(13));
//            dataModel.setDESIGNATION(cursor.getString(14));
//            dataModel.setWORK_NATURE(cursor.getString(15));
//            dataModel.setBUSINESS_LOCATION(cursor.getString(16));
//            //Need And Requirment
//            dataModel.setCONFIG_ONE(cursor.getString(17));
//            dataModel.setCONFIG_TWO(cursor.getString(18));
//            dataModel.setCONFIG_THREE(cursor.getString(19));
//            dataModel.setCONFIG_OTHER(cursor.getString(20));
//            dataModel.setSPECIFY(cursor.getString(21));
//            dataModel.setBUDGET(cursor.getString(22));
//            dataModel.setLOAN(cursor.getString(23));
//            dataModel.setBANKNAME(cursor.getString(24));
//            dataModel.setPURCHASE(cursor.getString(25));
//            dataModel.setRESIDENTAL(cursor.getString(26));
//            //About Project
//            dataModel.setSOURCE_ADV(cursor.getString(27));
//            dataModel.setNEWSPAPER_ADV(cursor.getString(28));
//            dataModel.setNEWSPAPER_INSERT(cursor.getString(29));
//            dataModel.setHORDING(cursor.getString(30));
//            dataModel.setADVERTISEMENT(cursor.getString(31));
//            dataModel.setTELECALLING(cursor.getString(32));
//            dataModel.setBROKER_FNAME(cursor.getString(33));
//            dataModel.setBROKER_LNAME(cursor.getString(34));
//            dataView.add(dataModel);
//        }
//        adapter = new MyDataAdapter(dataView, getApplicationContext());
//        rcv.setAdapter(adapter);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), com.example.salesenquiry.welcome.class));
        finish();
    }
}