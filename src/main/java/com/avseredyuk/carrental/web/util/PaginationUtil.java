package com.avseredyuk.carrental.web.util;

import com.avseredyuk.carrental.web.util.wrapper.RequestWrapper;

/**
 * Created by lenfer on 1/25/17.
 */
public class PaginationUtil {
    private PaginationUtil() {
    }

    public static PaginationInformation getPaginationInformation(RequestWrapper req, int totalCount) {
        int pageIndex = 1;
        int itemsPerPage = 5;
        String itemsPerPageString = (String) req.getSession().getAttribute(ConstantClass.ITEMS_PER_PAGE);
        if (itemsPerPageString != null) {
            try {
                itemsPerPage = Integer.parseInt(itemsPerPageString);
            } catch(NumberFormatException e) {
                // do nothing
            }
        }
        try {
            if (req.getParameterMap().containsKey(ConstantClass.PAGE)) {
                pageIndex = Integer.parseInt(req.getParameter(ConstantClass.PAGE));
            }
        } catch(NumberFormatException e) {
            // do nothing
        }
        int numberOfPages = (int) Math.ceil(totalCount * 1.0 / itemsPerPage);
        if ((pageIndex <= 0) || ((pageIndex > numberOfPages) && (numberOfPages > 0))) {
            pageIndex = 1;
        }
        return new PaginationInformation(pageIndex, numberOfPages, (pageIndex - 1) * itemsPerPage, itemsPerPage);
    }


}
