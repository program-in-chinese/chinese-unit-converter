package com.codeinchinese.度量衡;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 功能 {

  private static Map<度量类型, Set<单位>> 单位度量类型 = new HashMap<>();
  private static Map<单位, Double> 相对值 = new HashMap<>();
  static {
    单位度量类型.put(度量类型.长度, Stream.of(单位.公里, 单位.千米, 单位.米, 单位.分米, 单位.厘米, 单位.毫米)
        .collect(Collectors.toCollection(HashSet::new)));
    单位度量类型.put(度量类型.质量,
        Stream.of(单位.吨, 单位.千克, 单位.公斤, 单位.克).collect(Collectors.toCollection(HashSet::new)));
    
    // 长度相对米
    相对值.put(单位.千米, 1000.0);
    相对值.put(单位.公里, 1000.0);
    相对值.put(单位.米, 1.0);
    相对值.put(单位.分米, 0.1);
    相对值.put(单位.厘米, 0.01);
    相对值.put(单位.毫米, 0.001);

    // 质量相对克
    相对值.put(单位.吨, 1000000.0);
    相对值.put(单位.千克, 1000.0);
    相对值.put(单位.公斤, 1000.0);
    相对值.put(单位.克, 1.0);

    // 盎司同时是质量和容量单位
  }

  public static double 换算(double 值, 单位 原单位, 单位 目标单位) throws Exception {
    if (同类型(原单位, 目标单位)) {
      return 值 * 相对值.get(原单位) / 相对值.get(目标单位);
    } else {
      throw new Exception("无法换算这两种单位");
    }
  }

  private static boolean 同类型(单位 原单位, 单位 目标单位) {
    // TODO: 避免遍历
    for (度量类型 类型 : 度量类型.values()) {
      if (单位度量类型.containsKey(类型)) {
        Set<单位> 该类型单位 = 单位度量类型.get(类型);
        if (该类型单位.contains(原单位) && 该类型单位.contains(目标单位)) {
          return true;
        }
      }
    }
    return false;
  }
}
