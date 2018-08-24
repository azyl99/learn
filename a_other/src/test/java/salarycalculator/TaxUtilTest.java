package salarycalculator;

import org.junit.Before;
import org.junit.Test;

/**
 * @author guya
 * @date 2018/8/16
 */
public class TaxUtilTest {

    TaxUtil taxUtil;

    @Before
    public void before() {
        taxUtil = new TaxUtil();
        taxUtil.setTaxPolicy(TaxPolicy.defaultTaxPolicy());
    }

    @Test
    public void test() {
        assert (0 == taxUtil.tax(0).getTax());
        assert (0 == taxUtil.tax(1000).getTax());
        assert (0 == taxUtil.tax(3500).getTax());
        assert (15 == taxUtil.tax(4000).getTax());
        assert (11195 == taxUtil.tax(50000).getTax());
        assert (29920 == taxUtil.tax(100000).getTax());
    }
}
