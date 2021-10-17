Resource components implement ${javadoc/org.nasdanika.common.ConsumerFactory}<${javadoc/org.nasdanika.common.resources.BinaryEntityContainer}>.
Their ``execute()`` methods takes an instance of ``BinaryEntityContainer`` and may manipulate zero or more resources in the container.

To manipulate resources in-memory use ${javadoc/org.nasdanika.common.resources.EphemeralBinaryEntityContainer}, 
to work with file system files and directories use ${javadoc/org.nasdanika.common.resources.FileSystemContainer}.
In Eclipse environment you can use ${javadoc/org.nasdanika.eclipse.resources.EclipseContainer}.
If none of those implementations fits your needs you can create your own implementation of ``BinaryEntityContainer``. 