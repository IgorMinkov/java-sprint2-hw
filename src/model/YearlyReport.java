package model;

import model.YearTransaction;

import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    public HashMap<String, ArrayList<YearTransaction>> yearsData = new HashMap<>(); // общая мапа для них, нужа ли?
    public ArrayList<YearTransaction> expenses = new ArrayList<>(); //список расходов из годового отчета
    public ArrayList<YearTransaction> incomes = new ArrayList<>(); //список доходов из годового отчета

}
