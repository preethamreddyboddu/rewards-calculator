package com.rewards.retailer;

import static org.assertj.core.api.Assertions.assertThat;

import com.rewards.retailer.model.Purchase;
import com.rewards.retailer.model.RewardPoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RewardsCalculatorApplicationTests {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private final String API_PURCHASE = "purchase";
  private final String API_REWARDS = "rewards";
  Long customerId = 1l;

  @Test
  public void addPurchase() {
    String purchaseURL = "http://localhost:" + port + "/" + API_PURCHASE;
    Purchase purchase = new Purchase(1, customerId, "description", 120.00, new Date());
    ResponseEntity<Purchase> purchaseEntity =
        restTemplate.postForEntity(purchaseURL, purchase, Purchase.class);
    assertThat(purchaseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  public void retrievePurchase() {
    String purchaseURL = "http://localhost:" + port + "/" + API_PURCHASE;
    Purchase purchase = new Purchase(1, customerId, "description", 120.00, new Date());
    ResponseEntity<Purchase> purchaseEntity =
        restTemplate.postForEntity(purchaseURL, purchase, Purchase.class);
    assertThat(purchaseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    purchaseEntity = restTemplate.getForEntity(purchaseURL + "/" + purchase.getCustomerId(), Purchase.class);
    purchase = purchaseEntity.getBody();
    assertThat(purchaseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertCompare(purchase, purchase, true);
  }

  @Test
  public void getRewards() throws Exception {

    String purchaseURL = "http://localhost:" + port + "/" + API_PURCHASE;
    Purchase purchase = new Purchase(1, customerId, "description", 120.00, new Date());
    ResponseEntity<Purchase> purchaseEntity = restTemplate.postForEntity(purchaseURL, purchase, Purchase.class);
    assertThat(purchaseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    String rewardsURL = "http://localhost:" + port + "/" + API_REWARDS + "/" + customerId;

    // Initialize Variables
    ResponseEntity<RewardPoints> initialRewardEntity = restTemplate.getForEntity(rewardsURL, RewardPoints.class);
    assertThat(initialRewardEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    RewardPoints initialRewardPoints = initialRewardEntity.getBody();
  }

  private void assertCompare(Purchase pa, Purchase pb, boolean includeId) {
    if (includeId) assertThat(pa.getId()).isEqualTo(pb.getId());
    assertThat(pa.getCustomerId()).isEqualTo(pb.getCustomerId());
    assertThat(pa.getDescription()).isEqualTo(pb.getDescription());
    assertThat(pa.getValue()).isEqualTo(pb.getValue());
    assertThat(pa.getDate()).isCloseTo(pb.getDate(), 1000);
  }
}
