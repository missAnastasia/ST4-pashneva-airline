package ua.nure.pashneva.SummaryTask4.db.entity.search;

import ua.nure.pashneva.SummaryTask4.db.entity.Entity;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.util.List;
import java.util.Map;

/**
 * Interface which provides behavior for searching entities in datasource.
 *
 * @param <T> class which extends Entity class.
 * @author Anastasia Pashneva
 */
public interface Searcher<T extends Entity> {
    List<T> search(Language language, Map<String, String> params) throws Exception;
}
