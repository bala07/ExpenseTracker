package com.expenseTracker.controller;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.service.ExpenseService;
import org.mockito.Mock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseControllerTest {
    private ExpenseController expenseController;
    @Mock
    private ExpenseService expenseService;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        expenseController = new ExpenseController(expenseService);
    }

    @Test
    public void shouldPopulateViewModelWithListOfExpenses() {
        List<Expense> expenses = newArrayList();

        when(expenseService.getExpensesOfExpenseSheet(1)).thenReturn(expenses);

        ModelAndView modelAndView = expenseController.expenseList(1);

        assertThat(modelAndView.getViewName(), is("expense_list"));
        assertThat(modelAndView.getModelMap().get("expenses"), is(expenses));
    }

    @Test
    public void shouldContainRightRequestMappingUrlForFetchingExpenses() throws NoSuchMethodException {
        Method method = ExpenseController.class.getMethod("expenseList", Integer.class);
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        RequestMapping requestMapping = (RequestMapping) annotations[0];
        assertThat(requestMapping.value().length, is(1));
        assertThat(requestMapping.value()[0], is("{expenseSheetId}/expenses"));


    }
}