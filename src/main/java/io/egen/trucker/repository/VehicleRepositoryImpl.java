package io.egen.trucker.repository;

import io.egen.trucker.entity.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean findByVinExists(String vin) {
        try {
            Vehicle vehicle1 = entityManager.find(Vehicle.class,vin);

            if(vehicle1!=null){
                return true;
            }else
                return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean findByVehicleExists(Vehicle vehicle) {
        try {

            Vehicle vehicle1 = entityManager.find(Vehicle.class,vehicle.getVin());

            if(vehicle.equals(vehicle1)){
                return true;
            }else
                return false;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicle update(Vehicle vehicle){
        entityManager.merge(vehicle);
        entityManager.flush();
        return vehicle;
    }

    @Override
    public Vehicle create(Vehicle vehicle){
        entityManager.persist(vehicle);
        entityManager.flush();
        return vehicle;
    }

    @Override
    public Vehicle findByVin(String vin) {
        Vehicle vehicle1 = null;
        try {
            vehicle1 = entityManager.find(Vehicle.class,vin);

        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicle1;
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicles = null;
        Query q = entityManager.createQuery(
                "SELECT e FROM " + Vehicle.class.getName() + " e");
        vehicles =  (List<Vehicle>) q.getResultList();
        return vehicles;
    }
}
