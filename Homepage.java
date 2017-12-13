package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity
{
    ImageButton idonor,irece,idetails;
    TextView td,tr,tdtls,tu;
    private static final int OK_MENU_ITEM = Menu.FIRST;
    private static final int BACK_MENU_ITEM = OK_MENU_ITEM + 1;
//    private static final int DELETE_MENU_ITEM = BACK_MENU_ITEM + 1;
    MenuItem item;
    String nm,pw;
    String cid;
    ImageButton id,ir,in;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
//        td = (TextView)findViewById(R.id.tvdonor);
//        tu = (TextView)findViewById(R.id.tuname);
//        tr = (TextView)findViewById(R.id.tvreceiver);
//        tdtls = (TextView)findViewById(R.id.tvdetails);
//
//        Intent j = getIntent();
        Intent i =getIntent();
        nm=i.getStringExtra("KN");
        pw=i.getStringExtra("KP");
        cid=i.getStringExtra("KID");

        tu = (TextView)findViewById(R.id.tuname);
        idonor = (ImageButton)findViewById(R.id.idonor);
        irece = (ImageButton)findViewById(R.id.irece);
        td = (TextView)findViewById(R.id.tvdonor);
        tr = (TextView)findViewById(R.id.tvreceiver);
        tdtls = (TextView)findViewById(R.id.tvdetails);
        idetails = (ImageButton)findViewById(R.id.idetails);
        tu.setText(nm);
        idonor.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),DonorRegister.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("KN",nm);
                startActivity(i);
            }
        });

        irece.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),BloodReceiver.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("KN",nm);
                startActivity(i);

            }
        });

        idetails.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
             Intent i = new Intent(getApplicationContext(),AllDetails.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("KN",nm);
              startActivity(i);
            }
        });
        td.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),DonorRegister.class);
                i.putExtra("KN",nm);
                startActivity(i);
            }

        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BloodReceiver.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("KN",nm);
                startActivity(i);
            }
        });


        tdtls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AllDetails.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("KN",nm);
                startActivity(i);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
//        menu.add(0,DELETE_MENU_ITEM,0,"Delete Account");
        menu.add(0, BACK_MENU_ITEM, 0, "Logout");


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

//            case DELETE_MENU_ITEM:
//                try {
//                    DB d = new DB(getApplicationContext());
//                    d.delete(cid);
//                    Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_SHORT).show();
//                    Intent k = new Intent(getApplicationContext(), Login.class);
//                    startActivity(k);
//                    finish();
//                }
//                catch (Exception e)
//                {
//                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();
//                }
//                break;

            case BACK_MENU_ITEM:
                Intent j =new Intent(getApplicationContext(),Login.class);
                j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(j);
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
