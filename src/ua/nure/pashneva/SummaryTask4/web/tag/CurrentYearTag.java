package ua.nure.pashneva.SummaryTask4.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentYearTag extends TagSupport {

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try{
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String year = dateFormat.format(currentDate);
            out.print(year);
        } catch(Exception e){
            System.out.println(e);
        }
        return SKIP_BODY;
    }
}
