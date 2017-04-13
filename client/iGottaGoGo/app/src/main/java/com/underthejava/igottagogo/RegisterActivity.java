package com.underthejava.igottagogo;
import android.app.Dialog;
import android.app.AlertDialog;
//import android.content.DialogInterface;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.*;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;
import android.widget.*;
import android.content.Intent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import android.util.Log;
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etRegisterUser = (EditText) findViewById(R.id.etRegisterUser);
        final EditText etRegisterPass = (EditText) findViewById(R.id.etRegisterPass);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        final Button bSubmit = (Button) findViewById(R.id.bSubmit);

        bSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.d("BUTTON","Button Clicked");
                final String firstname = etFirstName.getText().toString();
                final String lastname = etLastName.getText().toString();
                final String username = etRegisterUser.getText().toString();
                final String password = etRegisterPass.getText().toString();
                final String email = etEmail.getText().toString();
                /*
                BufferedWriter bw = null;
                FileWriter fw = null;

                try {

                    String content = username + password + "\n";

                    fw = new FileWriter("/Users/vincentdelacruz/Documents/CSC301/Final/Register/app/src/main/java/com/example/vincentdelacruz/register/accounts.txt");
                    bw = new BufferedWriter(fw);
                    bw.write(content);

                    System.out.println("Done");
                    bw.close();
                } catch (IOException e) {

                    e.printStackTrace();

                }

                Intent intent = new Intent(RegisterActivity.this, Login.class);
                RegisterActivity.this.startActivity(intent);
                */
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            Log.d("JSON RESPONSE", "GOT A JSON RESPONSE");
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            /*
                            BufferedWriter bw = null;
                            FileWriter fw = null;

                            try {

                                String content = username + password;

                                fw = new FileWriter("accounts.txt");
                                bw = new BufferedWriter(fw);
                                bw.write(content);

                                System.out.println("Done");
                                bw.close();
                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                            */

                            if (success)
                            {
                                Intent intent = new Intent(RegisterActivity.this, Login.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, username, password, email, responseListener );
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}