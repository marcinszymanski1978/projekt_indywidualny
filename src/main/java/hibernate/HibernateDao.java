package hibernate;

import monitoredElements.Glucose;
import monitoredElements.Ingredients;
import monitoredElements.Meals;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public class HibernateDao implements HibernateEntity {
   private String transactionStatus;



    public String  saveHibernateEntity(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(hibernateEntity);
            transaction.commit();
            transactionStatus = getTransactionStatus(transaction);

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                transactionStatus = getTransactionStatus(transaction);
            }
            e.printStackTrace();

        }
        finally {if(session!=null){

            session.close();}
        }

    return transactionStatus;}

    public String updateHibernateEntity(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(hibernateEntity);
            transaction.commit();
            transactionStatus = getTransactionStatus(transaction);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transactionStatus = getTransactionStatus(transaction);
            }
            e.printStackTrace();

        }
        finally {if(session!=null){

            session.close();}
        }
    return transactionStatus; }

    public String deleteHibernateEntity(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(hibernateEntity);
            transaction.commit();
            transactionStatus = getTransactionStatus(transaction);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transactionStatus = getTransactionStatus(transaction);
            }
            e.printStackTrace();
        } finally {
            if (session != null) {

                session.close();

            }

        }
    return transactionStatus;}

    public List<Glucose> getGlucoseLevels() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Glucose", Glucose.class).list();
        }
    }

    public String getTransactionStatus(Transaction transaction) {
        System.out.println(transaction.getStatus());
            return transaction.getStatus().toString();
        }




}

