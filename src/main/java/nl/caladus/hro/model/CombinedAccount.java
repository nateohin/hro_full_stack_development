//package nl.caladus.hro.model;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.NotNull;
//import java.util.Set;
//
//@Entity
//public class CombinedAccount extends BaseEntity {
//
//    @NotNull(message = "Account holder must not be null")
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    private Set<Account> accounts;
//
//    @NotNull
//    private String IBAN;
//
//    public String getIBAN() {
//        return IBAN;
//    }
//
//    public void setIBAN(String IBAN) {
//        this.IBAN = IBAN;
//    }
//
//    public Set<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }
//}
