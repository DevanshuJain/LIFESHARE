package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonorRegister extends AppCompatActivity
{

    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String PHONE_MSG = "###-#######";
    private static final String REQUIRED_MSG = "required";

    public String item,itemcity,itemarea;
    EditText en,em;
    Button reg;
    String name;
    String d = "--Select City--";
    String e = "--Select Area--";
    int a=0,b=0,c=0;
    //TextView tva;
    Spinner spin,spcity,sparea;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_register);
        Intent j = getIntent();
        name = j.getStringExtra("KN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        en = (EditText) findViewById(R.id.edname);
        em = (EditText) findViewById(R.id.edmob);
        // tva = (TextView) findViewById(R.id.tvreg);
        reg = (Button) findViewById(R.id.register);
        // eb = (EditText)findViewById(R.id.edbg);
        spin = (Spinner) findViewById(R.id.edspin);
        spcity = (Spinner) findViewById(R.id.spcity);
        sparea = (Spinner) findViewById(R.id.sparea);
        reg.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                String ename = en.getText().toString();
                if(ename.length()==0)
                {
                    en.requestFocus();
                    en.setError("FIELD CANNOT BE EMPTY");
                }
                CharSequence inputStr1 = ename;
                Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
                Matcher matcher = pattern.matcher(inputStr1);
                if(matcher.matches())
                {
                    a=1;
                }
                else
                {
                    a=0;
                    en.setError("Numbers not allowed");
                }

//
//                String ecity = ec.getText().toString();
//
//                if(ecity.length()==0)
//                {
//
//                    ec.requestFocus();
//                    ec.setError("FIELD CANNOT BE EMPTY");
//                }
//                CharSequence inputStr = ecity;
//                Pattern pattern1 = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
//                Matcher matcher1 = pattern.matcher(inputStr);
//                if(matcher1.matches())
//                {
//                    b=1;
//
//                }
//                else
//                {
//                    b=0;
//                    ec.setError("Numbers not allowed");
//                }
//
//                String earea = ea.getText().toString();
//
//                if(earea.length()==0)
//                {
//
//                    ea.requestFocus();
//                    ea.setError("FIELD CANNOT BE EMPTY");
//                }
//                CharSequence inputStr2 = ecity;
//                Pattern pattern2 = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
//                Matcher matcher2 = pattern.matcher(inputStr2);
//                if(matcher2.matches())
//                {
//                    c=1;
//                }
//                else
//                {
//                    c=0;
//                    ea.setError("Numbers not allowed");
//                }
                String emob = em.getText().toString();

//                if(!hasText(em)&&(isPhoneNumber(em,true)))
//                {
//                    Toast.makeText(getApplicationContext(),"Enter Valid Phone no.", Toast.LENGTH_SHORT).show();
//                }
                if (!isValidPhoneNo(emob))
                {
                    // a=0;
                    em.requestFocus();
                    em.setError("Invalid Mobile No.");
                }
                DB d = new DB(getApplicationContext());
                // String m = d.donorCheck(emob);
                if (a==1&&ename.length() > 0 &&  itemcity.length() > 0 && !itemcity.equals(d) && itemarea.length()>0 && !itemarea.equals(e) && emob.length() == 10 && item.length() < 4)
                {
//                    String q = d.donorCheck(emob);
//                    if(!q.equals(emob))
//                    {

                    long g = d.addDonor(ename, itemcity, itemarea, emob, item);

                    try {
                        if (g != -1)
                        {

                            Toast.makeText(getApplicationContext(), "Successfully Register", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Homepage.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.putExtra("KN",name);
                            en.setText("");
                            em.setText("");
                            startActivity(i);
                            finish();
                            //  else{Toast.makeText(getApplicationContext(), "Account Already Exist" , Toast.LENGTH_SHORT).show();}
                        } else
                            Toast.makeText(getApplicationContext(), "Mobile No. Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();

                    }
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(), "Account Already Exist", Toast.LENGTH_SHORT).show();
//
//                    }

                }
//                else
//                    {
//                        Toast.makeText(getApplicationContext(),"Account Already Exist"+bgp,Toast.LENGTH_SHORT).show();
//
//                    }



                else
                    Toast.makeText(getApplicationContext(), "please enter full details" , Toast.LENGTH_SHORT).show();
            }
        });

//        tva.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), HomePage.class);
//                startActivity(i);
//
//            }
//        });

        // spinner

        List<String> bg = new ArrayList<String>();
        bg.add("Blood Group");
        bg.add("A+");
        bg.add("A-");
        bg.add("B+");
        bg.add("B-");
        bg.add("AB+");
        bg.add("AB-");
        bg.add("O+");
        bg.add("O-");
        ArrayAdapter<String> adt = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,bg);
        adt.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(adt);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                item = parent.getItemAtPosition(position).toString();
                // Toast.makeText(getApplicationContext(), ""+item, Toast.LENGTH_LONG).show();

            }


            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner City
        List<String> city = new ArrayList<String>();
        city.add("--Select City--");
        city.add("Indore");
        city.add("Mandsaur");
