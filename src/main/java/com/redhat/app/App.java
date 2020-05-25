package com.redhat.app;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.redhat.app.model.Customer;
import com.redhat.app.model.OrdinaryAccount;
import com.redhat.app.model.SpecialAccount;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            System.out.println(kSession);
            // testcase1
            System.out.println("***Test with Ordinary account, accountrules2 should be invoked first, followed by salience****");
            Customer<OrdinaryAccount> c1= new Customer<OrdinaryAccount>("John");
            OrdinaryAccount account = new OrdinaryAccount(200,1.5, "acc1");
            c1.setAccount(account);
            //Account account = new Account(200);
            account.withdraw(50);
            //kSession.insert(account);
            kSession.insert(c1);
            //Simulate calling AccountRules2.drl followed by AccountRules1.drl separate drl files
            kSession.getAgenda().getAgendaGroup("accountrules1").setFocus();
            kSession.getAgenda().getAgendaGroup("accountrules2").setFocus();
            kSession.fireAllRules();
            // testcase2
            System.out.println("****Test with special account, accountrules1 should be invoked first, followed by salience****");
            Customer<SpecialAccount> c2= new Customer<SpecialAccount>("Jack");
            SpecialAccount account1 = new SpecialAccount(200,1.5,"acc2");
            account1.withdraw(50);
            c2.setAccount(account1);
//            kSession = kContainer.newKieSession("ksession-rules");
            kSession.insert(c2);
            kSession.getAgenda().getAgendaGroup("accountrules2").setFocus();
            kSession.getAgenda().getAgendaGroup("accountrules1").setFocus();
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
