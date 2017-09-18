package ua.nure.pashneva.SummaryTask4.db.entity.search;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;

import java.util.*;

/**
 * Realization of Searcher for Staff entity.
 *
 * @author Anastasia Pashneva
 */
public class StaffSearcher implements Searcher<Staff> {

    @Override
    public List<Staff> search(Language language, Map<String, String> params) throws Exception {
        List<Staff> staffList = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "post_id" : {
                    staffList.addAll(searchByPost(language, Integer.parseInt(entry.getValue())));
                    break;
                }
            }
        }

        if (params.size() > 1 && staffList.size() > 0) {
            Set<Staff> staffSet = new HashSet<>();

            for (int i = 0; i < staffList.size(); i++) {
                int count = 0;
                for (int j = 0; j < staffList.size(); j++) {
                    if (staffList.get(i).equals(staffList.get(j))) {
                        count++;
                    }
                }

                if (count == params.size()) {
                    staffSet.add(staffList.get(i));
                }
            }
            staffList = new ArrayList<>(staffSet);
        }
        return staffList;
    }

    /**
     * Private method for obtaining staff by its post identifier.
     *
     * @param language object of Language class which contains data of current locale.
     * @param postId string which contains value of post identifier.
     * @return collection (List) of obtained from database Staff objects.
     * @throws Exception
     */
    private List<Staff> searchByPost(Language language, int postId) throws Exception {
        return DAOFactory.getInstance().getStaffDAO().readByPost(postId, language);
    }
}
