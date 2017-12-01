package com.anderscore.mody.lager;

import com.anderscore.modx.article.Artikel;

public class Lager {
        public static void main(String[] args) {
            Artikel a1 = new Artikel(1234, "Teddybär", 10);
			Artikel a2 = new Artikel(1701, "Earl Grey Tea", 1);
			Artikel a3 = new Artikel(1138, "Lightsaber", 99);
			
			System.out.println(a1);
			System.out.println(a2);
			System.out.println(a3);
        }
    }