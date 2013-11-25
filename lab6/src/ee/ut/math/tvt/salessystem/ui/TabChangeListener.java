package ee.ut.math.tvt.salessystem.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class TabChangeListener implements ChangeListener {
	
	private SalesSystemUI ui;
	
	public TabChangeListener(SalesSystemUI ui){
		this.ui = ui;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		ui.getActiveTabAndRefresh();
	}

}
