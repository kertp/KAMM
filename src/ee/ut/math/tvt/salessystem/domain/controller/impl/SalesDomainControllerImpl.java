package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.kamm.SubmittedPurchase;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	private List<SubmittedPurchase> purchaseList;
	private List<StockItem> stockItems;

	public SalesDomainControllerImpl() {
		stockItems = new ArrayList<StockItem>();
		purchaseList = new ArrayList<SubmittedPurchase>();

		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0,
				5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0,
				8);
		StockItem frankfurters = new StockItem(3l, "Frankfurters",
				"Beer sauseges", 15.0, 12);
		StockItem beer = new StockItem(4l, "Free Beer", "Student's delight",
				0.0, 100);

		stockItems.add(chips);
		stockItems.add(chupaChups);
		stockItems.add(frankfurters);
		stockItems.add(beer);
	}

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		float sum = 0;
		for (SoldItem item : goods) {
			sum += item.getSum();
			for (StockItem stockitem : stockItems) {
				if (stockitem.getId() == item.getId()) {
					stockitem.setQuantity(stockitem.getQuantity()
							- item.getQuantity());
				}
			}
		}
		purchaseList.add(new SubmittedPurchase(new Date(), sum, goods));
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
}
