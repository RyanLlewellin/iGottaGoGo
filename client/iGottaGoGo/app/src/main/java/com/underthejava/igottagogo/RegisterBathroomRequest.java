package com.underthejava.igottagogo;

import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import com.android.volley.*;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by vincentdelacruz on 2017-03-29.
 */

public class RegisterBathroomRequest extends StringRequest{
private static final String UPDATE_REQUEST_URL = "http://ig2gg.000webhostapp.com/RegisterBathroom.php";
private Map<String, String> params;

public RegisterBathroomRequest(String name, String description, Double longitude, Double latitude, Response.Listener<String> listener)
        {
        super(Request.Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("description", description);
        params.put("longitude", longitude + "");
        params.put("latitude", latitude + "");

        }

@Override
public Map<String, String> getParams()
        {
        return params;
        }
}
