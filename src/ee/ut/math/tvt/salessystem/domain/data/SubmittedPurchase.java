package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

@Entity
@Table(name = "SUBMITTEDPURCHASE")
public class SubmittedPurchase implements Cloneable, DisplayableItem {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "DATE")
	private String date;
	@Column(name = "TIME")
	private String time;
	@Transient
	private float total;
	@OneToMany(mappedBy = "purchase")
	private List<SoldItem> soldItems;


	public SubmittedPurchase(Date date, float total, List<SoldItem> soldItems) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
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
