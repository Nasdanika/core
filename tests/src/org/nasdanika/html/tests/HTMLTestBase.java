package org.nasdanika.html.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Assert;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Select;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

public class HTMLTestBase {
	
	/**
	 * Writes content to a bootstrap/fontawesome/jstree/knockout page and to a file under repository site.
	 * @param path
	 * @param title
	 * @param content
	 * @throws Exception
	 */
	protected void writePage(String path, String title, Object... content) throws IOException {		
		HTMLPage bootstrapPage = BootstrapFactory.INSTANCE.bootstrapCdnHTMLPage();
		FontAwesomeFactory.INSTANCE.cdn(bootstrapPage);
		JsTreeFactory.INSTANCE.cdn(bootstrapPage);
		KnockoutFactory.INSTANCE.cdn(bootstrapPage);
		// More declarations as needed.		
		bootstrapPage.title(title);
		bootstrapPage.body(content);
		writeFile(path, bootstrapPage.toString());
	}
	
	/**
	 * Writes content to a bootstrap/fontawesome/jstree/knockout page and to a file under repository site.
	 * A theme select is added above the content for live switching between available themes.
	 * @param path
	 * @param title
	 * @param content
	 * @throws IOException
	 */
	protected void writeThemedPage(String path, String title, Object... content) throws IOException {		
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		Container container = factory.container();
		Select select = factory.themeSelect(Theme.Default);
		InputGroup selectInputGroup = factory.inputGroup();
		selectInputGroup.prepend("Select Bootstrap theme");
//		selectInputGroup.append(FontAwesomeFactory.INSTANCE.icon("desktop", Style.SOLID));
		selectInputGroup.input(select);		
		container.row().margin().bottom(1).toBootstrapElement().col(selectInputGroup);
		container.row().col(content);
		writePage(path, title, container);
	}
	
	/**
	 * Writes text file.
	 * @param path
	 * @param content
	 */
	protected void writeFile(String path, String content) throws IOException {
		File target = new File(("target/test-dumps/"+path).replace("/", File.separator));
		File parent = target.getParentFile();
		if (!parent.exists()) {
			if (!parent.mkdirs()) {
				Assert.fail("Cannot create "+parent.getAbsolutePath());
			}
		}
		
		System.out.println("Writing to "+target.getAbsolutePath());
		try (Writer writer = new FileWriter(target)) {
			writer.write(content);
		}		
		
	}

}
