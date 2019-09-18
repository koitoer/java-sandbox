package com.koitoer.java.let.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * 811. Subdomain Visit Count
 * Solution use recursion, this will use a hashmap to keep the counter, each recursion will cut the string using the "." separator
 */
public class Solution811 {

    @Test
    public void test1(){
        Assertions.assertThat(new Solution811().subdomainVisits(new String[]{"9001 discuss.leetcode.com"}))
            .contains("9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com");
    }

    @Test
    public void test2(){
        Assertions.assertThat(new Solution811().subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}))
            .contains("901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com");
    }


    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> values = new HashMap();

        for(int i=0; i<cpdomains.length; i++){
            visit(cpdomains[i], values);
        }

        List<String> a = new ArrayList();
        for (Map.Entry<String,Integer> entry : values.entrySet()){
            a.add(entry.getValue() + " " + entry.getKey());
        }

        return a;
    }

    public void visit(String cpDomains, Map<String, Integer> values){

        String [] aux = cpDomains.split(" ");
        Integer value = Integer.parseInt(aux[0]);
        String domain = aux[1];

        //System.out.println(value + " : " + domain);
        values.put(domain, values.getOrDefault(domain, 0) + value);

        int index = domain.indexOf(".");
        if(index == -1){
            return;
        }

        visit(value + " " + domain.substring(index + 1, domain.length()), values);
    }

}
