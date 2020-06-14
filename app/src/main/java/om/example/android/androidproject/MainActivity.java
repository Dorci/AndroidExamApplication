package om.example.android.androidproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import om.example.android.androidproject.view.AllPoemsFragment;
import om.example.android.androidproject.view.BaseLayoutFragment;
import om.example.android.androidproject.view.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginFragment loginFragment = LoginFragment.newInstance();

        BaseLayoutFragment baseLayoutFragment = BaseLayoutFragment.newInstance();
        AllPoemsFragment allPoemsFragment = AllPoemsFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.e(TAG, "onStart: " + currentUser.getEmail());
            transaction.replace(R.id.fragmentReplacer, baseLayoutFragment);
            transaction.replace(R.id.base_frame_layout, allPoemsFragment);
            setTitle("All Poems");
        } else {
            transaction.replace(R.id.fragmentReplacer, loginFragment);
        }
        transaction.commit();
    }

}
