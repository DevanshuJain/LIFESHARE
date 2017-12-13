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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BloodReceiver extends AppCompatActivity
{
    String name;
    public String item,itemcity,itemarea;
    public Spinner spin,spcity,sparea;
    Button submit;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_receiver);
        Intent j= getIntent();
        name = j.getStringExtra("KN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spin = (Spinner)findViewById(R.id.brspin);
        sparea = (Spinner)findViewById(R.id.spinarea);
        spcity =(Spinner)findViewById(R.id.spincity);
        submit = (Button)findViewById(R.id.submit);

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
        //adt.setDropDownViewResource();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                item = parent.getItemAtPosition(position).toString();
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


        // City

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
        //
        submit.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
//
//                final  String ecity = ec.getText().toString();
//
//                CharSequence inputStr = ecity;
//                Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
//                Matcher matcher = pattern.matcher(inputStr);
//                if(matcher.matches())
//                {
//                    a=1;
//                }
//                else
//                {
//                    a=0;
//                    ec.setError("Numbers not allowed");
//                }
//
//                final  String earea = ea.getText().toString();
//
//                CharSequence inputStr1 = earea;
//                Pattern pattern1 = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
//                Matcher matcher1 = pattern.matcher(inputStr1);
//                if(matcher.matches())
//                {
//                    b=1;
//                }
//                else
//                {
//                    b=0;
//                    ea.setError("Numbers not allowed");
//                }
                if(item.length()<4 &&  itemcity.length()>0 && itemarea.length()>0)
                {
                    try
                    {
                        DB d = new DB(getApplicationContext());
                        String s = d.getDonor(item,itemcity,itemarea);
                        if(itemarea.equalsIgnoreCase(s))
                        {
                            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),ShowDisplay.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.putExtra("KN",name);
                            i.putExtra("KB", item);
                            i.putExtra("KC", itemcity);
                            i.putExtra("KA",itemarea);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Donor",Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"please enter your full details",Toast.LENGTH_SHORT).show();
            }
        });
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
