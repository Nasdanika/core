package org.nasdanika.exec.gen.content;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;

public class Base64SupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {
	
	protected Base64SupplierFactoryAdapter(org.nasdanika.exec.content.Base64 base64) {
		setTarget(base64);
	}
	
	@Override
	public org.nasdanika.exec.content.Base64 getTarget() {
		return (org.nasdanika.exec.content.Base64) super.getTarget();
	}
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		EObject source = getTarget().getSource();
		SupplierFactory<InputStream> ssf = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(source, InputStream.class), "Cannot adapt to SupplierFactory: " + source);
		Function<InputStream, InputStream> encoder = new Function<InputStream, InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return Base64SupplierFactoryAdapter.this.getClass().getName();
			}

			@Override
			public InputStream execute(InputStream input, ProgressMonitor progressMonitor) throws Exception {
				if (input == null) {
					return null;
				}
				byte[] data = DefaultConverter.INSTANCE.toByteArray(input);
				byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(data);
				return new ByteArrayInputStream(encoded);
			}
			
		};
		return ssf.create(context).then(encoder);
	}

}

// 