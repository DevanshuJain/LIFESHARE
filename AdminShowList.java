package lifeshare.example.dell.lifeshare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdminShowList extends ArrayAdapter<Admin>
{
    public TextView ttname, ttdob, ttmob, ttpass;
    public List<Admin> bl;
    final Context context;

    public AdminShowList(Context context, int resource, List<Admin> items)
    {
        super(context, resource, items);
        this.bl = items;
        this.context = context;
    }



    public int getCount() {
        // TODO Auto-generated method stub
        return bl.size();
    }


    public Admin getItem(int poss) {
        // TODO Auto-generated method stub
        return bl.get(poss);
    }


    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    public View getView(final int position, View arg1, ViewGroup arg2) {

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vw = inf.inflate(R.layout.admin_show_list,null);
        //  chkbox = (CheckBox)vw.findViewById(R.id.checkbox);
        ttname = (TextView) vw.findViewById(R.id.taname);
        ttdob = (TextView) vw.findViewById(R.id.tadob);
        ttpass = (TextView) vw.findViewById(R.id.tapass);
        ttmob = (TextView) vw.findViewById(R.id.tamob);


        final Admin itm= bl.get(position);

        String number=itm.getMob();
        ttname.setText(itm.getName());
        ttdob.setText(itm.getDob());
        ttpass.setText(itm.getPass());
        ttmob.setText(""+number);

        // TODO Auto-generated method stub
        ttmob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Admin p=bl.get(position);
//                Intent i=	new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",p.getName() , null));
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
                Admin p = bl.get(position);
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", p.getName(), null));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


//        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//
//
//                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//                dialog.setMessage("Do you want to Delete Account");
//
//                dialog.setPositiveButton("No", new DialogInterface.OnClickListener()
//                {
//
//
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                       // Toast.makeText(AdminShowList.this,"OK",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener()
//                {
//
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        DB d = new DB(getApplicationContext());
//                            d.deleteUsers();
//                            Toast.makeText(getApplicationContext(),"Account Deleted",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                AlertDialog alert = dialog.create();
//                alert.show();
//
//            }
//        });


        return vw;



    }
}
