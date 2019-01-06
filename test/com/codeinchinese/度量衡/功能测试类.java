package com.codeinchinese.度量衡;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

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
  public void 取单位信息() {
    List<单位信息> 信息 = 功能.取单位信息(单位.升);
    assertEquals(2, 信息.size());
    assertEquals(new 单位信息(单位.升, 度量类型.体积, 度量衡制度.公制), 信息.get(0));
    assertEquals(new 单位信息(单位.升, 度量类型.体积, 度量衡制度.市制), 信息.get(1));
  }

  @Test
  public void 确认所有单位都有类型() {
    for (单位 某单位 : 单位.values()) {
      assertTrue(某单位 + " 无类型", 功能.取单位类型(某单位).size() > 0);
    }
  }

  @Test
  public void 确认所有单位都有制度() {
    for (单位 某单位 : 单位.values()) {
      assertTrue(某单位 + " 无制度", 功能.取单位制度(某单位).size() > 0);
    }
  }

  @Test
  public void 确认所有多义单位的含义() {
    for (单位 某单位 : 单位.values()) {
      int 类型数 = 功能.取单位类型(某单位).size();
      int 制度数 = 功能.取单位制度(某单位).size();
      if (类型数 > 1 || 制度数 > 1) {
        assertTrue(某单位 + " 应有多义: " + 类型数 + "类型 " + 制度数 + "制度", 功能.取单位信息(某单位).size() > 1);
      }
    }
  }
}
