package ma.formations.multiconnector.config;

import lombok.RequiredArgsConstructor;
import ma.formations.multiconnector.common.CommonTools;
import ma.formations.multiconnector.dao.BankAccountRepository;
import ma.formations.multiconnector.dao.CustomerRepository;
import ma.formations.multiconnector.dao.UserRepository;
import ma.formations.multiconnector.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CommonTools commonTools;

    @Override
    public void run(String... args) {
        // users agents
        User agent1 = userRepository.save(User.builder().username("user1").firstname("FIRST_NAME1").lastname("LAST_NAME1").build());
        userRepository.save(User.builder().username("user2").firstname("FIRST_NAME2").lastname("LAST_NAME2").build());

        // customers
        Customer c1 = customerRepository.save(Customer.builder()
                .identityRef("A100")
                .user(User.builder().username("cust1").firstname("CUST_FIRST1").lastname("CUST_LAST1").build())
                .build());

        Customer c2 = customerRepository.save(Customer.builder()
                .identityRef("A200")
                .user(User.builder().username("cust2").firstname("CUST_FIRST2").lastname("CUST_LAST2").build())
                .build());

        // bank accounts
        bankAccountRepository.save(BankAccount.builder()
                .rib("RIB-001")
                .amount(10000)
                .createdAt(commonTools.now())
                .accountStatus(AccountStatus.OPENED)
                .customer(c1)
                .build());

        bankAccountRepository.save(BankAccount.builder()
                .rib("RIB-002")
                .amount(5000)
                .createdAt(commonTools.now())
                .accountStatus(AccountStatus.OPENED)
                .customer(c2)
                .build());
    }
}
