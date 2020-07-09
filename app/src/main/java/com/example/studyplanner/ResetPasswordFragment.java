package com.example.studyplanner;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPasswordFragment extends Fragment {



    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    private EditText registeresEmail;
    private Button resetPasswordBtn;
    private TextView goback;
    private FrameLayout parentFrameLayout;
    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emalIconText;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_reset_password, container, false);
        registeresEmail = view.findViewById(R.id.forgot_password_email);
        resetPasswordBtn = view.findViewById(R.id.reset_password_btn);
        goback = view.findViewById(R.id.tv_forgot_password_goback);
        firebaseAuth = FirebaseAuth.getInstance();

        emailIconContainer = view.findViewById(R.id.forgot_password_email_icon_container);
        emailIcon = view.findViewById(R.id.forgot_password_email_icon);
        emalIconText = view.findViewById(R.id.forgot_password_email_icon_text);
        progressBar = view.findViewById(R.id.forget_password_progressBar);

        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registeresEmail.addTextChangedListener(new TextWatcher() {
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
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(emailIconContainer);
                emalIconText.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(emailIconContainer);
                emalIconText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                resetPasswordBtn.setEnabled(false);
                resetPasswordBtn.setTextColor(Color.rgb(183,183,183));
                firebaseAuth.sendPasswordResetEmail(registeresEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,emailIcon.getWidth()/2,emailIcon.getHeight()/2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setRepeatCount(1);

                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            emailIcon.setColorFilter(getResources().getColor(R.color.successgreen));
                                            TransitionManager.beginDelayedTransition(emailIconContainer);
                                            emailIcon.setVisibility(View.VISIBLE);
                                            emalIconText.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                            emailIcon.setImageResource(R.drawable.ic_greenmail);
                                        }
                                    });


                                    emailIcon.startAnimation(scaleAnimation);


                                }
                                else
                                {
                                    String error = task.getException().getMessage();
                                    resetPasswordBtn.setEnabled(true);
                                    resetPasswordBtn.setTextColor(Color.rgb(255,255,255));
                                    emalIconText.setText(error);
                                    emalIconText.setTextColor(getResources().getColor(R.color.btnred));
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    emalIconText.setVisibility(View.VISIBLE);


                                }
                                progressBar.setVisibility(View.GONE);

                            }
                        });

            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
    }

    private void checkInputs()
    {
        if(TextUtils.isEmpty(registeresEmail.getText()))
        {
            resetPasswordBtn.setEnabled(false);
            resetPasswordBtn.setTextColor(Color.rgb(183,183,183));
        }
        else
        {
            resetPasswordBtn.setEnabled(true);
            resetPasswordBtn.setTextColor(Color.rgb(255,255,255));
        }
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slid_from_left,R.anim.slidout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}