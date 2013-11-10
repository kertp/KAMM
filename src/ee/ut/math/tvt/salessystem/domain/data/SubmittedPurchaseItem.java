package ee.ut.math.tvt.salessystem.domain.data;

public class SubmittedPurchaseItem {

	private Long id;
	private Long item_id;
	private Long purchase_id;
	
	public SubmittedPurchaseItem(Long id, Long item_id, Long purchase_id){
		this.id = id;
		this.item_id = item_id;
		this.purchase_id = purchase_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Long getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(Long purchase_id) {
		this.purchase_id = purchase_id;
	}

	public Long getId() {
		return id;
	}
	
	
}
