package com.syntax.instantfuel.USER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.instantfuel.LoginActivity;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.RegistrationActivity;
import com.syntax.instantfuel.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserRegistration extends AppCompatActivity {

    EditText name,address,phone,email,password;
    Button register;
    TextView login;
    String emailPattern;

    String n_ame, a_ddress, p_hone, e_mail, p_swd, d_ateOfjoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy 'at' hh:mm");
        d_ateOfjoin = ft.format(dNow);

//        System.out.println("Current Date: " + ft.format(dNow));

        name=findViewById(R.id.input_name);
        address=findViewById(R.id.input_address);
        phone=findViewById(R.id.input_phone);
        email=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);
        register=findViewById(R.id.customer_registerButton);
        login=findViewById(R.id.link_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        finish();
    }
    private void validate() {

        n_ame = name.getText().toString();
        a_ddress = address.getText().toString();
        p_hone = phone.getText().toString();
        e_mail = email.getText().toString();
        p_swd = password.getText().toString();

        if (n_ame.isEmpty()) {
            name.requestFocus();
            name.setError("enter username");
        } else if (a_ddress.isEmpty()) {
            address.requestFocus();
            address.setError("enter a address");
        } else if (p_hone.isEmpty()) {
            phone.requestFocus();
            phone.setError("enter a mobile number");
        }else if(p_hone.length()!= 10){
            phone.requestFocus();
            phone.setError("enter a valid mobile number");
        }else if (e_mail.isEmpty()) {
            email.requestFocus();
            email.setError("enter an email address");
        }else if (!e_mail.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            email.requestFocus();
            email.setError("enter a valid email address");
        }else if (p_swd.isEmpty()) {
            password.requestFocus();
            password.setError("enter password");
        }else {
            customerRegistration();
        }
    }


    public void customerRegistration() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if(response.trim().equals("Already Exist")){
                    Toast.makeText(getApplicationContext(), "Account is already Exist", Toast.LENGTH_SHORT).show();
                } else if(!response.trim().equals("failed")) {
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "customer_register");
                map.put("c_name", n_ame);
                map.put("c_address", a_ddress);
                map.put("c_phone", p_hone);
                map.put("c_email", e_mail);
                map.put("c_pswd", p_swd);
                map.put("c_doj", d_ateOfjoin);

                return map;
            }
        };
        queue.add(request);
    }
}
