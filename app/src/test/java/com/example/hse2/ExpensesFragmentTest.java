package com.example.hse2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExpensesFragmentTest {
    String testsOfLogin[] = {"vvs", "JORIK", "kek123", "ежик"};
    ExpensesFragment test = new ExpensesFragment();
    @Test
    public void setLogin_isCorrect() {
        test.setLogin(testsOfLogin[0]);
        assertEquals(testsOfLogin[0], test.login);

        test.setLogin(testsOfLogin[1]);
        assertEquals(testsOfLogin[1], test.login);

        test.setLogin(testsOfLogin[2]);
        assertEquals(testsOfLogin[2], test.login);

        test.setLogin(testsOfLogin[3]);
        assertEquals(testsOfLogin[3], test.login);
    }
}