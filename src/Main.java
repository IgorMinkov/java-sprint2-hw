import engines.MonthlyReportEngine;
import engines.YearReportEngine;
import engines.MonthsToYearCheckEngine;
import model.MonthlyReport;
import model.YearlyReport;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        MonthlyReportEngine monthlyEngine = new MonthlyReportEngine();
        YearReportEngine yearlyEngine = new YearReportEngine();
        MonthsToYearCheckEngine reportCheckEngine = new MonthsToYearCheckEngine();
        HashMap<String,MonthlyReport> monthsData = null;
        YearlyReport yearReport = null;

        System.out.println("\n"); // пустая строка для отделения в консоли от технической инфы.
        System.out.println("Приложение для бухгалтерии v1.0");

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();
            int intUserInput = Integer.parseInt(userInput);

            switch (intUserInput) {
                case 1:
                monthsData = monthlyEngine.readAllMonthlyReports();
                break;
                case 2:
                yearReport = yearlyEngine.readYearReport();
                break;
                case 3:
                reportCheckEngine.monthsToYearCheck(monthsData, yearReport);
                break;
                case 4:
                monthlyEngine.getMonthsStatistic(monthsData);
                break;
                case 5:
                yearlyEngine.getYearStatistic(yearReport);
                break;
                case 0:
                System.out.println("До новых встреч!");
                scanner.close();
                return;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }

    static void printMenu() {
        System.out.println(); // пустая строка для отделения меню от результатов
        System.out.println("Доступные функции:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить месячные отчёты с годовым");
        System.out.println("4 - Вывести информацию всех месячных отчётов");
        System.out.println("5 - Вывести информацию годового отчёта");
        System.out.println("Для выхода из приложения наберите 0");
    }
}

