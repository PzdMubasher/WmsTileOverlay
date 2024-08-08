package com.pzd.wmstileoverlay;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class TileProviderFactory {

    public static WMSTileProvider getGeoServerWmsTileProvider(String tileName) {

        // Geo server url
        final String geoServerUrl = "http://13.235.206.126:8080/geoserver/cite/wms" +
                "?service=WMS" +
                "&version=1.1.1" +
                "&request=GetMap" +
                "&layers=" + tileName +
                "&bbox=%f,%f,%f,%f" +
                "&width=256" +
                "&height=256" +
                "&srs=EPSG:900913" +
                "&format=image/png" +
                "&transparent=true";

        return new WMSTileProvider(256, 256) {

            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                double[] bBox = getBoundingBox(x, y, zoom);
                String formattedUrl = String.format(Locale.US, geoServerUrl, bBox[MINX], bBox[MINY], bBox[MAXX], bBox[MAXY]);
                Log.d("geoServerUrl", formattedUrl);
                Log.d("bBox", bBox[MINX] + ", " + bBox[MINY] + ", " + bBox[MAXX] + ", " + bBox[MAXY]);
                URL url;
                try {
                    url = new URL(formattedUrl);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
                return url;
            }
        };
    }

}
