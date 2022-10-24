package com.ggggght.learningjava8.compiler;

import com.sun.tools.javac.util.Context;
import java.util.Set;
import com.sun.tools.javac.api.JavacTrees;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;

import static javax.lang.model.SourceVersion.RELEASE_17;

/**
 * annotation process
 */
@SupportedAnnotationTypes({""})
@SupportedSourceVersion(RELEASE_17)
public class DataAnnoProcessor extends AbstractProcessor {
    private JavacTrees javacTrees;
    private TreeMaker treeMaker;
    private Names names;
    //
    /**
     * Initializes the processor with the processing environment by
     * setting the {@code processingEnv} field to the value of the
     * {@code processingEnv} argument.  An {@code
     * IllegalStateException} will be thrown if this method is called
     * more than once on the same object.
     *
     * @param processingEnv environment to access facilities the tool framework
     *                      provides to the processor
     * @throws IllegalStateException if this method is called more than once.
     */
    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        javacTrees = JavacTrees.instance(context);
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
