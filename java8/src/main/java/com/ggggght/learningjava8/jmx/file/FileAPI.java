package com.ggggght.learningjava8.jmx.file;

import java.io.File;
import java.io.FileFilter;
import org.junit.jupiter.api.Test;

public class FileAPI {

	@Test
	public void listHiddenFiles() {
		File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isHidden();
			}
		});

		File[] hiddenFiles1 = new File(".").listFiles(File::isHidden);
	}

}
