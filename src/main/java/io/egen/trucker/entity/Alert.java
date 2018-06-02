package io.egen.trucker.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="alert_id")
    private int alertID;

    @Column(name = "priority")
    private String priority;

    @Column(name = "timestamp", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date timestamp;


    public int getAlertID() {
        return alertID;
    }

    public void setAlertID(int alertID) {
        this.alertID = alertID;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return getAlertID() == alert.getAlertID() &&
                Objects.equals(getPriority(), alert.getPriority()) &&
                Objects.equals(getTimestamp(), alert.getTimestamp());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAlertID(), getPriority(), getTimestamp());
    }
}
