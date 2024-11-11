package dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "account")
@Data
public class AccountDTO {

    @Id
    private String username;
    private String password;
}
