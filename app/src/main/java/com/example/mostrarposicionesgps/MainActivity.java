package com.example.mostrarposicionesgps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actovity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DB_SQLite db = new DB_SQLite(this);

        SQLiteDatabase conn = db.getReadableDatabase();

        String sql = "SELECT id, latitud, longitud, descripcion, idCategoria FROM POSICIONES ";
        Cursor cursor = conn.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay posiciones que mostrar", Toast.LENGTH_LONG).show();
        } else {
            cursor.moveToFirst();
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                Double latitud = cursor.getDouble(cursor.getColumnIndex("latitud"));
                Double longitud = cursor.getDouble(cursor.getColumnIndex("longitud"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
                Integer idCategoria = cursor.getInt(cursor.getColumnIndex("idCategoria"));

                LatLng nuevaPosicion = new LatLng(latitud, longitud);
                mMap.addMarker(new MarkerOptions().position(nuevaPosicion).title(descripcion).icon(BitmapDescriptorFactory.defaultMarker(codigoColorPorCategoria(idCategoria))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        conn.close();
    }

    private float codigoColorPorCategoria (Integer idCategoria) {
        float resutlado = BitmapDescriptorFactory.HUE_RED;
        switch (idCategoria){
            case 1:
                resutlado = BitmapDescriptorFactory.HUE_AZURE;
                break;
            case 2:
                resutlado = BitmapDescriptorFactory.HUE_CYAN;
                break;
            case 3:
                resutlado = BitmapDescriptorFactory.HUE_GREEN;
                break;
            case 4:
                resutlado = BitmapDescriptorFactory.HUE_ORANGE;
                break;
            case 5:
                resutlado = BitmapDescriptorFactory.HUE_YELLOW;
                break;
            case 6:
                break;
        }
        return resutlado;
    }
}
