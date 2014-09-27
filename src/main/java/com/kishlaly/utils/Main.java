package com.kishlaly.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.maven.internal.commons.io.ByteOrderMark;
import org.apache.maven.internal.commons.io.input.BOMInputStream;
import sun.misc.IOUtils;

import java.io.*;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vladimir on 27.09.2014.
 */
public class Main {

    private static int PROCESSED;
    private static int UPDATED;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: folder mask\nExample: c:\\test *.properties");
            System.exit(1);
        }
        String src = args[0];
        String mask = args[1];
        File directory = new File(src);
        Collection filesCollection = FileUtils.listFiles(directory, new WildcardFileFilter(mask), DirectoryFileFilter.DIRECTORY);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Object obj : filesCollection) {
            PROCESSED++;
            final File file = (File) obj;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    work(file);
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(3, TimeUnit.HOURS);
            System.out.printf("\n=========================\n\nFinished.\n\nUpdated %d of %d files\n\n=========================\n\n", UPDATED, PROCESSED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void work(File file) {
        int bomLength = checkBOM(file);
        if (bomLength > 0) {
            long truncatedSize = file.length() - bomLength;
            byte[] cache = new byte[(int)(truncatedSize)];
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                inputStream.skip(bomLength);
                int totalBytes = 0;
                while (totalBytes < truncatedSize) {
                    int bytesRemaining = (int)truncatedSize - totalBytes;
                    int bytesRead = inputStream.read(cache, totalBytes, bytesRemaining);
                    if(bytesRead > 0){
                        totalBytes = totalBytes + bytesRead;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                org.apache.commons.io.IOUtils.write(cache, outputStream);
                System.out.println(file.getName());
                UPDATED++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int checkBOM(File file) {
        int result = 0;
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                BOMInputStream bomIn = new BOMInputStream(fileInputStream,
                        ByteOrderMark.UTF_8,
                        ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE,
                        ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_16BE);
        ) {
             if (bomIn.hasBOM()) {
                result = bomIn.getBOM().length();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

