package org.nasdanika.html.tests;

import org.junit.Test;
import org.nasdanika.html.Tag;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.fontawesome.Icon;
import org.nasdanika.html.fontawesome.Icon.Rotate;
import org.nasdanika.html.fontawesome.Icon.Size;
import org.nasdanika.html.fontawesome.Icon.Style;


public class TestFontAwesome extends HTMLTestBase {
	
	@Test
	public void testListGroup() throws Exception {		
		Icon<Tag> icon = FontAwesomeFactory.INSTANCE.icon("university", Style.SOLID)
				.size(Size.x5)
				.rotate(Rotate.R180);					
		writeThemedPage("fontawesome/icons.html", "Icons", icon); 
	}	
		
}
