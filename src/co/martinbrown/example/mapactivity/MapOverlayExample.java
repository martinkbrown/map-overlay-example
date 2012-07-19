package co.martinbrown.example.mapactivity;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MapOverlayExample extends MapActivity {

    MapView map;
    MapController mc;
    LocationManager lm;
    GeoPoint geoPoint;
    Drawable marker;

    class MyOverlay extends ItemizedOverlay<OverlayItem> {

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();

        public MyOverlay(Drawable drawable) {
            super(drawable);

            boundCenterBottom(drawable);

            items.add(new OverlayItem(geoPoint, "Hello", "Welcome to the Mobile Lab!"));
            items.add(new OverlayItem(new GeoPoint((int) (30.446172 * 1E6),
                    (int) (-84.299466 * 1E6)),
                    "You're late", "You're late, Class starts at 2PM!"));

            populate();
        }

        @Override
        protected OverlayItem createItem(int index) {

            return items.get(index);
        }

        @Override
        protected boolean onTap(int i) {
            Toast.makeText(MapOverlayExample.this,
                    items.get(i).getSnippet(),
                    Toast.LENGTH_SHORT).show();
            return(true);
        }

        @Override
        public int size() {
            return items.size();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        map = (MapView) findViewById(R.id.mapView);
        mc = map.getController();

        map.setBuiltInZoomControls(true);

        geoPoint = new GeoPoint((int) (30.446142 * 1E6), (int) (-84.299673 * 1E6));
        mc.setCenter(geoPoint);

        marker = getResources().getDrawable(R.drawable.mobile_logo);
        marker.setBounds(105, 105, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());

        mc.setZoom(17);

        map.getOverlays().add(new MyOverlay(marker));


    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}