package com.codeinchinese.度量衡;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class 量测试类 {

  @Test
  public void 基本换算() throws Exception {
    assertEquals(量.新建(1, 单位.千米),
        量.新建(1000, 单位.米).换算(单位.千米));
    assertEquals(量.新建(1, 单位.吨),
        量.新建(1000, 单位.公斤).换算(单位.吨));
  }

  @Test
  public void 更多换算() throws Exception {
    // 小数
    assertEquals(量.新建(30000000000.0, 单位.厘米),
        量.新建(300000.0, 单位.公里).换算(单位.厘米));
    assertEquals(量.新建(1.01, 单位.米),
        量.新建(101, 单位.厘米).换算(单位.米));
  }
}
