package ua.nure.pashneva.SummaryTask4.db.entity.comparator;

import ua.nure.pashneva.SummaryTask4.db.entity.Flight;

import java.util.Comparator;

public class FlightNumberComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) {
        return flight1.getNumber().compareToIgnoreCase(flight2.getNumber());
    }
}
