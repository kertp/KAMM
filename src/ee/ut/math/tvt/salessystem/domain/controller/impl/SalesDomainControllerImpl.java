package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;

import ee.ut.math.tvt.kamm.HibernateDataService;
import ee.ut.math.tvt.salessystem.domain.data.SubmittedPurchase;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
    /** Logger for this class and subclasses */
	private static final Logger log = Logger.getLogger(SalesSystemUI.class);
	private List<SubmittedPurchase> purchaseList;
	private List<StockItem> stockItems;
	HibernateDataService service = new HibernateDataService();
	public SalesDomainControllerImpl() {
		stockItems = new ArrayList<StockItem>();
		purchaseList = new ArrayList<SubmittedPurchase>();
		stockItems.addAll(service.getStockItems());
		purchaseList.addAll(service.getSubmittedPurchases());
		for (SubmittedPurchase p : purchaseList) {
			float total = 0;
			for (SoldItem s : p.getSoldItems()) {
				total += s.getSum();
			}
			p.setTotal(total);
		}
	}

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		for (SoldItem item : goods) {
			for (StockItem stockitem : stockItems) {
				if (stockitem.getId() == item.getId()) {
					stockitem.setQuantity(stockitem.getQuantity()
							- item.getQuantity());
				}
			}
		}
		SubmittedPurchase purchase = new SubmittedPurchase(new Date(), goods);
		purchaseList.add(purchase);
		for (StockItem s: stockItems) {
			service.update(s);
		}
		service.addItem(purchase);
		for (SoldItem s : purchase.getSoldItems()) {
			s.setPurchase(purchase);
			service.addItem(s);
		}
		// Let's assume we have checked and found out that the buyer is
		// underaged and
		// cannot buy chupa-chups
		// throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}
	
	public void endSession() {
	    HibernateUtil.closeSession();
	}

	public List<SubmittedPurchase> loadHistory() {
		return purchaseList;
	}

	public List<StockItem> loadWarehouseState() {
		return stockItems;
	}
	
	public void addStockItem(StockItem stockitem) {
		try {
			service.addItem(stockitem);
		} catch (NonUniqueObjectException e) {
			service.update(stockitem);
		}
	}
	
	

}
