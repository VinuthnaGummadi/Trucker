package io.egen.trucker.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Reading")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reading_id")
    private int readingID;

    @Transient
    private String vin;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name = "timestamp", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date timestamp;

    @Column(name="fuel_volume")
    private double fuelVolume;

    @Column(name="speed")
    private double speed;

    @Column(name="engine_hp")
    private double engineHp;

    @Column(name="check_engine_light_on")
    private boolean checkEngineLightOn;

    @Column(name="engine_coolant_low")
    private boolean engineCoolantLow;

    @Column(name="cruise_control_on")
    private boolean cruiseControlOn;

    @Column(name="engine_rpm")
    private double engineRpm;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="tyre_id",referencedColumnName = "tyre_id")
    private Tyre tires;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="reading_id",referencedColumnName="reading_id")
    private List<Alert> alerts;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="vin",referencedColumnName="vin")
    private Vehicle vehicle;

    public int getReadingID() {
        return readingID;
    }

    public void setReadingID(int readingID) {
        this.readingID = readingID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(double engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public double getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(double engineRpm) {
        this.engineRpm = engineRpm;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Tyre getTires() {
        return tires;
    }

    public void setTires(Tyre tires) {
        this.tires = tires;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reading)) return false;
        Reading reading = (Reading) o;
        return getReadingID() == reading.getReadingID() &&
                Double.compare(reading.getFuelVolume(), getFuelVolume()) == 0 &&
                Double.compare(reading.getSpeed(), getSpeed()) == 0 &&
                Double.compare(reading.getEngineHp(), getEngineHp()) == 0 &&
                isCheckEngineLightOn() == reading.isCheckEngineLightOn() &&
                isEngineCoolantLow() == reading.isEngineCoolantLow() &&
                isCruiseControlOn() == reading.isCruiseControlOn() &&
                Double.compare(reading.getEngineRpm(), getEngineRpm()) == 0 &&
                Objects.equals(getLatitude(), reading.getLatitude()) &&
                Objects.equals(getLongitude(), reading.getLongitude()) &&
                Objects.equals(getTimestamp(), reading.getTimestamp()) &&
                Objects.equals(getTires(), reading.getTires());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getReadingID(), getLatitude(), getLongitude(), getTimestamp(), getFuelVolume(), getSpeed(), getEngineHp(), isCheckEngineLightOn(), isEngineCoolantLow(), isCruiseControlOn(), getEngineRpm(), getTires());
    }
}
