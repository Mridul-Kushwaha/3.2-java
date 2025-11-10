package example.c;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class BankService {

    private final AccountDAO accountDAO;
    public BankService(AccountDAO dao) { this.accountDAO = dao; }

    @Transactional
    public void transfer(Integer fromId, Integer toId, BigDecimal amount) {

        Account from = accountDAO.findById(fromId);
        Account to   = accountDAO.findById(toId);

        if (from == null || to == null)
            throw new IllegalArgumentException("Account not found");

        if (from.getBalance().compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient funds");

        from.setBalance(from.getBalance().subtract(amount));
        accountDAO.update(from);

        // simulate failure to check rollback
        // if (true) throw new RuntimeException("Simulated failure");

        to.setBalance(to.getBalance().add(amount));
        accountDAO.update(to);
    }
}
