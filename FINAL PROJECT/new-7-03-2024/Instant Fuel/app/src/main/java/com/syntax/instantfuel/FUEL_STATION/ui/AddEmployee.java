    package com.syntax.instantfuel.FUEL_STATION.ui;

    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentTransaction;

    import com.android.volley.AuthFailureError;
    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;
    import com.syntax.instantfuel.R;
    import com.syntax.instantfuel.Utility;


    import java.util.HashMap;
    import java.util.Map;

    import static android.content.Context.MODE_PRIVATE;


    public class AddEmployee extends Fragment {

        String NAME,PHONE,EMAIL,PASSWORD;
        EditText name,phone,email,password;
        Button add;



        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {


            View root = inflater.inflate(R.layout.add_employee, container, false);

            name=root.findViewById(R.id.mech_name);
            phone=root.findViewById(R.id.mech_phone);
            email=root.findViewById(R.id.mech_email);
            password=root.findViewById(R.id.mech_password);
            add=root.findViewById(R.id.mech_add);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    NAME=name.getText().toString().trim();
                    PHONE=phone.getText().toString().trim();
                    EMAIL=email.getText().toString().trim();
                    PASSWORD=password.getText().toString().trim();

                    if (NAME.isEmpty()) {
                        name.requestFocus();
                        name.setError("Enter name");
                    } else if (PHONE.isEmpty()) {
                        phone.requestFocus();
                        phone.setError("Enter Phone");
                    }
                    else if (EMAIL.isEmpty()) {
                        email.requestFocus();
                        email.setError("Enter email");
                    }
                    else if (PASSWORD.isEmpty()) {
                        password.requestFocus();
                        password.setError("Enter Password");
                    }else{
                        addMechanic(NAME,PHONE,EMAIL,PASSWORD);
                    }
                }
            });


            return root;
        }

        public void addMechanic(final String ...arr)
        {
            RequestQueue queue = Volley.newRequestQueue(getContext());

            StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("******",response);
                    if(!response.trim().equals("failed"))
                    {
                        Toast.makeText(getContext(), "Employee Added ..!", Toast.LENGTH_LONG).show();
                        Fragment fragment = new AddEmployee();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        Toast.makeText(getContext(), "eroor in register", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                    Log.i("My error", "" + error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                    final String uid = prefs.getString("u_id", "No logid");
                    final String type = prefs.getString("type", "No logid");//"No name defined" is the default value.
                    map.put("key","addMechanic");
                    map.put("uid",uid);
                    map.put("name",arr[0]);
                    map.put("phone",arr[1]);
                    map.put("email",arr[2]);
                    map.put("password",arr[3]);


                    return map;
                }
            };
            queue.add(request);
        }
    }
