package com.avseredyuk.carrental.web.command.impl.order.strategy;

import com.avseredyuk.carrental.web.command.impl.order.strategy.impl.FirstStepProcessor;
import com.avseredyuk.carrental.web.command.impl.order.strategy.impl.FourthStepProcessor;
import com.avseredyuk.carrental.web.command.impl.order.strategy.impl.SecondStepProcessor;
import com.avseredyuk.carrental.web.command.impl.order.strategy.impl.ThirdStepProcessor;

/**
 * Created by lenfer on 1/27/17.
 */
public enum OrderSteps{
    STEP1(new FirstStepProcessor()),
    STEP2(new SecondStepProcessor()),
    STEP3(new ThirdStepProcessor()),
    STEP4(new FourthStepProcessor());

    private final OrderStepProcessor processor;

    OrderSteps(OrderStepProcessor processor) {
        this.processor = processor;
    }

    public OrderStepProcessor getProcessor() {
        return processor;
    }
}
