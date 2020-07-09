package com.example.studyplanner;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private List<ActualData> data= new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<ActualData> ad, List<String> keys);

    }

    public FirebaseDatabaseHelper(String s) {
        db=FirebaseDatabase.getInstance();
        ref=db.getReference("TotalSubj").child(s);
    }
    int i=1;
    public void readData(final DataStatus ds){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    ActualData ad= keyNode.getValue(ActualData.class);
                    data.add(ad);
                }
                ds.DataIsLoaded(data,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

