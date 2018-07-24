package com.example.admin.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.firebase.manager.AuthManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class LoginActivity extends AppCompatActivity implements AuthManager.Callback,SignInButton.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName() + "_TAG";
    private FirebaseAuth mAuth;
    private EditText etPassword;
    private EditText etEmail;
    private AuthManager authManager;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private GoogleSignInClient googleClient;
    private SignInButton googleSignInButton;
    private TwitterLoginButton twitterLoginButton;
    private Boolean facebookorTwitter;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Twitter must be set up here or button will be greyed out
        Twitter.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        authManager = AuthManager.getInstance(this);
        callbackManager = CallbackManager.Factory.create();
        facebookorTwitter = true;
        etPhone = findViewById(R.id.etPhone);

        SetUpGoogleSignIn();

        SetUpFacebookLoginButton();
        SetUpTwitterSignIn();



    }

    private void SetUpTwitterSignIn() {

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(String.valueOf(R.string.com_twitter_sdk_android_CONSUMER_KEY), String.valueOf(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true).build();

        Twitter.initialize(twitterConfig);

        twitterLoginButton = findViewById(R.id.twitter_login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "success: ");
                facebookorTwitter=false;
                authManager.handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "failure: ");
                facebookorTwitter=false;
            }
        });
    }

    private void SetUpGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        //check if user is already signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            //updateUI(account);
        }

        googleSignInButton = findViewById(R.id.sign_in_button);
        googleSignInButton.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

        if (authManager.checkSession()) {
            //redirect to different activity
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void onCreateAccount(View view) {
        authManager.createAccount(etEmail.getText().toString(), etPassword.getText().toString());
    }

    public void onSignIn(View view) {
        authManager.signIn(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void onLoginResults(FirebaseUser user) {

        if (user != null) {
            startHomeActivity();
            ;
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void onSignOut() {

    }

    private void SetUpFacebookLoginButton() {

        //check facebook login status

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                //log in
                Log.d(TAG, "onSuccess: " + loginResult);
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                facebookorTwitter=true;
                //   LoginManager.getInstance().logInWithReadPermissions(getParent(), Arrays.asList("public_profile"));
                authManager.handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

                Log.d(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {

                Log.d(TAG, "onError: " + exception.getMessage());
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//if we signed in to google
        if (requestCode == authManager.RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult: Google");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//handle sign in result
            authManager.handleSignInResult(task);
        } else if (requestCode==64206) {
            Log.d(TAG, "onActivityResult: Facebook Request: "+requestCode+"Result: "+resultCode);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult: Twitter Request: "+requestCode+"Result: "+resultCode);
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                authManager.googleSignIn(googleClient);
                break;
        }
    }



    public void onSignInWithPhone(View view) {

        String phoneNumber = etPhone.getText().toString();
        Log.d(TAG, "onSignInWithPhone: "+phoneNumber);

        if(phoneNumber==""){
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted: ");
                authManager.signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d(TAG, "onVerificationFailed: ");
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }
        });
    }

}
