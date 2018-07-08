package ml.rushabh.rushapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class ComplainList extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView mListView;

    private ChildEventListener mcomplainListener;

    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);

        mDatabase = FirebaseDatabase.getInstance().getReference("complains");

        mListView = (RecyclerView)findViewById(R.id.listView_comp);

        mListView.setLayoutManager(new LinearLayoutManager(this));

        class ComplainHolder extends RecyclerView.ViewHolder{

            TextView titleTextView;
            TextView queryTextView;
            TextView emailTextView;
            public ComplainHolder(View itemView) {
                super(itemView);
                titleTextView = (TextView)itemView.findViewById(R.id.titleTextView);
                queryTextView = (TextView)itemView.findViewById(R.id.queryTextView);
                emailTextView = (TextView)itemView.findViewById(R.id.emailTextView);
            }
            public void setTitleTextView(String title){
                titleTextView.setText(title);
            }
            public void setQueryTextView(String query){
                queryTextView.setText(query);
            }

            public void setEmailTextView(String email) {
                emailTextView.setText(email);
            }
        }

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("complains")
                .limitToLast(50);

        FirebaseRecyclerOptions<Complain> options = new FirebaseRecyclerOptions.Builder<Complain>()
                .setQuery(query, Complain.class)
                .build();


        adapter = new FirebaseRecyclerAdapter<Complain, ComplainHolder>(options){
            @NonNull
            @Override
            public ComplainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_complain, parent, false);
                return new ComplainHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ComplainHolder holder, int position, @NonNull Complain model) {
                Complain complain = getItem(position);
                holder.setTitleTextView(complain.getTitle());
                holder.setQueryTextView(complain.getQuery());
                holder.setEmailTextView(complain.getEmail());

            }
        };

        mListView.setAdapter(adapter);


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        };
        query.addChildEventListener(childEventListener);

    }


    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
