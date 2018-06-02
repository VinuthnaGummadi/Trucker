package io.egen.trucker.repository;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${spring.queries.vehicles.geo}")
    private String vechilesQuery;

    @Value("${spring.queries.vehicles.historical.alerts}")
    private String historicalAlertsQuery;

    @Override
    public List<Reading> findVechiclesGeo(String vin) {
        List<Reading> readings= new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(vechilesQuery);
            query.setParameter("vin", vin);

            List<Object[]> rows = query.getResultList();
            for (Object[] row : rows) {
                Reading reading = new Reading();
                Timestamp stamp = (Timestamp) row[1];
                Date date = new Date(stamp.getTime());
                reading.setTimestamp(date);
                reading.setVin(vin);
                reading.setLatitude((String) row[2]);
                reading.setLongitude((String) row[3]);
                readings.add(reading);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return readings;
    }

    @Override
    public List<Alert> findByVin(String vin) {
        List<Alert> alerts= new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(historicalAlertsQuery);
            query.setParameter("vin", vin);

            List<Object[]> rows = query.getResultList();
            for (Object[] row : rows) {
                Alert alert = new Alert();
                alert.setPriority((String) row[0]);
                Timestamp stamp = (Timestamp) row[1];
                Date date = new Date(stamp.getTime());
                alert.setTimestamp(date);
                alerts.add(alert);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return alerts;
    }

    @Override
    public void save(Reading reading) {
        entityManager.persist(reading);
        entityManager.flush();
    }
}
