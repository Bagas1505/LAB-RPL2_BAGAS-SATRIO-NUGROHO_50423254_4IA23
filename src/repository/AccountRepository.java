package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Account account) {
        if (account.getId() == 0) {
            entityManager.persist(account);
        } else {
            entityManager.merge(account);
        }
    }

    public List<Account> findAll() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }
}