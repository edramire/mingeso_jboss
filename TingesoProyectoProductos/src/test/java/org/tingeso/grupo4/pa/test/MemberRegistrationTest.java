package org.tingeso.grupo4.pa.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tingeso.grupo4.pa.model.Member;
import org.tingeso.grupo4.pa.service.MemberRegistration;
import org.tingeso.grupo4.pa.util.Resources;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Member.class, MemberRegistration.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    MemberRegistration memberRegistration;

    @Inject
    Logger log;
    
    @Test
    public void testRegistration() throws Exception {
        Member member = new Member();
        member.setEmail("hola@gmail.com");
        member.setName("Pepe");
        member.setPhoneNumber("1234567890");
        memberRegistration.register(member);
        assertNotNull(member.getId());
        log.info(member.getName() + " guardado con id " + member.getId());
    }
}
