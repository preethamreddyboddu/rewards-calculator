package com.rewards.retailer.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Builder
public class RewardPoints {

  @Temporal(TemporalType.DATE)
  private Date from;

  @Temporal(TemporalType.DATE)
  private Date to;

  private Long customerPoints;
}
