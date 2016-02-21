package com.apixandru.utils.annotationprocessing;

/**
 * @author Alexandru-Constantin Bledea
 * @since February 21, 2016
 */
public class SetupCompilation {

    private final CompilerContext compilerContext;

    final String sourceFile;

    String errorMessage;

    SetupCompilation(final CompilerContext compilerContext, final String sourceFile) {
        this.sourceFile = sourceFile;
        this.compilerContext = compilerContext;
    }

    public void expectNoErrors() {
        compilerContext.addCompilation(new Compilation(this));
    }

    public void expectError(final String errorMessage) {
        this.errorMessage = errorMessage;
        compilerContext.addCompilation(new Compilation(this));
    }

}
