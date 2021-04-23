package com.ggggght.learningjava8.springboot;

import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/19 20:30
 * @author: ggggght
 */
@TransactionService("test")
public class AnnotationReflection {
	public static void main(String[] args) {
		AnnotatedElement annotatedElement = AnnotationReflection.class;
		final TransactionService annotation = annotatedElement.getAnnotation(TransactionService.class);
		final String value = annotation.value();

		// ReflectionUtils.doWithMethods(TransactionService.class,
		// 		method -> System.out.printf("@TransactionService.%s() = %s\n", method.getName(),
		// 				ReflectionUtils.invokeMethod(method, annotation)),
		// 		method -> !method.getDeclaringClass().equals(Annotation.class));

		// final Set<Annotation> metaAnnotations = getAllMetaAnnotations(annotation);
		// metaAnnotations.forEach(AnnotationReflection::printAnnotationAttribute);
		final AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);
		final AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);

		print(serviceAttributes);
		print(transactionalAttributes);
	}

	private static void print(AnnotationAttributes annotationAttributes) {
		System.out.printf("注解 @%s 属性集合: \n",annotationAttributes.annotationType().getName());
		annotationAttributes.forEach((name,value) -> System.out.printf("\t属性 %s: %s \n",name,value));
	}

	private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
		final Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();

		if (metaAnnotations.length == 0) {
			return Collections.emptySet();
		}

		final Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations).filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage())).collect(Collectors.toSet());

		final HashSet<Annotation> metaMetaAnnotationSet = metaAnnotationsSet.stream().map(AnnotationReflection::getAllMetaAnnotations).collect(HashSet::new, Set::addAll, Set::addAll);
		metaAnnotationsSet.addAll(metaMetaAnnotationSet);

		return metaAnnotationsSet;
	}

	private static void printAnnotationAttribute(Annotation annotation) {
		final Class<? extends Annotation> aClass = annotation.annotationType();

		ReflectionUtils.doWithMethods(aClass,
				method -> System.out.printf("@%s.%s() = %s\n",
						aClass.getSimpleName(),
							method.getName(),ReflectionUtils.invokeMethod(method,annotation)),
				method -> !method.getDeclaringClass().equals(Annotation.class));
	}

	public String removeDuplicates(String S) {
		if (S.length() == 0) {
			return "";
		}

		if (S.length() == 1) {
			return S;
		}

		Stack<Character> stack = new Stack<>();
		stack.push(S.charAt(0));
		final char[] chars = S.toCharArray();
		for (int i = 1; i < chars.length; i++) {
			if (!stack.isEmpty() && stack.peek().equals(chars[i])) {
				stack.pop();
				continue;
			}

			stack.push(chars[i]);
		}

		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty()) {
			builder.append(stack.pop());
		}
		return builder.reverse().toString();
	}

}
