package dao;

import dto.AccountDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountDAO {

    @Inject
    EntityManager em;

    @Transactional
    public AccountDTO findByUsernameAndPassword(String username, String password) {
        return em.createQuery("select username, password from account where username= :value1 and password=:value2", AccountDTO.class).setParameter("value1", username).setParameter("value2", password).getSingleResult();
    }
}
