package com.java.jetcache;

import com.java.jetcache.model.Address;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris
 * @date 2020/3/21 11:55
 */
public class MainTest {
    public static void main(String[] args) {
       List<Double> numList = new ArrayList<>();
       numList.add(3.2d);
       numList.add(3.5d);
       numList.add(7.2d);
       numList.add(3.9d);
       numList.add(11.2d);

       Double fin = numList.stream().map(num -> num -2).reduce(Double::sum).get();

        System.out.println(fin);
    }


    private void testStreamForech() {
        List<Address> addressList = new ArrayList<>();
        Address address2 = new Address();
        address2.setId(3l);
        addressList.add(address2);
        Address address1 = new Address();
        address1.setId(2l);
        addressList.add(address1);
        Address address = new Address();
        address.setId(1l);
        addressList.add(address);

        addressList.stream().filter(addresssdf -> addresssdf.getId() != 1l).forEach(addresstt -> addresstt.setMobile("123213"));
        System.out.println(addressList);
    }
}
