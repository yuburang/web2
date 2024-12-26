package cn.wzptsoft.common.utils;


import org.apache.commons.codec.digest.Md5Crypt;


/**
 * @author Shiwei Xu
 * @date 2021-12-22
 */
public class MD5Utils {

  /**
   * 根据源数据生成MD5摘要
   * @param source 源数据
   * @return MD5摘要
   */
  public static String md5Digest(String source) {
    return Md5Crypt.md5Crypt(source.getBytes());
  }

  /**
   * 对源数据加盐混淆后生成MD5摘要
   * @param source 源数据
   * @param salt 盐值
   * @return MD5摘要
   */
  public static String md5Digest(String source, String salt) {
    return Md5Crypt.md5Crypt(source.getBytes(), salt);
  }


  //  测试
  public static void main(String[] args) {
    String password = "123456";
    String salt = "$1$test4";
    String md5Digest = md5Digest(password, salt);
    System.out.println(md5Digest);
  }

}
