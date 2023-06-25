package engines;

import model.MonthlyReport;
import model.YearlyReport;
import java.util.HashMap;

public class MonthsToYearCheckEngine {

    MonthlyReportEngine monthlyEngine = new MonthlyReportEngine();
    YearReportEngine yearlyEngine = new YearReportEngine();

    public void monthsToYearCheck(HashMap<String, MonthlyReport> monthsData, YearlyReport yearReport) {

        if (monthsData == null) {
            System.out.println("месячные отчеты не считаны");
            System.out.println("Сначала считайте их командой 1");
            return;
        }

        if (yearReport == null) {
            System.out.println("Годовой отчет не считан");
            System.out.println("Сначала считайте его командой 2");
            return;
        }

        for (int i = 1; i <= 3; i++) {
            MonthlyReport currentMonth = monthsData.get(getMonthName(i));
            int incomeFromMonthReport = monthlyEngine.calculateMonthsIncomeSum(currentMonth);
            int incomeFromYearReport = yearlyEngine.calculateYearIncomeSum(yearReport, i);
            if (incomeFromMonthReport != incomeFromYearReport) {
                System.out.println("Расхождения в доходах за " + getMonthName(i));
                return;
            }
            int expenseFromMonthReport = monthlyEngine.calculateMonthsExpenseSum(currentMonth);
            int expenseFromYearReport = yearlyEngine.calculateYearExpenseSum(yearReport, i);
            if (expenseFromMonthReport != expenseFromYearReport) {
                System.out.println("Расхождения в расходах за " + getMonthName(i));
                return;
            }
        }
        System.out.println("Отчеты успешно сверены, расхождений не найдено.");
    }

    public String getMonthName (int i) { // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }

}