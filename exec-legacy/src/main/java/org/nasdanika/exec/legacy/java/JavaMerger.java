package org.nasdanika.exec.java;

import java.io.InputStream;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.Merger;

/**
 * Leverages EMF Codegen {@link JMerger}. Takes compiler compliance level from {@link JdkLevel} context service.
 * @author Pavel
 *
 */
public class JavaMerger implements Merger {

	public static final JavaMerger INSTANCE = new JavaMerger();
	
	@Override
	public InputStream merge(Context context, BinaryEntity entity, InputStream oldContent, InputStream newContent, ProgressMonitor progressMonitor) throws Exception {
	    JControlModel controlModel = new JControlModel();
		
	    // Obtaining merge rules URI.
		// create model
		GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();

		// create adapter factory
		GeneratorAdapterFactory adapterFactory = GenModelGeneratorAdapterFactory.DESCRIPTOR.createAdapterFactory();
		adapterFactory.setGenerator(new org.eclipse.emf.codegen.ecore.generator.Generator());
		adapterFactory.initialize(genModel);

		// get merge rules URI
		String mergeRulesURI = adapterFactory.getGenerator().getOptions().mergeRulesURI;
	    
	    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(ASTFacadeHelper.class.getCanonicalName());
	    facadeHelper.setCompilerCompliance(context.get(JdkLevel.class).literal);
		controlModel.initialize(facadeHelper, mergeRulesURI);
	    
		JMerger jMerger = new JMerger(controlModel);												
		
		JCompilationUnit scu = jMerger.createCompilationUnitForContents(Util.toString(context, newContent));
		jMerger.setSourceCompilationUnit(scu);
		
		JCompilationUnit tcu = jMerger.createCompilationUnitForContents(Util.toString(context, oldContent));
		jMerger.setTargetCompilationUnit(tcu);
		
		jMerger.merge();
		
		return Util.toStream(context, jMerger.getTargetCompilationUnitContents());
	}

}
