/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.plugin.geospatial;

import com.facebook.presto.plugin.geospatial.BingTileFunctions.BingTileCoordinatesFunction;
import com.facebook.presto.plugin.geospatial.aggregation.ConvexHullAggregation;
import com.facebook.presto.plugin.geospatial.aggregation.GeometryUnionAgg;
import com.facebook.presto.spi.Plugin;
import com.facebook.presto.spi.type.Type;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static com.facebook.presto.plugin.geospatial.BingTileType.BING_TILE;
import static com.facebook.presto.plugin.geospatial.GeometryType.GEOMETRY;
import static com.facebook.presto.plugin.geospatial.KdbTreeType.KDB_TREE;
import static com.facebook.presto.plugin.geospatial.SphericalGeographyType.SPHERICAL_GEOGRAPHY;

public class GeoPlugin
        implements Plugin
{
    @Override
    public Iterable<Type> getTypes()
    {
        return ImmutableList.of(GEOMETRY, BING_TILE, KDB_TREE, SPHERICAL_GEOGRAPHY);
    }

    @Override
    public Set<Class<?>> getFunctions()
    {
        return ImmutableSet.<Class<?>>builder()
                .add(GeoFunctions.class)
                .add(BingTileOperators.class)
                .add(BingTileFunctions.class)
                .add(BingTileCoordinatesFunction.class)
                .add(ConvexHullAggregation.class)
                .add(GeometryUnionAgg.class)
                .add(KdbTreeCasts.class)
                .add(SpatialPartitioningAggregateFunction.class)
                .add(SpatialPartitioningInternalAggregateFunction.class)
                .build();
    }
}
