package com.underthejava.igottagogo;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.util.Log;

//public class ViewProfileActivity extends AppCompatActivity {
public class ViewProfileActivity extends Fragment {
    User curr_user;
    static final int PICK_CONTACT_REQUEST = 1;  // The request code given to EditProfileActivity
    @Nullable
    TextView tvUsername;
    TextView tvFirstName;
    TextView tvLastName;
    TextView tvEmail;
    TextView tvUserCredits;
    Button bEdit;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_view_profile, container, false);


        tvUsername = (TextView) rootView.findViewById(R.id.tvUsername);
        tvFirstName = (TextView) rootView.findViewById(R.id.tvFirstName);
        tvLastName = (TextView) rootView.findViewById(R.id.tvLastName);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvUserCredits = (TextView) rootView.findViewById(R.id.tvUserCredits);
        bEdit = (Button) rootView.findViewById(R.id.bEdit);

        Log.d("GettingIntent", "About to get intent");
        Intent intent = getActivity().getIntent();
        Log.d("gotIntent", "Got intent");
        Log.d("CreatingUser", "Creating User");
        this.curr_user = User.getInstance();
        Log.d("CreatedUser", "Created User");

        tvUsername.setText(this.curr_user.getUsername());
        tvFirstName.setText(this.curr_user.getFirst_name());
        tvLastName.setText(this.curr_user.getLast_name());
        tvEmail.setText(this.curr_user.getEmail());
        tvUserCredits.setText(this.curr_user.getCredits() + "");

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ViewProfileActivity.this.getActivity(), EditProfileActivity.class);
                //ViewProfileActivity.this.startActivity(intent);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==PICK_CONTACT_REQUEST)
        {
            curr_user = User.getInstance();
            tvUsername.setText(this.curr_user.getUsername());
            tvFirstName.setText(this.curr_user.getFirst_name());
            tvLastName.setText(this.curr_user.getLast_name());
            tvEmail.setText(this.curr_user.getEmail());
            tvUserCredits.setText(this.curr_user.getCredits() + "");

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Account Info");
    }



}
