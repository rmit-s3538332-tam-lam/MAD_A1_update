package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

/**
 * Created by tamlam on 9/10/17.
 */

public class SharedPreferencesController {
    Context context;

    public SharedPreferencesController(Context context) {
        this.context = context;
    }

    public void savePreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        Log.i("SharedPreferences", "saved key: " + key + " with value: " + value);
        editor.apply();
    }

    public String getStringPreferences(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String str = "";
        sharedPreferences.getString(key, "");
        Log.i("SharedPreferences", "get key: " + key + " with value: " + str);
        return str;
    }
    public void saveLocation(Context context, Location location){
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String latitude = Double.toString(location.getLatitude());
        String longtitube = Double.toString(location.getLongitude());

        editor.putString("userLatitude", latitude);
        editor.putString("userLongtitude", longtitube);
        Log.i("SharedPreferences", "saved latitude: " + latitude + " with longtitude: " + longtitube);
        editor.apply();
    }
}
