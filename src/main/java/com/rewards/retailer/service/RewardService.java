package com.rewards.retailer.service;

import com.rewards.retailer.model.Purchase;
import com.rewards.retailer.model.RewardPoints;
import com.rewards.retailer.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RewardService {

  private static final int SPEND_ABOVE50 = 50;
  private static final int REWARD_POINTS_1 = 1;
  private static final int SPEND_ABOVE100 = 100;
  private static final int REWARD_POINTS_2 = 2;

  @Autowired PurchaseRepository purchaseRepository;

  public int calculateRewardPoints(Purchase purchase) {
    int value = purchase.getValue().intValue();
    if (value > SPEND_ABOVE100) {
      return (SPEND_ABOVE100 - SPEND_ABOVE50) * REWARD_POINTS_1
          + (value - SPEND_ABOVE100) * REWARD_POINTS_2;
    }
    if (value > SPEND_ABOVE50) {
      return (value - SPEND_ABOVE50) * REWARD_POINTS_1;
    }
    return 0;
  }

  public RewardPoints getRewardPoints(Long customerId) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -3);
    Date from = calendar.getTime();

    RewardPoints rewardPoints =
        RewardPoints.builder().to(Calendar.getInstance().getTime()).from(from).customerPoints(0l).build();
    List<Purchase> purchases =
        purchaseRepository.findAllByCustomerIdAndDateBetween(
            customerId, from, Calendar.getInstance().getTime());

    for (Purchase purchase : purchases) {
      rewardPoints.setCustomerPoints(
          rewardPoints.getCustomerPoints() + this.calculateRewardPoints(purchase));
    }
    return rewardPoints;
  }
}
