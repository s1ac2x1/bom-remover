package com.kishlaly.utils.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kishlaly.utils.config.AbstractRemover;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.maven.internal.commons.io.ByteOrderMark;
import org.apache.maven.internal.commons.io.input.BOMInputStream;

/**
 * @author Vladimir Kishlaly
 *         Date: 29.09.2014
 */
public class PlainRemover extends AbstractRemover {

	public PlainRemover(Parameters parameters) {
		super(parameters);
	}

	@Override
	public int checkBOM(File file) {
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

	@Override
	public void work() {
		String src = parameters.getSrc();
		String mask = parameters.getMask();
		File directory = new File(src);
		IOFileFilter subfolders = parameters.isDeep() ? DirectoryFileFilter.DIRECTORY : null;
		Collection filesCollection = FileUtils.listFiles(directory, new WildcardFileFilter(mask), subfolders);
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (Object obj : filesCollection) {
			PROCESSED++;
			final File file = (File) obj;
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					process(file);
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

	private void process(File file) {
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

}
