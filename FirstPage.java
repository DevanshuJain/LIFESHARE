package lifeshare.example.dell.lifeshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstPage extends AppCompatActivity
{
    AlertDialog alertDialog;
    public ProgressDialog pdlg;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        pdlg=new ProgressDialog(FirstPage.this);
        //  pdlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // pdlg.setMessage("Loading...");
        pdlg.setCancelable(false);
        //pdlg.show();
        new Handler().postDelayed(new Runnable()
        {

            public void run()
            {
                startActivity(new Intent(FirstPage.this,Login.class));
                finish();

            }
        }, 3000);
    }
}
