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
public class SecondResourceTest {

    private static final String DEFAULT_CONDITION = "1";
    private static final String DEFAULT_LOGIN = "l1";

    private User user;

    @Inject
    public SharedResources<User> sharedResources;

    @Before
    public void init() {
        System.out.println(" SECOND -> " + System.currentTimeMillis());
    }

    @Test
    public void secondTest() throws InterruptedException {
        System.out.println(sharedResources);
        Thread.sleep(2000);
        user = sharedResources.occupy(user -> user.getCondition().contains(DEFAULT_CONDITION));
        assertThat("Should get defaultUser", user.getLogin(), is(DEFAULT_LOGIN));
    }

    @After
    public void release() {
        sharedResources.release(user);
    }
}
