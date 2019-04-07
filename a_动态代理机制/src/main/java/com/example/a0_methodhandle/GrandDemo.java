package com.example.a0_methodhandle;

/**
 * @author guya on 2019/3/24
 */

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 在Java程序中，可以通过“super”关键字很方便地调用到父类中的方法，但如果要访问祖类的方法呢？
 * <p>
 * 在JDK 1.7之前，使用纯粹的Java语言很难处理这个问题（直接生成字节码就很简单，如使用ASM等字节码工具），
 * 原因是在Son类的thinking（）方法中无法获取一个实际类型是GrandFather的对象引用，
 * 而invokevirtual指令的分派逻辑就是按照方法接收者的实际类型进行分派，这个逻辑是固化在虚拟机中的，
 * 程序员无法改变。在JDK 1.7中，可以使用MethodHandle来解决相关问题。
 */
public class GrandDemo {
    static class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    static class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("i am father");
        }
    }

    static class Son extends Father {
        @Override
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                // 构造方法句柄
                MethodHandle mh = lookup()
                        .findVirtual(GrandFather.class, "thinking", mt)
                        .bindTo(new GrandFather());
                // 精确调用祖类的virtual方法
                mh.invokeExact();
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        (new Son()).thinking();
    }
}
