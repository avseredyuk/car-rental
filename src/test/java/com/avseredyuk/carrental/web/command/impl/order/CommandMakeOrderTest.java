package com.avseredyuk.carrental.web.command.impl.order;

import com.avseredyuk.carrental.util.Utils;
import com.avseredyuk.carrental.domain.Automobile;
import com.avseredyuk.carrental.domain.DeliveryPlace;
import com.avseredyuk.carrental.domain.Order;
import com.avseredyuk.carrental.service.impl.factory.ServiceFactoryImplementation;
import com.avseredyuk.carrental.util.RandomUtil;
import com.avseredyuk.carrental.web.command.impl.factory.CommandFactory;
import com.avseredyuk.carrental.web.command.impl.order.strategy.OrderSteps;
import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.ConfigurationManager;
import com.avseredyuk.carrental.web.util.ConstantClass;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.SessionWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.avseredyuk.carrental.web.util.ConstantClass.*;

/**
 * Created by lenfer on 1/30/17.
 */
public class CommandMakeOrderTest extends Utils {
    public static final String NON_VALID_STEP_ID = "nonvalidstepid";
    RequestWrapper req = mock(RequestWrapper.class);
    SessionWrapper session = mock(SessionWrapper.class);

    @Before
    public void setUp() throws Exception {
        resetDB();
    }

