package com.guilin.java.nginxlog;

/**
 * Created by T57 on 2017/1/17 0017.
 */
public class TimeCount {
    private String time;
    private int count;

    public TimeCount() {
    }

    public TimeCount(String time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeCount timeCount = (TimeCount) o;

        if (count != timeCount.count) return false;
        return time != null ? time.equals(timeCount.time) : timeCount.time == null;

    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + count;
        return result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
