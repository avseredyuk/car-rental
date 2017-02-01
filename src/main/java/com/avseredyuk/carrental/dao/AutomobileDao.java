package com.avseredyuk.carrental.dao;

import com.avseredyuk.carrental.domain.Automobile;

import java.util.Date;
import java.util.List;

/**
 * Created by lenfer on 12/22/16.
 */
public interface AutomobileDao extends CrudDao<Automobile>{
    List<Automobile> getFreeAutomobilesInDateRange(Date dateFrom, Date dateTo);
}
