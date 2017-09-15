package ua.nure.pashneva.SummaryTask4.db.entity.search;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;

import java.util.*;

public class StaffSearcher implements Searcher<Staff> {

    private static final Logger LOG = Logger.getLogger(StaffSearcher.class);

    @Override
    public List<Staff> search(Language language, Map<String, String> params) throws Exception {
        List<Staff> staffList = new ArrayList<>();
        LOG.trace("Params --> " + params.toString());

        LOG.debug("for starts");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            LOG.trace("entry.getKey() --> " + entry.getKey());
            switch (entry.getKey()) {
                case "post_id" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    staffList.addAll(searchByPost(language, Integer.parseInt(entry.getValue())));
                    break;
                }
            }
        }
        LOG.trace("Staff --> " + staffList.toString());

        if (params.size() > 1 && staffList.size() > 0) {
            Set<Staff> staffSet = new HashSet<>();
            LOG.debug("staffSet --> " + staffSet.toString());
            LOG.debug("staffList size --> " + staffList.size());

            for (int i = 0; i < staffList.size(); i++) {
                int count = 0;
                LOG.debug("for i = " + i + ", count --> " + count);
                for (int j = 0; j < staffList.size(); j++) {
                    LOG.debug("for j = " + j);
                    if (staffList.get(i).equals(staffList.get(j))) {
                        count++;
                        LOG.debug("count --> " + count);
                    }
                }

                if (count == params.size()) {
                    LOG.debug("if count < " + params.size());
                    staffSet.add(staffList.get(i));
                    LOG.debug("staffSet.add " + staffList.get(i).toString());
                }
            }

            LOG.trace("Staff Set --> " + staffSet.toString());
            staffList = new ArrayList<>(staffSet);
        }

        LOG.trace("Staff List --> " + staffList.toString());
        return staffList;
    }

    private List<Staff> searchByPost(Language language, int postId) throws Exception {
        return DAOFactory.getInstance().getStaffDAO().readByPost(postId, language);
    }
}
