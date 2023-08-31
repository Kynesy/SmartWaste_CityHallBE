package it.unisalento.pas.cityhallbe.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("warnings")
@NoArgsConstructor
@Getter
@Setter
public class Warning {
    @Id private String id;
    private String userId;
    private String message;
}
