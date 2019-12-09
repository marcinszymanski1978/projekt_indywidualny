package hibernate;

import monitoredElements.Glucose;
import monitoredElements.Ingredients;
import monitoredElements.Meals;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDao implements HibernateEntity {

    public void saveHibernateEntity(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public void updateHibernateEntity(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteHibernateEntity(HibernateEntity hibernateEntity) {


        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Glucose> getGlucoseLevels() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Glucose", Glucose.class).list();
        }
    }

    public List<Meals> getMeals() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Meals", Meals.class).list();
        }
    }


    public List<Ingredients> getIngredients() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Ingredients", Ingredients.class).list();

        }
    }




}

