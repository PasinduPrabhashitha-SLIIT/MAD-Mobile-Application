package com.mobileapplicationdevelopment.dogvio;

import static android.widget.AdapterView.*;

import static com.mobileapplicationdevelopment.dogvio.R.style.MyDialogTheme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapplicationdevelopment.dogvio.daycareDatabase.BookingAddapter;
import com.mobileapplicationdevelopment.dogvio.daycareDatabase.DBDayCareHandler;
import com.mobileapplicationdevelopment.dogvio.daycareDatabase.DayCareModule;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListDayCareAll extends AppCompatActivity {
    private Button addnew;
    private ListView listview;
    private TextView countbooking;
    Context context;
    private DBDayCareHandler  dbdaycarehandler;
    private List<DayCareModule>  dayCareModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_day_care_all);
        context = this;
        dbdaycarehandler = new DBDayCareHandler(context);

        addnew = findViewById(R.id.addnewbooking);
        listview = findViewById(R.id.bookinglist);
        countbooking =findViewById(R.id.noofbooking);
        dayCareModules = new ArrayList<>();

        dayCareModules = dbdaycarehandler.getAllBookingHistory();


        BookingAddapter addapter = new BookingAddapter(context,R.layout.activity_day_care_booking_history,dayCareModules);

        listview.setAdapter(addapter);

        //get booking count from table

        int countBooking = dbdaycarehandler.countBooking();
        countbooking.setText(" You have " + countBooking+ " booking before");

        addnew.setOnClickListener((v)->{
            startActivity(new Intent(context,Booking_DayCare.class));
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                DayCareModule dayCareModule = dayCareModules.get(position);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Booking Changes");
                builder.setTitle( Html.fromHtml("<font color='#ff3300'>Booking Changes</font>"));
                //builder.setBackgroundColor(getResources().getColor(R.color.purple_700));

                //builder.setMessage(dayCareModule.getBedogage());
                //builder.setMessage(dayCareModule.getBedogout());

                //AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.MyDialogTheme).create();

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                           dayCareModule.setFinished(System.currentTimeMillis());
                           dbdaycarehandler.updatesingleBooking(dayCareModule);
                        //dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(neededColor);
                            startActivity(new Intent(context,ListDayCareAll.class));

                    }
                });
                builder.setNegativeButton("Delete Booking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int  which) {
                            dbdaycarehandler.deleteBooking(dayCareModule.getId());
                            startActivity(new Intent(context,ListDayCareAll.class));
                    }
                });
                builder.setNeutralButton("Update Booking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int  which) {
                        Intent intent = new Intent(context,Update_Booking.class);
                        intent.putExtra("keyid",String.valueOf(dayCareModule.getId()));
                        startActivity(intent);

                    }
                });
               /* AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(MyDialogTheme);
                    }
                });*/
                builder.show();
            }
        });

    }
}