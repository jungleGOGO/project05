package com.tsherpa.team35.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Faq {
  private int fno;
  private String question;
  private String author;
  private String answer;
  private int cnt;
}
