package ua.nure.pashneva.SummaryTask4.db.entity.comparator;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class FlightDateComparator implements Comparator<Flight> {

    private static final Logger LOG = Logger.getLogger(FlightDateComparator.class);

    @Override
    public int compare(Flight flight1, Flight flight2) {
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date parsed1 = new Date();
        Date parsed2 = new Date();
        try {
            parsed1 = format.parse(flight1.getDate());
            parsed2 = format.parse(flight2.getDate());
        } catch (ParseException e) {
            LOG.trace(e.getMessage());
        }*/
        return flight2.getDate().compareTo(flight1.getDate());
    }
}
