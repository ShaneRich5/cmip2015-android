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
public class LoginFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "LoginFragment";
    public static final CharSequence TITLE = "Login";

    private OnLoginListener mListener;

    private CircularProgressBar mProgress;
    private EditText etUsername, etPassword;
    private Button btnLogin, btnCancel;

    private String username, password;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
    }

    public CharSequence getTitle() {
        return "Login";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mProgress = (CircularProgressBar) root.findViewById(R.id.circular_progress);

        etUsername = (EditText) root.findViewById(R.id.username);
        etPassword = (EditText) root.findViewById(R.id.password);

        btnLogin = (Button) root.findViewById(R.id.login);
        btnCancel = (Button) root.findViewById(R.id.cancel);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                mProgress.setVisibility(View.VISIBLE);

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                int response;

                mListener.attemptLogin(username, password);

//                if (mListener.attemptLogin(username, password) == Util.FAIL)
//                {
                mProgress.setVisibility(View.INVISIBLE);
//                }

                break;
            case R.id.cancel:
                mListener.cancelLogin();
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLoginListener {
        public int attemptLogin(String username, String password);
        public void cancelLogin();
    }
}
