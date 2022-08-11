package br.com.ivanilson.cloudparking.service;

import br.com.ivanilson.cloudparking.exception.ParkingNotFoundException;
import br.com.ivanilson.cloudparking.model.Parking;
import br.com.ivanilson.cloudparking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ParkingServise {

    @Autowired
    private ParkingRepository parkingRepository;

    private static Map<String, Parking> parkingMap = new HashMap();

//    static {
//        var id1 = getUUID();
//        var id2 = getUUID();
//        Parking parking1 = new Parking(id1,"DMS-1111","SC", "CELTA","PRETO", null, null,null);
//        Parking parking2 = new Parking(id2,"DTG-1431","PE", "FOX","BRANCO", null, null,null);
//        parkingMap.put(id1,parking1);
//        parkingMap.put(id2,parking2);
//    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();
        //return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(()->
                new ParkingNotFoundException(id));
//        Parking parking = parkingMap.get(id);
//        if(parking == null){
//            throw new ParkingNotFoundException(id);
//        }
//        return parking;
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        //parkingMap.put(uuid, parkingCreate);
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
        //parkingMap.remove(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        //parkingMap.replace(id,parking);
        parkingRepository.save(parkingCreate);
        return parking;
    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
