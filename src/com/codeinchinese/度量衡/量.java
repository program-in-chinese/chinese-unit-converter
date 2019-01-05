package com.codeinchinese.度量衡;

public class 量 {

  private 单位 本单位;
  private double 值;

  public static 量 新建() {
    return new 量();
  }

  public 量 单位(单位 单位) {
    this.本单位 = 单位;
    return this;
  }

  public 量 值(double 值) {
    this.值 = 值;
    return this;
  }

  public 单位 取单位() {
    return this.本单位;
  }

  public double 取值() {
    return this.值;
  }

  public 量 换算(单位 目标单位) throws Exception {
    return 量.新建().值(功能.换算(this.值, this.本单位, 目标单位)).单位(目标单位);
  }

  @Override
  public boolean equals(Object 个例) {
    if (个例 instanceof 量) {
      量 比较量 = (量) 个例;
      return 比较量.本单位.equals(this.本单位) && 比较量.值 == this.值;
    }
    return false;
  }

}
