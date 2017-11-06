package ru.kurau.exp;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author kurau (Yuri Kalinin)
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(SharedResourceModule.class)
public class ResourcesTest {

    private static final String DEFAULT_CONDITION = "1";
    private static final String DEFAULT_LOGIN = "l1";

    private User user;

    @Inject
    public SharedResources<User> sharedResources;

    @Before
    public void init() {
        System.out.println(" FIRST -> " + System.currentTimeMillis());
    }

    @Test
    public void fastTest() throws InterruptedException {
        System.out.println(sharedResources);
        user = sharedResources.occupy(user -> user.getCondition().contains(DEFAULT_CONDITION));
        System.out.println(" FAST WAIT -> " + System.currentTimeMillis());
        Thread.sleep(15000);
        System.out.println(" FAST DONE -> " + System.currentTimeMillis());
        shouldGetDefaultUser();
    }

    @After
    public void releaseUser() {
        sharedResources.release(user);
    }

    private void shouldGetDefaultUser() {
        assertThat("Should get defaultUser", user.getLogin(), is(DEFAULT_LOGIN));
    }
}
