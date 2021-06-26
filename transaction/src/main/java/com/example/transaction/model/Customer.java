package com.example.transaction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transactions> transactions;

    @JsonInclude
    @Transient
    private Long rewardPoints;

    @JsonInclude
    @Transient
    private Double totalPurchases;

    @JsonInclude
    @Transient
    private Map<String, Long> rewardsPerMonth;

    public Customer() { }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Long getRewardPoints() {
        if (CollectionUtils.isEmpty(transactions))
            return 0L;

        return transactions.stream().map(Transactions::getPoints).reduce(0L, Long::sum);
    }

    public void setRewardPoints(Long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Double getTotalPurchases() {
        if (CollectionUtils.isEmpty(transactions))
            return 0d;

        return transactions.stream().map(Transactions::getTotal).reduce(0d, Double::sum);

    }

    public void setTotalPurchases(Double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public void setRewardsPerMonth(Map<String, Long> rewardsPerMonth) {
        this.rewardsPerMonth = rewardsPerMonth;
    }

    public Map<String, Long> getRewardsPerMonth() {
        rewardsPerMonth = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        transactions.forEach(trns -> {
            cal.setTime(trns.getSavedDate());
            String month = Month.of(cal.get(Calendar.MONTH) + 1).name();
            if (this.rewardsPerMonth.containsKey(month)) {
                rewardsPerMonth.put(month, trns.getPoints() + rewardsPerMonth.get(month));
            } else {
                this.rewardsPerMonth.put(month, trns.getPoints());
            }
        });
        return rewardsPerMonth;
    }

}
