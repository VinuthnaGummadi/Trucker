package io.egen.trucker.repository;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import io.egen.trucker.entity.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class AlertRepositoryImpl implements AlertRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${spring.queries.vehicles.alerts}")
    private String vechilesQuery;

    @Override
    public List<Vehicle> findVechiclesByAlert(String alert) {
        List<Vehicle> vehicles= new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(vechilesQuery);
            query.setParameter(1, alert);

            List<Object[]> rows = query.getResultList();
            for (Object[] row : rows) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin((String) row[1]);
                vehicle.setMake((String) row[2]);
                vehicle.setModel((String) row[3]);
                vehicle.setYear((int) row[4]);
                vehicle.setRedlineRpm((double) row[5]);
                vehicle.setMaxFuelVolume((double) row[6]);
                Timestamp stamp = (Timestamp) row[7];
                Date date = new Date(stamp.getTime());
                vehicle.setLastServiceDate(date);

                vehicles.add(vehicle);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Alert save(Alert alert) {
        entityManager.persist(alert);
        entityManager.flush();
        return alert;
    }
}
