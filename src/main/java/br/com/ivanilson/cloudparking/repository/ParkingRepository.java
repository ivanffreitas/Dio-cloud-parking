package br.com.ivanilson.cloudparking.repository;

import br.com.ivanilson.cloudparking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
}
