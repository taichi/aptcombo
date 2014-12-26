package examples;

import javax.inject.Inject;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;

@AnnotateWith(annotations = @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Inject.class))
public @interface InjectConfig {
}