    @Test
    public void executeInvalidStep() throws Exception {
        when(req.getParameter(ConstantClass.ORDERSTEP)).thenReturn(NON_VALID_STEP_ID);
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                CommandFactory.getInstance().getByName(COMMAND_MAKE_ORDER).execute(req));
    }

    @Test
    public void processValidStep1() throws Exception {
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.1"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP1.getProcessor().process(req));
        verify(req).setAttribute(eq(PLACES), Matchers.anyListOf(DeliveryPlace.class));
        verify(req).setAttribute(eq(DATE_NOW), any(Date.class));
    }

    @Test
    public void processStep2Empty() throws Exception {
        when(req.getSession()).thenReturn(session);
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
    }

    @Test
    public void processStep2InvalidRequestDates() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(DATE_FROM)).thenReturn("x2017-01-30T17:32");
        when(req.getParameter(DATE_TO)).thenReturn("201x7-0d1-30T17:");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
    }

    @Test
    public void processStep2InvalidRequestPlaces() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(PLACE_FROM)).thenReturn("ewrerhhgdf");
        when(req.getParameter(PLACE_TO)).thenReturn("201x7hhvjg");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
    }

    @Test
    public void processStep2IncorrectRequestDates() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(DATE_FROM)).thenReturn("2017-01-30T17:32");
        when(req.getParameter(DATE_TO)).thenReturn("2017-01-29T17:32");
        when(req.getParameter(PLACE_FROM)).thenReturn("1");
        when(req.getParameter(PLACE_TO)).thenReturn("2");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.makeorder"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
    }

    @Test
    public void processStep2IncorrectRequestDatesInterval() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(DATE_FROM)).thenReturn("2017-01-30T17:32");
        when(req.getParameter(DATE_TO)).thenReturn("2017-01-30T18:33");
        when(req.getParameter(PLACE_FROM)).thenReturn("1");
        when(req.getParameter(PLACE_TO)).thenReturn("2");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.makeorder"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
    }

    @Test
    public void processStep2Valid() throws Exception {
        DeliveryPlace placeFrom = RandomUtil.getPlace();
        ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().persist(placeFrom);
        DeliveryPlace placeTo = RandomUtil.getPlace();
        ServiceFactoryImplementation.getInstance().getDeliveryPlaceService().persist(placeTo);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(DATE_FROM)).thenReturn("2017-01-30T17:32");
        when(req.getParameter(DATE_TO)).thenReturn("2017-01-30T21:34");
        when(req.getParameter(PLACE_FROM)).thenReturn(placeFrom.getId().toString());
        when(req.getParameter(PLACE_TO)).thenReturn(placeTo.getId().toString());
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.2"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP2.getProcessor().process(req));
        verify(req).setAttribute(eq(AUTOMOBILES), Matchers.anyListOf(Automobile.class));
        verify(session).setAttribute(eq(DATE_FROM), any(Date.class));
        verify(session).setAttribute(eq(DATE_TO), any(Date.class));
        verify(session).setAttribute(eq(PLACE_FROM), any(DeliveryPlace.class));
        verify(session).setAttribute(eq(PLACE_TO), any(DeliveryPlace.class));
    }

    @Test
    public void processStep3IncorrectRequestAutomobileId() throws Exception {
        when(req.getSession()).thenReturn(session);
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP3.getProcessor().process(req));
    }

    @Test
    public void processStep3RequestNonexistentAutomobileId() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(AUTOMOBILES)).thenReturn("1234");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.makeorder"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP3.getProcessor().process(req));
    }

    @Test
    public void processStep3RequestValidAutomobileGuest() throws Exception {
        Automobile automobile = RandomUtil.getAutomobile();
        ServiceFactoryImplementation.getInstance().getAutomobileService().persist(automobile);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(AUTOMOBILES)).thenReturn(automobile.getId().toString());
        when(session.getAttribute(DATE_FROM)).thenReturn(new Date());
        when(session.getAttribute(DATE_TO)).thenReturn(new Date());
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.3"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP3.getProcessor().process(req));
        verify(session).setAttribute(eq(ORDER_SUM), anyInt());
    }

    @Test
    public void processStep3RequestValidAutomobileRegistered() throws Exception {
        Automobile automobile = RandomUtil.getAutomobile();
        ServiceFactoryImplementation.getInstance().getAutomobileService().persist(automobile);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(AUTOMOBILES)).thenReturn(automobile.getId().toString());
        when(session.getAttribute(DATE_FROM)).thenReturn(new Date());
        when(session.getAttribute(DATE_TO)).thenReturn(new Date());
        when(session.getAttribute(USERLOGIN)).thenReturn("vasya");
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.4"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP3.getProcessor().process(req));
        verify(session).setAttribute(eq(ORDER_SUM), anyInt());
    }

    @Test
    public void processStep4RequestInvalidParameters() throws Exception {
        when(req.getSession()).thenReturn(session);
        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.notfound"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP4.getProcessor().process(req));
    }

    @Test
    public void processStep4RequestValidParameters() throws Exception {
        Order order = RandomUtil.getOrder();

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(DATE_FROM)).thenReturn(order.getDateFrom());
        when(session.getAttribute(DATE_TO)).thenReturn(order.getDateTo());
        when(session.getAttribute(USERLOGIN)).thenReturn(order.getUser().getLogin());
        when(session.getAttribute(ORDER_SUM)).thenReturn(order.getSum().toString());
        when(session.getAttribute(AUTOMOBILE)).thenReturn(order.getAutomobile());
        when(session.getAttribute(PLACE_FROM)).thenReturn(order.getPlaceFrom());
        when(session.getAttribute(PLACE_TO)).thenReturn(order.getPlaceTo());

        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.makeorder.5"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP4.getProcessor().process(req));
    }

    @Test
    public void processStep4RequestValidParametersAlreadyTaken() throws Exception {
        Order order = RandomUtil.getOrder();
        ServiceFactoryImplementation.getInstance().getOrderService().persist(order);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute(DATE_FROM)).thenReturn(order.getDateFrom());
        when(session.getAttribute(DATE_TO)).thenReturn(order.getDateTo());
        when(session.getAttribute(USERLOGIN)).thenReturn(order.getUser().getLogin());
        when(session.getAttribute(ORDER_SUM)).thenReturn(order.getSum().toString());
        when(session.getAttribute(AUTOMOBILE)).thenReturn(order.getAutomobile());
        when(session.getAttribute(PLACE_FROM)).thenReturn(order.getPlaceFrom());
        when(session.getAttribute(PLACE_TO)).thenReturn(order.getPlaceTo());

        assertEquals(new CommandResult(ConfigurationManager.getProperty("path.page.error.makeorder"), CommandResult.ActionType.FORWARD),
                OrderSteps.STEP4.getProcessor().process(req));
    }

}