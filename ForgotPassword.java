package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    Button verify;
    EditText emobile;
    int b;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        Intent j = getIntent();
        emobile = (EditText)findViewById(R.id.efmob);
        verify = (Button)findViewById(R.id.verify);

        verify.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                String eemobile = emobile.getText().toString();
                if(!isValidEmail(eemobile))
                {
                    emobile.setError("Invalid Email id");
                    b=0;
                }
                if(isValidEmail(eemobile))
                {
                    b=1;
                }
                if(b==1)
                {
                    try
                    {
                        DB d = new DB(getApplicationContext());
                        String c = d.getPassword(eemobile);
                        if(eemobile.equals(c))
                        {
                            Toast.makeText(getApplicationContext(), "Verified ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),Homepage.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Incorrect ", Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception e )
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    Toast.makeText(getApplicationContext(), "Enter your Email Id ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
