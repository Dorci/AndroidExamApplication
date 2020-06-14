package om.example.android.androidproject.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import om.example.android.androidproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private View rootView;
    private TextInputLayout editTextUsername;
    private TextInputLayout editTextPassword;
    private Button login;
    private Button createAccount;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        editTextUsername = rootView.findViewById(R.id.username);
        editTextPassword = rootView.findViewById(R.id.password);
        createAccount = rootView.findViewById(R.id.CreateAccountButton);
        getActivity().setTitle("Login");
        login = rootView.findViewById(R.id.LoginButton);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login button executed");

                String email = editTextUsername.getEditText().getText().toString();
                String password = editTextPassword.getEditText().getText().toString();
                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(editTextUsername.getEditText().getText().toString(), editTextPassword.getEditText().getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e(TAG, "onComplete: " + editTextUsername.getEditText().getText().toString());
                                Log.e(TAG, "onComplete: " + editTextPassword.getEditText().getText().toString());
                                AllPoemsFragment allPoemsFragment = AllPoemsFragment.newInstance();

                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                BaseLayoutFragment baseLayoutFragment = BaseLayoutFragment.newInstance();
                                transaction.replace(R.id.fragmentReplacer, baseLayoutFragment);
                                transaction.replace(R.id.base_frame_layout, allPoemsFragment);
                                getActivity().setTitle("All Poems");

                                transaction.commit();

                            } else {
                                Toast.makeText(getActivity(), R.string.empty_fields,
                                        Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onComplete: Pass" + editTextPassword.getEditText().getText().toString());
                                Log.i(TAG, "onComplete: Email" + editTextUsername.getEditText().getText().toString());
                                Log.i(TAG, "onComplete: error" + task.getException().toString());
                            }

                        }
                    });
                } else {

                    CharSequence text = getString(R.string.empty_fields);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: new account");

                NewAccountFragment newAccountFragment = NewAccountFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragmentReplacer, newAccountFragment);
                transaction.addToBackStack("Login Fragment");
                transaction.commit();

            }


        });
        return rootView;

    }


}


