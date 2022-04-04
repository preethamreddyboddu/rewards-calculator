package com.rewards.retailer.controller;

import com.rewards.retailer.exception.ResourceNotFoundException;
import com.rewards.retailer.model.Purchase;
import com.rewards.retailer.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

  @Autowired PurchaseRepository purchaseRepository;

  @GetMapping
  public ResponseEntity<List<Purchase>> getAllPurchases() {
    return ResponseEntity.ok().body(purchaseRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Purchase> createPurchase(@Valid @RequestBody Purchase purchase) {
    return ResponseEntity.status(HttpStatus.CREATED).body(purchaseRepository.save(purchase));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Purchase> getPurchaseById(@PathVariable(value = "id") Integer purchaseId) {
    Purchase purchase =
        purchaseRepository
            .findById(purchaseId)
            .orElseThrow(() -> new ResourceNotFoundException("Purchase", "id", purchaseId));
    return ResponseEntity.ok().body(purchase);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<?> notFound(ResourceNotFoundException exception) {
    exception.printStackTrace();
    throw exception;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> serverError(Exception exception) {
    exception.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
