package ee.ut.math.tvt.kamm;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SubmittedPurchase;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;



@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}

	public List<SoldItem> getSoldItems() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}
	
	public List<SubmittedPurchase> getSubmittedPurchases() {
		List<SubmittedPurchase> result = session.createQuery("from SubmittedPurchase").list();
		return result;
	}
	
	public void update(Object stockitem) {
		session.beginTransaction();
		session.update(stockitem);
		session.getTransaction().commit();
	}
	
	public void addItem(Object stockitem) {
		session.beginTransaction();
		session.save(stockitem);
		session.getTransaction().commit();
	}

	

}