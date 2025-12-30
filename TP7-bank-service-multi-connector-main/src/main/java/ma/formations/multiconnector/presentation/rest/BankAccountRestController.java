package ma.formations.multiconnector.presentation.rest;

import jakarta.validation.Valid;
import ma.formations.multiconnector.dtos.bankaccount.*;
import ma.formations.multiconnector.service.IBankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/bank")
@CrossOrigin("http://localhost:3000/")
public class BankAccountRestController {

    private final IBankAccountService bankAccountService;

    public BankAccountRestController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/all")
    List<BankAccountDto> bankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping
    BankAccountDto bankAccountByRib(@RequestParam(value = "rib") String rib) {
        return bankAccountService.getBankAccountByRib(rib);
    }

    @PostMapping("/create")
    public ResponseEntity<AddBankAccountResponse> addBankAccount(@Valid @RequestBody AddBankAccountRequest dto) {
        return new ResponseEntity<>(bankAccountService.saveBankAccount(dto), HttpStatus.CREATED);
    }
}
