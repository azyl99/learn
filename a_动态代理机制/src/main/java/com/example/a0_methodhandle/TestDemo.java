package com.example.a0_methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

/**
 * MethodHandlez:把方法用成参数
 *
 * @author guya on 2019/3/20
 */
public class TestDemo {
    public static void main(String[] args) throws Throwable {
        // Lookup 相当于是 MethodHandle 的工厂类
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // mh 相当于是 doubleVal() 函数的句柄，可以用它实现把函数当成参数的功能，这个是 lambda 的实现原理
        MethodHandle mh = lookup.findStatic(TestDemo.class, "doubleVal",
                MethodType.methodType(int.class, int.class));
        MethodHandle mhh = lookup.findStatic(TestDemo.class, "tripleVal",
                MethodType.methodType(int.class, int.class));
        List<Integer> dataList = Arrays.asList(1, 2, 3);

        // 这里是把方法做为参数的例子：
        TestDemo.transform(dataList, mh);
        for (Integer data : dataList) {
            // 2,4,6
            System.out.print(data + " ");
        }
        System.out.println();

        // 用另一个方法作为参数
        TestDemo.transform(dataList, mhh);
        for (Integer data : dataList) {
            System.out.print(data + " ");
        }

    }

    public static List<Integer> transform(List<Integer> dataList, MethodHandle handle) throws Throwable {
        for (int i = 0; i < dataList.size(); i++) {
            //invoke
            dataList.set(i, (Integer) handle.invoke(dataList.get(i)));
        }
        return dataList;
    }

    public static int doubleVal(int val) {
        return val * 2;
    }

    public static int tripleVal(int val) {
        return val * 3;
    }
}
