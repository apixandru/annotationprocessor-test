package com.apixandru.utils.annotationprocessing;

import org.junit.Before;
import org.junit.Test;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.US;

/**
 * @author Alexandru-Constantin Bledea
 * @since February 21, 2016
 */
public abstract class AbstractProcessorTest {

    private static final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    private final CompilationBatch compilationBatch;
    private final CompilerContext compilerContext;
    private final DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
    private final StandardJavaFileManager fileManager = compiler.getStandardFileManager(collector, US, UTF_8);

    public AbstractProcessorTest(final CompilationBatch compilationBatch) {
        this.compilationBatch = compilationBatch;
        this.compilerContext = new CompilerContext();
    }

    @Before
    public void setup() throws Exception {
        compilationBatch.setup(compilerContext);
    }

    @Test
    public void testCompilation() throws Exception {
        compilerContext.verify(compile(), collector.getDiagnostics());
    }

    private boolean compile() {
        return compiler.getTask(null, fileManager, collector, null, null, getJavaFileObjects()).call();
    }

    private Iterable<? extends JavaFileObject> getJavaFileObjects() {
        return fileManager.getJavaFileObjectsFromStrings(compilerContext.getSourceFiles());
    }

}
