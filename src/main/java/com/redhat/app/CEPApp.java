package com.redhat.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
import com.redhat.app.model.events.Transaction.TX_LOCATION;

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
            KieBaseConfiguration config = ks.newKieBaseConfiguration();
            config.setOption(EventProcessingOption.STREAM);
            
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("cep-rules");
            EntryPoint atmStream = kSession.getEntryPoint("ATM Stream");
            System.out.println(atmStream);



            System.out.println(kSession);

            Thread kThread= new Thread( new Runnable() {
                @Override
                public void run() {
                    kSession.fireUntilHalt();
                }
                
              } );
              kThread.start();
             ArrayList<Transaction> txList = initData();
             txList = insertFraud(txList);
              streamData(atmStream, txList);
            // testcase1
  

            kSession.halt();
            kSession.dispose();
            //kThread.interrupt();
            
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    } // main

    private static int NUM_OF_RECORDS=50;
    private static int[] fraudlist = {4,5,9,12,34};

    private static ArrayList<Transaction> initData() {
        Random random = new Random();
        Customer<Account> cu = null;
        Account acct = null;
        Transaction tx = null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>(NUM_OF_RECORDS);
        for (int i=0; i < NUM_OF_RECORDS;i++) {
            tx = new Transaction();
            tx.setId("tx_"+i);
            cu = new Customer<Account>("user_"+i);
            acct = new OrdinaryAccount(2000, 1.0, "acct_"+i);
            cu.setAccount((Account)acct);
            tx.setCustomer(cu);
            tx.setTimestamp(new Date(System.currentTimeMillis()));
            tx.setLocation(Transaction.TX_LOCATION.values()[random.nextInt(4)]);
            tx.setAmount(random.nextInt(1000));
            transactions.add(tx);
            System.out.println(tx.toString());
        }
        return transactions;
    }
    public static void streamData(EntryPoint ep, ArrayList<Transaction> txList){

        for (Transaction transaction : txList) {
            System.out.println("inserting event "+transaction);
            ep.insert(transaction);
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

    public static ArrayList<Transaction> insertFraud(ArrayList<Transaction> txList) {
        Transaction[] tempList = new Transaction[fraudlist.length];
        Transaction ftx = null;
        int cnt = 0;
        Random random = new Random();
        for (int i : fraudlist) {
            System.out.println("Fraud record "+i);
            Transaction tx = txList.get(i);
            ftx = new Transaction();
            ftx.setId("ftx_"+i);
            ftx.setCustomer(tx.getCustomer());
            while (ftx.getLocation() == null || tx.getLocation() == ftx.getLocation()) {
                ftx.setLocation(Transaction.TX_LOCATION.values()[random.nextInt(4)]);
            }   

            ftx.setAmount(1000);
            long ftime = tx.getTimestamp().getTime()+10000;
            ftx.setTimestamp(new Date(ftime));
            tempList[cnt++] = ftx;
            //System.out.println("original event:"+tx);
            //System.out.println("fraud event:"+ftx);
        }

        for (Transaction transaction : tempList) {
            txList.add(random.nextInt(txList.size()),transaction);
        }
        System.out.println("-------------------new list -------------");

        for (Transaction t : txList) {
            System.out.println(t);
        }
        System.out.println("-------------------------------------");

        return txList;
    }
}//class
