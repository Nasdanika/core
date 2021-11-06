package org.nasdanika.mxgraph.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

/**
 * Tests of agile flows.
 * @author Pavel
 *
 */
public class TestMxGraph {
		
	@Test
	public void processDrawio() throws Exception {
		String diagramSource = DefaultConverter.INSTANCE.stringContent(getClass().getResource("aws.drawio"));
		Document xmlDocument = mxXmlUtils.parseXml(diagramSource);
        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        String textContent = diagram.getTextContent();

        if (!textContent.startsWith("<")) {
            //content is compressed, following steps need to take place

            byte[] compressed = Base64.getDecoder().decode(textContent);
            byte[] decompressed = inflate(compressed);
            textContent = urlDecode(new String(decompressed));
        }

        System.out.println(textContent);
        
        Document diagramDoc = mxXmlUtils.parseXml(textContent);
        mxCodec codec = new mxCodec(diagramDoc);
        mxIGraphModel graphModel = (mxIGraphModel) codec.decode(diagramDoc.getDocumentElement());
        
		mxCell root = (mxCell) graphModel.getRoot();
        dump(root, 0);
	}

	private void dump(mxCell cell, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("  ");
		}
		for (int i = 0; i < cell.getChildCount(); ++i) {
        	mxICell child = cell.getChildAt(i);
			System.out.println(child);
			if (child instanceof mxCell) {
				dump((mxCell) child, indent + 1);
			}
        }
	}	
		
    private byte[] inflate(byte[] compressed) throws IOException, DataFormatException {
        int compressedDataLength = 0;
        Inflater compresser = new Inflater(true);
        compresser.setInput(compressed, 0, compressed.length);
        ByteArrayOutputStream o = new ByteArrayOutputStream(compressed.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.inflate(result);
                if (compressedDataLength == 0) {
                    break;
                }
                o.write(result, 0, compressedDataLength);
            }
        } finally {
            o.close();
        }
        compresser.end();
        return o.toByteArray();
    }
	
    private String urlDecode(String input) {
        return URLDecoder.decode(input, Charset.defaultCharset());
    }
	
}
