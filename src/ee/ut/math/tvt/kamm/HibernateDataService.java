package ee.ut.math.tvt.kamm;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;



@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStudents() {
		List<StockItem> result = session.createQuery("from Student").list();
		return result;
	}

	public List<SoldItem> getCourses() {
		List<SoldItem> result = session.createQuery("from Course").list();
		return result;
	}


}