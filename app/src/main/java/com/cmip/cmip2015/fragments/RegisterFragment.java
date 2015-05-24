package com.cmip.cmip2015.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmip.cmip2015.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Shane on 5/24/2015.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    public static final CharSequence TITLE = "Register";

    private OnRegistrationListener mListener;

    private EditText etUsername, etEmail, etPassword;
    private Button btnRegister, btnCancel;
    private CircularProgressBar mProgress;

    private String username, email, password;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

        mProgress = (CircularProgressBar) root.findViewById(R.id.circular_progress);

        etUsername = (EditText) root.findViewById(R.id.username);
        etEmail = (EditText) root.findViewById(R.id.email);
        etPassword = (EditText) root.findViewById(R.id.password);

        btnRegister = (Button) root.findViewById(R.id.register);
        btnCancel = (Button) root.findViewById(R.id.cancel);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public CharSequence getTitle() {
        return "Register";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register:
                mProgress.setVisibility(View.VISIBLE);

                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                mListener.attemptRegistration(username,email,password);
//                if (mListener.attemptRegistration(username, email, password) == Util.FAIL)
//                {
                mProgress.setVisibility(View.INVISIBLE);
//                }

                break;
            case R.id.cancel:
                mListener.cancelRegistration();
                break;

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRegistrationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRegistrationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRegistrationListener {
        public int attemptRegistration(String username, String email, String password);
        public void cancelRegistration();
    }
}
