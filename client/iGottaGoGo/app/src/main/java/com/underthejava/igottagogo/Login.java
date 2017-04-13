package com.underthejava.igottagogo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLoginUser = (EditText) findViewById(R.id.etLoginUser);
        final EditText etLoginPass = (EditText) findViewById(R.id.etLoginPass);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(Login.this, MapsActivity.class);
                Login.this.startActivity(intent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String username = etLoginUser.getText().toString();
                final String password = etLoginPass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                Intent intent = new Intent(Login.this, MainActivity.class);

                                int userId = jsonResponse.getInt("user_id");
                                String firstName = jsonResponse.getString("first_name");
                                String lastName = jsonResponse.getString("last_name");
                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                String email = jsonResponse.getString("email");
                                int credits = jsonResponse.getInt("credits");


                                User currUser = User.getInstance();
                                currUser.buildUser(userId, username, password, email, credits, firstName, lastName);
                                intent.putExtra("curr_user", currUser);

                                Login.this.startActivity(intent);
                            }

                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }

    public void onBackPressed() {
        // do nothing
        // prevents user from hitting back button after logging out
    }
}
