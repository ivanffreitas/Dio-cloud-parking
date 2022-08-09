package br.com.ivanilson.cloudparking.controller.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //ocutar os campos null
public class ParkingCreateDTO {

    private String license;
    private String state;
    private String model;
    private String color;
}
