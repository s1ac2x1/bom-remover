package com.kishlaly.utils.config;

import java.io.File;

import com.kishlaly.utils.core.Parameters;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public interface Remover {

	int checkBOM(File file);
	int checkBOM(String str);
	int checkBOM(StringBuilder builder);

	void work();

}
