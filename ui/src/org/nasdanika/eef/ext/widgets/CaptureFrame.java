package org.nasdanika.eef.ext.widgets;
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * License: LGPL.
 * @author Pavel Vlasov.
 *
 */
@SuppressWarnings("serial")
public class CaptureFrame extends javax.swing.JFrame {
	
	private JPanel capturePanel;	
	private Rectangle bounds;
	private Consumer<byte[]> imageConsumer;
	
	public CaptureFrame(Consumer<byte[]> imageConsumer) throws Exception {		
		super("Screen capture");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("camera.png")));
		
		this.imageConsumer = imageConsumer;

    	setUndecorated(true);
    	
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
        	this.setOpacity(0.8f);	
        }		
    	
		setAlwaysOnTop(true);
		
		//--- GUI construction ---
		
		capturePanel = new JPanel();

		final JLabel dimensionsLabel = new JLabel("");
		capturePanel.add(dimensionsLabel, BorderLayout.CENTER);		
		
		capturePanel.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {				
				super.componentResized(e);
				dimensionsLabel.setText(e.getComponent().getWidth()+" x "+e.getComponent().getHeight());
			}
		});
		
		JButton captureButton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bounds = capturePanel.getBounds();
				Point loc = bounds.getLocation();
				SwingUtilities.convertPointToScreen(loc, capturePanel);
				bounds.setLocation(loc);
				capturing.set(true);
				setVisible(false);
			}
			
		});
		captureButton.setText("Capture");
		captureButton.setToolTipText("Create a snapshot of the screen");
		capturePanel.add(captureButton, BorderLayout.CENTER);		
		
		JButton cancelButton = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				capturing.set(false);
				CaptureFrame.this.setVisible(false);
			}
			
		});
		cancelButton.setText("Cancel");
		capturePanel.add(cancelButton, BorderLayout.CENTER);
		
		getContentPane().add(capturePanel, BorderLayout.CENTER);
				
		capturePanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		Insets dragInsets = new Insets(5, 5, 5, 5);
    	new ComponentResizer(dragInsets, this);
    	
    	ComponentMover cm = new ComponentMover();
    	cm.registerComponent(this);
    	cm.setDragInsets(dragInsets);
    	
    	addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentHidden(ComponentEvent e) {
				if (capturing.get()) {
					capturing.set(false);
					try {
						capture();
					} catch (Exception ex) {
						ex.printStackTrace();
					}							
				} 
			}
		});
		
	}

	protected void capture() throws Exception {
		try {
			Thread.sleep(200); // For Ubuntu.
		} catch (InterruptedException ie) {
			// Ignore
		}
		
		for (GraphicsDevice screen : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
		    Rectangle deviceBounds = screen.getDefaultConfiguration().getBounds();
		    if (deviceBounds.contains(bounds)) {
		    	
		        DisplayMode displayMode = screen.getDisplayMode();
		    			    	
		        int displayWidth = displayMode.getWidth();
		        int displayHeight = displayMode.getHeight();

		        // Scaling if needed
		        Rectangle effectiveBounds = bounds;
		        if (displayHeight != deviceBounds.height || displayWidth != deviceBounds.width) {
		        	AffineTransform tx = new AffineTransform();
		        	tx.scale(((double) displayWidth) / (double) deviceBounds.getWidth(), ((double) displayHeight) / (double) deviceBounds.getHeight());
		        	effectiveBounds = tx.createTransformedShape(bounds).getBounds();
		        }
		    	
				BufferedImage screenShot = new Robot(screen).createScreenCapture(effectiveBounds);
				
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(screenShot, "PNG", baos);
					baos.close();
					imageConsumer.accept(baos.toByteArray());
				} catch (IOException ex) {
			    	JOptionPane.showMessageDialog(
			    			this,
							ex.toString(), 
							"Error saving image",
							JOptionPane.ERROR_MESSAGE);									
				}
				break;
		    }
		}			
	}

	private AtomicBoolean capturing = new AtomicBoolean(false);

}
