package com.apixandru.utils.annotationprocessing;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Locale.ENGLISH;
import static javax.tools.Diagnostic.Kind.ERROR;
import static org.junit.Assert.assertEquals;

/**
 * @author Alexandru-Constantin Bledea
 * @since February 21, 2016
 */
public class CompilerContext {

    private final Map<String, Compilation> compilations = new HashMap<>();

    public SetupCompilation whenCompiling(final String sourceFile) {
        return new SetupCompilation(this, sourceFile);
    }

    void addCompilation(final Compilation compilation) {
        this.compilations.put(compilation.sourceFile, compilation);
    }

    public List<String> getSourceFiles() {
        return new ArrayList<>(compilations.keySet());
    }

    public void verify(final boolean successfulCompilation, final List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
            final Compilation compilation = compilations.get(diagnostic.getSource().getName());
            if (ERROR != diagnostic.getKind()) {
                throw new IllegalStateException("Only implemented errors so far.");
            }
            assertEquals(compilation.errorMessage, diagnostic.getMessage(ENGLISH));
        }
    }

}
