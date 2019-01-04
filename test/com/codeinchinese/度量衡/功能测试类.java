package com.codeinchinese.度量衡;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class 功能测试类 {

  double 误差 = 0.0000000001;

  @Test
  public void 基本换算() throws Exception {
    assertEquals(1, 功能.换算(1000, 单位.米, 单位.千米), 误差);
    assertEquals(1, 功能.换算(1000, 单位.米, 单位.公里), 误差);
    assertEquals(1, 功能.换算(10, 单位.分米, 单位.米), 误差);
    assertEquals(1, 功能.换算(10, 单位.厘米, 单位.分米), 误差);
    assertEquals(1, 功能.换算(10, 单位.毫米, 单位.厘米), 误差);

    assertEquals(1, 功能.换算(1000, 单位.公斤, 单位.吨), 误差);
    assertEquals(1, 功能.换算(1000, 单位.克, 单位.千克), 误差);
    assertEquals(1, 功能.换算(1000, 单位.克, 单位.公斤), 误差);
  }

  @Test
  public void 更多换算() throws Exception {
    assertEquals(30000000000.0, 功能.换算(300000, 单位.公里, 单位.厘米), 误差);

    // 小数
    assertEquals(1.01, 功能.换算(101, 单位.厘米, 单位.米), 误差);
  }

  @Test
  public void 换算失败() {
    try {
      功能.换算(1, 单位.千克, 单位.米);
      fail("不能换算");
    } catch (Exception e) {
      assertEquals("无法换算这两种单位", e.getMessage());
    }
  }

  @Test
  public void 确认所有单位都有类型() {

  }
}
