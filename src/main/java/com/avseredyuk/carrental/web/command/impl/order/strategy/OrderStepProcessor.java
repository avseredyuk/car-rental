package com.avseredyuk.carrental.web.command.impl.order.strategy;

import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;
import com.avseredyuk.carrental.web.util.wrapper.ResponseWrapper;

/**
 * Created by lenfer on 1/27/17.
 */
public interface OrderStepProcessor {
    String process(RequestWrapper req, ResponseWrapper resp);
}
