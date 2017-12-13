package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AllDetails extends AppCompatActivity {

    Button gafter,gbefore,need;
    String name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_details);

        Intent j = getIntent();
        name = j.getStringExtra("KN");
        gafter = (Button)findViewById(R.id.guideafter);
        gbefore = (Button)findViewById(R.id.guidebefore);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        need = (Button)findViewById(R.id.need);

        gbefore.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),GuideBefore.class);
                i.putExtra("KN",name);
                startActivity(i);
            }
        });
        gafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GuideAfter.class);
                i.putExtra("KN",name);
                startActivity(i);
            }
        });
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Need.class);
                i.putExtra("KN",name);
                startActivity(i);
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
