package app.smty.practica7;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import app.smty.practica7.Adapters.FacultadAdapter;
import app.smty.practica7.Models.Facultad;
import app.smty.practica7.Services.DBFacultadesFakeService;
import app.smty.practica7.databinding.ActivityMapsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final double[] LAT_LNG_CENTRAL = { -1.0123358880449087, -79.47078543647346 };
    private final double[] LAT_LNG_MARIA = { -1.0802279369870107, -79.50134344131985 };
    private boolean CENTRAL = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Call<List<Facultad>> facultades = serviceFakeFacultades().getFacultades();
        facultades.enqueue(new Callback<List<Facultad>>() {
            @Override
            public void onResponse(Call<List<Facultad>> call, Response<List<Facultad>> response) {
                List<Facultad> facultadList = response.body();

                facultadList.forEach(e -> {
                    LatLng ubicacionMap = new LatLng(e.getMap()[0], e.getMap()[1]);
                    mMap.addMarker(new MarkerOptions()
                            .position(ubicacionMap)
                            .title(e.getFacultad())
                            .snippet(e.toString())
                    );
                });
            }

            @Override
            public void onFailure(Call<List<Facultad>> call, Throwable t) {

            }
        });

        mMap.setInfoWindowAdapter(new FacultadAdapter(this));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(LAT_LNG_CENTRAL[0], LAT_LNG_CENTRAL[1]), 17));
    }

    private DBFacultadesFakeService serviceFakeFacultades() {
        List<Facultad> facultadList;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/otherluis/db-fake-facultades-uteq/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DBFacultadesFakeService service = retrofit.create(DBFacultadesFakeService.class);
        return service;
    }

    public void cambiarCampus(View v) {
        if (CENTRAL) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(LAT_LNG_MARIA[0], LAT_LNG_MARIA[1]), 17));
            CENTRAL = false;
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(LAT_LNG_CENTRAL[0], LAT_LNG_CENTRAL[1]), 17));
            CENTRAL = true;
        }
    }
}