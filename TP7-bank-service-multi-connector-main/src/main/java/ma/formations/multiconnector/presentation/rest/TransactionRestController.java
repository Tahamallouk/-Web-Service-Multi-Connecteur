package ma.formations.multiconnector.presentation.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.formations.multiconnector.common.CommonTools;
import ma.formations.multiconnector.dtos.transaction.*;
import ma.formations.multiconnector.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rest/transaction")
@CrossOrigin("http://localhost:3000/")
public class TransactionRestController {

    private ITransactionService transactionService;
    private CommonTools commonTools;

    @PostMapping("/create")
    public ResponseEntity<AddWirerTransferResponse> addWirerTransfer(@Valid @RequestBody AddWirerTransferRequest dto) {
        return new ResponseEntity<>(transactionService.wiredTransfer(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TransactionDto> getTransactions(GetTransactionListRequest dto) {
        return transactionService.getTransactions(dto);
    }
}
