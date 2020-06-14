package om.example.android.androidproject.view;

import android.os.Bundle;
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
public class NewAccountFragment extends Fragment {

    TextInputLayout username;
    TextInputLayout email;
    TextInputLayout password;
    Button createAccount;
    private FirebaseAuth mAuth;
    public NewAccountFragment() {
        // Required empty public constructor
    }

    public static NewAccountFragment newInstance() {
        return new NewAccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_account, container, false);
        username = rootView.findViewById(R.id.usernameForCreateAccount);
        email = rootView.findViewById(R.id.emailForCreateAccount);
        password = rootView.findViewById(R.id.passwordForCreateAccount);
        createAccount = rootView.findViewById(R.id.ConfirmCreateButton);
        mAuth = FirebaseAuth.getInstance();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = username.getEditText().getText().toString();
                String emailText = email.getEditText().getText().toString();
                String passwordText = password.getEditText().getText().toString();
                if (!emailText.equals("") && !usernameText.equals("") && !passwordText.equals("")) {
                mAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LoginFragment loginFragment = LoginFragment.newInstance();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                            transaction.replace(R.id.fragmentReplacer, loginFragment);
                            transaction.commit();
                        } else {
                            task.getException();
                            Toast.makeText(getActivity(), "Authentication failed." +
                                            "Exception : " + task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
                else
                {
                    Toast.makeText(getActivity(), R.string.empty_fields,
                            Toast.LENGTH_SHORT).show();
                }
        }



    });
        return rootView;
}
}
