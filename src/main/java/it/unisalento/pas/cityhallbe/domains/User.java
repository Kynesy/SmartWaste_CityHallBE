package it.unisalento.pas.cityhallbe.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document("users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id private String id;
    private String name;
    private String surname;
    private String email;
    private Date bdate;
}
