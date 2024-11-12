package dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @Id
    private String username;
    private String password;

}
