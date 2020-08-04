package com.dsm.ims.grammar;

import java.util.Date;

public class GrmmarMainTest {
    public static void main(String[] args) {
        Date now = new Date(System.currentTimeMillis());                    // 현재 시간
        Date more = new Date(System.currentTimeMillis() + 1000000000);      // 생성한 토큰의 시간

        System.out.println(now.after(more));
        System.out.println(more.after(now));
    }
}
