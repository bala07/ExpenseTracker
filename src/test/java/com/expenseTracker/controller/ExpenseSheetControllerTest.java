package com.expenseTracker.controller;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.nonDomain.Settlement;
import com.expenseTracker.service.DebtSettlementService;
import com.expenseTracker.service.ExpenseSheetService;
import com.expenseTracker.viewmodel.SettlementViewModel;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseSheetControllerTest {
    private ExpenseSheetController expenseSheetController;
    @Mock
    private ExpenseSheetService expenseSheetService;
    @Mock
    private DebtSettlementService debtSettlementService;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        expenseSheetController = new ExpenseSheetController(expenseSheetService, debtSettlementService);
    }

    @Test
    public void shouldReturnCorrectViewForIndex() {
        String view = expenseSheetController.index();

        assertThat(view, is("index"));
    }

    @Test
    public void shouldContainCorrectRequestUrlMappingForIndex() throws NoSuchMethodException {
        Method method = ExpenseSheetController.class.getMethod("index", null);
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        RequestMapping annotation = (RequestMapping) annotations[0];
        assertThat(annotation.value().length, is(1));
        assertThat(annotation.value()[0], is("/"));
    }

    @Test
    public void shouldCreateNewExpenseSheet() {
        ModelAndView modelAndView = expenseSheetController.createExpenseSheet();

        verify(expenseSheetService).addExpenseSheet(any(ExpenseSheet.class));
        //TODO: How to test the id of the redirect URL???
        assertThat(modelAndView.getViewName(), is("redirect:/expense-sheet/null"));
    }

    @Test
    public void shouldContainCorrectRequestUrlMappingForCreateExpenseSheet() throws NoSuchMethodException {
        Method method = ExpenseSheetController.class.getMethod("createExpenseSheet");
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        RequestMapping annotation = (RequestMapping) annotations[0];
        assertThat(annotation.value().length, is(1));
        assertThat(annotation.value()[0], is("/expense-sheet/create-expense-sheet"));
    }

    @Test
    public void shouldGetExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        when(expenseSheetService.getExpenseSheet(1)).thenReturn(expenseSheet);

        ModelAndView modelAndView = expenseSheetController.getExpenseSheet(1);

        assertThat(modelAndView.getViewName(), is("expense_sheet"));
        assertThat(modelAndView.getModelMap().get("expenseSheet"), is(expenseSheet));
    }

    @Test
    public void shouldContainCorrectRequestUrlMappingForGetExpenseSheetMethod() throws NoSuchMethodException {
        Method method = ExpenseSheetController.class.getMethod("getExpenseSheet", int.class);
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        RequestMapping annotation = (RequestMapping) annotations[0];
        assertThat(annotation.value().length, is(1));
        assertThat(annotation.value()[0], is("/expense-sheet/{expenseSheetId}"));
    }

    @Test
    public void shouldGetSettlementList() {
        ExpenseSheet expenseSheet = new ExpenseSheet();
        Settlement settlement1 = new Settlement(1, "foo", 2, "bar", 10);
        Settlement settlement2 = new Settlement(3, "foobar", 4, "barfoo", 20);
        List<Settlement> settlements = newArrayList(settlement1, settlement2);

        when(expenseSheetService.getExpenseSheet(1)).thenReturn(expenseSheet);
        when(debtSettlementService.computeSettlements(expenseSheet)).thenReturn(settlements);

        ModelAndView modelAndView = expenseSheetController.getSettlementList(1);

        assertThat(modelAndView.getViewName(), is("settlements"));
        List<SettlementViewModel> settlementViewModels = (List<SettlementViewModel>) modelAndView.getModelMap().get("settlements");
        assertThat(settlementViewModels.size(), is(2));
        assertThat(settlementViewModels.stream().anyMatch(viewModel -> viewModel.creditorName == "foo" && viewModel.debtorName == "bar" && viewModel.amount == 10), is(true));
        assertThat(settlementViewModels.stream().anyMatch(viewModel -> viewModel.creditorName == "foobar" && viewModel.debtorName == "barfoo" && viewModel.amount == 20), is(true));
    }

    @Test
    public void shouldContainCorrectRequestUrlMappingForGetSettlementsMethod() throws NoSuchMethodException {
        Method method = ExpenseSheetController.class.getMethod("getSettlementList", int.class);
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        RequestMapping annotation = (RequestMapping) annotations[0];
        assertThat(annotation.value().length, is(1));
        assertThat(annotation.value()[0], is("/expense-sheet/{expenseSheetId}/settlements"));
    }
}
