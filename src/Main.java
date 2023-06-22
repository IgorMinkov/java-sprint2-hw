import engines.MonthlyReportEngine;
import engines.YearReportEngine;
import model.MonthlyReport;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        MonthlyReportEngine monthlyEngine = new MonthlyReportEngine();
        YearReportEngine yearlyEngine = new YearReportEngine();
        //MonthlyReport monthlyReport = new MonthlyReport();
        // model.YearlyReport yearlyReport = new model.YearlyReport();
        // создать объект класса MonthsToYearCheck;

        System.out.println("\n"); // просто пустая строка для отделения в консоли от технической инфы.
        System.out.println("Приложение для бухгалтерии v1.0");

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();
            int intUserInput = Integer.parseInt(userInput);

            if (intUserInput == 1) {
                monthlyEngine.readAllMonthlyReports();
            } else if (intUserInput == 2) {
                yearlyEngine.readYearReport();
            } else if (intUserInput == 3) {
                System.out.println("выполняется 3 - Сверить месячные отчёты с годовым");
            } else if (intUserInput == 4) {
                System.out.println("выполняется 4 - Вывести информацию всех месячных отчётов");
                //monthlyReport.getMonthsStatistic();
            } else if (intUserInput == 5) {
                System.out.println("выполняется 5 - Вывести информацию годового отчёта");
            }
            else if (intUserInput == 916) {
                System.out.println("До новых встреч!");
                scanner.close();
                return;
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    static void printMenu() {
        System.out.println(); // просто пустая строка для отделения меню от результатов
        System.out.println("Доступные функции:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить месячные отчёты с годовым");
        System.out.println("4 - Вывести информацию всех месячных отчётов");
        System.out.println("5 - Вывести информацию годового отчёта");
        System.out.println("Для выхода из приложения наберите 916");
    }
}

