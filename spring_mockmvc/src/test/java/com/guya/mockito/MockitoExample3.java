package com.guya.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2018/11/1
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoExample3 {


    /**
     * spy的原理是，如果不打桩默认都会执行真实的方法，如果打桩则返回桩实现。
     */
    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = Mockito.spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");

        // 验证被监视的实例 spyList 执行了两个 add 方法
        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        Assert.assertEquals(spyList.get(0), "one");
        Assert.assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        Assert.assertEquals(100, spyList.size());
    }



    /**
     * <b>@Spy注解</b>：监视<b>已经存在</b>的类
     */
    @Spy
    private List<String> spiedList = new ArrayList<>();

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjected() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        Assert.assertEquals(2, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();
        Assert.assertEquals(100, spiedList.size());
    }

    /**
     * 严重注意：
     */
    @Test
    public void spyTest1() {
        Jack spyJack = Mockito.spy(new Jack());
        // 这种方法使用spy的桩实现实际还是会调用stub的方法（打印"Say: "），但是参数没有传进去。。。，只是返回了手动设置的stub的值
        Mockito.when(spyJack.go(1)).thenReturn(false);
        Assert.assertFalse(spyJack.go(1));
    }
    @Test
    public void spyTest2() {
        Jack spyJack = Mockito.spy(new Jack());
        // 这种方法使用spy的桩实现实际不会调用stub的方法！（没有任何打印信息）
        Mockito.doReturn(false).when(spyJack).go(2);
        Assert.assertFalse(spyJack.go(2));
    }

    class Jack {
        public boolean go(int i) {
            System.out.println("Say: " + i);
            return true;
        }
    }
}
