package ee.ut.math.tvt.kamm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class SubmittedPurchase implements Cloneable, DisplayableItem {

	private Long id;
	private String date;
	private String time;
	private float total;
	private List<SoldItem> soldItems;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public SubmittedPurchase(Date date, float total, List<SoldItem> soldItems) {
		this.date = dateFormat.format(date);
		this.time = timeFormat.format(date);
		this.total = total;
		this.soldItems = soldItems;
	}

	public List<SoldItem> getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public Long getId() {
		return id;
	}

}
