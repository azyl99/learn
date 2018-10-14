package AnnotationDemo;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author guya on 2018/10/9
 */
@SupportedAnnotationTypes({"AnnotationDemo.Description"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class DescriptionImpl extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("hhhdf");
        return false;
    }
}
