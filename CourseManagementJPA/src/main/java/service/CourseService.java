package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import databaseApplication.jpa.Course;
import serviceBean.EntityManagerFactoryBean;

public class CourseService {
	private EntityManagerFactory factory;

	public CourseService(EntityManagerFactoryBean factoryBean) {
		this.factory = factoryBean.getEntityManagerFactory();
	}

	public List<Course> getCourses() {
		EntityManager em = factory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		TypedQuery<Course> tq = em.createQuery(cq);
		List<Course> courses = tq.getResultList();
		em.close();
		return courses;
	}

	public void addCourse(Course course) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(course);
		txn.commit();
	}
}
