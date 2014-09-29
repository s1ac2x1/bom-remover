package com.kishlaly.utils;

import com.kishlaly.utils.config.Factory;
import com.kishlaly.utils.config.ParametersResolver;
import com.kishlaly.utils.config.Remover;
import com.kishlaly.utils.core.Parameters;

/**
 * Created by Vladimir on 27.09.2014.
 */
public class Main {

    public static void main(String[] args) {
		Parameters parameters = new ParametersResolver(args).buildParameters();
		Remover remover = Factory.getInstance(parameters);
		remover.work();
    }

}

