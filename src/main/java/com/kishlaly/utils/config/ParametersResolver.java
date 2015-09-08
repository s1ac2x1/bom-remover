package com.kishlaly.utils.config;

import com.kishlaly.utils.core.Parameters;
import org.apache.commons.cli.*;

import java.io.File;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class ParametersResolver {

    private static String folder;
    private static String[] masks;
    private static boolean recursively = false;

    public ParametersResolver(String[] args) {
        Options options = new Options();
        options.addOption("f", true, "folder to start with");
        options.addOption("m", true, "file mask");
        options.addOption("r", false, "recursively");
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('f')) {
                String f = cmd.getOptionValue('f');
                File checkFolder = new File(f);
                if (checkFolder.exists()) {
                    folder = f;
                } else {
                    System.out.println("Given folder does not exist: " + f);
                }
                if (cmd.hasOption('m')) {
                    masks = cmd.getOptionValues('m');
                } else {
                    masks = new String[]{"*"};
                }
                if (cmd.hasOption('r')) {
                    recursively = true;
                }
            } else {
                System.out.println("Usage: -f folder [-m mask1] [-m mask2] [-m maskN] [-r]");
                System.exit(1);
            }
        } catch (ParseException e) {
            System.out.println("Error while parsing parameters + " + e.getLocalizedMessage());
        }
    }

    public Parameters buildParameters() {
        return new Parameters.Builder()
                .folder(folder)
                .mask(masks)
                .recursively(recursively)
                .build();
    }

}
