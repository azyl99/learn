package 泛型;

import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 工具：
 *  ResolvableType：泛型参数的解析
 *  ReflectionUtils：反射工具
 *  ClassUtils：类工具，获得类的字段、方法、构造函数等
 * 参考https://jinnianshilongnian.iteye.com/blog/1993608
 * @author guya on 2018/12/7
 */
public class TypeDemo<T> {
    // GenericArrayType：组件类型为类型变量的数组
    private T[] a;
    // GenericArrayType：组件类型为参数化类型的数组
    private List<? extends String>[] b;     // String 是 final 类，不能被extends，这里只是举个例子
    // ParameterizedType：参数化类型
    // Map<> 里的 String, ? extends Map<?, Integer> 即通配符表达式，也就是WildcardType
    private Map<Long, ? extends Map<String, Integer>> c;
    // Class：普通类型
    private List d;
    // TypeVariable 类型变量
    private T e;

    public TypeDemo(Integer i, Map<Long, ? extends Map<String, List<Short>>> t) {
    }

    public Map<Integer, String> func(Set<Long> list) {
        return null;
    }

    public static final String DELIMETER = " #\t";
    public static final String DELIMETER2 = " $\t";
    public static final String DELIMETER3 = " *\t";
    public static void main(String [] args) throws Exception{
        Class<?> clazz = TypeDemo.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);      // 否则无法访问私有变量和方法
            Type type = field.getType();
            Type genericType = field.getGenericType();
            System.out.println(field.getName()
                    + DELIMETER + type.getTypeName()        // java.util.List[]     java.lang.Object[]
                    + DELIMETER + ((Class) type).getName()  // [Ljava.util.List;    [Ljava.lang.Object;
                    + DELIMETER + genericType.getTypeName() // java.util.List<?>[]  T[]
            );
            // Long, ? extends Map<String, Integer>
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                // Long
                // ? extends Map<String, Integer>
                for (Type actualTypeArg : actualTypeArguments) {
                    System.out.println("\t" + actualTypeArg.getTypeName()
                            + DELIMETER2 + actualTypeArg.getClass()
                    );
                }
                // 字段
                ResolvableType resolvableType = ResolvableType.forField(field);// ReflectionUtils.findField(TypeDemo.class, "c")
                // Long, ? extends Map<String, Integer>
                System.out.println("\t\t" + resolvableType.getGeneric(0).resolve()           // Long
                        + DELIMETER3 + resolvableType.getGeneric(1).resolve()                // Map
                        + DELIMETER3 + resolvableType.getGeneric(1).getGeneric(0).resolve()  // String
                        + DELIMETER3 + resolvableType.getGeneric(1, 1).resolve()             // Integer
                );
            }
        }
        System.out.println();

        // 构造函数
        // Integer i, Map<Long, ? extends Map<String, List<Short>>> t
        // index=1 -->  Map<Long, ? extends Map<String, List<Short>>
        ResolvableType resolvableType2 = ResolvableType.forConstructorParameter(
                ClassUtils.getConstructorIfAvailable(TypeDemo.class, Integer.class, Map.class), 1);
        System.out.println(resolvableType2.getGeneric(0).resolve()  // Long
                + DELIMETER3 + resolvableType2.getGeneric(1).resolve()       // Map
                + DELIMETER3 + resolvableType2.getGeneric(1, 0).resolve()    // String
                + DELIMETER3 + resolvableType2.getGeneric(1, 1).resolve()    // List
                + DELIMETER3 + resolvableType2.getGeneric(1, 1, 0).resolve() // Short
        );

        // 普通方法
        // Map<Integer, String>  --> Integer, String
        ResolvableType resolvableType3 = ResolvableType.forMethodReturnType(
                ReflectionUtils.findMethod(TypeDemo.class, "func", Set.class));// 等价于ClassUtils.getMethod(TypeDemo.class, "func", Set.class);
        System.out.println(resolvableType3.getGeneric(0).resolve()          // Integer
                + DELIMETER3 + resolvableType3.getGeneric(1).resolve()      // String
        );

        // 数组 getComponentType
        // private List<? extends String>[] b;   -->  ? extends String
        ResolvableType resolvableType4 = ResolvableType.forField(
                ReflectionUtils.findField(TypeDemo.class, "b"));
        if (resolvableType4.isArray()) {
            System.out.println(resolvableType4.getComponentType().getGeneric(0).resolve());
        }

        // 自定义泛型类型
        ResolvableType resolvableType5 = ResolvableType.forClassWithGenerics(Map.class, String.class, Integer.class);// 相当于创建一个 Map<String, Integer>类型
        ResolvableType resolvableType6 = ResolvableType.forArrayComponent(resolvableType5);// 相当于创建一个 Map<String, Integer>[] 数组；
        System.out.println(resolvableType6.getComponentType().getGeneric(0).resolve()
                + DELIMETER3 + resolvableType6.getComponentType().getGeneric(1).resolve()
        );

        // 创建一个List<Integer>[]数组
        ResolvableType resolvableType7 = ResolvableType.forClassWithGenerics(List.class, Integer.class);
        ResolvableType resolvableType8= ResolvableType.forArrayComponent(resolvableType7);
        ResolvableType resolvableType9 = ResolvableType.forClassWithGenerics(List.class, Object.class);
        ResolvableType resolvableType10= ResolvableType.forArrayComponent(resolvableType9);
        System.out.println(resolvableType7.isAssignableFrom(resolvableType9)        // List<Integer> <- List<Object>  : false
                + DELIMETER3 + resolvableType9.isAssignableFrom(resolvableType7)    // List<Object> <- List<Integer>  : true
        );
        System.out.println(ResolvableType.forClass(Integer.class).isAssignableFrom(ResolvableType.forClass(Object.class))       // Integer <- Object : false
                + DELIMETER3 + ResolvableType.forClass(Object.class).isAssignableFrom(ResolvableType.forClass(Integer.class))   // Object <- Integer : true
        );
    }
}
