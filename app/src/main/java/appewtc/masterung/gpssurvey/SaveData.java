package appewtc.masterung.gpssurvey;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveData extends AppCompatActivity {

    //Explicit
    private TextView dateTextView, areaTextView;
    private EditText nameEditText, addressEditText;
    private ListView listView;
    private String dateString, nameString, addressString, areaString;
    private String[] latSrings, lngStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        //Bind Widget
        bindWidget();

        //Show Textview
        showTextview();

        //Create listview
        creatListview();

    }   // Main Method

    private void creatListview() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_latlngTABLE, null);
        cursor.moveToFirst();
        int intcount = cursor.getCount();
        String[] pointStrings = new String[intcount];
        String[] latStrings = new String[intcount];
        String[] lngStrings = new String[intcount];

        for (int i = 0; i < intcount; i++) {

            pointStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_ID));
            latStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_lat));
            lngStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_lng));

            cursor.moveToNext();



        }   //for
        cursor.close();

        SaveAdapter saveAdapter = new SaveAdapter(this, pointStrings, latStrings, lngStrings);
        listView.setAdapter(saveAdapter);


    } // createListView

    private void showTextview() {

        //Show date
        java.text.DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        dateString = dateFormat.format(date);
        dateTextView.setText(dateString);

        //Show Area
        areaString = getIntent().getStringExtra("Area");
        areaTextView.setText("Area = " + areaString );

    }   // showtextview



    private void bindWidget() {

        dateTextView = (TextView) findViewById(R.id.textView8);
        areaTextView = (TextView) findViewById(R.id.textView11);
        nameEditText = (EditText) findViewById(R.id.editText);
        addressEditText = (EditText) findViewById(R.id.editText2);
        listView = (ListView) findViewById(R.id.listView);



    } // bindWidget

}   // Main Class
