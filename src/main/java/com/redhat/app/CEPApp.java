package com.redhat.app;

import java.util.Date;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import com.redhat.app.model.Account;
import com.redhat.app.model.Customer;
import com.redhat.app.model.OrdinaryAccount;
import com.redhat.app.model.events.Transaction;

/**
 * Hello world!
 *
 */
public class CEPApp 
{
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieBaseConfiguration config = ks.newKieBaseConfiguration();
            config.setOption(EventProcessingOption.STREAM);
            
            KieSession kSession = kContainer.newKieSession("cep-rules");
            EntryPoint atmStream = kSession.getEntryPoint("ATM Stream");
            System.out.println(atmStream);



            System.out.println(kSession);
            // testcase1
            Customer<Account> c1= new Customer<Account>("John");
            OrdinaryAccount account = new OrdinaryAccount(200,1.5);
            c1.setAccount(account);
            Transaction tx= new Transaction();
            tx.setCustomer(c1);
            tx.setAmount(c1.getAccount().getBalance());
            tx.setTimestamp(new Date(System.currentTimeMillis()));
            //atmStream.insert(c1);
            //atmStream.insert(tx);
            //kSession.insert(tx);
            //kSession.fireAllRules();
            new Thread( new Runnable() {
            	  @Override
            	  public void run() {
            	      kSession.fireUntilHalt();
            	  }
            	} ).start();  
            atmStream.insert(tx);
            Thread.sleep(2000);
            //account.setYearlyInterest(-1.0);
            //tx.getCustomer().setAccount(account);
            Transaction tx1= new Transaction();
            tx1.setTimestamp(new Date(System.currentTimeMillis()));
            OrdinaryAccount account1 = new OrdinaryAccount(200,-1.5);
            c1.setAccount(account1);
            tx1.setCustomer(c1);
            tx1.setAmount(2);
            atmStream.insert(tx1);
            Thread.sleep(2000);
            kSession.halt();
            kSession.dispose();
            
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
