package appewtc.masterung.gpssurvey;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void clickSaveData(View view) {

        nameString = nameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") || addressString.equals("") ) {
            Toast.makeText(this, "กรุณากรอกให้ครบทุกช่อง", Toast.LENGTH_SHORT).show();
        } else {

            AddValueToSQLite();


        }


    }   //clickSavedata

    private void AddValueToSQLite() {

        ManageTABLE manageTABLE = new ManageTABLE(this);
        manageTABLE.addPlanet(nameString, dateString, areaString);

        for (int i = 0; i < latSrings.length; i++) {

            manageTABLE.addSuevey(dateString, nameString,
                    addressString, latSrings[i], lngStrings[i], areaString);

        }   // for

        Toast.makeText(this, "ขอบคุณครับ บันทึกข้อมูล เรียบร้อย", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SaveData.this, ShowHistory.class);
        startActivity(intent);
        finish();


    } // AddValueSQLite

    private void creatListview() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_latlngTABLE, null);
        cursor.moveToFirst();
        int intcount = cursor.getCount();
        String[] pointStrings = new String[intcount];
         latSrings = new String[intcount];
         lngStrings = new String[intcount];

        for (int i = 0; i < intcount; i++) {

            pointStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_ID));
            latSrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_lat));
            lngStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_lng));

            cursor.moveToNext();



        }   //for
        cursor.close();

        SaveAdapter saveAdapter = new SaveAdapter(this, pointStrings, latSrings, lngStrings);
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
