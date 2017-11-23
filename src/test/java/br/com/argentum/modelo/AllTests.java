package br.com.argentum.modelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CandlestickFactoryTest.class, NegociacaoTest.class, CandlestickTest.class })
public class AllTests {

}
