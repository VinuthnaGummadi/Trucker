package io.egen.trucker.entity;



import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    @Id
    @Column(name = "vin")
    private String vin;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "redline_rpm")
    private double redlineRpm;

    @Column(name = "max_fuel_volume")
    private double maxFuelVolume;

    @Column(name = "last_service_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date lastServiceDate;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(double redlineRpm) {
        this.redlineRpm = redlineRpm;
    }

    public double getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(double maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getYear() == vehicle.getYear() &&
                Double.compare(vehicle.getRedlineRpm(), getRedlineRpm()) == 0 &&
                Double.compare(vehicle.getMaxFuelVolume(), getMaxFuelVolume()) == 0 &&
                Objects.equals(getVin(), vehicle.getVin()) &&
                Objects.equals(getMake(), vehicle.getMake()) &&
                Objects.equals(getModel(), vehicle.getModel()) &&
                Objects.equals(getLastServiceDate(), vehicle.getLastServiceDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getVin(), getMake(), getModel(), getYear(), getRedlineRpm(), getMaxFuelVolume(), getLastServiceDate());
    }
}
