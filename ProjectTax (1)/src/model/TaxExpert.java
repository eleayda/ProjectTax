package model;

public class TaxExpert {
	private double tax_local;
	private double tax_state_low, tax_state_up;
	private double limit_low, limit_up;
	private double tax_employer_min;
	private double divident_max, tax_divident;
	private double tax_employer_max;
	private double tax_company;

	public TaxExpert() {

	}

	/**
	 * this method count netto income  company owner
	 * depend his age
	 * @param company_owner
	 * @param age
	 * @return income_total_Netto
	 */
	public double calcNettoIncomeCompany(TaxObject company_owner, String age) {

		double income_company = company_owner.getIncomeCompany();
		double cost_company = company_owner.getCostCompany();

		double tax_employer = 0;
		{
			if (age.equals("adult"))
				tax_employer = tax_employer_max;
			if (age.equals("youth"))
				tax_employer = tax_employer_min;
		}
		double profit = 0;
		double salory = 0;
		double saloryNetto = 0;
		double divident = 0;
		double dividentNetto = 0;
		double income_total_Netto = 0;

		if (income_company > cost_company) {
			income_company = income_company - cost_company;

			if (income_company <= divident_max / (1 - tax_company)) {
				divident = income_company - (income_company * tax_company);
				dividentNetto = divident - (divident * tax_divident);

				income_total_Netto = dividentNetto;
				return income_total_Netto;
			} else if (income_company > divident_max / (1 - tax_company)) {
				TaxObject to2 = new TaxObject();
				profit = divident_max / (1 - tax_company);
				salory = (income_company - profit) / (1 + tax_employer);
				to2.setIncomeWorker(salory);

				saloryNetto = calcNettoIncomeWorker(to2);
				divident = divident_max;
				dividentNetto = divident - (divident * tax_divident);

				income_total_Netto = saloryNetto + dividentNetto;
				return income_total_Netto;
			}

		} else if (income_company < cost_company) {

			income_total_Netto = income_company - cost_company;
			return income_total_Netto;
		}
		return income_total_Netto;

	}
/**
 * this method counts netto income worker
 * @param worker
 * @return income_worker_Netto
 * 
 */
	public double calcNettoIncomeWorker(TaxObject worker) {
		double income_worker = worker.getIncomeWorker();
		double income_worker_Netto = 0;
		double tax = 0;
		income_worker = income_worker * 1.12; // inklusive semester

		if (income_worker <= limit_low)
			tax = income_worker * tax_local;
		else if (income_worker > limit_low && income_worker <= limit_up)
			tax = income_worker * tax_local + (income_worker - limit_low)
					* tax_state_low;
		else if (income_worker > limit_up) {
			tax = income_worker * tax_local + (income_worker - limit_low)
					* tax_state_low + (income_worker - limit_up)
					* (tax_state_up - tax_state_low);
		}

		income_worker_Netto = income_worker - tax;

		return income_worker_Netto;

	}

	// Setters---------------------------

	public void setTax_local(double tax_local) {
		this.tax_local = tax_local;
	}

	public double getTax_local() {
		return tax_local;
	}

	public void setTax_state_low(double tax_state_low) {
		this.tax_state_low = tax_state_low;
	}

	public void setTax_state_up(double tax_state_up) {
		this.tax_state_up = tax_state_up;
	}

	public void setLimit_low(double limit_low) {
		this.limit_low = limit_low;
	}

	public void setLimit_up(double limit_up) {
		this.limit_up = limit_up;
	}

	public void setTaxEmployer_min(double tax_employer_min) {
		this.tax_employer_min = tax_employer_min;
	}

	public void setDivident_max(double divident_max) {
		this.divident_max = divident_max;
	}

	public void setTax_divident(double tax_divident) {
		this.tax_divident = tax_divident;
	}

	public void setTax_employer_max(double tax_employer_max) {
		this.tax_employer_max = tax_employer_max;
	}

	public void setTax_company(double tax_company) {
		this.tax_company = tax_company;
	}

}
