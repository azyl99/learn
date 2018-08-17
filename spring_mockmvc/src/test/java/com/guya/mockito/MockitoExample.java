package com.guya.mockito;


import lombok.Data;
import lombok.Getter;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Answers.RETURNS_DEEP_STUBS;
//import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.junit.Assert.assertTrue;

import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;

public class MockitoExample {


    /**
     * 验证行为
     */
    @Test
    public void verify_behaviour() {
        //模拟创建一个List对象
        List mock = Mockito.mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        Mockito.verify(mock).clear();
        Mockito.verify(mock).add(1);
    }

    /**
     * 模拟我们所期望的结果
     */
    @Test
    public void when_thenReturn() {
        //mock一个Iterator类
        Iterator iterator = Mockito.mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");// 第二个thenReturn override 了前面一个thenReturn
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        assertEquals("hello world world", result);
    }

    /**
     * 模拟方法体抛出异常
     *
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        Mockito.doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    @Test(expected = RuntimeException.class)
    public void doThrow_when() {
        List list = Mockito.mock(List.class);
        Mockito.doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    /**
     * 在创建mock对象时，有的方法我们没有进行stubbing，所以调用时会放回Null这样在进行操作是很可能抛出NullPointerException。
     * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法时会返回SmartNull。
     * 例如：返回类型是String，会返回"";是int，会返回0；是List，会返回空的List。另外，在控制台窗口中可以看到SmartNull的友好提示。
     */

    @Test
    public void returnsSmartNullsTest() {
        List mock = Mockito.mock(List.class);
        System.out.println(mock.get(0));
        System.out.println(mock.toArray());
//        System.out.println(mock.toArray().length); // NullPointerException
        System.out.println("------------");

        // 使用 RETURNS_SMART_NULLS 参数创建的mock对象，不会抛出NullPointerException异常。
        // 另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        List mock1 = Mockito.mock(List.class, RETURNS_SMART_NULLS);// RETURNS_DEEP_STUBS并不能起到这个效果
        System.out.println(mock1.get(0));
        System.out.println("---");
        System.out.println(mock1.toArray());
        System.out.println(mock1.toArray().length);
        System.out.println("------------");
    }

    /**
     * That means that classes like this can be used without having to mock the behavior
     */
    @Test
    public void deepstubsTest() {
        Account account = Mockito.mock(Account.class, RETURNS_DEEP_STUBS);
        Mockito.when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        Mockito.verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2() {
        Account account = Mockito.mock(Account.class);
        RailwayTicket railwayTicket = Mockito.mock(RailwayTicket.class);
        Mockito.when(account.getRailwayTicket()).thenReturn(railwayTicket);
        Mockito.when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        Mockito.verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Data
    public class RailwayTicket {
        private String destination;
    }

    @Data
    public class Account {
        private RailwayTicket railwayTicket;
    }
    // ----------------END----------------------



    /**
     * 捕获参数来进一步断言
     */
    @Test
    public void capturing_args() {
        PersonDao personDao = Mockito.mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        //  captor: 捕获者；俘虏者
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);// 捕获Person类的相关操作
        personService.update(1, "jack");// 调用了Person类
        Mockito.verify(personDao).update(argument.capture());
        assertEquals(1, argument.getValue().getId());
        assertEquals("jack", argument.getValue().getName());
    }

    @Getter
    class Person {
        private int id;
        private String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    interface PersonDao {
        public void update(Person person);
    }

    class PersonService {
        private PersonDao personDao;

        PersonService(PersonDao personDao) {
            this.personDao = personDao;
        }

        public void update(int id, String name) {
            personDao.update(new Person(id, name));
        }
    }
    // ----------------END----------------------


}
