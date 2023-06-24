package engines;

import model.MonthlyReport;
import model.YearTransaction;
import model.YearlyReport;

import java.util.HashMap;
import java.util.ArrayList;

public class MonthsToYearCheckEngine {

    public void monthsToYearCheck(HashMap<String, MonthlyReport> monthsData, YearlyReport yearReport) {

        if (monthsData == null) {
            System.out.println("месячные отчеты не считан");
            System.out.println("Сначала считайте их командой 1");
            return;
        }

        if (yearReport == null) {
            System.out.println("Годовой отчет не считан");
            System.out.println("Сначала считайте его командой 2");
            return;

        }

        //Подсчитать суммы доходов и расходов по каждому из месяцев.
        //MonthlyReportEngine calculateMonthsIncomeSum
        //MonthlyReportEngine calculateMonthsExpenseSum

        //Сверить полученные суммы с суммой доходов и расходов в отчёте по году.

        //При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
        //Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции.
    }

    public void getTotalPerMonth(YearlyReport yearReport) { // доходы и расходы за год разносит на две мапы

        HashMap<Integer, Integer> expensesPerMonths = new HashMap<>();
        HashMap<Integer, Integer> incomesPerMonths = new HashMap<>();

        for (int i = 0; i < yearReport.yearsData.size(); i++) {
            YearTransaction yearTransaction = yearReport.yearsData.get(i);
            if (yearTransaction.isExpense) {
                Integer expensePerMonth = expensesPerMonths.get(yearTransaction.amount);
                if (expensePerMonth == null) {
                    expensePerMonth = 0;
                }
                expensesPerMonths.put(yearTransaction.month, expensePerMonth);
            } else {
                Integer incomePerMonth = incomesPerMonths.get(yearTransaction.amount);
                if (incomePerMonth == null) {
                    incomePerMonth = 0;
                }
                incomesPerMonths.put(yearTransaction.month, incomePerMonth);
            }
        }

        //for (Integer month : profitPerMonths.keySet()) {
        //    String monthName = getMonthName(month);
        //    System.out.println("Прибыль за " + monthName + ": " + profitPerMonths.get(month));}
    }


    public String getMonthName (int i) { // Получить имя месяца для вывода статистики
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return months[i - 1];
    }



}