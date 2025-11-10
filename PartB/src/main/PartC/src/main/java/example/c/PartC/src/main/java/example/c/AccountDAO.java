package example.c;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

@Repository
public class AccountDAO {

    private final SessionFactory sessionFactory;
    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account findById(Integer id) {
        Session s = sessionFactory.getCurrentSession();
        return s.get(Account.class, id);
    }

    public void update(Account account) {
        Session s = sessionFactory.getCurrentSession();
        s.update(account);
    }
}
