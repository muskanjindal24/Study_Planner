package com.example.myhomeworkplanner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }
    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullname;
    private EditText password;
    private EditText confirmPassword;

    private ImageButton closeBtn;
    private Button signUpBtn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        alreadyHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account);
        email = view.findViewById(R.id.sing_up_email);
        fullname = view.findViewById(R.id.sign_up_fullname);
        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);

        signUpBtn = view.findViewById(R.id.sign_up_btn);

        progressBar = view.findViewById(R.id.sign_up_progressBar);

        firebaseAuth =  FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todosend data to firebase
                checkEmailAndPassword();
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slid_from_left,R.anim.slidout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(fullname.getText()))
            {
                if(!TextUtils.isEmpty(password.getText()) && password.length()>=6)
                {
                    if(!TextUtils.isEmpty(confirmPassword.getText()))
                    {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(255,255,255));
                    }
                    else
                    {
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.rgb(183,183,183));
                    }
                }
                else
                {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.rgb(183,183,183));
                }
            }
            else
            {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(183,183,183));
            }
        }
        else
        {
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.rgb(183,183,183));
        }
    }
    private  void checkEmailAndPassword(){
        if(email.getText().toString().matches(emailPattern))
        {
            if(password.getText().toString().equals(confirmPassword.getText().toString()))
            {
                progressBar.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(183,183,183));
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Map<Object,String> userdata = new HashMap<>();
                                    userdata.put("fullname",fullname.getText().toString());
                                    firebaseFirestore.collection("USERS")
                                            .add(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<DocumentReference> task) {
                                                                           if(task.isSuccessful())
                                                                           {
                                                                               Intent mainIntent = new Intent(getActivity(),MainActivityHP.class);
                                                                               startActivity(mainIntent);
                                                                               getActivity().finish();
                                                                           }
                                                                           else
                                                                           {
                                                                               progressBar.setVisibility(View.INVISIBLE);
                                                                               signUpBtn.setEnabled(true);
                                                                               signUpBtn.setTextColor(Color.rgb(255,255,255));
                                                                               String error = task.getException().getMessage();
                                                                               Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT ).show();
                                                                           }
                                                                       }
                                                                   }
                                            );

                                }
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpBtn.setEnabled(true);
                                    signUpBtn.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT ).show();
                                }
                            }
                        });
            }
            else
            {
                confirmPassword.setError("Password Doesn't Match..!");
            }
        }
        else
        {
            email.setError("Invalid Email..!");

        }
    }
}