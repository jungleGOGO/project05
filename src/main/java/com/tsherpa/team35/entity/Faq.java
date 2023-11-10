package com.tsherpa.team35.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Faq {
  private int fno;
  private String title;
  private String content;
  private String author;
  private Date regdate;
}
