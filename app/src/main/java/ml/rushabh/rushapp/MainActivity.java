package ml.rushabh.rushapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView email_tv;
    TextView name_tv;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_tv = (TextView)findViewById(R.id.user_email);
        name_tv = (TextView)findViewById(R.id.user_name);
        mUser = mAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            // User is signed in
            email_tv.append(mUser.getEmail());
            name_tv.append(mUser.getDisplayName());
        } else {
            // No user is signed in
            email_tv.append("No user is signed in");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.sign_out == item.getItemId()){
            AuthUI.getInstance()
                    .signOut(this);
            Intent intent = new Intent(MainActivity.this,IntroActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        AuthUI.getInstance()
                .signOut(this);
    }
}
