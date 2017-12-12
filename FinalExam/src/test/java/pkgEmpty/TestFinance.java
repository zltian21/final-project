package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

import org.apache.poi.ss.formula.functions.*;

public class TestFinance {

	@Test
	public void TestAmounts1() {

		int iYearsToWork = 40;
		double dAnnualReturnWorking = 7;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 2;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;

		Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);

		double pv = retirement.TotalAmountSaved();
		double pmt = retirement.AmountToSave();

		assertEquals(-1454485.55, pv, .001);
		assertEquals(554.13, pmt, .001);
	}

	@Test
	public void TestAmounts2() {

		int iYearsToWork = 30;
		double dAnnualReturnWorking = 19;
		int iYearsRetired = 15;
		double dAnnualReturnRetired = 2.5;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;

		Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);

		double pv = retirement.TotalAmountSaved();
		double pmt = retirement.AmountToSave();

		assertEquals(-1103497.16, pv, .001);
		assertEquals(61.35, pmt, .001);
	}
	
	
	

}
