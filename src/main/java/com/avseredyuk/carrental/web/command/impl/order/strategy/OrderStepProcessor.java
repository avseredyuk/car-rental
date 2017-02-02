package com.avseredyuk.carrental.web.command.impl.order.strategy;

import com.avseredyuk.carrental.web.command.result.CommandResult;
import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

/**
 * Created by lenfer on 1/27/17.
 */
public interface OrderStepProcessor {
    CommandResult process(RequestWrapper req);
}
