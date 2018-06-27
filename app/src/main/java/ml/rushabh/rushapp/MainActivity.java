package ml.rushabh.rushapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView mUserEmail;
    private TextView mUserName;


    private EditText mTitle;
    private EditText mDepartment;
    private EditText mONGCId;
    private EditText mQuery;
    private Button mSubmit;

    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = FirebaseAuth.getInstance().getCurrentUser();


        if (mUser != null) {
            // User is signed in
            mUserEmail = (TextView)findViewById(R.id.email_tv);
            mUserName = (TextView)findViewById(R.id.userName_tv);
            mTitle = (EditText)findViewById(R.id.Title_tv);
            mDepartment = (EditText)findViewById(R.id.Department_tv);
            mONGCId = (EditText)findViewById(R.id.ONGCId_tv);
            mQuery = (EditText)findViewById(R.id.Query_tv);
            mSubmit = (Button) findViewById(R.id.Submit_button);



            mDatabase = FirebaseDatabase.getInstance().getReference();
            mSubmit.setEnabled(true);
            mUserEmail.append(mUser.getEmail());
            mUserName.append(mUser.getDisplayName());

            mSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSubmit.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Uploading Data...",Toast.LENGTH_SHORT).show();

                    Complain complain = new Complain(mUser.getDisplayName(),mUser.getEmail(),mTitle.getText().toString(),
                            mDepartment.getText().toString(),mONGCId.getText().toString(),
                            mQuery.getText().toString());
                    mDatabase.child("complaints").push().setValue(complain).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Data upload complete",Toast.LENGTH_SHORT).show();
                            mSubmit.setEnabled(true);
                            clearForm();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Data upload failed. Try again later.",Toast.LENGTH_SHORT).show();
                            mSubmit.setEnabled(true);
                        }
                    });
                }
            });

        }
        else {
            Intent intent = new Intent(this,IntroActivity.class);
            startActivity(intent);
            finish();
        }

    }

    void clearForm(){
        mTitle.setText("");
        mQuery.setText("");
        mONGCId.setText("");
        mDepartment.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(R.id.sign_out == item.getItemId()){
            AuthUI.getInstance()
                    .signOut(this);
            intent = new Intent(MainActivity.this,IntroActivity.class);
            startActivity(intent);
            finish();

        }
        else if(R.id.All_complains == item.getItemId()){
            intent = new Intent(MainActivity.this,ComplainList.class);
            startActivity(intent);
        }
        return true;
    }

}
