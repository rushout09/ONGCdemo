package ml.rushabh.rushapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComplainList extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mListView;
    private ComplainAdapter mComplainAdapter;

    private ChildEventListener mcomplainListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);

        mDatabase = FirebaseDatabase.getInstance().getReference("complains");

        mListView = (ListView)findViewById(R.id.listView_comp);

        List<Complain> mComplainList = new ArrayList<>();
        mComplainAdapter = new ComplainAdapter(this, R.layout.item_complain,mComplainList);
        mListView.setAdapter(mComplainAdapter);
        attachDatabaseListener();

    }
    private void attachDatabaseListener(){
        if(mcomplainListener == null){
            mcomplainListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Complain complain = dataSnapshot.getValue(Complain.class);
                    mComplainAdapter.add(complain);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            mDatabase.addChildEventListener(mcomplainListener);
        }
    }
    private void detachDatabaseListener(){
        mDatabase.removeEventListener(mcomplainListener);
        mcomplainListener = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachDatabaseListener();
    }
}
