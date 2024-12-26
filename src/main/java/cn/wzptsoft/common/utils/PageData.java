package cn.wzptsoft.common.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 * @author Shiwei Xu
 */
@Data
public class PageData<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 总记录数
   */
  private int total;

  /**
   * 当前分页结果
   */
  private List<T> list;

  /**
   * 构造方法
   * @param list   列表数据
   * @param total  总记录数
   */
  public PageData(List<T> list, int total) {
    this.list = list;
    this.total = total;
  }

  /**
   * 构造方法重载，total为long类型
   * @param list   列表数据
   * @param total  总记录数
   */
  public PageData(List<T> list, long total) {
    this.list = list;
    this.total = (int) total;
  }

  public static <E> PageData<E> fromJpaPage(Page<E> pageResult) {
    return new PageData<>(pageResult.getContent(), pageResult.getTotalElements());
  }
}