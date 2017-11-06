package ru.kurau.exp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import static java.util.Arrays.asList;

/**
 * @author kurau (Yuri Kalinin)
 */
public class SharedResourceModule extends AbstractModule {

    private final static Locks locks = new HashMapLocks();

    protected void configure() {
    }

    @Provides
    @Singleton
    public SharedResources<User> provideSharedAccounts() {
        System.out.println(" LOCKS -> " + locks);
        return new SharedResources<User>(asList(
                new User("l1", "p1", "c1"),
                new User("l2", "p2", "c2"),
                new User("l3", "p3", "c3")), locks);
    }

}
