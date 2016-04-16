package appewtc.masterung.gpssurvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Acer on 16/4/2559.
 */
public class SaveAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] pointStrings, latStrings, lngStrings;

    public SaveAdapter(Context context,
                       String[] pointStrings,
                       String[] latStrings,
                       String[] lngStrings) {
        this.context = context;
        this.lngStrings = lngStrings;
        this.latStrings = latStrings;
        this.pointStrings = pointStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return pointStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.save_listview, viewGroup, false);

        TextView pointTextView = (TextView) view1.findViewById(R.id.textView12);
        pointTextView.setText(pointStrings[i]);

        TextView latTextView = (TextView) view1.findViewById(R.id.textView13);
        latTextView.setText(latStrings[i]);

        TextView lngTextView = (TextView) view1.findViewById(R.id.textView14);
        lngTextView.setText(lngStrings[i]);



        return view1;
    }
}   //Main class
