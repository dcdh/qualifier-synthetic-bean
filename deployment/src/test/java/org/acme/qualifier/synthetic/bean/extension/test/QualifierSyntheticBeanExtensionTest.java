package org.acme.qualifier.synthetic.bean.extension.test;

import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;
import org.acme.qualifier.synthetic.bean.extension.runtime.HelloWorldService;
import org.acme.qualifier.synthetic.bean.extension.runtime.Person;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class QualifierSyntheticBeanExtensionTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class));

    @Inject
    @Person(name = "Martin")
    HelloWorldService helloWorldServiceMartin;

    @Inject
    @Person(name = "Guillaume")
    HelloWorldService helloWorldServiceGuillaume;

    @Test
    public void shouldSayHelloMartin() {
        Assertions.assertEquals("Hello Martin", helloWorldServiceMartin.sayHello());
    }

    @Test
    public void shouldSayHelloGuillaume() {
        Assertions.assertEquals("Hello Guillaume", helloWorldServiceGuillaume.sayHello());
    }

}
