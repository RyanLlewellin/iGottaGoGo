package com.underthejava.igottagogo;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity {

    User curr_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextView tvUserCredits = (TextView) findViewById(R.id.tvUserCredits);
        final Button bCancel = (Button) findViewById(R.id.bCancel);
        final Button bSave = (Button) findViewById(R.id.bSave);

        Log.d("GettingIntent", "About to get intent");
        Intent intent = getIntent();
        Log.d("gotIntent", "Got intent");
        Log.d("CreatingUser", "Creating User");
        this.curr_user = User.getInstance();
        Log.d("CreatedUser", "Created User");

        tvUsername.setText(this.curr_user.getUsername());
        etFirstName.setText(this.curr_user.getFirst_name());
        etLastName.setText(this.curr_user.getLast_name());
        etEmail.setText(this.curr_user.getEmail());
        tvUserCredits.setText(this.curr_user.getCredits() + "");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               // Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                //intent.putExtra("curr_user", curr_user);
               // EditProfileActivity.this.startActivity(intent);
                Intent intent=new Intent();
                setResult(1,intent);
                finish();//finishing activity
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                curr_user.setFirstName(etFirstName.getText().toString());
                curr_user.setLastName(etLastName.getText().toString());
                curr_user.setEmail(etEmail.getText().toString());
                Log.d("SAVE", "SAVE CLICKED AND NEW USER INFO SET");
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            Log.d("JSON RESPONSE", "GOT A JSON RESPONSE");
                            Log.d("RESPONSE", response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Intent intent=new Intent();
                                setResult(1,intent);
                                finish();//finishing activity
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                                builder.setMessage("Update Failed")
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

                UpdateRequest updateRequest = new UpdateRequest(curr_user.getUsername(), curr_user.getFirst_name(), curr_user.getLast_name(), curr_user.getEmail(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(EditProfileActivity.this);
                queue.add(updateRequest);
            }
        });
    }
}
