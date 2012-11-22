/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2012, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.geotools.process.vector;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Simple rasterizer which covers the entire bounding box of the geometry.
 * 
 * @author Kevin Smith, OpenGeo
 *
 */
public class BoundingBoxGeometryRasterizer extends AbstractGeometryRasterizer {

    @Override
    public void rasterize(Geometry g, Object userData) {
        final Envelope geomBounds = g.getEnvelopeInternal();
        
        
        final int minI = trans.safeI(geomBounds.getMinX());
        final int minJ = trans.safeJ(geomBounds.getMinY());
        final int maxI = trans.safeI(geomBounds.getMaxX());
        final int maxJ = trans.safeJ(geomBounds.getMaxY());
        
        
        for (int i = minI;  i <= maxI;  i++) {
            for (int j = minJ;  j <= maxJ;  j++) {
                handler.point(i, j, userData, null);
            }
        }
    }

}
