package com.codeinchinese.度量衡;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 功能 {

  private static Map<度量类型, Set<单位>> 单位度量类型 = new HashMap<>();
  private static Map<度量衡制度, Set<单位>> 单位制度 = new HashMap<>();
  private static Map<单位, Double> 相对值 = new HashMap<>();
  private static Map<单位, List<单位信息>> 单位信息表 = new HashMap<>();
  static {
    单位度量类型.put(度量类型.长度,
        Stream.of(单位.公里,
            单位.千米,
            单位.米,
            单位.分米,
            单位.厘米,
            单位.毫米,
            单位.里,
            单位.丈,
            单位.尺,
            单位.寸)
        .collect(Collectors.toCollection(HashSet::new)));
    单位度量类型.put(度量类型.质量,
        Stream.of(单位.吨,
            单位.千克,
            单位.公斤,
            单位.克,
            单位.担,
            单位.斤,
            单位.两,
            单位.盎司).collect(Collectors.toCollection(HashSet::new)));
    单位度量类型.put(度量类型.面积,
        Stream.of(单位.平方千米,
            单位.平方公里,
            单位.平方米,
            单位.顷,
            单位.亩,
            单位.分,
            单位.厘).collect(Collectors.toCollection(HashSet::new)));
    单位度量类型.put(度量类型.体积,
        Stream.of(单位.立方米,
            单位.立方分米,
            单位.立方厘米,
            单位.升,
            单位.毫升,
            单位.石,
            单位.斗,
            单位.盎司).collect(Collectors.toCollection(HashSet::new)));

    单位制度.put(度量衡制度.公制,
        Stream.of(单位.千米,
            单位.公里,
            单位.米,
            单位.分米,
            单位.厘米,
            单位.毫米,
            单位.吨,
            单位.千克,
            单位.公斤,
            单位.克,
            单位.平方千米,
            单位.平方公里,
            单位.平方米,
            单位.立方米,
            单位.立方分米,
            单位.立方厘米,
            单位.升,
            单位.毫升).collect(Collectors.toCollection(HashSet::new)));
    单位制度.put(度量衡制度.市制,
        Stream.of(单位.里,
            单位.丈,
            单位.尺,
            单位.寸,
            单位.担,
            单位.斤,
            单位.两,
            单位.顷,
            单位.亩,
            单位.分,
            单位.厘,
            单位.石,
            单位.斗,
            单位.升).collect(Collectors.toCollection(HashSet::new)));
    单位制度.put(度量衡制度.英制,
        Stream.of(单位.盎司).collect(Collectors.toCollection(HashSet::new)));

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

    单位信息表.put(单位.升,
        Arrays.asList(new 单位信息(单位.升, 度量类型.体积, 度量衡制度.公制), new 单位信息(单位.升, 度量类型.体积, 度量衡制度.市制)));
    单位信息表.put(单位.盎司,
        Arrays.asList(new 单位信息(单位.盎司, 度量类型.质量, 度量衡制度.英制), new 单位信息(单位.盎司, 度量类型.体积, 度量衡制度.英制)));
  }

  public static double 换算(double 值, 单位 原单位, 单位 目标单位) throws Exception {
    if (同类型(原单位, 目标单位)) {
      return 值 * 相对值.get(原单位) / 相对值.get(目标单位);
    } else {
      throw new Exception("无法换算这两种单位");
    }
  }

  public static List<单位信息> 取单位信息(单位 单位) {
    if (单位信息表.containsKey(单位)) {
      return 单位信息表.get(单位);
    } else {
      return Arrays.asList(new 单位信息(单位, 取单位类型(单位).get(0), 取单位制度(单位).get(0)));
    }
  }

  // 返回所有度量类型(可能有多个)
  protected static List<度量类型> 取单位类型(单位 单位) {
    List<度量类型> 所有类型 = new ArrayList<>();
    for (度量类型 类型 : 度量类型.values()) {
      if (单位度量类型.containsKey(类型)) {
        if (单位度量类型.get(类型).contains(单位)) {
          所有类型.add(类型);
        }
      }
    }
    return 所有类型;
  }

  // 返回所有度量制度(可能有多个)
  protected static List<度量衡制度> 取单位制度(单位 单位) {
    List<度量衡制度> 制度 = new ArrayList<>();
    for (度量衡制度 制 : 度量衡制度.values()) {
      if (单位制度.containsKey(制)) {
        if (单位制度.get(制).contains(单位)) {
          制度.add(制);
        }
      }
    }
    return 制度;
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
