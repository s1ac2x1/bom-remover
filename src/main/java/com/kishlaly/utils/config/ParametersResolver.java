package com.kishlaly.utils.config;

import com.kishlaly.utils.core.Parameters;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class ParametersResolver {

	private static String folder;
	private static String mask;
	private static String type;
	private static String deep;

	public ParametersResolver(String[] args) {
		if (args.length < 4) {
			System.out.println("Usage: folder mask type deep");
			System.out.println("Example: 'C:\\test *.xml default y' will process all *.xml in all found subfolders of C:\\test using default processing");
			System.out.println("Available processing types: " + Type.values());
			System.exit(1);
		} else {
			folder = args[0];
			mask = args[1];
			type = args[2];
			deep = args[3];
		}
	}

	public Parameters buildParameters() {
		return new Parameters.Builder()
				.folder(folder)
				.mask(mask)
				.type(Type.valueOf(type.toUpperCase()))
				.deep(deep.equals("y") ?  true : false)
				.build();
	}

}
