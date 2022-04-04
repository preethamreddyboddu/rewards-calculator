package com.rewards.retailer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "purchase")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double value;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public void copyNotNullData(Purchase purchase) {

        if (Objects.nonNull(purchase.customerId))
            this.customerId = purchase.customerId;

        if (Objects.nonNull(purchase.description))
            this.description = purchase.description;

        if (Objects.nonNull(purchase.value))
            this.value = purchase.value;

        if (Objects.nonNull(purchase.date))
            this.date = purchase.date;        
    }

    public static void main(String args[]){
        Date dt= Calendar.getInstance().getTime();
        System.out.println(dt);
    }
}
