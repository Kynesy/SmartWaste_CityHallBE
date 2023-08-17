package it.unisalento.pas.cityhallbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String bdate;
}
