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
    public List<AccountDTO> findByUsernameAndPassword(String username) {
        return em.createQuery("select userName, password from account where userName= :value1 ", AccountDTO.class).setParameter("value1",username).getResultList();
    }
}
