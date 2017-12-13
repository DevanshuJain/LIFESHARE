package lifeshare.example.dell.lifeshare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newuser extends AppCompatActivity
{

    EditText en,email,ep,em;
    TextView tv,ed;
    Button register;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private int a=0, b=0;
    EditText editText;
    String  d,m,y,edob;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        Intent j = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        register = (Button)findViewById(R.id.register);
        tv = (TextView) findViewById(R.id.tv);
        email = (EditText)findViewById(R.id.eemail);
        em = (EditText)findViewById(R.id.enmob);
        en = (EditText)findViewById(R.id.enuser);
        ed = (TextView)findViewById(R.id.endob);



        register.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                String edob = ed.getText().toString();

//                else{
//                     en.requestFocus();
//                en.setError("hiii");}



                final  String emob = em.getText().toString();

                if (!isValidPhoneNo(emob)) {
                    em.setError("Invalid Mobile No.");
                    //isValidPhoneNo(emob);
                }

//                final String epass = ep.getText().toString();
//                if (!isValidPassword(epass))
//                {
//                    ep.setError("MORE THAN 5 CHARACTERS");
//                    // isValidPassword(epass);
//                }
                    final String eemail = email.getText().toString();
                    if(!isValidEmail(eemail))
                    {
                        email.setError("Invalid Email id");
                        b=0;
                    }
                if(isValidEmail(eemail))
                {
                    b=1;
                }


                final  String ename = en.getText().toString();
//                boolean b=validateLetters(ename);
//                if(!ename.matches("[a-zA-Z][a-zA-Z ]*"))
//                {
//
//                    a=1;
//                    en.setError("ENTER ONLY ALPHABETICAL LETTER");
//                    en.requestFocus();
//                }
                CharSequence inputStr = ename;
                Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
                Matcher matcher = pattern.matcher(inputStr);
                if(matcher.matches())
                {
                    a=1;
                }
                else
                {
                    a=0;
                    en.setError("Numbers not allowed");
                }
                if(a==1&&b==1&&ename.length()!=0&&edob.length()>0 && eemail.length()>=6 && emob.length() == 10)
                {
                    try
                    {
                        DB d = new DB(getApplicationContext());
                        long c = d.addDetails(ename, edob, eemail, emob);

                        if (c != -1)
                        {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), Homepage.class);
                            i.putExtra("KN",ename);
                            startActivity(i);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "Mobile Number Already Exists ", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(Newuser.this, "Please enter your full details", Toast.LENGTH_SHORT).show();

            }
        });
        tv.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH);
                year = cal.get(Calendar.YEAR);


                ed.setText(day+"/"+month+"/"+"/"+year);
                DateDialog();

            }
        });

    }

    public void DateDialog()
    {

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {


                ed.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

            }};

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }
//    public static boolean validateLetters(String txt) {
//
//        String regx = "[a-zA-Z]+\\.?";
//        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(txt);
//        return matcher.find();
//
//
//    }

  /*  private boolean isValidName(String name)

    {
        String namesss = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Pattern pattern = Pattern.compile(namesss);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }*/

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 5) {
            return true;
        }
        return false;
    }
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

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