//        city.add("Bhopal");
//        city.add("Bhind");
//        city.add("Dewas");
//        city.add("Gwalior");

        ArrayAdapter<String> adts = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,city);
        adts.setDropDownViewResource(R.layout.spinner_item);
        spcity.setAdapter(adts);
        //adt.setDropDownViewResource();
        spcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                itemcity = parent.getItemAtPosition(position).toString();
                if(itemcity.equals("Indore"))
                {
                    List<String> area = new ArrayList<String>();
                    area.add("--Select Area--");
                    area.add("Arjun Nagar");
                    area.add("Airport");
                    area.add("Bhwarkua");
                    area.add("Kalani Nagar");
                    area.add("Bada Ganpati");
                    area.add("Vijay Nagar");
                    area.add("Palasiya");
                    area.add("Rau");
                    area.add("Rajendra Nagar");
                    ArrayAdapter<String> adtss = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, area);
                    adtss.setDropDownViewResource(R.layout.spinner_item);
                    sparea.setAdapter(adtss);
                }
                if(itemcity.equals("Mandsaur")) {
                    // spinner area
                    List<String> mand = new ArrayList<String>();
                    mand.add("--Select Area--");
                    mand.add("Nanesh Nagar");
                    mand.add("Ram Takri");
                    mand.add("Sneh Nagar");
                    mand.add("Goving Nagar");
                    mand.add("Janta Colony");

                    ArrayAdapter<String> adtsss = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, mand);
                    adtsss.setDropDownViewResource(R.layout.spinner_item);
                    sparea.setAdapter(adtsss);
                }

                //Toast.makeText(getApplicationContext(), ""+item, Toast.LENGTH_SHORT).show();
                //View views = super.getDropDown(position,view, parent);
                //TextView tv = (TextView) view;

                // Set the Text color
                // tv.setTextColor(Color.parseColor("#FF9933CC"));
                //tv.setText(bg.get(position));
                //tv.setBackgroundColor(7829368);
            }


            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

//        if(itemcity.toString().equals("Indore"))
//        {
//            List<String> area = new ArrayList<String>();
//            area.add("--Select Area--");
//            area.add("Arjun Nagar");
//            area.add("Airport");
//            area.add("Bhwarkua");
//            area.add("Kalani Nagar");
//            area.add("Bada Ganpati");
//            area.add("Vijay Nagar");
//            area.add("Palasiya");
//            area.add("Rau");
//            area.add("Rajendra Nagar");
//            ArrayAdapter<String> adtss = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,area);
//            adtss.setDropDownViewResource(R.layout.spinner_item);
//            sparea.setAdapter(adtss);
//
//        }

        // spinner area

//        List<String> area = new ArrayList<String>();
//        area.add("--Select Area--");
//        area.add("Arjun Nagar");
//        area.add("Airport");
//        area.add("Bhwarkua");
//        area.add("Kalani Nagar");
//        area.add("Bada Ganpati");
//        area.add("Vijay Nagar");
//        area.add("Palasiya");
//        area.add("Rau");
//        area.add("Rajendra Nagar");
//        ArrayAdapter<String> adtss = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,area);
//        adtss.setDropDownViewResource(R.layout.spinner_item);
//        sparea.setAdapter(adtss);
        //adt.setDropDownViewResource();
        sparea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                itemarea = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), ""+item, Toast.LENGTH_SHORT).show();
                //View views = super.getDropDown(position,view, parent);
                //TextView tv = (TextView) view;

                // Set the Text color
                // tv.setTextColor(Color.parseColor("#FF9933CC"));
                //tv.setText(bg.get(position));
                //tv.setBackgroundColor(7829368);
            }


            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    private boolean isValidPhoneNo(String mob)
    {
        boolean b;
        if (mob != null && mob.length() ==10)
        {
            b=true;

        }
        else
        {
            b=false;
        }
        return b;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(),Homepage.class);
                i.putExtra("KN",name);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private boolean isValidMobiles(String phone) {
//        return android.util.Patterns.PHONE.matcher(phone).matches();
//    }
//public static boolean isPhoneNumber(EditText editText, boolean required) {
//    return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
//}
//    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
//
//        String text = editText.getText().toString().trim();
//        // clearing the error, if it was previously set by some other values
//        editText.setError(null);
//
//        // text required and editText is blank, so return false
//        if ( required && !hasText(editText) ) return false;
//
//        // pattern doesn't match so returning false
//        if (required && !Pattern.matches(regex, text)) {
//            editText.setError(errMsg);
//            return false;
//        };
//
//        return true;
//    }
//    public static boolean hasText(EditText editText) {
//
//        String text = editText.getText().toString().trim();
//        editText.setError(null);
//
//        // length 0 means there is no text
//        if (text.length() == 0) {
//            editText.setError(REQUIRED_MSG);
//            return false;
//        }
//
//        return true;
//    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}