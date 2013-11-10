package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

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
		stockItems = service.getStockItems();
		//stockItems.addAll(service.getStockItems());
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
		for (StockItem s: stockItems)
			service.update(s);
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



    /*public List<StockItem> getProductList() {
        log.info("Getting products!");
        stockItems = getSimpleJdbcTemplate().query(
                "select id, description, price from products", 
                new ProductMapper());
        return stockItems;
    }

    public void saveProduct(StockItem prod) {
        log.info("Saving product: " + prod.getDescription());
        int count = getSimpleJdbcTemplate().update(
            "update products set description = :description, price = :price where id = :id",
            new MapSqlParameterSource().addValue("description", prod.getDescription())
                .addValue("price", prod.getPrice())
                .addValue("id", prod.getId()));
        log.info("Rows affected: " + count);
    }
    
    private static class ProductMapper implements ParameterizedRowMapper<StockItem>{

        public StockItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            StockItem prod = new StockItem();
            prod.setId(rs.getLong("id"));
            prod.setDescription(rs.getString("description"));
            prod.setPrice(new Double(rs.getDouble("price")));
            return prod;
        }

    }*/
}
