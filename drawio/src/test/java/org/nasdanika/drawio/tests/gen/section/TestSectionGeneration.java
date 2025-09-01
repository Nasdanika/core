package org.nasdanika.drawio.tests.gen.section;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.Section;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.gen.section.DrawioSectionGenerator;

import reactor.core.publisher.Flux;

public class TestSectionGeneration {
	
	@Test
	public void testSectionGenerator() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		DrawioSectionGenerator sectionGenerator = new DrawioSectionGenerator();
		Flux<Section> sectionFlux = sectionGenerator.creatSectionsAsync(document, new PrintStreamProgressMonitor());
		List<Section> sections = sectionFlux.collectList().block();
		System.out.println(sections.size());
		sections.forEach(section -> {
			System.out.println(section.toMarkdown(1));
		});
	}
	
	@Test
	public void testInternetBankingSystemSectionGenerator() throws Exception {
		Document document = Document.load(new File("../../../git-demos/internet-banking-system/internet-banking-system.drawio").getCanonicalFile());
		DrawioSectionGenerator sectionGenerator = new DrawioSectionGenerator();
		Flux<Section> sectionFlux = sectionGenerator.creatSectionsAsync(document, new PrintStreamProgressMonitor());
		List<Section> sections = sectionFlux.collectList().block();
		System.out.println(sections.size());
		StringBuilder sb = new StringBuilder();
		sections.forEach(section -> {
			sb.append(section.toHtml(1));
		});		
		
		Files.writeString(Path.of("target/ibs.html"), sb);
	}

}
