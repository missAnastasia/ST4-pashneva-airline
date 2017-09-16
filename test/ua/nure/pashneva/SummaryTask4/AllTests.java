package ua.nure.pashneva.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.nure.pashneva.SummaryTask4.web.filter.EncodingFilterTest;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManagerTest;

@RunWith(Suite.class)
@SuiteClasses({SessionManagerTest.class, EncodingFilterTest.class})
public class AllTests {

}
