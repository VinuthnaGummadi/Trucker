package io.egen.trucker.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tyre")
public class Tyre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tyre_id")
    private int tyreID;

    @Column(name="front_left")
    private double frontLeft;

    @Column(name="front_right")
    private double frontRight;

    @Column(name="rear_left")
    private double rearLeft;

    @Column(name="rear_right")
    private double rearRight;

    public int getTyreID() {
        return tyreID;
    }

    public void setTyreID(int tyreID) {
        this.tyreID = tyreID;
    }

    public double getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(double frontLeft) {
        this.frontLeft = frontLeft;
    }

    public double getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(double frontRight) {
        this.frontRight = frontRight;
    }

    public double getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(double rearLeft) {
        this.rearLeft = rearLeft;
    }

    public double getRearRight() {
        return rearRight;
    }

    public void setRearRight(double rearRight) {
        this.rearRight = rearRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tyre)) return false;
        Tyre tyre = (Tyre) o;
        return getTyreID() == tyre.getTyreID() &&
                Double.compare(tyre.getFrontLeft(), getFrontLeft()) == 0 &&
                Double.compare(tyre.getFrontRight(), getFrontRight()) == 0 &&
                Double.compare(tyre.getRearLeft(), getRearLeft()) == 0 &&
                Double.compare(tyre.getRearRight(), getRearRight()) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTyreID(), getFrontLeft(), getFrontRight(), getRearLeft(), getRearRight());
    }
}
