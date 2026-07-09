package com.leetcode;

public class LeetCodeSolns {

    public static void main(String[] args) {
        LeetCodeSolns leetCodeSolns = new LeetCodeSolns();
        System.out.println(leetCodeSolns.sumAndMultiply(10203004));
        System.out.println(leetCodeSolns.sumAndMultiply(1000));


    }


    public long sumAndMultiply(int n) {
        int val = n;
        int sum = 0;
        String digit = "";
        while (val != 0) {
            int rem = val % 10;
            if (rem != 0) {
                digit += rem;
                sum += rem;
            }
            val = val / 10;
        }
        if(digit.isEmpty()){
            digit="0";
        }
        return Long.parseLong(new StringBuffer(digit).reverse().toString()) * sum;
    }

}
