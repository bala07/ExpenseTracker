package com.expenseTracker.service;

import com.expenseTracker.nonDomain.Settlement;
import com.expenseTracker.nonDomain.UserBalanceSummary;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DebtSettlementHelperTest {
    private DebtSettlementHelper debtSettlementHelper;

    @BeforeMethod
    public void setup() {
        debtSettlementHelper = new DebtSettlementHelper();
    }

    @Test
    public void shouldReturnCorrectSettlements1() {
        UserBalanceSummary user1 = new UserBalanceSummary(1, "foo", 25);
        UserBalanceSummary user2 = new UserBalanceSummary(2, "bar", -25);

        List<Settlement> settlements = debtSettlementHelper.computeSettlementForDebts(newArrayList(user1, user2));

        assertThat(settlements.size(), is(1));
        assertThat(settlements.get(0).debtorId, is(2));
        assertThat(settlements.get(0).creditorId, is(1));
        assertThat(settlements.get(0).amountSettled, is(25));
    }

    @Test
    public void shouldReturnCorrectSettlements2() {
        UserBalanceSummary user1 = new UserBalanceSummary(1, "foo", 25);
        UserBalanceSummary user2 = new UserBalanceSummary(2, "bar", 30);
        UserBalanceSummary user3 = new UserBalanceSummary(3, "foobar", -55);

        List<Settlement> settlements = debtSettlementHelper.computeSettlementForDebts(newArrayList(user1, user2, user3));

        assertThat(settlements.size(), is(2));
        assertThat(settlements.stream().anyMatch(settlement -> (settlement.creditorId == 1 && settlement.debtorId == 3 && settlement.amountSettled == 25)), is(true));
        assertThat(settlements.stream().anyMatch(settlement -> (settlement.creditorId == 2 && settlement.debtorId == 3 && settlement.amountSettled == 30)), is(true));
    }

    @Test
    public void shouldReturnCorrectSettlements3() {
        UserBalanceSummary user1 = new UserBalanceSummary(1, "foo", -20);
        UserBalanceSummary user2 = new UserBalanceSummary(2, "bar", -20);
        UserBalanceSummary user3 = new UserBalanceSummary(3, "foobar", 10);
        UserBalanceSummary user4 = new UserBalanceSummary(4, "barfoo", 30);


        List<Settlement> settlements = debtSettlementHelper.computeSettlementForDebts(newArrayList(user1, user2, user3, user4));

        assertThat(settlements.size(), is(3));
        assertThat(settlements.stream().anyMatch(settlement -> (settlement.creditorId == 4 && settlement.debtorId == 1 && settlement.amountSettled == 20)), is(true));
        assertThat(settlements.stream().anyMatch(settlement -> (settlement.creditorId == 4 && settlement.debtorId == 2 && settlement.amountSettled == 10)), is(true));
        assertThat(settlements.stream().anyMatch(settlement -> (settlement.creditorId == 3 && settlement.debtorId == 2 && settlement.amountSettled == 10)), is(true));
    }

    @Test
    public void shouldReturnEmptyListWhenThereAreNoUsers() {
        List<Settlement> settlements = debtSettlementHelper.computeSettlementForDebts(newArrayList());

        assertThat(settlements.size(), is(0));
    }
}