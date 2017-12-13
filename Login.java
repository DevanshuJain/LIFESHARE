package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity
{
    final String n = "admin";
    final String p = "123456";
    Button login,newuser;
    int a;
    EditText ep,en;
    TextView tv,tforgot;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent j = getIntent();
        login = (Button)findViewById(R.id.login);
        newuser = (Button)findViewById(R.id.newuser);
        ep = (EditText)findViewById(R.id.epass);
        tforgot = (TextView)findViewById(R.id.tforgot);
        en = (EditText)findViewById(R.id.euser);

        newuser.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),Newuser.class);
                startActivity(i);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                String ename = en.getText().toString();

                if (ename.length() == 0) {
                    en.requestFocus();
                    en.setError("FIELD CANNOT BE EMPTY");
                } else if (!ename.matches("[a-zA-Z ]+")) {
                    en.requestFocus();
                    en.setError("NUMBERS CANNOT BE ALLOWED");
                }

                final String epass = ep.getText().toString();
                {
                    if (!isValidPhoneNo(epass))
                    {
                        a=0;
                        ep.setError("Invalid Mobile No.");
                    }
                    else
                    {
                        a=1;
                    }
                        // isValidPassword(epass);
                }


                if (ename.length() > 0 && epass.length() > 0) {
                    try {

                        if (ename.equals(n) && epass.equals(p)) {
                            Intent i = new Intent(getApplicationContext(), AdminList.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            ep.setText("");
                            en.setText("");
                            startActivity(i);
                            finish();
                        }
                        if(a==1) {
                            DB d = new DB(getApplicationContext());

                            String s = d.getDetails(ename, epass);

                            final String ii = d.getDetailsID(ename, epass);


                            if (epass.equals(s)) {
                                Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Homepage.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("KN", ename);
                                i.putExtra("KP", epass);
                                ep.setText("");
                                en.setText("");
                                startActivity(i);
                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception e){
                            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else

                    {
                        Toast.makeText(getApplicationContext(), "Please enter your full details", Toast.LENGTH_SHORT).show();
                    }
                }

        });


        tforgot.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);

            }
        });
        }


//        tforgot.setOnClickListener(new View.OnClickListener()
//        {
//
//            public void onClick(View v)
//            {
//                Intent i = new Intent(getApplicationContext(),ForgotPassword.class);
//                startActivity(i);
//
//            }
//        });
//
//
//    private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() >= 6) {
//            return true;
//        }
//        else
//        return false;
//    }
    private boolean isValidPhoneNo(String mob)
    {
        boolean a;
        if (mob != null && mob.length() == 10)
        {
            a=true;
            //return a;
        }
        else
        {
            a=false;
        }
        return a;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
