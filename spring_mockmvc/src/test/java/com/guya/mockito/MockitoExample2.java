package com.guya.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)// 自动初始化mock
public class MockitoExample2 {

    /**
     * 在上面的测试中我们在每个测试方法里都mock了一个List对象，
     * 为了避免重复的mock，使测试类更具有可读性，我们可以使用下面的注解方式来快速模拟对象
     */
    @Mock
    private List mockList;

//    @Before
//    public void before(){
//        MockitoAnnotations.initMocks(this);// 必须初始化mock。添加@RunWith(MockitoJUnitRunner.class)后就不用了
//    }

    @Test
    public void shorthand() {
        mockList.add(1);
        Mockito.verify(mockList).add(1);
    }


    /**
     * 匹配定制参数
     */
    @Test
    public void with_arguments() {
        Comparable comparable = Mockito.mock(Comparable.class);
        //预设根据不同的参数返回不同的结果
        Mockito.when(comparable.compareTo("Test")).thenReturn(1);
        Mockito.when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        // 对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    /**
     * 匹配自己想要的任意参数
     */
    @Test
    public void with_unspecified_arguments() {
        Mockito.when(mockList.get(Mockito.anyInt())).thenReturn(1);
        assertEquals(1, mockList.get(1));
        assertEquals(1, mockList.get(999));

        Mockito.when(mockList.contains(Mockito.argThat(new IsValid()))).thenReturn(true);
        assertTrue(mockList.contains("1"));
        assertTrue(!mockList.contains("3"));

        assertTrue(!mockList.remove(6L));// 默认false. 对于没有预设的情况会返回默认值
        Mockito.when(mockList.remove(Mockito.argThat(o -> o instanceof String))).thenReturn(true);// lambda
        assertTrue(mockList.remove("4"));
        assertTrue(!mockList.remove(6L));// 默认false
    }

    private class IsValid implements ArgumentMatcher<Object> {
        @Override
        public boolean matches(Object o) {
            return (String) o == "1" || (String) o == "2";
        }
    }

    /**
     * 如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
     */
    @Test
    public void all_arguments_provided_by_matchers() {
        Comparator comparator = Mockito.mock(Comparator.class);
        comparator.compare("nihao", "hello");
        Mockito.verify(comparator).compare(ArgumentMatchers.anyString(), ArgumentMatchers.eq("hello"));
//        Mockito.verify(comparator).compare(ArgumentMatchers.anyString(), "hello");// 无效的参数匹配使用
    }


    /**
     * 使用方法预期回调接口生成期望值（Answer结构）
     */
    @Test
    public void answerTest() {
        Mockito.when(mockList.get(Mockito.anyInt())).thenAnswer(new CustomAnswer());
        Assert.assertEquals("hello world: 0", mockList.get(0));
        Assert.assertEquals("hello world: 999", mockList.get(999));
        Mockito.when(mockList.remove(Mockito.anyString())).thenAnswer(invocation -> invocation.getArguments()[0].equals("hhh"));
        Assert.assertEquals(true, mockList.remove("hhh"));
        Assert.assertEquals(false, mockList.remove("xxx"));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world: " + args[0];
        }
    }

}
