package com.pzd.wmstileoverlay;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

public class MapTileOverlay {

    // get map tiles from geo server
    public static void getMapTiles(GoogleMap googleMap, String tileName) {
        TileProvider tileProvider = TileProviderFactory.getGeoServerWmsTileProvider(tileName);
        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider).zIndex(0));
    }

}
