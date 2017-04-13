package com.underthejava.igottagogo;

import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import com.android.volley.*;
import java.io.StringReader;
import java.util.HashMap;
/**
 * Created by vincentdelacruz on 2017-03-15.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://ig2gg.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String firstName, String lastName, String username, String password, String email, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);

    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}