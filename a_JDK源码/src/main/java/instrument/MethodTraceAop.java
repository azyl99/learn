package instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 参考：http://www.360doc.com/content/13/0607/21/1332348_291361050.shtml
 * 问题：如何打包？
 *
 * @author guya on 2020/1/5
 */
public class MethodTraceAop implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className != null && className.contains("/")) {
            className = className.replaceAll("/", ".");
        }

        try {
            CtClass clazz = ClassPool.getDefault().get(className);
            CtMethod[] ctMethods = clazz.getDeclaredMethods();
            for (CtMethod m : ctMethods) {
                m.insertBefore("{ System.out.println(\\\">> \\\" + Thread.currentThread().getStackTrace()[1].getMethodName()); }");
                m.insertAfter("{ System.out.println(\\\"<< \\\" + Thread.currentThread().getStackTrace()[1].getMethodName()); }");
            }
        } catch (Exception e) {
        }

        return new byte[0];
    }

    public static void premain(String[] args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new MethodTraceAop());
    }
}
