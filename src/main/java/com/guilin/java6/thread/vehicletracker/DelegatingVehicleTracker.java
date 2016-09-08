package com.guilin.java6.thread.vehicletracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by T57 on 2016/9/8 0008.
 * 基于委托的车辆追踪器
 */
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    //返回一个不可修改但却实时的车辆位置视图
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    //返回一个不可修改的车辆位置的快照
    public Map<String, Point> getLocations2() {
        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name:" + id);
        }
    }

    public static void main(String[] args) {
        Map<String, Point> map = new HashMap<>();
        map.put("car", new Point(11, 12));

        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(map);

        Map<String, Point> locations = tracker.getLocations();
        Map<String, Point> locations2 = tracker.getLocations2();

        tracker.setLocations("car", 22, 23);

        System.out.println(tracker.getLocations());

    }


}
