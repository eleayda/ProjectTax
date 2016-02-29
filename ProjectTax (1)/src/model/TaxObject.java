package model;

public class TaxObject {
	double incomeCompany, costCompany;
	double incomeWorker;
	double nettoIncCompany;
	double nettoIncWorker;

	// ---Constructors---
	public TaxObject() {

	}

	// ---Getters and setters--<

	public double getIncomeCompany() {
		return incomeCompany;
	}

	public void setIncomeCompany(double incomeCompany) {
		this.incomeCompany = incomeCompany;
	}

	public double getCostCompany() {
		return costCompany;
	}

	public void setCostCompany(double costCompany) {
		this.costCompany = costCompany;
	}

	public double getIncomeWorker() {
		return incomeWorker;
	}

	public void setIncomeWorker(double incomeWorker) {
		this.incomeWorker = incomeWorker;
	}

	public double getNettoIncCompany() {
		return nettoIncCompany;
	}

	public void setNettoIncCompany(double nettoIncCompany) {
		this.nettoIncCompany = nettoIncCompany;
	}

	public double getNettoIncWorker() {
		return nettoIncWorker;
	}

	public void setNettoIncWorker(double nettoIncWorker) {
		this.nettoIncWorker = nettoIncWorker;
	}

	// -----methods-----

	/**
	 * this method counts income worker per year
	 * and adds it to incomeWorker
	 * @param inc_worker
	 * @param period
	 * @return
	 */
	public double addIncWorker(String inc_worker, String period) {
		double incD = 0;

		String digit = "[0-9]+";

		if (inc_worker.matches(digit) && inc_worker != null && period != null) {
			incD = Double.parseDouble(inc_worker) * Double.parseDouble(period);
		}
		return incomeWorker += incD;

	}
/**
 * This method counts cost per year
 * and adding it to costCompany
 * @param cost  
 * @param  period (month/year)
 * @return cost_year per year
 */
	public double addCostCompany(String cost, String period) {
		double cost_year = 0;

		String digit = "[0-9]+";

		if (cost.matches(digit) && cost!= null && period!= null) {

			cost_year = Double.parseDouble(cost) * Double.parseDouble(period);

		}

		return costCompany += cost_year;
	}
/**
 * this method counts income company per year
 * and adds it to incomeCompany
 * @param inc_hour
 * @return inc_year
 */
	public double addIncCompany(String inc_hour) {
		double inc_year = 0;
		String digit = "[0-9]+";

		if (inc_hour.matches(digit) && inc_hour!= null) {
			inc_year = Double.parseDouble(inc_hour) * 1848;// 168tim*11mån
		}
		return incomeCompany += inc_year;

	}

}
