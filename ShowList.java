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

public class ShowList extends ArrayAdapter<Blood> {

    ArrayAdapter<String> adapter;
    TextView ttname, ttcity, ttmob, ttbg, ttarea;
    List<Blood> bl;
    final Context context;


    public ShowList(Context context, int resource, List<Blood> items) {
        super(context, resource, items);
        this.bl = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return bl.size();
    }


    public Blood getItem(int poss) {
        // TODO Auto-generated method stub
        return bl.get(poss);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vw = inf.inflate(R.layout.show_list, null);
        ttname = (TextView) vw.findViewById(R.id.tsname);
        ttcity = (TextView) vw.findViewById(R.id.tscity);
        ttmob = (TextView) vw.findViewById(R.id.tsmob);
        ttarea = (TextView) vw.findViewById(R.id.tsarea);
        ttbg = (TextView) vw.findViewById(R.id.tsbg);


        final Blood itm = bl.get(position);

        String number = itm.getMob();
        ttname.setText(itm.getName());
        ttbg.setText(itm.getBgroup());
        ttcity.setText(itm.getCity());
        ttarea.setText(itm.getArea());
        ttmob.setText("" + number);

//        int pimg= itm.getImg();
//        double pr=itm.getPrice();
//
//        tphone.setText(""+number);
//
//        tprice.setText(""+pr);
//        primg.setImageResource(pimg);
//        // TODO Auto-generated method stub
        ttmob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Blood p = bl.get(position);
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", p.getName(), null));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        return vw;
    }
}