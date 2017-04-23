package com.guilin.java.commons.lang3.builder;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/23.
 */
public class Staff implements Comparable {
    private long staffId;
    private String staffName;
    private Date dateJoined;

    public Staff() {
    }

    public Staff(long staffId, String staffName, Date dateJoined) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.dateJoined = dateJoined;
    }

    @Override
    public int compareTo(Object o) {
        int res = -1;
        if (o != null && Staff.class.isAssignableFrom(o.getClass())) {
            Staff s = (Staff) o;
            res = new CompareToBuilder()
                    .append(dateJoined, s.getDateJoined())
                    .append(staffName, s.getStaffName()).toComparison();
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return new EqualsBuilder()
                .append(staffId, staff.staffId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(staffId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("staffId", staffId)
                .append("staffName", staffName)
                .append("dateJoined", dateJoined)
                .toString();
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
}
