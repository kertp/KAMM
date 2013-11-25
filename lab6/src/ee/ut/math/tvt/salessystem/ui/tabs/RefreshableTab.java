package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

public interface RefreshableTab {

	public void refresh(SalesDomainController domainController);
}
