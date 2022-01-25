package app.smty.practica7.Services;

import java.util.List;

import app.smty.practica7.Models.Facultad;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DBFacultadesFakeService {
    @GET("facultades")
    Call<List<Facultad>> getFacultades();
}
