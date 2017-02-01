package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.Automobile;

import java.util.Date;
import java.util.List;

/**
 * Created by lenfer on 1/8/17.
 */
public interface AutomobileService extends CrudService<Automobile> {
    List<Automobile> getFreeAutomobilesInDateRange(Date dateFrom, Date dateTo);
}
