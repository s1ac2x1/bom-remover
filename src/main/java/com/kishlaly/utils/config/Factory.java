package com.kishlaly.utils.config;

import com.kishlaly.utils.core.Parameters;
import com.kishlaly.utils.core.PlainRemover;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class Factory {

    public static Remover getInstance(Parameters parameters) {
        return new PlainRemover(parameters);
    }

}