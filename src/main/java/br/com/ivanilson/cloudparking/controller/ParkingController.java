package br.com.ivanilson.cloudparking.controller;

import br.com.ivanilson.cloudparking.controller.DTO.ParkingDTO;
import br.com.ivanilson.cloudparking.controller.mapper.ParkingMapper;
import br.com.ivanilson.cloudparking.model.Parking;
import br.com.ivanilson.cloudparking.service.ParkingServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingServise parkingServise;

    private final ParkingMapper parkingMapper;
    public ParkingController(ParkingMapper parkingMapper){
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll(){
       List<Parking> parkingList = parkingServise.findAll();
       List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
       return result;
    }
}
