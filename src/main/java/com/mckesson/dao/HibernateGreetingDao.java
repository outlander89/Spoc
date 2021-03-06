package com.mckesson.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.mckesson.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;

@Repository("hibernateGreetingDao")
public class HibernateGreetingDao implements GreetingDao {
	
	protected static Logger logger = Logger.getLogger("HibernateGreetingDao");
	
	public HibernateGreetingDao() {}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Greeting> getAllGreetings() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select g from Greeting g order by id desc");
		List<Greeting> greetingList = q.list();
		return greetingList;
	}
	
	public void addGreeting(Greeting greeting) {
		Session session = sessionFactory.getCurrentSession();
		session.save(greeting);
	}
}
