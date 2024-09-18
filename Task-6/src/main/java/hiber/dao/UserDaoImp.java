package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   public User findUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String hql = "select u from User u join u.car c where c.model = :model and c.series = :series";
      TypedQuery<User> query = session.createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      try {
         return query.getSingleResult(); // Используйте getSingleResult() вместо uniqueResult()
      } catch (NoResultException e) {
         return null; // Обработка случая, когда результат не найден
      }
   }

   @Override
   @Transactional
   public void cleanAllTables() {
      Session session = sessionFactory.getCurrentSession();
      Transaction transaction = null;
      try {
         // Если транзакция не активна, начать новую
         if (!session.getTransaction().isActive()) {
            transaction = session.beginTransaction();
         }

         // Выполнение операций удаления
         session.createQuery("DELETE FROM User").executeUpdate();
         session.createQuery("DELETE FROM Car").executeUpdate();

         // Коммит транзакции
         if (transaction != null) {
            transaction.commit();
         }
      } catch (Exception e) {
         if (transaction != null && transaction.isActive()) {
            transaction.rollback();
         }
         throw e;
      }
   }
}
