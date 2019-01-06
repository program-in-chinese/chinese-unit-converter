package com.codeinchinese.度量衡;

public class 单位信息 {

  private 单位 单位;
  private 度量类型 类型;
  private 度量衡制度 制;

  // TODO: 时代/朝代
  // private List<时代>

  public 单位信息(单位 单位, 度量类型 类型, 度量衡制度 制) {
    this.单位 = 单位;
    this.类型 = 类型;
    this.制 = 制;
  }

  public 单位 取单位() {
    return this.单位;
  }

  public 度量类型 取类型() {
    return this.类型;
  }

  public 度量衡制度 取制() {
    return this.制;
  }

  @Override
  public boolean equals(Object 个例) {
    if (个例 instanceof 单位信息) {
      单位信息 比较信息 = (单位信息) 个例;
      return 比较信息.单位.equals(this.单位) && 比较信息.类型 == this.类型 && 比较信息.制 == this.制;
    }
    return false;
  }

}
