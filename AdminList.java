package lifeshare.example.dell.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class AdminList extends AppCompatActivity {

    ListView ll;
    private static final int OK_MENU_ITEM = Menu.FIRST;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_list);
        Intent j =getIntent();

        ll = (ListView)findViewById(R.id.lladmin);

        Intent i = getIntent();
//        String bgg=i.getStringExtra("KN");
//        String bct=i.getStringExtra("KC");
        DB db=new DB(getApplicationContext());
        List<Admin> bl = db.getAllDetails();

        AdminShowList adt=new AdminShowList(getApplicationContext(), R.layout.admin_list, bl);
        ll.setAdapter((ListAdapter) adt);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, OK_MENU_ITEM, 0, "Logout");

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case OK_MENU_ITEM:
                Intent j =new Intent(getApplicationContext(),Login.class);
                startActivity(j);
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}
