package com.ggggght.learningjava8.springboot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:42
 * @author: ggggght
 */
public class ServerImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		final Map<String, Object> annotationAttributes =
				importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
		final Server.Type type = (Server.Type) annotationAttributes.get("type");

		String[] importClassNames = new String[0];
		if (type == Server.Type.HTTP) {
			importClassNames = new String[]{HttpServer.class.getName()};
		} else if (type == Server.Type.FTP) {
			importClassNames = new String[]{FtpServer.class.getName()};
		}

		return importClassNames;
	}
}
