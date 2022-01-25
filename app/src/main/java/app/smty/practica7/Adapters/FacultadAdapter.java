package app.smty.practica7.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import app.smty.practica7.R;

public class FacultadAdapter implements GoogleMap.InfoWindowAdapter {
    private final View view;
    private Context context;

    public FacultadAdapter(Context context) {
        this.context = context;
        view = LayoutInflater
                    .from(context)
                    .inflate(R.layout.google_maps_marker_info, null);
    }

    public void buildData(Marker marker, View view) {
        TextView textViewFacultad, textViewDecano, textViewDireccion, textViewTelefono, textViewEmail;
        textViewFacultad = view.findViewById(R.id.textViewFacultad);
        textViewDecano = view.findViewById(R.id.textViewDecano);
        textViewDireccion = view.findViewById(R.id.textViewDireccion);
        textViewTelefono = view.findViewById(R.id.textViewTelefono);
        textViewEmail = view.findViewById(R.id.textViewEmail);

        ImageView imageViewLogoFacultad = view.findViewById(R.id.imageViewLogoFacultad);

        String[] facultad = marker.getSnippet().split(",");
        if(facultad.length > 0) {
            textViewFacultad.setText(facultad[0].toUpperCase());
            textViewDecano.setText(facultad[1].toUpperCase());
            textViewDireccion.setText(facultad[2]);
            textViewTelefono.setText(facultad[4]);
            textViewEmail.setText(facultad[5]);

            Picasso.get().load(facultad[3])
                    .error(R.drawable.uteq)
                    .into(imageViewLogoFacultad);

        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        buildData(marker, view);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        buildData(marker, view);
        return view;
    }
}
