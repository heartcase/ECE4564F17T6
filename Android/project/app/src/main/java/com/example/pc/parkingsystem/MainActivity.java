package com.example.pc.parkingsystem;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ZXING_REQUEST_CODE = 49374;
    public static final int LOGIN_REQUEST_CODE = 13392;

    DataFragment dataFragment;
    UserPackage user;
    ParkingSpotPackage spot;
    ParkingSpotPackage[] spots;
    ToggleButton toggleButton;
    ListView listView;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        dataFragment = (DataFragment) getFragmentManager().findFragmentByTag("DATAFRAG");
        if(dataFragment == null){
            dataFragment = new DataFragment();
            getFragmentManager().beginTransaction().add(dataFragment, "DATAFRAG").commit();

        }
        if(dataFragment.task != null) dataFragment.task.cancel(false);
        dataFragment.task = new UpdateTask();
        ((UpdateTask) dataFragment.task).caller = this;
        ((UpdateTask) dataFragment.task).execute();
        if(dataFragment.user == null){
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivityForResult(myIntent, LOGIN_REQUEST_CODE);
        }
        View.OnClickListener handle_scan = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setBeepEnabled(false);
                integrator.setCameraId(0);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        };
        View.OnClickListener handle_search = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Method callback = MainActivity.class.getMethod("objectCallback", ParkingSpotPackage[].class);
                    ParkingAPI api = new ParkingAPI(dataFragment.address, MainActivity.this, null, null , callback);
                    if(!toggleButton.isChecked()){
                        api.getParkingSpots("all");
                    }else{
                        api.getParkingSpots("available");
                    }
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this,"Connection Fails Try agian", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        };
        findViewById(R.id.button_scan).setOnClickListener(handle_scan);
        findViewById(R.id.button_search).setOnClickListener(handle_search);
        toggleButton = findViewById(R.id.toggleButton_filter);
        listView = findViewById(R.id.list_view_spots);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ZXING_REQUEST_CODE){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                String scannedData = result.getContents();
                if (scannedData != null) {
                    Toast.makeText(MainActivity.this, scannedData, Toast.LENGTH_LONG).show();
                    int spotId = Integer.valueOf(scannedData);
                    try {
                        Method callback = MainActivity.class.getMethod("objectCallback", ParkingSpotPackage.class);
                        ParkingAPI api = new ParkingAPI(dataFragment.address, MainActivity.this, null, callback , null);
                        if (dataFragment.user.status.equals("check_out")){
                            api.postParkingSpot(dataFragment.user.username,dataFragment.user.password,spotId,"check_in", 0);
                        }else {
                            api.postParkingSpot(dataFragment.user.username,dataFragment.user.password,spotId,"check_out", 0);
                        }
                    }catch(Exception e){
                        Toast.makeText(MainActivity.this,"Connection Fails Try agian", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        }
        if(requestCode == LOGIN_REQUEST_CODE){
            if(resultCode != -1){
                dataFragment.address = data.getStringExtra("address");
                dataFragment.user = (UserParcel) data.getParcelableExtra("user");
                ((TextView)findViewById(R.id.text_name)).setText(dataFragment.user.username);
                String status;
                if(dataFragment.user.status.equals("check_in")){
                    Date date;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        date = simpleDateFormat.parse(dataFragment.user.park_time);
                        long t = Calendar.getInstance().getTimeInMillis() - date.getTime();
                        int seconds = (int) (t / 1000) % 60 ;
                        int minutes = (int) ((t / (1000*60)) % 60);
                        int hours   = (int) ((t / (1000*60*60)) % 24);
                        String timeString = String.format("Parking time: %02d:%02d:%02d", hours, minutes, seconds);
                        ((TextView)findViewById(R.id.text_time)).setText(timeString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    status = String.format("Parking");
                    ((Button)findViewById(R.id.button_scan)).setText("Check_out");
                }
                else{
                    status = "Not parking";
                    ((TextView)findViewById(R.id.text_time)).setText("");
                    ((Button)findViewById(R.id.button_scan)).setText("Check_in");
                }
                ((TextView)findViewById(R.id.text_status)).setText(status);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void objectCallback(UserPackage user){
        this.user = user;
        dataFragment.user = user;
        ((TextView)findViewById(R.id.text_name)).setText(dataFragment.user.username);
        String status;
        if(dataFragment.user.status.equals("check_in")){
            Date date;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = simpleDateFormat.parse(dataFragment.user.park_time);
                long t = Calendar.getInstance().getTimeInMillis() - date.getTime();
                int seconds = (int) (t / 1000) % 60 ;
                int minutes = (int) ((t / (1000*60)) % 60);
                int hours   = (int) ((t / (1000*60*60)) % 24);
                String timeString = String.format("Parking time: %02d:%02d:%02d", hours, minutes, seconds);
                ((TextView)findViewById(R.id.text_time)).setText(timeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            status = String.format("Parking");
            ((Button)findViewById(R.id.button_scan)).setText("Check_out");
        }else{
            status = "Not parking";
            ((TextView)findViewById(R.id.text_time)).setText("");
            ((Button)findViewById(R.id.button_scan)).setText("Check_in");
        }
        ((TextView)findViewById(R.id.text_status)).setText(status);
    }
    public void objectCallback(ParkingSpotPackage spot){
        this.spot = spot;
        try {
            Method callback = MainActivity.class.getMethod("objectCallback", UserPackage.class);
            ParkingAPI api = new ParkingAPI(dataFragment.address, MainActivity.this, callback, null , null);
            api.getUser(dataFragment.user.username,dataFragment.user.password,dataFragment.user._id);
        }catch(Exception e){
            Toast.makeText(MainActivity.this,"Connection Fails Try agian", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void objectCallback(ParkingSpotPackage[] spots){
        this.spots = spots;
        List<ParkingSpotPackage> list = new ArrayList<>();
        for(ParkingSpotPackage s: spots){
            list.add(s);
        }
        ArrayAdapter<ParkingSpotPackage> adapter = new ListItemAdapter(list);
        listView.setAdapter(adapter);
    }

    private class ListItemAdapter extends ArrayAdapter<ParkingSpotPackage>{
        List<ParkingSpotPackage> items;
        ListItemAdapter(List<ParkingSpotPackage> items){
            super(MainActivity.this, R.layout.parking_spot_item, items);
            this.items = items;
        }
        ListItemAdapter adapter = this;
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.parking_spot_item, parent, false);
            }
            ParkingSpotPackage item = this.items.get(position);
            ((TextView)itemView.findViewById(R.id.text_name)).setText(item.name);
            ((TextView)itemView.findViewById(R.id.text_location)).setText(item.location);
            ((TextView)itemView.findViewById(R.id.text_status)).setText(item.status);
            adapter.notifyDataSetChanged();
            return itemView;
        }
    }
    class UpdateTask extends AsyncTask<Void, Void, Void> {
        Object caller;
        @Override
        protected Void doInBackground(Void... voids) {
            while (!this.isCancelled()){
                SystemClock.sleep(1000);
                if (isCancelled())
                    break;
                publishProgress();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            if (dataFragment.user == null) return;
            try {
                Method callback = MainActivity.class.getMethod("objectCallback", UserPackage.class);
                ParkingAPI api = new ParkingAPI(dataFragment.address, caller, callback, null , null);
                api.getUser(dataFragment.user.username,dataFragment.user.password,dataFragment.user._id);
            }catch(Exception e){
                Toast.makeText(MainActivity.this,"Connection Fails Try agian", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}



