package org.acme.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.dto.AccountDTO;

import java.util.List;

@ApplicationScoped
public class AccountDAO {

    @Inject
    EntityManager em;

    @Transactional
    public AccountDTO findByUsernameAndPassword(String username, String password) {
        List<AccountDTO> result = em.createQuery("select userName, password from account where userName= :value1 and password=:value2", AccountDTO.class).setParameter("value1", username).setParameter("value2", password).getResultList();
        if (!result.isEmpty()) {
            return result.getFirst();
        } else {
            return null;
        }
    }
}
