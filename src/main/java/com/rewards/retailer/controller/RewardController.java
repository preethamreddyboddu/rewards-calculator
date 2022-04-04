package com.rewards.retailer.controller;

import com.rewards.retailer.model.RewardPoints;
import com.rewards.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {

  @Autowired RewardService rewardService;

  @GetMapping("/{customerId}")
  public ResponseEntity<RewardPoints> getRewardPoints(@PathVariable Long customerId) {
    RewardPoints rewardPoints = rewardService.getRewardPoints(customerId);
    return ResponseEntity.ok().body(rewardPoints);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> serverError(Exception exception) {
    exception.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
