package com.avseredyuk.carrental.web.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by lenfer on 1/19/17.
 */
public class PaginationTag extends TagSupport {
    private static final Logger logger = Logger.getLogger(PaginationTag.class);
    private String returnURI;
    private String command;
    private PaginationInformation pageInfo;

    public PaginationTag() {
        // do nothing
    }

    public void setReturnURI(String returnURI) {
        this.returnURI = returnURI;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setPageInfo(PaginationInformation pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        String contextPath = req.getContextPath();
        try {
            int intNumberOfPages = pageInfo.getNumberOfPages();
            int intCurrentPage = pageInfo.getCurrentPage();
            JspWriter out = pageContext.getOut();
            out.write("<table cellspacing=\"0\" class=\"pagination\"><tr><td>");
            if (intCurrentPage != 1) {
                out.write("<a href=\"" + contextPath + "/?command=" + command +
                        "&page=" + (intCurrentPage - 1) + "\"><<</a>&nbsp;&nbsp;");
            }
            for(int i = 1; i <= intNumberOfPages; i++) {
                if (intCurrentPage == i) {
                    out.write(i + "&nbsp;&nbsp;");
                } else {
                    out.write("<a href=\"" + contextPath + "/?command=" + command
                            + "&page=" + i + "\">" + i + "</a>&nbsp;&nbsp;");
                }
            }
            if (intCurrentPage < intNumberOfPages) {
                out.write("<a href=\"" + contextPath + "/?command=" + command
                        + "&page=" + (intCurrentPage + 1) + "\">>></a>&nbsp;&nbsp;");
            }

            out.write("</td><td width=\"7%\"><form method=\"POST\" action=\"" + contextPath +
                    "/\" style=\"display: inline;\">" +
                    "<input type=\"hidden\" name=\"command\" value=\"change_itemsperpage\"/>\n" +
                    "                    <input type=\"hidden\" name=\"return\" value=\"" + returnURI + "\"/>" +
                    "<select name=\"items_per_page\" onchange=\"this.form.submit()\">");
            out.write("<option value=\"5\"" + (pageInfo.getItemsPerPage() == 5 ? " selected" : "") + ">5</option>");
            out.write("<option value=\"10\"" + (pageInfo.getItemsPerPage() == 10 ? " selected" : "") + ">10</option>");
            out.write("<option value=\"25\"" + (pageInfo.getItemsPerPage() == 25 ? " selected" : "") + ">25</option>");
            out.write("</select></form></td></tr></table>");

        } catch (IOException e) {
            logger.error("Error at PaginationTag", e);
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}