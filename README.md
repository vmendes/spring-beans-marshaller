spring-beans-marshaller
========================

Generates the bean definition entries from runtime state of Java objects to Spring XML application context. 

### Useful for:
- Generation of test data: together with @Configuration annotation or simply initiated separately, you can now generate your test data for your test cases very easily.
- Generation of initialization data: similar to test data, that can be used to generate the initial runtime state of your application.
- Externalize runtime state of your java objects: the output is simply XML, that is, it can be used anywhere.
- Whatever you want...

### How to use:

1. The simplest way (the shortcut):

		String context = SpringBeansMarshaller.marshalString(new MyObject());

2. The other ways (also available for DOMResult and StreamResult):

		StringResult result = new StringResult();
		ApplicationContextMarshaller<StringResult> marshaller = new StringApplicationContextMarshaller();		
		marshaller.addBean(new MyObject());
		marshaller.addBean(new MyOtherObject());
		marshaller.marshal(result);
		String context = result.getString();
		
### How to install:

1. Add Maven repository:

		<repositories>
			<repository>
				<id>vmendes-snapshots</id>
				<url>https://raw.github.com/vmendes/mvn-repo/snapshots</url>
			</repository>
			<repository>
				<id>vmendes-releases</id>
				<url>https://raw.github.com/vmendes/mvn-repo/releases</url>
			</repository>
		</repositories>

2. Add Maven dependency:

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans-marshaller</artifactId>
			<version>0.0.1</version>
		</dependency>

Note 1: This project does not contain any dependency to Spring Framework, so it's unlikely you will come across major conflicts with your dependencies. Having said that you can generate namespace correctly by:	
		
		SpringBeansMarshaller.setSpringVersion("3.1");
		
Note 2: You may consider using the library temporally in your project. Once you generate all necessary artifacts for your application, you will be able to remove the dependency from your pom.xml. Another way to include the library is to keep the dependency through untracked configuration, like Eclipse Build Path.