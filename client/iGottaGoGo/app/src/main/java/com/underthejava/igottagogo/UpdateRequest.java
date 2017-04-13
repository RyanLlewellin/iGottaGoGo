package com.underthejava.igottagogo;

import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import com.android.volley.*;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by vincentdelacruz on 2017-03-28.
 */

public class UpdateRequest extends StringRequest{
    private static final String UPDATE_REQUEST_URL = "http://ig2gg.000webhostapp.com/Update.php";
    private Map<String, String> params;

    public UpdateRequest(String username, String firstName, String lastName, String email, Response.Listener<String> listener)
    {
        super(Request.Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("username", username);
        params.put("email", email);

    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
