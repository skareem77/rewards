package com.example.transaction;

import com.example.transaction.model.Customer;
import com.example.transaction.model.Transactions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RewardsTest {

    @Test
    public void test1() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        Customer customer = new Customer (1000, "Amar");
        Set<Transactions> transactionsList = new HashSet<>();
        Transactions t1 = new Transactions(1L, "des 1", 185d, formatter.parse("07-01-2021") );
        Transactions t2 = new Transactions(2L, "des 2", 115d, formatter.parse("07-01-2021") );
        Transactions t3 = new Transactions(3L, "des 3", 674d, formatter.parse("03-02-2021") );
        Transactions t4 = new Transactions(4L, "des 4", 322d, formatter.parse("01-02-2021") );
        Transactions t5 = new Transactions(5L, "des 5", 1016d, formatter.parse("05-03-2021") );
        Transactions t6 = new Transactions(5L, "des 6", 3d, formatter.parse("13-03-2021") );
        transactionsList.add(t1);
        transactionsList.add(t2);
        transactionsList.add(t3);
        transactionsList.add(t4);
        transactionsList.add(t5);
        customer.setTransactions(transactionsList);

        Assertions.assertEquals(t1.getPoints(), 220l);
        Assertions.assertEquals(t2.getPoints(), 80l);
        Assertions.assertEquals(t3.getPoints(), 1198l);
        Assertions.assertEquals(t4.getPoints(), 494l);
        Assertions.assertEquals(t5.getPoints(), 1882l);
        Assertions.assertEquals(t6.getPoints(), 0l);

        Assertions.assertEquals(customer.getRewardPoints(), 3874l );

        Map<String, Long> rewardsPerMonth = customer.getRewardsPerMonth();
        Assertions.assertEquals(rewardsPerMonth.get("JANUARY"), 300l);
        Assertions.assertEquals(rewardsPerMonth.get("FEBRUARY"), 1692l);
        Assertions.assertEquals(rewardsPerMonth.get("MARCH"), 1882l);
    }
}
