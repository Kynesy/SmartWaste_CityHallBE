package it.unisalento.pas.cityhallbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WarningDTO {
    private String id;
    private String userId;
    private String message;
}
