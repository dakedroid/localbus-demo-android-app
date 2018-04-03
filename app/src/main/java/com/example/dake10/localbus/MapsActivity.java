package com.example.dake10.localbus;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;
import java.util.List;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static GoogleMap map;
    SupportMapFragment mapFragment;
    double longitud = (float)-96.1231898, latitud = (float)18.0880947;
    LatLng tuxtepec = new LatLng(latitud, longitud);
    Marker marker;
    Marker marker2;
    Marker follow;
    Handler mHandler = new Handler();
    GeoBus geo;
//    ParseObject testObject = new ParseObject("GeoBus");
    //List<GeoBus> geo = new ArrayList<GeoBus>();
    static Polyline xPoly;
    int init = 0;
    static int cont = 0;
    //ParseQuery<ParseObject> query = ParseQuery.getQuery("GeoBus");
    int cont2 = 0;
    int turn = 0;
    Polyline linesTec[] = new Polyline[37];
    Polyline linesCobao[] = new Polyline[71];
    LatLngInterpolator inter = new LatLngInterpolator.Linear();

    LatLng latLongTec[] = new LatLng[]{
            new LatLng(18.085873, -96.134958),
            new LatLng(18.085241, -96.132528),
            new LatLng(18.084751, -96.130801),
            new LatLng(18.083956, -96.128097),
            new LatLng(18.082610, -96.123419),
            new LatLng(18.082610, -96.123419),
            new LatLng(18.081631, -96.119686),
            new LatLng(18.082446, -96.119353),
            new LatLng(18.082926, -96.121080),
            new LatLng(18.084343, -96.125533),
            new LatLng(18.085516, -96.129245),
            new LatLng(18.086455, -96.132335),
            new LatLng(18.086883, -96.133580),
            new LatLng(18.087770, -96.132442),
            new LatLng(18.088627, -96.131402),
            new LatLng(18.090544, -96.129964),
            new LatLng(18.092431, -96.132389),
            new LatLng(18.096939, -96.137850),
            new LatLng(18.097224, -96.139040),
            new LatLng(18.096877, -96.141315),
            new LatLng(18.096582, -96.143600),
            new LatLng(18.096204, -96.143879),
            new LatLng(18.094817, -96.143997),
            new LatLng(18.092543, -96.144888),
            new LatLng(18.091207, -96.145478),
            new LatLng(18.088199, -96.146411),
            new LatLng(18.087893, -96.144813),
            new LatLng(18.087332, -96.142120),
            new LatLng(18.087454, -96.141530),
            new LatLng(18.087760, -96.140821),
            new LatLng(18.088250, -96.140049),
            new LatLng(18.088301, -96.139641),
            new LatLng(18.088154, -96.139083),
            new LatLng(18.087277, -96.138161),
            new LatLng(18.086675, -96.137538),
            new LatLng(18.086166, -96.136249),
            new LatLng(18.085870, -96.134940),
            new LatLng(18.085873, -96.134958),
    };

    LatLng latLongCobao[] = new LatLng[]{
            new LatLng(18.073396, -96.117628), //cobao
            new LatLng(18.072743, -96.119044),
            new LatLng(18.070826, -96.122434),
            new LatLng(18.070367, -96.122660),
            new LatLng(18.069561, -96.122273 ),//desviacion san bartolo
            new LatLng(18.068449, -96.123335),
            new LatLng(18.068480, -96.123883),
            new LatLng(18.068867, -96.124516),
            new LatLng(18.069092, -96.125063),
            new LatLng(18.069714, -96.126854 ),//puente rio salado
            new LatLng(18.071356, -96.130931),
            new LatLng(18.073008, -96.132648 ),//cruce 4 carriles
            new LatLng(18.074753, -96.134204),
            new LatLng(18.075732, -96.135202),
            new LatLng(18.077048, -96.136361),
            new LatLng(18.077884, -96.136983),
            new LatLng(18.080719, -96.137530),
            new LatLng(18.081678, -96.137627),
            new LatLng(18.083514, -96.137562),
            new LatLng(18.083932, -96.137423),
            new LatLng(18.084840, -96.136339 ),//20 de noviembre
            new LatLng(18.085788, -96.135116 ),//la piña
            new LatLng(18.085860, -96.134859),
            new LatLng(18.086023, -96.134773),
            new LatLng(18.086890, -96.133539),
            new LatLng(18.087726, -96.132477 ),// Entrada Carranza
            new LatLng(18.086961, -96.130041),
            new LatLng(18.086594, -96.128893),
            new LatLng(18.086533, -96.128153),
            new LatLng(18.085829, -96.125406),
            new LatLng(18.085187, -96.123046),
            new LatLng(18.084279, -96.119602),
            new LatLng( 18.083800, -96.117853),
            new LatLng(18.083524, -96.116620),
            new LatLng(18.083330, -96.115311),
            new LatLng(18.082984, -96.113787 ),// panteon carranza
            new LatLng(18.082453, -96.113776 ),// panteon libertad
            new LatLng(18.082300, -96.115675),
            new LatLng(18.082158, -96.117821),
            new LatLng(18.082219, -96.118594),
            new LatLng(18.082647, -96.120074),
            new LatLng(18.083290, -96.122317),
            new LatLng(18.083596, -96.123357),
            new LatLng(18.084310, -96.125503),
            new LatLng(18.085533, -96.129269),
            new LatLng(18.086105, -96.130943),
            new LatLng(18.086931, -96.133732),
            new LatLng(18.086115, -96.134869),
            new LatLng(18.086023, -96.135170),
            new LatLng(18.085809, -96.135170),
            new LatLng(18.084850, -96.136511),
            new LatLng(18.083973, -96.137551),
            new LatLng(18.083483, -96.137734),
            new LatLng(18.081617, -96.137798),
            new LatLng(18.079424, -96.137519),
            new LatLng(18.077721, -96.137122),
            new LatLng(18.076895, -96.136650),
            new LatLng(18.074763, -96.134569),
            new LatLng(18.073764, -96.133464),
            new LatLng(18.073008, -96.132648), //cruce 4 carriles
            new LatLng(18.071356, -96.130931),
            new LatLng(18.069714, -96.126854), //puente rio salado
            new LatLng(18.069092, -96.125063),
            new LatLng(18.068867, -96.124516),
            new LatLng(18.068480, -96.123883),
            new LatLng(18.068449, -96.123335),
            new LatLng(18.069561, -96.122273), //desviacion san bartolo
            new LatLng(18.070367, -96.122660),
            new LatLng(18.070826, -96.122434),
            new LatLng(18.072743, -96.119044),
            new LatLng(18.073396, -96.117628) //cobao
    };


    public void polyCreateTec(LatLng[] latis){
        for (int i = 0; i < latis.length - 1; i++){
            linesTec[i] = map.addPolyline(new PolylineOptions()
                    .add(latis[i], latis[i+1])
                    .width(5)
                    .color(Color.BLUE))
            ;
        }

    }
    public void polyCreateCobao(LatLng[] latis){
        for (int i = 0; i < latis.length - 1; i++){
            linesCobao[i] = map.addPolyline(new PolylineOptions()
                    .add(latis[i], latis[i+1])
                    .width(5)
                    .color(Color.GREEN))
            ;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Parse.initialize(this, "5Kxb7OmfPifXZzTF2gbaxPvyDvGOyu6nI9Z37iin", "OKLupkTwTyL3OxiDspd8pdWXfBVQvd0eazMqVsyc");
        //ParseObject.registerSubclass(GeoBus.class);



    }


    @Override
    public void onMapReady(final GoogleMap googleMap)
    {
        if (init == 0)
        {

            map = googleMap;
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(tuxtepec, (float) 13.5));
            polyCreateCobao(latLongCobao);
            polyCreateTec(latLongTec);
            //doStuff("tec num: " + linesTec.length);
            //doStuff("cobao num: "+ linesCobao.length);
            //askBackground();
        }

            mHandler.postDelayed(new Runnable() {
                public void run() {

                    if (cont < 37 && (cont + 1) != 37) {
                        if (cont > 0) {
                            marker.remove();
                        }
                        tuxtepec = latLongTec[cont];
                        String k = "/home/dake10/Downloads/ParseCode/LocalBus/app/src/main/res/assets/school_bus.png";
                        marker = map.addMarker(new MarkerOptions().position(tuxtepec).title("Marker: " + cont));
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.school_bus));
                        animateMarkerToGB(marker, latLongTec[cont + 1], inter);

                    }
                    if (cont >= 1 && cont < 37) {
                        //linesTec[cont - 1].setColor(Color.RED);
                    }
                    if (cont2 < 71 && (cont2 + 1) != 71) {
                        if (cont2 > 0) {
                            marker2.remove();
                        }
                        tuxtepec = latLongCobao[cont2];
                        marker2 = map.addMarker(new MarkerOptions().position(tuxtepec).title("Marker: " + cont2));
                        marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.school_bus));
                        animateMarkerToGB(marker2, latLongCobao[cont2 + 1], inter);

                    }
                    if (cont2 >= 1 && cont2 < 71) {
                        //linesCobao[cont2 - 1].setColor(Color.BLACK);
                    }

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            init = 1;
                            cont++;
                            cont2++;
                            onMapReady(googleMap);
                        }
                    }, 1);
                }
            }, 5000);
    }

    public void doStuff(String mensaje) {
        Toast.makeText(MapsActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    static void animateMarkerToGB(final Marker marker, final LatLng finalPosition, final LatLngInterpolator latLngInterpolator) {
        final LatLng startPosition = marker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationInMs = 5000;

        handler.post(new Runnable() {
            long elapsed;
            float t;
            float v;

            @Override
            public void run() {
                // Calculate progress using interpolator
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed / durationInMs;
                v = interpolator.getInterpolation(t);

                marker.setPosition(latLngInterpolator.interpolate(v, startPosition, finalPosition));
                /*xPoly = map.addPolyline(new PolylineOptions()
                        .add(startPosition,marker.getPosition())
                        .width(5)
                        .color(Color.RED));*/
                // Repeat till progress is complete.
                if (t < 1) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    void askBackground(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GeoL");
        query.whereEqualTo("lNCZlEIU4K","root");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (objects == null) {
                    Toast.makeText(MapsActivity.this, "fallo", Toast.LENGTH_SHORT).show();
                    Log.d("score", "The getFirst request failed.");
                } else {
                    Log.d("score", "Retrieved the object.");
                }
            }
        });
    }
}