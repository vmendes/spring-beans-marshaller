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


Does not contain any dependency to Spring Framework, so that you can generate namespace correctly by:
		SpringBeansMarshaller.setSpringVersion("3.1");
