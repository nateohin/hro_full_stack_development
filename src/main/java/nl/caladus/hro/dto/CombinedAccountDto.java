//package nl.caladus.hro.dto;
//
//import nl.caladus.hro.model.Account;
//
//import javax.validation.constraints.NotNull;
//import java.util.Set;
//
//public class CombinedAccountDto {
//
//    private long id;
//
//    @NotNull(message = "Account holder must not be null")
//    private Set<Account> accounts;
//
//    @NotNull
//    private String IBAN;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
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
