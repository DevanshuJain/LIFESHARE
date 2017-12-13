package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowDisplay extends AppCompatActivity
{
    ArrayAdapter<String> adapter;
    ListView ll;
    String bgg,bct,bca,name;
    protected void onCreate(Bundle savedInstanceState)
{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_display);
    ll = (ListView)findViewById(R.id.llview);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    Intent i = getIntent();
    name = i.getStringExtra("KN");
     bgg=i.getStringExtra("KB");
     bct=i.getStringExtra("KC");
     bca=i.getStringExtra("KA");
    DB db=new DB(getApplicationContext());
    List<Blood> bl = db.getAllContacts(bgg,bct,bca);
    ShowList adt=new ShowList(this, R.layout.show_display, bl);
    ll.setAdapter(adt);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(),BloodReceiver.class);
                i.putExtra("KN",name);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
