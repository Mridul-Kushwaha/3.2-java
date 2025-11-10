package example.c;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="account")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="account_number", unique=true)
    private String accountNumber;

    @Column(name="holder_name")
    private String holderName;

    private BigDecimal balance;

    public Account() {}
    public Account(String accountNumber, String holderName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public Integer getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
