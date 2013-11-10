package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;

public class SubmittedPurchase {
	private Long id;
	private Date date;
	
	public SubmittedPurchase(Long id, Date date){
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
