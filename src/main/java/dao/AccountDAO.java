package dao;

import dto.AccountDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AccountDAO extends CrudRepository<AccountDTO, Long> {
    AccountDTO findByUsernameAndPassword(String username, String password);
}
