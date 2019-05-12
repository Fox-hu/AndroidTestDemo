package com.example.algorithms.java8;

import com.example.algorithms.java8.stream.Trader;
import com.example.algorithms.java8.stream.Transaction;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionTest {
    private Trader fan;
    private Trader lu;
    private Trader huang;
    private Trader hu;

    private List<Transaction> transactions = new ArrayList<>();

    @Before
    public void init() {
        fan = new Trader("fan", "Beijing");
        lu = new Trader("lu", "Shanghai");
        huang = new Trader("huang", "Guangzhou");
        hu = new Trader("hu", "Shenzhen");

        transactions.add(new Transaction(fan, 2011, 300));
        transactions.add(new Transaction(lu, 2012, 1000));
        transactions.add(new Transaction(lu, 2011, 400));
        transactions.add(new Transaction(huang, 2012, 700));
        transactions.add(new Transaction(huang, 2012, 710));
        transactions.add(new Transaction(hu, 2012, 950));
    }

    //1.找出2011年所有的交易，并按照交易额排序(低->高)
    @Test
    public void test1() {
        List<Transaction> collect = transactions.stream().filter(
                transaction -> transaction.getYear() == 2011).sorted(
                Comparator.comparing(Transaction :: getValue)).collect(Collectors.toList());
        collect.forEach(transaction -> System.out.println(transaction.toString()));
    }

    //2.交易员都在哪些城市工作过
    @Test
    public void test2() {
        List<String> collect = transactions.stream().map(
                transaction -> transaction.getTrader().getCity()).distinct().collect(
                Collectors.toList());
        collect.forEach(System.out :: println);
    }

    //3.查找所有来自上海的交易员，并按姓名排序
    @Test
    public void test3() {
        List<Trader> collect = transactions.stream().map(Transaction :: getTrader).filter(
                trader -> "Shanghai".equals(trader.getCity())).distinct().sorted(
                Comparator.comparing(Trader :: getName)).collect(Collectors.toList());
        collect.forEach(System.out :: println);
    }

    //4.返回所有交易员姓名的字符串，按字母顺序排序
    @Test
    public void test4() {
        String traderStr = transactions.stream().map(
                transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("",
                (n1, n2) -> n1 + n2);
        System.out.println(traderStr);
    }

    //5.有没有交易员是在广州工作的
    @Test
    public void test5() {
        boolean guangzhou = transactions.stream().map(Transaction :: getTrader).distinct().anyMatch(
                trader -> trader.getCity().equals("Guangzhou"));
        System.out.println("has trader in guangzhou " + guangzhou);
    }

    //6.生活在上海的交易员的所有交易额
    @Test
    public void test6() {
        transactions.stream().filter(
                transaction -> transaction.getTrader().getCity().equals("Shanghai")).distinct().map(
                Transaction :: getValue).forEach(System.out :: println);
    }

    //7.所有交易中，最高和最低的交易额是多少
    @Test
    public void test7() {
        Optional<Integer> maxValue = transactions.stream().map(Transaction :: getValue).reduce(
                Integer :: max);
        Optional<Integer> minValue = transactions.stream().map(Transaction :: getValue).reduce(
                Integer :: min);
        System.out.print("maxValue = " + maxValue.get() + " minValue = " + minValue.get());
    }

}