// package com.ggggght.learningjava8.compiler;
//
//  import javax.annotation.processing.*;
//  import javax.lang.model.SourceVersion;
//  import javax.lang.model.element.TypeElement;
// import java.util.Set;
//
// @SuppressWarnings("all")
// @SupportedAnnotationTypes("*")
// // @SupportedSourceVersion(SourceVersion.RELEASE_11)
// public class NameCheckProcessor extends AbstractProcessor {
//     private NameChecker nameChecker;
//
//     @Override
//     public synchronized void init(ProcessingEnvironment processingEnv) {
//         super.init(processingEnv);
//         this.nameChecker = new NameChecker(processingEnv);
//     }
//
//     @Override
//     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//         if (!roundEnv.processingOver()) {
//             roundEnv.getRootElements().forEach(e -> nameChecker.checkNames(e));
//         }
//         return false;
//     }
// }
