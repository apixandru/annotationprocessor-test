package com.apixandru.utils.annotationprocessing;

/**
 * @author Alexandru-Constantin Bledea
 * @since February 21, 2016
 */
class Compilation {

    final String sourceFile;
    final String errorMessage;

    Compilation(final SetupCompilation setup) {
        this.sourceFile = setup.sourceFile;
        this.errorMessage = null != setup.errorMessage ? setup.errorMessage : null;
    }

}
