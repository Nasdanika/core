package org.nasdanika.eef.ext.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;

public class ScrolledPageBookTest extends Composite {
	
	Object selectedIndex;
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScrolledPageBookTest(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		text = new Text(scrolledComposite, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text.setText("adadakldfalkdfajsfl asjfasl fsadjfldfj asdlfksjd flsfkjsa flkasdjf lskfj sdlkfjas flkdjf asdf");
		scrolledComposite.setContent(text);
		scrolledComposite.setMinSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
