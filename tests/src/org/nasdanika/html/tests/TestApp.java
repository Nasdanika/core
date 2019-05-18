package org.nasdanika.html.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.HTMLTableApplication;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

public class TestApp extends HTMLTestBase {
	
	@Test
	public void testHTMLApp() throws Exception {
		Application app = new HTMLTableApplication();
		app.header("Header").navigationBar("Navigation bar").navigationPanel("Navigation panel").contentPanel("Content").footer("Footer");
		writeFile("app/html/index.html", app.toString());
	}
	
	@Test
	public void testBootstrapApp() throws Exception {
		Application app = new BootstrapContainerApplication(Theme.Litera, false);
		
		Tag treeContainer = app.getHTMLPage().getFactory().div();
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		app
			.header("Header")
			.navigationBar("Navigation bar")
			.navigationPanel(treeContainer)
			.contentPanel(/* htmlFactory.overlay("Content overlay"), */ "Content")
			.footer("Footer");
		
		JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
		jsTreeFactory.cdn(app.getHTMLPage());
		
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("node.id == '#' ? 'jstree.json' : 'jstree-' + node.id + '.json'", "'context-menu-' + node.id + '.json'")));		
		
		writeFile("app/bootstrap/index.html", app.toString());
		
		// JsTree
				
		JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
		rootNode.icon("far fa-user");
		rootNode.text("User");
		rootNode.id(htmlFactory.nextId());
		rootNode.hasChildren();
		JSONArray jsTreeRootNodes = new JSONArray();
		jsTreeRootNodes.put(rootNode.toJSON());
		writeFile("app/bootstrap/jstree.json", jsTreeRootNodes.toString());		
		
		JSONArray jsTreeChildNodes = new JSONArray();
		
		JsTreeNode childNode = jsTreeFactory.jsTreeNode();
		childNode.icon("far fa-user");
		childNode.text("Child");
		childNode.id(jsTreeFactory.getHTMLFactory().nextId());
		jsTreeChildNodes.put(childNode.toJSON());
		writeFile("app/bootstrap/jstree-"+rootNode.getId()+".json", jsTreeChildNodes.toString());
		
		// JsTree context menu - the same menu for both nodes.
		JsTreeContextMenuItem item = jsTreeFactory.jsTreeContextMenuItem();
		item.label("Do it!");
		item.icon("far fa-user");
		item.action("window.location.href='http://www.nasdanika.org'; console.log('hey');");
		
		JSONObject menu = new JSONObject();
		menu.put("do-it", item.toJSON());
		writeFile("app/bootstrap/context-menu-"+rootNode.getId()+".json", menu.toString());
		writeFile("app/bootstrap/context-menu-"+childNode.getId()+".json", menu.toString());
	}
	
}
